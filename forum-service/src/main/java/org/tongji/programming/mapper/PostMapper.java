package org.tongji.programming.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.tongji.programming.pojo.Post;

import java.util.List;

/**
 * @author cineazhan
 */
public interface PostMapper extends BaseMapper<Post> {
    /**
     * 获取课程整体的帖子
     */
    List<Post> getCoursePosts(@Param("term") String term, @Param("courseCode") String courseCode, @Param("tagNames") String[] tagNames, @Param("showHidden") boolean showHidden, @Param("showContent") boolean showContent, @Param("withReplies") boolean withReplies);

    /**
     * 获取某周的整体帖子
     */
    List<Post> getWeekPosts(@Param("term") String term, @Param("courseCode") String courseCode, @Param("week") int week, @Param("tagNames") String[] tagNames, @Param("showHidden") boolean showHidden, @Param("showContent") boolean showContent, @Param("withReplies") boolean withReplies);

    /**
     * 获取某次作业的帖子
     */
    List<Post> getHomeworkPosts(@Param("term") String term, @Param("courseCode") String courseCode, @Param("homeworkId") int homeworkId, @Param("tagNames") String[] tagNames, @Param("showHidden") boolean showHidden, @Param("showContent") boolean showContent, @Param("withReplies") boolean withReplies);

    Post getPostWithoutContent(@Param("postId") Integer postId);

    /**
     * 递归查询某个帖子旗下的子帖子
     */
    List<Post> getPostsRecursively(@Param("id") Integer postId);

    /**
     * 递归查询某个帖子的父帖子
     */
    Post getParentPostRecursively(@Param("id") Integer postId);
}
