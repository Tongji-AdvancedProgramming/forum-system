package org.tongji.programming.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tongji.programming.dto.CourseTree;
import org.tongji.programming.service.CourseService;
import org.tongji.programming.service.UserCourseService;

/**
 * @author cinea
 */
@Component
public class CourseServiceImpl implements CourseService {

    UserCourseService userCourseService;

    @Autowired
    public void setUserCourseService(UserCourseService userCourseService) {
        this.userCourseService = userCourseService;
    }

    @Override
    public CourseTree getCoursesDetailed(String userId) {
        // 获取独立的课程（课程代码不同的）
        var courseCodes = userCourseService.getCourseCodes(userId);
        // 获取所有可以访问的课程
        var courses = userCourseService.getCourses(userId);
        // 获取完整的可访问的课程列表


        return null;
    }
}
