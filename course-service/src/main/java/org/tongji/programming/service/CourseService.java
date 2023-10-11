package org.tongji.programming.service;

import com.google.common.collect.ImmutableList;
import org.tongji.programming.dto.CourseTree;
import org.tongji.programming.pojo.Course;

import java.util.List;

/**
 * 信息相关接口
 *
 * @author cinea
 */
public interface CourseService {
    List<Course> getCourses(List<ImmutableList<String>> keys);

    /**
     * 获取用户有权查看的课程及其以树形式的详细信息
     */
    CourseTree getCoursesTree(String userId);

    /**
     * 获取某门课程的周次数据（使用代码查询）
     */
    List<CourseTree.Week> getWeeks(String term, String courseCode);
}
