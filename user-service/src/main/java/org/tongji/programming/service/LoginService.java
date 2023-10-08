package org.tongji.programming.service;

import org.tongji.programming.dto.LoginResponse;

/**
 * 登录相关服务
 * @author cinea
 */
public interface LoginService {
    LoginResponse login(String username, String password);
}
