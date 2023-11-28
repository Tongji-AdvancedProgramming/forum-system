package org.tongji.programming.service.impl;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.base.CaseFormat;
import lombok.SneakyThrows;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tongji.programming.dto.PostService.GetPostResponse;
import org.tongji.programming.mapper.PostMapper;
import org.tongji.programming.mapper.StudentMapper;
import org.tongji.programming.pojo.Post;
import org.tongji.programming.pojo.Student;
import org.tongji.programming.service.BoardService;
import org.tongji.programming.service.MetadataService;
import org.tongji.programming.service.PostService;
import org.tongji.programming.service.StudentService;

import java.time.LocalDateTime;
import java.util.ArrayList;
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

    @Autowired
    public PostServiceImpl(PostMapper postMapper, StudentMapper studentMapper, BoardService boardService, StudentService studentService, MetadataService metadataService) {
        this.postMapper = postMapper;
        this.studentMapper = studentMapper;
        this.boardService = boardService;
        this.studentService = studentService;
        this.metadataService = metadataService;
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
    public List<Post> getPosts(String boardId, String tags, boolean withContent, boolean withReplies) {

        // 解码Tags
        var tagNames = resolveTags(tags);

        var board = boardService.parseId(boardId);
        switch (board.getLocation()) {
            case WEEKLY -> {
                return getWeekPosts(board.getCourse().getCourseTerm(), board.getCourse().getCourseCode(), board.getWeek(), new String[]{}, false, false, withReplies);
            }
            case HOMEWORK -> {
                return getHomeworkPosts(board.getCourse().getCourseTerm(), board.getCourse().getCourseCode(), board.getHomework().getHwId(), new String[]{}, false, false, withReplies);
            }
            case COURSE -> {
                return getCoursePosts(board.getCourse().getCourseTerm(), board.getCourse().getCourseCode(), new String[]{}, false, false, withReplies);
            }
        }
        return new ArrayList<>();
    }

    @Override
    public List<Post> getCoursePosts(String term, String courseCode, String[] tagNames, boolean showHidden, boolean withContent, boolean withReplies) {
        return postMapper.getCoursePosts(term, courseCode, tagNames, showHidden, withContent, withReplies);
    }

    @Override
    public List<Post> getWeekPosts(String term, String courseCode, int week, String[] tagNames, boolean showHidden, boolean withContent, boolean withReplies) {
        return postMapper.getWeekPosts(term, courseCode, week, tagNames, showHidden, withContent, withReplies);
    }

    @Override
    public List<Post> getHomeworkPosts(String term, String courseCode, int homeworkId, String[] tagNames, boolean showHidden, boolean withContent, boolean withReplies) {
        return postMapper.getHomeworkPosts(term, courseCode, homeworkId, tagNames, showHidden, withContent, withReplies);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String addPost(String userId, String boardId, String title, String content) {
        var board = boardService.parseIdAndFetch(boardId);

        var post = new Post();
        post.setPostTerm(board.getHomework().getHwTerm());
        post.setPostCcode(board.getCourse().getCourseCode());

        switch (board.getLocation()) {
            case COURSE -> {
                if (studentService.getUserLevel(userId) < 4) {
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

                throw new NotImplementedException();
            }
            case HOMEWORK -> {
                post.setPostHwId(board.getHomework().getHwId());
                post.setPostWeek(board.getWeek());
                post.setPostChapter(board.getHomework().getHwChapter());
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

        return String.format("Success-%d", post.getPostId());
    }

    @Override
    public String addReply(String userId, Integer fatherPostId, String content) {
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

        return Integer.parseInt(postUser.getStuUserlevel()) < Integer.parseInt(user.getStuUserlevel());
    }

    @Override
    public void editPost(String userId, Integer postId, String newContent) {
        // 记录日志

        var updatePost = new Post();
        updatePost.setPostId(postId);
        updatePost.setPostContent(newContent);
        postMapper.updateById(updatePost);
    }

    @SneakyThrows
    @Override
    public void setPostTag(String userId, Integer postId, int[] targetTags) {
        var tags = metadataService.getTags();

        // 记录日志

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
    public void deletePost(String userId, Integer postId) {
        // 记录日志

        var updatePost = new Post();
        updatePost.setPostId(postId);
        updatePost.setPostIsDel("1");
        postMapper.updateById(updatePost);
    }
}
