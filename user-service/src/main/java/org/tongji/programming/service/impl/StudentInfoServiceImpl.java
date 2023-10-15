package org.tongji.programming.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.tongji.programming.mapper.StudentInfoMapper;
import org.tongji.programming.pojo.StudentInfo;
import org.tongji.programming.service.StudentInfoService;

/**
 * @author cineazhan
 */
@Service
public class StudentInfoServiceImpl implements StudentInfoService {

    StudentInfoMapper studentInfoMapper;

    @Autowired
    public void setStudentInfoMapper(StudentInfoMapper studentInfoMapper) {
        this.studentInfoMapper = studentInfoMapper;
    }

    /**
     * 新用户的默认头像
     */
    @Value("${forum.user.default-avatar}")
    private String defaultAvatar;

    @Override
    public StudentInfo getByStuNo(String stuNo) {
        var result =  studentInfoMapper.selectById(stuNo);
        // 如果不存在，就生成一个默认的补上。
        if (result == null) {
            var student = studentInfoMapper.getStudentDefaultInfo(stuNo);
            if (student == null) {
                return null;
            }
            var defaultOne = new StudentInfo();
            defaultOne.setStuNo(stuNo);
            defaultOne.setNickname(student.getStuName());
            defaultOne.setAvatar(defaultAvatar);
            studentInfoMapper.insert(defaultOne);
            result = defaultOne;
        }
        return result;
    }

    @Override
    public int setByStuNo(StudentInfo studentInfo) {
        return studentInfoMapper.updateById(studentInfo);
    }
}
