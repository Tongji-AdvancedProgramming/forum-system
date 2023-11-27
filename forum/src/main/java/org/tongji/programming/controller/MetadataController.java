package org.tongji.programming.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.tongji.programming.dto.ApiDataResponse;
import org.tongji.programming.dto.ApiResponse;
import org.tongji.programming.dto.StudentShortInfo;
import org.tongji.programming.pojo.Student;
import org.tongji.programming.pojo.StudentInfo;
import org.tongji.programming.service.MetadataService;
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
        name = "Metadata",
        description = "元数据"
)
@RestController
@RequestMapping("/meta")
public class MetadataController {

    MetadataService metadataService;

    @Autowired
    public MetadataController(MetadataService metadataService) {
        this.metadataService = metadataService;
    }

    @Operation(
            summary = "获取所有标签"
    )
    @GetMapping("/tags")
    public ApiDataResponse<org.tongji.programming.pojo.Tag[]> getTags() {
        return ApiDataResponse.success(metadataService.getTags());
    }
}
