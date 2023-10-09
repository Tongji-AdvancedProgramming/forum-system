package org.tongji.programming.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.tongji.programming.pojo.Course;

import java.util.List;

/**
 * 以树形式返回的课程列表数据
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
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode(callSuper = true)
    public static class Class extends org.tongji.programming.pojo.Course {
        private List<Week> weeks;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Week {
        private int number;
        private String content;
        private List<String> homeworks;
    }

    private List<Course> courses;
}
