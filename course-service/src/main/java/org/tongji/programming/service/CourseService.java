package org.tongji.programming.service;

import org.tongji.programming.dto.CourseTree;

/**
 * 信息相关接口
 *
 * @author cinea
 */
public interface CourseService {
    /**
     * 获取用户有权查看的课程及其以树形式的详细信息
     */
    CourseTree getCoursesDetailed(String userId);
}
