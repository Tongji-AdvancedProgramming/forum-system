package org.tongji.programming.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.google.common.collect.ImmutableList;
import org.apache.ibatis.annotations.Param;
import org.tongji.programming.dto.CourseTree;
import org.tongji.programming.pojo.Course;
import org.tongji.programming.pojo.Homework;

import java.util.List;

/**
 * @author cinea
 */
public interface CourseMapper extends BaseMapper<Course> {
    List<Course> getAllCourseDetail(@Param("courseKeys") List<ImmutableList<String>> courseKeys);

    Course getCourseDetail(@Param("courseKey") ImmutableList<String> courseKey);
}
