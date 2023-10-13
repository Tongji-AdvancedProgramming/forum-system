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
    List<Post> getCoursePosts(@Param("term") String term, @Param("courseCode") String courseCode);

    /**
     * 获取某周的整体帖子
     */
    List<Post> getWeekPosts(@Param("term") String term, @Param("courseCode") String courseCode, @Param("week") int week);

    /**
     * 获取某次作业的帖子
     */
    List<Post> getHomeworkPosts(@Param("term") String term, @Param("courseCode") String courseCode, @Param("homeworkId") String homeworkId);
}
