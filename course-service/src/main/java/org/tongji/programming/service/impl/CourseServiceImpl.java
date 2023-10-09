package org.tongji.programming.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.ImmutableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tongji.programming.dto.CourseTree;
import org.tongji.programming.mapper.CourseMapper;
import org.tongji.programming.mapper.HomeworkMapper;
import org.tongji.programming.pojo.Course;
import org.tongji.programming.pojo.Homework;
import org.tongji.programming.service.CourseService;
import org.tongji.programming.service.UserCourseService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;

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

    CourseMapper courseMapper;

    @Autowired
    public void setCourseMapper(CourseMapper courseMapper) {
        this.courseMapper = courseMapper;
    }

    HomeworkMapper homeworkMapper;

    @Autowired
    public void setHomeworkMapper(HomeworkMapper homeworkMapper) {
        this.homeworkMapper = homeworkMapper;
    }

    @Override
    public CourseTree getCoursesTree(String userId) {
        // 获取所有可以访问的课程
        var courseKeys = userCourseService.getCourses(userId);
        // 获取完整的可访问的课程列表
        var courses = courseMapper.getAllCourseDetail(courseKeys);

        // 将课程按照课程代码分类
        var courseCodeMap = new HashMap<ImmutableList<String>, List<Course>>();
        courses.forEach(c -> {
            var list = courseCodeMap.getOrDefault(ImmutableList.of(c.getCourseTerm(), c.getCourseCode()), new ArrayList<>(2));
            list.add(c);
            courseCodeMap.put(ImmutableList.of(c.getCourseTerm(), c.getCourseCode()), list);
        });

        // 构造树形结构
        var response = new CourseTree();
        response.setCourses(
                courseCodeMap.values().stream().filter(list -> !list.isEmpty()).map(list -> {
                    var fatherCourse = CourseTree.Course.fromCourse(list.get(0));
                    fatherCourse.setCourseSname(null);
                    fatherCourse.setCourseNo(null);

                    var weeks = getWeeks(fatherCourse.getCourseTerm(), fatherCourse.getCourseCode());

                    fatherCourse.setClasses(
                            list.stream().map(CourseTree.Class::fromCourse)
                                    .peek(c -> c.setWeeks(weeks))
                                    .collect(Collectors.toList())
                    );

                    return fatherCourse;
                }).collect(Collectors.toList())
        );

        return response;
    }

    /**
     * 设计概要
     * <p>
     * 这个方法的作用主要是用来制作用于显示在前端的周次数据
     */
    @Override
    public List<CourseTree.Week> getWeeks(String term, String courseCode) {
        var result = new ArrayList<CourseTree.Week>();

        // 获取原始数据
        var wrapper = new QueryWrapper<Homework>()
                .eq("hw_term", term)
                .eq("hw_ccode", courseCode);
        var data = homeworkMapper.selectList(wrapper);

        // 加工数据
        var maxWeek = 0;
        var weekHomeworkMap = new TreeMap<Integer, List<Homework>>();
        for (var hw : data) {
            maxWeek = Integer.max(hw.getHwWeek(), maxWeek);
            weekHomeworkMap.computeIfAbsent(hw.getHwWeek(), k -> new ArrayList<>());
            weekHomeworkMap.getOrDefault(hw.getHwWeek(), new ArrayList<>()).add(hw);
        }

        for (int i = 1; i <= maxWeek; i++) {
            var week = new CourseTree.Week();
            week.setNumber(i);
            week.setHomeworks(weekHomeworkMap.getOrDefault(i, new ArrayList<>()));
            var chapters = week.getHomeworks().stream().map(Homework::getHwChapter).map(Object::toString).collect(Collectors.toSet());
            var weekTitle = new StringBuilder();
            weekTitle.append("第");
            weekTitle.append(i);
            weekTitle.append("周");
            if (!chapters.isEmpty()) {
                weekTitle.append(" - 第");
                weekTitle.append(String.join(",", chapters));
                weekTitle.append("章");
            }
            week.setContent(weekTitle.toString());
            result.add(week);
        }

        return result;
    }
}
