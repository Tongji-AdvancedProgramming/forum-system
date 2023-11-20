package org.tongji.programming.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.tongji.programming.dto.ApiDataResponse;
import org.tongji.programming.service.UploadService;

import java.io.IOException;
import java.security.Principal;

/**
 * 上传控制器类
 *
 * @author cinea
 */
@Tag(
        name = "Upload",
        description = "上传相关控制器"
)
@RestController
@RequestMapping("/upload")
public class UploadController {

    UploadService uploadService;

    @Autowired
    public void setUploadService(UploadService uploadService) {
        this.uploadService = uploadService;
    }

    @Secured("ROLE_USER")
    @Operation(
            summary = "上传图片，注册用户可调用"
    )
    @PostMapping("/images")
    protected ApiDataResponse<String> addImages(Principal principal, MultipartFile file) throws IOException {
        var id = principal.getName();
        var type = file.getContentType();
        var size = file.getSize();
        var suffix = file.getOriginalFilename();
        if (suffix != null) {
            suffix = suffix.substring(suffix.lastIndexOf('.') + 1);
        } else {
            suffix = "";
        }

        return ApiDataResponse.success(uploadService.uploadImage(id, file.getInputStream(), suffix, type, size));
    }

}
