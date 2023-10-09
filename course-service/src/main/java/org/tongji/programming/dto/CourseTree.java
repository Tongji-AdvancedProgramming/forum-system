package org.tongji.programming.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.tongji.programming.pojo.Homework;

import java.util.ArrayList;
import java.util.List;

/**
 * 以树形式返回的课程列表数据
 *
 * @author cinea
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseTree {
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode(callSuper = true)
    public static class Course extends org.tongji.programming.pojo.Course {
        private List<Class> classes;

        public static Course fromCourse(org.tongji.programming.pojo.Course course) {
            var newCourse = new Course();
            newCourse.setCourseTerm(course.getCourseTerm());
            newCourse.setCourseNo(course.getCourseNo());
            newCourse.setCourseCode(course.getCourseCode());
            newCourse.setCourseFname(course.getCourseFname());
            newCourse.setCourseSname(course.getCourseSname());
            newCourse.setCourseType(course.getCourseType());
            return newCourse;
        }
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode(callSuper = true)
    public static class Class extends org.tongji.programming.pojo.Course {
        private List<Week> weeks;

        public static Class fromCourse(org.tongji.programming.pojo.Course course) {
            var newClass = new Class();
            newClass.setCourseTerm(course.getCourseTerm());
            newClass.setCourseNo(course.getCourseNo());
            newClass.setCourseCode(course.getCourseCode());
            newClass.setCourseFname(course.getCourseFname());
            newClass.setCourseSname(course.getCourseSname());
            newClass.setCourseType(course.getCourseType());
            newClass.setWeeks(new ArrayList<>(0));
            return newClass;
        }
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Week {
        private int number;
        private String content;
        private List<Homework> homeworks;
    }

    private List<Course> courses;
}
