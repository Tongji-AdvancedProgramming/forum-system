package org.tongji.programming.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.tongji.programming.dto.ApiDataResponse;
import org.tongji.programming.dto.ApiResponse;
import org.tongji.programming.service.UserService;

import java.security.Principal;

/**
 * 用户控制器类
 * @author cinea
 */
@RestController
@RequestMapping("/user")
public class UserController {

    UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }


    @Secured("ROLE_USER")
    @RequestMapping(method = RequestMethod.GET)
    protected ApiResponse getMe(Principal principal){
        var id = principal.getName();
        return ApiDataResponse.success(userService.getMe(id));
    }

    @Secured("ROLE_USER")
    @RequestMapping(value = "/infoCompNeeded",method = RequestMethod.GET)
    protected ApiResponse informationCompletionNeeded(Principal principal){
        var id = principal.getName();
        return ApiDataResponse.success(userService.isInfoCompletionNeeded(id));
    }

}
