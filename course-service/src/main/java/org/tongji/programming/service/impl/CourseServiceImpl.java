package org.tongji.programming.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.ImmutableList;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tongji.programming.dto.CourseTree;
import org.tongji.programming.mapper.CourseMapper;
import org.tongji.programming.mapper.HomeworkMapper;
import org.tongji.programming.pojo.Course;
import org.tongji.programming.pojo.Homework;
import org.tongji.programming.service.CourseService;
import org.tongji.programming.service.UserCourseService;

import java.time.Duration;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.random.RandomGenerator;
import java.util.stream.Collectors;

/**
 * @author cinea
 */
@Slf4j
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

    LoadingCache<ImmutableList<String>, Course> courseCache = CacheBuilder.newBuilder()
            .maximumSize(100)
            .expireAfterWrite(Duration.ofHours(1))
            .build(new CacheLoader<ImmutableList<String>, Course>() {
                @Override
                public Course load(ImmutableList<String> key) throws Exception {
                    if (key.size() != 2)
                        return null;
                    return courseMapper.getCourseDetail(key);
                }
            });

    @Override
    public List<Course> getCourses(List<ImmutableList<String>> keys) {
        var results = new ArrayList<Course>(keys.size());

        keys.forEach(key -> {
            try {
                results.add(courseCache.get(key));
            } catch (ExecutionException e) {
                results.add(courseMapper.getCourseDetail(key));
            }
        });

        return results;
    }

    // 因为我不想做数据更新缓存作废的功能，所以直接设缓存1分钟作废一次。
    LoadingCache<ImmutableList<String>, List<CourseTree.Week>> getWeekCache = CacheBuilder.newBuilder()
            .maximumSize(100)
            .expireAfterWrite(Duration.ofMinutes(1))
            .build(new CacheLoader<ImmutableList<String>, List<CourseTree.Week>>() {
                @Override
                public @NotNull List<CourseTree.Week> load(@NotNull ImmutableList<String> key) throws Exception {
                    if (key.size() != 2) {
                        return new ArrayList<>(0);
                    }
                    return getWeeksLoads(key.get(0), key.get(1));
                }
            });

    @Override
    public CourseTree getCoursesTree(String userId) {
        var profile = new Random().nextInt(10000) == 1000; // 性能分析，随机触发
        var st = System.currentTimeMillis();
        if (profile) {
            log.info("[CourseTree] 本次执行将进行性能分析。");
        }

        // 获取所有可以访问的课程
        var courseKeys = userCourseService.getCourses(userId);
        // 获取完整的可访问的课程列表
        var courses = getCourses(courseKeys);

        if (profile) {
            var d = System.currentTimeMillis() - st;
            log.info("[CourseTree] CheckPoint-1，已执行 {} ms", d);
        }

        // 将课程按照课程代码分类
        var courseCodeMap = new HashMap<ImmutableList<String>, List<Course>>();
        courses.forEach(c -> {
            var list = courseCodeMap.getOrDefault(ImmutableList.of(c.getCourseTerm(), c.getCourseCode()), new ArrayList<>(2));
            list.add(c);
            courseCodeMap.put(ImmutableList.of(c.getCourseTerm(), c.getCourseCode()), list);
        });

        if (profile) {
            var d = System.currentTimeMillis() - st;
            log.info("[CourseTree] CheckPoint-2，已执行 {} ms", d);
        }

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

        if (profile) {
            var d = System.currentTimeMillis() - st;
            log.info("[CourseTree] CheckPoint-3，已执行 {} ms", d);
        }

        return response;
    }

    @Override
    public List<CourseTree.Week> getWeeks(String term, String courseCode) {
        try {
            return getWeekCache.get(ImmutableList.of(term, courseCode));
        } catch (ExecutionException e) {
            log.error("Course Week data读取缓存时出现错误。");
            return getWeeksLoads(term, courseCode);
        }
    }

    /**
     * 设计概要
     * <p>
     * 这个方法的作用主要是用来制作用于显示在前端的周次数据
     */
    private List<CourseTree.Week> getWeeksLoads(String term, String courseCode) {
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
