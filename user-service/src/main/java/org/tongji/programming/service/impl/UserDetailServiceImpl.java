package org.tongji.programming.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.MessageDigestPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.tongji.programming.mapper.StudentMapper;

import java.util.ArrayList;

/**
 * @author cinea
 */
@Slf4j
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Bean
    public PasswordEncoder passwordEncoder() {
        // 和上游数据库保持一致，使用不安全的md5算法。
        return new MessageDigestPasswordEncoder("MD5");
    }

    StudentMapper studentMapper;

    @Autowired
    public void setStudentMapper(StudentMapper studentMapper) {
        this.studentMapper = studentMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.debug("loadUser被调用。用户名：{}", username);

        var student = studentMapper.selectIdPassLevel(username);
        if (student == null) {
            throw new UsernameNotFoundException("用户不存在");
        }

        var roleList = new ArrayList<String>(2); // 性能调优：一般来说两个就够了
        var userLevel = Integer.parseInt(student.getStuUserlevel());

        if (userLevel >= 9) {
            roleList.add("USER");
            roleList.add("TA");
            roleList.add("ADMIN");
            roleList.add("SUPER");
        } else if (userLevel >= 5) {
            roleList.add("USER");
            roleList.add("TA");
            roleList.add("ADMIN");
        } else if (userLevel >= 2) {
            roleList.add("USER");
            roleList.add("TA");
        } else {
            roleList.add("USER");
        }

        return User
                .withUsername(username)
                .password(student.getStuPassword())
                .roles(roleList.toArray(new String[0]))
                .build();
    }
}
