package org.tongji.programming.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tongji.programming.dto.Board;
import org.tongji.programming.enums.PostLocation;
import org.tongji.programming.mapper.CourseMapper;
import org.tongji.programming.mapper.HomeworkMapper;
import org.tongji.programming.pojo.Course;
import org.tongji.programming.pojo.Homework;
import org.tongji.programming.pojo.Post;
import org.tongji.programming.service.BoardService;
import org.tongji.programming.service.PostService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author cinea
 */
@Service
public class BoardServiceImpl implements BoardService {
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
    public Board parseId(String id) {
        // 首先切分ID
        var tokens = id.split("_");
        if (tokens.length < 2 || tokens.length > 4) {
            throw new IllegalArgumentException("传入的ID格式不正确：组成部分不为2、3或4.");
        }

        // 识别学期和课程
        var term = tokens[0];
        var courseCode = tokens[1];
        try {
            assert StringUtils.isNumeric(term);
            assert StringUtils.isNumeric(courseCode);
        } catch (AssertionError error) {
            throw new IllegalArgumentException("传入的ID格式不正确：学期或课程代码不是纯数字");
        }

        // 识别板块位置
        var location = PostLocation.HOMEWORK;
        if (tokens.length == 2) {
            location = PostLocation.COURSE_SUMMARY;
        } else if (tokens.length == 3) {
            if ("general".equals(tokens[2])) {
                location = PostLocation.COURSE;
            } else {
                location = PostLocation.WEEKLY;
            }
        } else if ("p".equals(tokens[3])) {
            location = PostLocation.WEEK_SUMMARY;
        }

        // 识别周次
        int week = 0;
        if (location != PostLocation.COURSE && location != PostLocation.COURSE_SUMMARY) {
            var weekStr = tokens[2].substring(1);
            try {
                assert tokens[2].startsWith("w");
                assert StringUtils.isNumeric(weekStr);
            } catch (AssertionError error) {
                throw new IllegalArgumentException("传入的ID格式不正确：周次格式不正确");
            }
            week = Integer.parseInt(weekStr);
        }

        // 识别所属作业
        String hwId = null;
        if (location == PostLocation.HOMEWORK) {
            hwId = tokens[3];
        }

        // 制作成Board结构体
        var course = new Course();
        course.setCourseTerm(term);
        course.setCourseCode(courseCode);
        Homework homework = null;
        if (hwId != null) {
            homework = new Homework();
            homework.setHwTerm(term);
            homework.setHwCcode(courseCode);
            homework.setHwId(Integer.valueOf(hwId));
        }
        return Board.builder()
                .id(id)
                .course(course)
                .location(location)
                .week(week)
                .homework(homework)
                .build();
    }

    @Override
    public Board parseIdAndFetch(String id) {
        var board = parseId(id);

        // 获取课程信息
        var courseWrapper = new QueryWrapper<Course>();
        courseWrapper
                .eq("course_term", board.getCourse().getCourseTerm())
                .eq("course_code", board.getCourse().getCourseCode());
        var course = courseMapper.selectOne(courseWrapper);

        // 获取作业信息
        Homework homework = null;
        if (board.getHomework() != null) {
            var homeworkWrapper = new QueryWrapper<Homework>();
            homeworkWrapper
                    .eq("hw_term", board.getHomework().getHwTerm())
                    .eq("hw_ccode", board.getHomework().getHwCcode())
                    .eq("hw_id", board.getHomework().getHwId());
            homework = homeworkMapper.selectOne(homeworkWrapper);
        }

        return Board.builder()
                .id(board.getId())
                .week(board.getWeek())
                .location(board.getLocation())
                .course(course)
                .homework(homework)
                .build();
    }

    /**
     * 备忘：对应的上传文件/具体作业的序号
     * - 如果是在第x周的整体问题处发帖,则本字段值为上传文件序号('22232-000001-W0101' - 必须在homework_uploaded中存在)
     * - 如果是在第x周的某具体作业处发帖,则本字段值为具体作业序号('0401' - 必须在homework中存在)
     * - 如果是在课程的整体问题处发帖(仅管理员及超级用户允许),则本字段值为"学期-G5位序号-W4位周次"('22232-G00001-W0101'),
     */
    @Override
    public String generateIdFromHwId(String hwId, String term, String courseCode) {
        throw new NotImplementedException();
    }
}
