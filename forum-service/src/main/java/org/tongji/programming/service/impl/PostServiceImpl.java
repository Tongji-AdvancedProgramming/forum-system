package org.tongji.programming.service.impl;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.base.CaseFormat;
import lombok.SneakyThrows;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.commons.lang3.StringUtils;
import org.htmlcleaner.HtmlCleaner;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tongji.programming.dto.PostService.GetPostResponse;
import org.tongji.programming.enums.NotificationType;
import org.tongji.programming.mapper.PostMapper;
import org.tongji.programming.mapper.StudentMapper;
import org.tongji.programming.pojo.Notification;
import org.tongji.programming.pojo.Post;
import org.tongji.programming.pojo.Student;
import org.tongji.programming.service.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @author cinea
 */
@Service
public class PostServiceImpl implements PostService {

    private final PostMapper postMapper;
    private final StudentMapper studentMapper;
    private final BoardService boardService;
    private final StudentService studentService;
    private final MetadataService metadataService;
    private final SearchEngineService searchEngineService;
    private final LogService logService;
    private final UserCourseService userCourseService;
    private final NotificationService notificationService;
    private final HtmlCleaner htmlCleaner;

    @Value("${forum.level.ta}")
    private Integer levelTA;

    @Value("${forum.level.admin}")
    private Integer levelAdmin;

    public PostServiceImpl(PostMapper postMapper, StudentMapper studentMapper, BoardService boardService, StudentService studentService, MetadataService metadataService, SearchEngineService searchEngineService, LogService logService, UserCourseService userCourseService, NotificationService notificationService, HtmlCleaner htmlCleaner) {
        this.postMapper = postMapper;
        this.studentMapper = studentMapper;
        this.boardService = boardService;
        this.studentService = studentService;
        this.metadataService = metadataService;
        this.searchEngineService = searchEngineService;
        this.logService = logService;
        this.userCourseService = userCourseService;
        this.notificationService = notificationService;
        this.htmlCleaner = htmlCleaner;
    }

    private String[] resolveTags(String tags) {
        if ("[]".equals(tags)) {
            return new String[0];
        }
        var tagList = metadataService.getTags();
        var tagIndexes = JSON.parseArray(tags, Integer.class);
        tagIndexes = tagIndexes.stream().filter(i -> i >= 0 && i < tagList.length).toList();
        var tagNames = new String[tagIndexes.size()];
        for (int i = 0; i < tagIndexes.size(); i++) {
            tagNames[i] = tagList[tagIndexes.get(i)].getTagFieldname();
        }
        return tagNames;
    }

    @Override
    public boolean ensureQueryPermission(String userId, Integer postId) {
        var stuCourseCodes = userCourseService.getCourseCodes(userId);

        var post = postMapper.selectOne(
                new QueryWrapper<Post>().select("post_id", "post_term", "post_ccode")
                        .eq("post_id", postId)
        );

        return stuCourseCodes.stream().anyMatch(c -> c.get(0).equals(post.getPostTerm()) && c.get(1).equals(post.getPostCcode()));
    }

    @Override
    public boolean ensureQueryPermission(String userId, String boardId) {
        var stuCourseCodes = userCourseService.getCourseCodes(userId);

        var board = boardService.parseId(boardId);

        return stuCourseCodes.stream().anyMatch(c -> c.get(0).equals(board.getCourse().getCourseTerm()) && c.get(1).equals(board.getCourse().getCourseCode()));
    }

    @Override
    public List<Post> getPosts(String boardId, String tags, boolean showHidden, boolean withContent, boolean withReplies, int pageSize, int pageIndex) {
        // 解码Tags
        var tagNames = resolveTags(tags);

        // 计算Offset
        var offset = pageSize * (pageIndex - 1);

        var board = boardService.parseId(boardId);
        switch (board.getLocation()) {
            case WEEKLY -> {
                return postMapper.getWeekPosts(board.getCourse().getCourseTerm(), board.getCourse().getCourseCode(), board.getWeek(), tagNames, showHidden, false, withReplies, pageSize, offset);
            }
            case HOMEWORK -> {
                return postMapper.getHomeworkPosts(board.getCourse().getCourseTerm(), board.getCourse().getCourseCode(), board.getHomework().getHwId(), tagNames, showHidden, false, withReplies, pageSize, offset);
            }
            case COURSE -> {
                return postMapper.getCoursePosts(board.getCourse().getCourseTerm(), board.getCourse().getCourseCode(), tagNames, showHidden, false, withReplies, pageSize, offset);
            }
            case WEEK_SUMMARY -> {
                return postMapper.getWeekSummaryPosts(board.getCourse().getCourseTerm(), board.getCourse().getCourseCode(), board.getWeek(), tagNames, showHidden, false, withReplies, pageSize, offset);
            }
            case COURSE_SUMMARY -> {
                return postMapper.getCourseSummaryPosts(board.getCourse().getCourseTerm(), board.getCourse().getCourseCode(), tagNames, showHidden, false, withReplies, pageSize, offset);
            }
        }
        return new ArrayList<>();
    }

