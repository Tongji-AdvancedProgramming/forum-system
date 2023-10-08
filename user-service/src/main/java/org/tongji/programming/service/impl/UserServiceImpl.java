package org.tongji.programming.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tongji.programming.mapper.StudentInfoMapper;
import org.tongji.programming.mapper.StudentMapper;
import org.tongji.programming.pojo.Student;
import org.tongji.programming.pojo.StudentInfo;
import org.tongji.programming.service.UserService;

/**
 * @author cinea
 */
@Component
public class UserServiceImpl implements UserService {

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
        wrapper.eq("stu_no",userId);

        return !studentInfoMapper.exists(wrapper);
    }

    @Override
    public Student getMe(String userId) {
        return studentMapper.selectById(userId);
    }
}
