package org.tongji.programming.controller;

import com.google.common.collect.ImmutableList;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.tongji.programming.dto.ApiDataResponse;
import org.tongji.programming.dto.CourseTree;
import org.tongji.programming.pojo.Course;
import org.tongji.programming.service.CourseService;
import org.tongji.programming.service.UserCourseService;

import java.security.Principal;
import java.util.List;

/**
 * 课程控制器类
 *
 * @author cinea
 */
@Tag(
        name = "Course",
        description = "课程信息、课程数据"
)
@RestController
@RequestMapping("/course")
public class CourseController {

    UserCourseService userCourseService;

    @Autowired
    public void setUserCourseService(UserCourseService userCourseService) {
        this.userCourseService = userCourseService;
    }

    CourseService courseService;

    @Autowired
    public void setCourseService(CourseService courseService) {
        this.courseService = courseService;
    }

    @Secured("ROLE_USER")
    @Operation(
            summary = "当前用户拥有访问权的课程",
            description = "学生可访问已选课，助教和教师可访问所有课程"
    )
    @RequestMapping(value = "/my-course", method = RequestMethod.GET)
    protected ApiDataResponse<List<ImmutableList<String>>> getMyCourses(Principal principal) {
        var id = principal.getName();
        return ApiDataResponse.success(userCourseService.getCourses(id));
    }

    @Secured("ROLE_USER")
    @Operation(
            summary = "当前用户拥有访问权的课程详情",
            description = "学生可访问已选课，助教和教师可访问所有课程"
    )
    @RequestMapping(value = "/my-course/detail", method = RequestMethod.GET)
    protected ApiDataResponse<List<Course>> getMyCoursesDetail(Principal principal) {
        var id = principal.getName();
        return ApiDataResponse.success(userCourseService.getCoursesDetail(id));
    }

    @Secured("ROLE_USER")
    @Operation(
            summary = "当前用户拥有访问权的课程代码",
            description = "学生可访问已选课，助教和教师可访问所有课程"
    )
    @RequestMapping(value = "/my-course-code", method = RequestMethod.GET)
    protected ApiDataResponse<List<ImmutableList<String>>> getMyCourseCodes(Principal principal) {
        var id = principal.getName();
        return ApiDataResponse.success(userCourseService.getCourseCodes(id));
    }

    @Secured("ROLE_USER")
    @Operation(
            summary = "获取课程树",
            description = "课程树包含了课程、班级、周次和作业等信息"
    )
    @RequestMapping(value = "/tree", method = RequestMethod.GET)
    protected ApiDataResponse<CourseTree> getCourseTree(Principal principal) {
        var id = principal.getName();
        return ApiDataResponse.success(courseService.getCoursesTree(id));
    }

}
