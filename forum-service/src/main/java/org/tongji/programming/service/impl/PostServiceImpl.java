package org.tongji.programming.service.impl;

import org.apache.commons.lang3.NotImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tongji.programming.dto.PostService.GetPostResponse;
import org.tongji.programming.mapper.PostMapper;
import org.tongji.programming.pojo.Post;
import org.tongji.programming.service.BoardService;
import org.tongji.programming.service.PostService;
import org.tongji.programming.service.StudentService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author cinea
 */
@Service
public class PostServiceImpl implements PostService {

    private PostMapper postMapper;
    private BoardService boardService;
    private StudentService studentService;

    @Override
    public List<Post> getPosts(String boardId, boolean withContent) {
        var board = boardService.parseId(boardId);
        switch (board.getLocation()) {
            case WEEKLY -> {
                return getWeekPosts(board.getCourse().getCourseTerm(), board.getCourse().getCourseCode(), board.getWeek(), false, false);
            }
            case HOMEWORK -> {
                return getHomeworkPosts(board.getCourse().getCourseTerm(), board.getCourse().getCourseCode(), board.getHomework().getHwId(), false, false);
            }
            case COURSE -> {
                return getCoursePosts(board.getCourse().getCourseTerm(), board.getCourse().getCourseCode(), false, false);
            }
        }
        return new ArrayList<>();
    }

    @Override
    public List<Post> getCoursePosts(String term, String courseCode, boolean showHidden, boolean withContent) {
        return postMapper.getCoursePosts(term, courseCode, showHidden, withContent);
    }

    @Override
    public List<Post> getWeekPosts(String term, String courseCode, int week, boolean showHidden, boolean withContent) {
        return postMapper.getWeekPosts(term, courseCode, week, showHidden, withContent);
    }

    @Override
    public List<Post> getHomeworkPosts(String term, String courseCode, int homeworkId, boolean showHidden, boolean withContent) {
        return postMapper.getHomeworkPosts(term, courseCode, homeworkId, showHidden, withContent);
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
                var gId = 0;
                var newestId = postMapper.getNewestGeneralQuestionId(post.getPostTerm(), post.getPostCcode());
                var pattern = Pattern.compile(".*-G(\\d+)-.*");
                var matcher = pattern.matcher(newestId);
                if (matcher.find()) {
                    gId = Integer.parseInt(matcher.group(0));
                }

                post.setPostHwupOrHwId(String.format("%s-G%05d-W0000", post.getPostTerm(), gId));
                post.setPostWeek(-1);
                post.setPostChapter(-1);
            }
            case WEEKLY -> {
                post.setPostHwupOrHwId(""); // TODO: 待定
                post.setPostWeek(board.getWeek());
                post.setPostChapter(-1);

                throw new NotImplementedException();
            }
            case HOMEWORK -> {
                post.setPostHwupOrHwId(String.valueOf(board.getHomework().getHwId()));
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
    public GetPostResponse getPost(Integer postId) {
        var resp = new GetPostResponse();

        var fatherPost = postMapper.selectById(postId);
        var childPosts = postMapper.getPostsRecursively(postId);

        resp.setPost(fatherPost);
        resp.setFollowedPosts(childPosts);

        return resp;
    }

    @Autowired
    public void setPostMapper(PostMapper postMapper) {
        this.postMapper = postMapper;
    }

    @Autowired
    public void setBoardService(BoardService boardService) {
        this.boardService = boardService;
    }

    @Autowired
    public void setStudentService(StudentService studentService) {
        this.studentService = studentService;
    }
}
