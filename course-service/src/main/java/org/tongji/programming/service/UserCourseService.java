package org.tongji.programming.service;

import com.google.common.collect.ImmutableList;
import org.tongji.programming.pojo.Course;

import java.util.List;

/**
 * 和用户相关的方法
 * @author cinea
 */
public interface UserCourseService {

    /**
     * 获取用户有权查看的课程代码列表
     */
    List<ImmutableList<String>> getCourseCodes(String userId);

    /**
     * 获取用户有权查看的课程列表
     */
    List<ImmutableList<String>> getCourses(String userId);

    /**
     * 获取用户有权查看的课程详情
     */
    List<Course> getCoursesDetail(String userId);

}
