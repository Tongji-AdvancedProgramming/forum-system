package org.tongji.programming.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tongji.programming.mapper.StudentInfoMapper;
import org.tongji.programming.pojo.StudentInfo;
import org.tongji.programming.service.StudentInfoService;

/**
 * @author cineazhan
 */
@Component
public class StudentInfoServiceImpl implements StudentInfoService {

    StudentInfoMapper studentInfoMapper;

    @Autowired
    public void setStudentInfoMapper(StudentInfoMapper studentInfoMapper) {
        this.studentInfoMapper = studentInfoMapper;
    }

    @Override
    public StudentInfo getByStuNo(String stuNo) {
        return studentInfoMapper.selectById(stuNo);
    }

    @Override
    public int setByStuNo(StudentInfo studentInfo) {
        return studentInfoMapper.updateById(studentInfo);
    }
}
