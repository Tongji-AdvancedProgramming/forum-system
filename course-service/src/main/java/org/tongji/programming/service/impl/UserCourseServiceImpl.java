package org.tongji.programming.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.ImmutableList;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tongji.programming.dto.CourseTree;
import org.tongji.programming.mapper.CourseMapper;
import org.tongji.programming.mapper.StudentMapper;
import org.tongji.programming.pojo.Course;
import org.tongji.programming.pojo.Student;
import org.tongji.programming.service.UserCourseService;

import java.time.Duration;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

/**
 * @author cinea
 */
@Component
public class UserCourseServiceImpl implements UserCourseService {

    StudentMapper studentMapper;

    @Autowired
    public void setStudentMapper(StudentMapper studentMapper) {
        this.studentMapper = studentMapper;
    }

    CourseMapper courseMapper;

    @Autowired
    public void setCourseMapper(CourseMapper courseMapper) {
        this.courseMapper = courseMapper;
    }

    @Override
    public List<ImmutableList<String>> getCourseCodes(String userId) {
        var stu = studentMapper.selectCourses(userId);
        if (stu == null) {
            return new LinkedList<>();
        }

        if (Integer.parseInt(stu.getStuUserlevel()) >= 5) {
            // 用户是管理员或更高，可以访问所有课程。
            var wrapper = new QueryWrapper<Course>()
                    .groupBy("course_code")
                    .select("course_term", "course_code");
            var courses = courseMapper.selectList(wrapper);
            return courses.stream().map(c -> ImmutableList.of(c.getCourseTerm(), c.getCourseCode())).collect(Collectors.toList());
        } else {
            var result = getStudentCoursesFromEntity(stu);

            var wrapper = new QueryWrapper<Course>()
                    .eq("course_term", stu.getStuTerm())
                    .in("course_no", result)
                    .select("course_term", "course_code");
            var courses = courseMapper.selectList(wrapper);
            return courses.stream().map(c -> ImmutableList.of(c.getCourseTerm(), c.getCourseCode())).collect(Collectors.toList());
        }
    }

    LoadingCache<String, List<ImmutableList<String>>> getCoursesCache = CacheBuilder.newBuilder()
            .maximumSize(100)
            .expireAfterWrite(Duration.ofDays(1))
            .build(new CacheLoader<String, List<ImmutableList<String>>>() {
                @Override
                public @NotNull List<ImmutableList<String>> load(@NotNull String key) throws Exception {
                    return getCoursesLoads(key);
                }
            });

    @Override
    public List<ImmutableList<String>> getCourses(String userId) {
        try {
            return getCoursesCache.get(userId);
        } catch (ExecutionException e) {
            return getCoursesLoads(userId);
        }
    }

    private List<ImmutableList<String>> getCoursesLoads(String userId) {
        var stu = studentMapper.selectCourses(userId);
        if (stu == null) {
            return new LinkedList<>();
        }

        if (Integer.parseInt(stu.getStuUserlevel()) >= 5) {
            // 用户是管理员或更高，可以访问所有课程。
            var wrapper = new QueryWrapper<Course>()
                    .select("course_term", "course_no");
            var courses = courseMapper.selectList(wrapper);
            return courses.stream().map(c -> ImmutableList.of(c.getCourseTerm(), c.getCourseNo())).collect(Collectors.toList());
        } else {
            var result = getStudentCoursesFromEntity(stu);
            return result.stream().map(c -> ImmutableList.of(stu.getStuTerm(), c)).collect(Collectors.toList());
        }
    }

    @Override
    public List<Course> getCoursesDetail(String userId) {
        var list = getCourses(userId);
        return courseMapper.getAllCourseDetail(list);
    }

    private List<String> getStudentCoursesFromEntity(Student stu) {
        var result = new ArrayList<String>(2);

        if (stu.getStuCno1() != null && "0".equals(stu.getStuCno1IsDel())) {
            result.add(stu.getStuCno1());
        }
        if (stu.getStuCno2() != null && "0".equals(stu.getStuCno2IsDel())) {
            result.add(stu.getStuCno2());
        }
        if (stu.getStuCno3() != null && "0".equals(stu.getStuCno3IsDel())) {
            result.add(stu.getStuCno3());
        }

        return result;
    }
}
