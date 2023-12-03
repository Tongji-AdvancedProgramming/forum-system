package org.tongji.programming.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.tongji.programming.dto.ApiDataResponse;
import org.tongji.programming.mapper.HomeworkMapper;
import org.tongji.programming.pojo.Homework;
import org.tongji.programming.service.HomeworkService;
import org.tongji.programming.service.MetadataService;

/**
 * 作业控制器类
 *
 * @author cinea
 */
@Tag(
        name = "Homework",
        description = "作业相关控制器类"
)
@RestController
@RequestMapping("/homework")
public class HomeworkController {

    private final HomeworkService homeworkService;

    public HomeworkController(HomeworkService homeworkService) {
        this.homeworkService = homeworkService;
    }

    @Secured("ROLE_USER")
    @Operation(
            summary = "获取指定作业的信息"
    )
    @GetMapping
    public ApiDataResponse<Homework> getHomework(
            @RequestParam String term,
            @RequestParam Integer hwId
    ) {
        return ApiDataResponse.success(homeworkService.getHomework(term, hwId));
    }
}
