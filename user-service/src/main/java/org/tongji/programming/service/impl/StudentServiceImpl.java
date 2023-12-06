package org.tongji.programming.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tongji.programming.mapper.StudentInfoMapper;
import org.tongji.programming.mapper.StudentMapper;
import org.tongji.programming.pojo.Student;
import org.tongji.programming.pojo.StudentInfo;
import org.tongji.programming.service.StudentService;

/**
 * @author cinea
 */
@Component
public class StudentServiceImpl implements StudentService {

    StudentInfoMapper studentInfoMapper;

    @Autowired
    public void setStudentInfoMapper(StudentInfoMapper studentInfoMapper) {
        this.studentInfoMapper = studentInfoMapper;
    }

    StudentMapper studentMapper;

    @Autowired
    public void setStudentMapper(StudentMapper studentMapper) {
        this.studentMapper = studentMapper;
    }

    @Override
    public boolean isInfoCompletionNeeded(String userId) {
        var wrapper = new QueryWrapper<StudentInfo>();
        wrapper.eq("stu_no", userId);

        return !studentInfoMapper.exists(wrapper);
    }

    @Override
    public Student getMe(String userId) {
        var result = studentMapper.selectById(userId);
        result.setStuComment(null);
        result.setStuPassword(null);
        return result;
    }

    @Override
    public int getUserLevel(String userId) {
        var user = studentMapper.selectStuUserLevel(userId);
        if (user == null) {
            return 0;
        }
        return Integer.parseInt(user.getStuUserlevel());
    }
}
