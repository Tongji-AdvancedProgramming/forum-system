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
    List<Post> getCoursePosts(@Param("term") String term, @Param("courseCode") String courseCode, @Param("tagNames") String[] tagNames, @Param("showHidden") boolean showHidden, @Param("withContent") boolean withContent, @Param("withReplies") boolean withReplies, @Param("limit") int limit, @Param("offset") int offset);

    /**
     * 获取某周的整体帖子
     */
    List<Post> getWeekPosts(@Param("term") String term, @Param("courseCode") String courseCode, @Param("week") int week, @Param("tagNames") String[] tagNames, @Param("showHidden") boolean showHidden, @Param("withContent") boolean withContent, @Param("withReplies") boolean withReplies, @Param("limit") int limit, @Param("offset") int offset);

    /**
     * 获取某次作业的帖子
     */
    List<Post> getHomeworkPosts(@Param("term") String term, @Param("courseCode") String courseCode, @Param("homeworkId") int homeworkId, @Param("tagNames") String[] tagNames, @Param("showHidden") boolean showHidden, @Param("withContent") boolean withContent, @Param("withReplies") boolean withReplies, @Param("limit") int limit, @Param("offset") int offset);

    /**
     * 获取某课程所有帖子（汇总）
     */
    List<Post> getCourseSummaryPosts(@Param("term") String term, @Param("courseCode") String courseCode, @Param("tagNames") String[] tagNames, @Param("showHidden") boolean showHidden, @Param("withContent") boolean withContent, @Param("withReplies") boolean withReplies, @Param("limit") int limit, @Param("offset") int offset);

    /**
     * 获取某周所有帖子（汇总）
     */
    List<Post> getWeekSummaryPosts(@Param("term") String term, @Param("courseCode") String courseCode, @Param("week") int week, @Param("tagNames") String[] tagNames, @Param("showHidden") boolean showHidden, @Param("withContent") boolean withContent, @Param("withReplies") boolean withReplies, @Param("limit") int limit, @Param("offset") int offset);

    /**
     * 获取课程整体的帖子
     */
    Integer getCoursePostsCount(@Param("term") String term, @Param("courseCode") String courseCode, @Param("tagNames") String[] tagNames, @Param("showHidden") boolean showHiddenInteger, @Param("withReplies") boolean withReplies);

    /**
     * 获取某周的整体帖子
     */
    Integer getWeekPostsCount(@Param("term") String term, @Param("courseCode") String courseCode, @Param("week") int week, @Param("tagNames") String[] tagNames, @Param("showHidden") boolean showHiddenInteger, @Param("withReplies") boolean withReplies);

    /**
     * 获取某次作业的帖子
     */
    Integer getHomeworkPostsCount(@Param("term") String term, @Param("courseCode") String courseCode, @Param("homeworkId") int homeworkId, @Param("tagNames") String[] tagNames, @Param("showHidden") boolean showHiddenInteger, @Param("withReplies") boolean withReplies);

    /**
     * 获取某课程所有帖子
     */
    Integer getCourseSummaryPostsCount(@Param("term") String term, @Param("courseCode") String courseCode, @Param("tagNames") String[] tagNames, @Param("showHidden") boolean showHidden, @Param("withReplies") boolean withReplies);

    /**
     * 获取某周所有帖子
     */
    Integer getWeekSummaryPostsCount(@Param("term") String term, @Param("courseCode") String courseCode, @Param("week") int week, @Param("tagNames") String[] tagNames, @Param("showHidden") boolean showHidden, @Param("withReplies") boolean withReplies);

    /**
     * 获取帖子但不获取内容
     */
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
