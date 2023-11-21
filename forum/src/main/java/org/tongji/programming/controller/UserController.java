package org.tongji.programming.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.tongji.programming.dto.ApiDataResponse;
import org.tongji.programming.dto.ApiResponse;
import org.tongji.programming.pojo.Student;
import org.tongji.programming.pojo.StudentInfo;
import org.tongji.programming.service.StudentInfoService;
import org.tongji.programming.service.StudentService;

import java.io.IOException;
import java.security.Principal;

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

    StudentService studentService;

    @Autowired
    public void setUserService(StudentService studentService) {
        this.studentService = studentService;
    }

    StudentInfoService studentInfoService;

    @Autowired
    public void setStudentInfoService(StudentInfoService studentInfoService) {
        this.studentInfoService = studentInfoService;
    }

    @Secured("ROLE_USER")
    @Operation(
            summary = "当前登录的用户信息"
    )
    @RequestMapping(method = RequestMethod.GET)
    protected ApiDataResponse<Student> getMe(Principal principal) {
        var id = principal.getName();
        return ApiDataResponse.success(studentService.getMe(id));
    }

    @Secured("ROLE_USER")
    @Operation(
            summary = "当前用户是否需要完善信息（已废弃）",
            description = "主要是指头像、昵称之类的信息"
    )
    @RequestMapping(value = "/info-completion-needed", method = RequestMethod.GET)
    protected ApiDataResponse<Boolean> informationCompletionNeeded(Principal principal) {
        var id = principal.getName();
        return ApiDataResponse.success(studentService.isInfoCompletionNeeded(id));
    }


    @Secured("ROLE_USER")
    @Operation(
            summary = "当前登录的用户论坛信息",
            description = "返回的是一些帮助论坛更加友好运行的信息"
    )
    @GetMapping("/info")
    protected ApiDataResponse<StudentInfo> getMyInfo(Principal principal) {
        var id = principal.getName();
        return ApiDataResponse.success(studentInfoService.getByStuNo(id));
    }

    @Secured("ROLE_USER")
    @Operation(
            summary = "上传头像"
    )
    @PostMapping("/avatar")
    protected ApiResponse putAvatar(Principal principal, @RequestPart("file") MultipartFile file) {
        var id = principal.getName();
        try {
            studentInfoService.uploadStudentAvatar(id, file.getInputStream());
        } catch (IOException e) {
            return ApiResponse.fail(500, "读取用户上传文件失败");
        }
        return ApiResponse.success();
    }

}
