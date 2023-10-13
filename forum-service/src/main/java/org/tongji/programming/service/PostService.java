package org.tongji.programming.service;

import org.tongji.programming.pojo.Post;

import java.util.List;

/**
 * @author cineazhan
 */
public interface PostService {
    /**
     * 获取课程整体的帖子
     */
    List<Post> getCoursePosts(String term, String courseCode);

    /**
     * 获取某周的整体帖子
     */
    List<Post> getWeekPosts(String term, String courseCode, int week);

    /**
     * 获取某次作业的帖子
     */
    List<Post> getHomeworkPosts(String term, String courseCode, String homeworkId);
}
