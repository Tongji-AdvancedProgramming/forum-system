package org.tongji.programming.service;

import org.tongji.programming.pojo.Post;

import javax.annotation.Nullable;
import java.util.List;

/**
 * @author cineazhan
 */
public interface PostService {
    /**
     * 获取板块内的帖子
     */
    List<Post> getPosts(String boardId);

    /**
     * 获取课程整体的帖子
     */
    List<Post> getCoursePosts(String term, String courseCode, boolean showHidden);

    /**
     * 获取某周的整体帖子
     */
    List<Post> getWeekPosts(String term, String courseCode, int week, boolean showHidden);

    /**
     * 获取某次作业的帖子
     */
    List<Post> getHomeworkPosts(String term, String courseCode, int homeworkId, boolean showHidden);

    /**
     * 添加帖子
     */
    @Nullable
    String addPost(String userId, String boardId, String title, String content);
}
