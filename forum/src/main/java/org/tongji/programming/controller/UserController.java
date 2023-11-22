package org.tongji.programming.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.tongji.programming.dto.ApiDataResponse;
import org.tongji.programming.dto.ApiResponse;
import org.tongji.programming.dto.StudentShortInfo;
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
            summary = "设置昵称"
    )
    @PostMapping("/nickName")
    protected ApiResponse setNickName(Principal principal, @RequestPart String nickName) {
        var id = principal.getName();
        studentInfoService.setNickName(id, nickName);
        return ApiResponse.success();
    }

    @Secured("ROLE_USER")
    @Operation(
            summary = "设置签名档"
    )
    @PostMapping("/signature")
    protected ApiResponse setSignature(Principal principal, @RequestPart String signature) {
        var id = principal.getName();
        studentInfoService.setSignature(id, signature);
        return ApiResponse.success();
    }

    @Secured("ROLE_USER")
    @Operation(
            summary = "指定用户的简短信息",
            description = "返回的是一些帮助论坛更加友好运行的信息"
    )
    @GetMapping("/shortInfo")
    protected ApiDataResponse<StudentShortInfo> getShortInfo(@RequestParam String id) {
        return ApiDataResponse.success(studentInfoService.getStudentShortInfo(id));
    }

    @Secured("ROLE_USER")
    @Operation(
            summary = "上传头像"
    )
    @PostMapping("/avatar")
    protected ApiResponse putAvatar(Principal principal, @RequestPart("file") MultipartFile file) {
        var id = principal.getName();

        if (file.getSize() > (5 << 20)) {
            return ApiResponse.fail(5000, "图片不可大于5MB");
        }

        try {
            studentInfoService.uploadStudentAvatar(id, file.getInputStream(), file.getContentType(), file.getSize());
        } catch (IOException e) {
            return ApiResponse.fail(5000, "读取用户上传文件失败");
        }
        return ApiResponse.success();
    }

}
