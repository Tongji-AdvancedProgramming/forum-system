package org.tongji.programming.service;

import org.tongji.programming.dto.PostService.GetPostResponse;
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
    List<Post> getPosts(String boardId, String tags, boolean showHidden, boolean withContent, boolean withReplies);

    /**
     * 获取课程整体的帖子
     */
    List<Post> getCoursePosts(String term, String courseCode, String[] tagNames, boolean showHidden, boolean withContent, boolean withReplies);

    /**
     * 获取某周的整体帖子
     */
    List<Post> getWeekPosts(String term, String courseCode, int week, String[] tagNames, boolean showHidden, boolean withContent, boolean withReplies);

    /**
     * 获取某次作业的帖子
     */
    List<Post> getHomeworkPosts(String term, String courseCode, int homeworkId, String[] tagNames, boolean showHidden, boolean withContent, boolean withReplies);

    /**
     * 添加帖子
     */
    @Nullable
    String addPost(String userId, String boardId, String title, String content);

    /**
     * 添加回复
     */
    String addReply(String userId, Integer fatherPost, String content);

    /**
     * 确认用户可以编辑该帖子
     */
    boolean ensureEditPermission(String userId, Integer postId);

    /**
     * 编辑帖子
     */
    void editPost(String userId, Integer postId, String newContent);

    /**
     * 设置帖子标签
     */
    void setPostTag(String userId, Integer postId, int[] tag);

    /**
     * 设置帖子优先级
     */
    void setPostPriority(String userId, Integer postId, int priority);

    /**
     * 删除帖子
     */
    void deletePost(String userId, Integer postId);

    /**
     * 查询帖子，包含所有回帖及回帖的回帖
     */
    GetPostResponse getPost(Integer postId, boolean withHidden);
}
