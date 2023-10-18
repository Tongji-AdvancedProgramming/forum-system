package org.tongji.programming.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tongji.programming.dto.ApiDataResponse;
import org.tongji.programming.pojo.Course;

import java.security.Principal;
import java.util.List;

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

    @Secured("ROLE_USER")
    @Operation(
            summary = "上传图片，注册用户可调用"
    )
    @PostMapping("/images")
    protected ApiDataResponse<List<Course>> addImages(Principal principal) {
        var id = principal.getName();
        throw new NotImplementedException();
    }

}
