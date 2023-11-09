package org.tongji.programming.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.tongji.programming.dto.ApiResponse;
import org.tongji.programming.service.PostService;

import java.security.Principal;

/**
 * 帖子控制器类
 *
 * @author cinea
 */
@Tag(
        name = "Post",
        description = "论坛帖子"
)
@RestController
@RequestMapping("/post")
public class PostController {

    PostService postService;

    @Secured("ROLE_USER")
    @Operation(
            summary = "发布帖子"
    )
    @PostMapping
    public ApiResponse addPost(
            Principal principal,
            @Parameter(description = "板块id") @RequestPart String boardId,
            @Parameter(description = "帖子标题") @RequestPart String title,
            @Parameter(description = "帖子内容") @RequestPart String content
    ) {
        var result = postService.addPost(principal.getName(), boardId, title, content);
        if (result == null) {
            return ApiResponse.success();
        } else {
            return ApiResponse.fail(4000, result);
        }
    }


    @Autowired
    public void setPostService(PostService postService) {
        this.postService = postService;
    }
}