    @Override
    public Integer getPostsTotalCount(String boardId, String tags, boolean showHidden, boolean withReplies) {
        // 解码Tags
        var tagNames = resolveTags(tags);

        var board = boardService.parseId(boardId);
        switch (board.getLocation()) {
            case WEEKLY -> {
                return postMapper.getWeekPostsCount(board.getCourse().getCourseTerm(), board.getCourse().getCourseCode(), board.getWeek(), tagNames, showHidden, withReplies);
            }
            case HOMEWORK -> {
                return postMapper.getHomeworkPostsCount(board.getCourse().getCourseTerm(), board.getCourse().getCourseCode(), board.getHomework().getHwId(), tagNames, showHidden, withReplies);
            }
            case COURSE -> {
                return postMapper.getCoursePostsCount(board.getCourse().getCourseTerm(), board.getCourse().getCourseCode(), tagNames, showHidden, withReplies);
            }
            case WEEK_SUMMARY -> {
                return postMapper.getWeekSummaryPostsCount(board.getCourse().getCourseTerm(), board.getCourse().getCourseCode(), board.getWeek(), tagNames, showHidden, withReplies);
            }
            case COURSE_SUMMARY -> {
                return postMapper.getCourseSummaryPostsCount(board.getCourse().getCourseTerm(), board.getCourse().getCourseCode(), tagNames, showHidden, withReplies);
            }
        }
        return 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String addPost(String userId, String ipAddr, String boardId, String title, String content) {
        var board = boardService.parseIdAndFetch(boardId);

        var post = new Post();
        post.setPostTerm(board.getCourse().getCourseTerm());
        post.setPostCcode(board.getCourse().getCourseCode());

        switch (board.getLocation()) {
            case COURSE -> {
                if (studentService.getUserLevel(userId) < levelAdmin) {
                    return "用户权限不足，不可在此发帖。";
                }
                post.setPostHwId(-1);
                post.setPostWeek(-1);
                post.setPostChapter(-1);
            }
            case WEEKLY -> {
                post.setPostHwId(-1);
                post.setPostWeek(board.getWeek());
                post.setPostChapter(-1);
            }
            case HOMEWORK -> {
                post.setPostHwId(board.getHomework().getHwId());
                post.setPostWeek(board.getWeek());
                post.setPostChapter(board.getHomework().getHwChapter());
            }
            default -> {
                return "错误的传入参数，为保护系统不允许发帖。";
            }
        }

        post.setPostAnswerId(null);
        post.setPostType("Question");
        post.setPostSno(userId);
        post.setPostPriority("0");

        post.setPostTitle(title);
        post.setPostContent(content);
        post.setPostDate(LocalDateTime.now());

        postMapper.insert(post);
        searchEngineService.addPost(post.getPostId());

        // 记录日志
        var comment = "POST 内容为:" + content;
        logService.logPost(post.getPostId(), userId, ipAddr, comment);

        return String.format("Success-%d", post.getPostId());
    }

    @Override
    public String addReply(String userId, String ipAddr, Integer fatherPostId, String content) {
        var fatherPost = postMapper.getPostWithoutContent(fatherPostId);
        if (fatherPost == null) {
            return null;
        }

        var newPost = new Post();
        newPost.setPostTerm(fatherPost.getPostTerm());
        newPost.setPostCcode(fatherPost.getPostCcode());
        newPost.setPostHwId(fatherPost.getPostHwId());
        newPost.setPostWeek(fatherPost.getPostWeek());
        newPost.setPostChapter(fatherPost.getPostChapter());

        newPost.setPostPriority("0");
        newPost.setPostTitle("");
        newPost.setPostIsDel("0");

        newPost.setPostSno(userId);
        newPost.setPostAnswerId(fatherPostId);
        newPost.setPostContent(content);
        newPost.setPostDate(LocalDateTime.now());

        if (Objects.equals(userId, fatherPost.getPostSno())) {
            newPost.setPostType("Answer");
        } else {
            newPost.setPostType("QuestionsAdditional");
        }

        postMapper.insert(newPost);
        searchEngineService.addPost(newPost.getPostId());

        // 记录日志
        var comment = "POST 内容为:" + content;
        logService.logPost(newPost.getPostId(), userId, ipAddr, comment);

        // 发送通知
        if (!fatherPost.getPostSno().equals(userId)) {
            var notification = Notification.builder()
                    .title("收到新回复")
                    .content(String.format(
                            "%s",
                            StringUtils.abbreviate(htmlCleaner.clean(newPost.getPostContent()).getText().toString(), "...", 35)
                    ))
                    .type(NotificationType.REPLY)
                    .receiver(fatherPost.getPostSno())
                    .build();

            notificationService.sendNotification(notification);
        }

        return "";
    }

    @Override
    public GetPostResponse getPost(Integer postId, boolean withHidden) {
        var posts = postMapper.getPostsRecursively(postId);
        if (!withHidden) {
            posts.removeIf(post -> "1".equals(post.getPostIsDel()));
        }
        var resp = new GetPostResponse();
        resp.setPosts(posts);
        return resp;
    }

    @Override
    public Integer getParentPost(Integer postId) {
        var parentPost = postMapper.getParentPostRecursively(postId);
        if (parentPost == null) {
            return null;
        } else {
            return parentPost.getPostId();
        }
    }

    @Override
    public boolean ensureEditPermission(String userId, Integer postId) {
        var post = postMapper.selectOne(
                new QueryWrapper<Post>().select("post_sno").eq("post_id", postId)
        );
        if (post == null) {
            return false;
        }
        if (userId.equals(post.getPostSno())) {
            return true;
        }

        var postUser = studentMapper.selectOne(
                new QueryWrapper<Student>().select("stu_userlevel").eq("stu_no", post.getPostSno())
        );
        var user = studentMapper.selectOne(
                new QueryWrapper<Student>().select("stu_userlevel").eq("stu_no", userId)
        );

        if ("1".equals(user.getStuIsDel())) {
            return false;
        }

        return Integer.parseInt(postUser.getStuUserlevel()) < Integer.parseInt(user.getStuUserlevel());
    }

    @Override
    public void editPost(String userId, String ipAddr, Integer postId, String newContent) {
        // 记录日志
        var comment = "EDIT 新内容为:" + newContent;
        logService.logPost(postId, userId, ipAddr, comment);

        var updatePost = new Post();
        updatePost.setPostId(postId);
        updatePost.setPostContent(newContent);
        postMapper.updateById(updatePost);

        searchEngineService.addPost(postId);
    }

    @SneakyThrows
    @Override
    public void setPostTag(String userId, String ipAddr, Integer postId, int[] targetTags) {
        var tags = metadataService.getTags();

        // 记录日志
        var comment = "TAG 新标签为：" + Arrays.toString(targetTags);
        logService.logPost(postId, userId, ipAddr, comment);

        var clazz = Post.class;

        var updatePost = new Post();
        updatePost.setPostId(postId);
        for (var tag : tags) {
            var tagField = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, tag.getTagFieldname());
            clazz.getMethod("set" + tagField, Object.class).invoke(updatePost, "0");
        }
        for (var i : targetTags) {
            if (i < 0 || i >= tags.length) {
                continue;
            }
            var tagField = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, tags[i].getTagFieldname());
            clazz.getMethod("set" + tagField, Object.class).invoke(updatePost, "1");
        }

        postMapper.updateById(updatePost);
    }

    @Override
    public void setPostPriority(String userId, String ipAddr, Integer postId, int priority) {
        // 记录日志
        var comment = "PRIORITY 新优先级为：" + priority;
        logService.logPost(postId, userId, ipAddr, comment);

        var updatePost = new Post();
        updatePost.setPostId(postId);
        updatePost.setPostPriority(String.valueOf(priority));
        postMapper.updateById(updatePost);
    }

    @Override
    public void deletePost(String userId, String ipAddr, Integer postId) {
        // 记录日志
        var comment = "DELETE";
        logService.logPost(postId, userId, ipAddr, comment);

        var updatePost = new Post();
        updatePost.setPostId(postId);
        updatePost.setPostIsDel("1");
        postMapper.updateById(updatePost);
    }
}
