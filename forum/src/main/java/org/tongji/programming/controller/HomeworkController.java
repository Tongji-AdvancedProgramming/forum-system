package org.tongji.programming.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.tongji.programming.dto.ApiDataResponse;
import org.tongji.programming.dto.ApiResponse;
import org.tongji.programming.dto.PostService.GetPostResponse;
import org.tongji.programming.pojo.Homework;
import org.tongji.programming.pojo.HomeworkUploaded;
import org.tongji.programming.service.HomeworkService;

import java.util.List;

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

    @Secured("ROLE_USER")
    @Operation(
            summary = "获取指定板块的已上传作业的信息"
    )
    @GetMapping("/uploaded")
    public ApiDataResponse<List<HomeworkUploaded>> getHomeworkUploaded(
            Authentication authentication,
            @RequestParam String boardId,
            @RequestParam boolean withHidden
    ) {
        if (withHidden) {
            var authorities = authentication.getAuthorities();
            if (authorities.stream().noneMatch(a -> "ROLE_ADMIN".equals(a.getAuthority()))) {
                var resp = new ApiDataResponse<List<HomeworkUploaded>>();
                resp.setCode(4003);
                resp.setMsg("您无权查看隐藏的已上传作业");
                return resp;
            }
        }

        return ApiDataResponse.success(homeworkService.getHomeworkUploaded(boardId, withHidden));
    }

    @Secured("ROLE_ADMIN")
    @Operation(
            summary = "添加或更新已上传作业"
    )
    @PostMapping("/uploaded")
    public ApiResponse postHomework(
            @RequestBody HomeworkUploaded homeworkUploaded
    ) {
        var result = homeworkService.postHomework(homeworkUploaded);
        if (result.isEmpty()) {
            return ApiResponse.success();
        } else {
            return ApiResponse.fail(5000, result);
        }
    }

}
