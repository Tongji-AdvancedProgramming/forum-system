package org.tongji.programming.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tongji.programming.dto.LoginResponse;
import org.tongji.programming.mapper.StudentMapper;
import org.tongji.programming.service.LoginService;

/**
 * @author cinea
 */
@Component
public class LoginServiceImpl implements LoginService {

    StudentMapper studentMapper;

    @Autowired
    public void setStudentMapper(StudentMapper studentMapper) {
        this.studentMapper = studentMapper;
    }

    @Override
    public LoginResponse login(String username, String password) {
        var stu = studentMapper.selectByIdPass(username,password);
        if (stu == null) {
            return LoginResponse.builder()
                    .success(false)
                    .data(null)
                    .infoCompletionNeeded(false)
                    .build();
        }else{
            return LoginResponse.builder()
                    .success(true)
                    .data(stu)
                    .infoCompletionNeeded(false)
                    .build();
        }
    }
}
