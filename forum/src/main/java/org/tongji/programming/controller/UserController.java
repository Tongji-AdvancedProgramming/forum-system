package org.tongji.programming.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.tongji.programming.dto.ApiDataResponse;
import org.tongji.programming.dto.ApiResponse;
import org.tongji.programming.pojo.Student;
import org.tongji.programming.service.UserService;

import java.security.Principal;
import java.util.List;

/**
 * 用户控制器类
 *
 * @author cinea
 */
@Tag(
        name = "User",
        description = "用户信息/数据"
)
@RestController
@RequestMapping("/user")
public class UserController {

    UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }


    @Secured("ROLE_USER")
    @Operation(
            summary = "当前登录的用户信息"
    )
    @RequestMapping(method = RequestMethod.GET)
    protected ApiDataResponse<Student> getMe(Principal principal) {
        var id = principal.getName();
        return ApiDataResponse.success(userService.getMe(id));
    }

    @Secured("ROLE_USER")
    @Operation(
            summary = "当前用户是否需要完善信息",
            description = "主要是指头像、昵称之类的信息"
    )
    @RequestMapping(value = "/info-completion-needed", method = RequestMethod.GET)
    protected ApiDataResponse<Boolean> informationCompletionNeeded(Principal principal) {
        var id = principal.getName();
        return ApiDataResponse.success(userService.isInfoCompletionNeeded(id));
    }

}
