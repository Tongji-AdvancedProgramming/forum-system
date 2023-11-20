package org.tongji.programming.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.tongji.programming.dto.ApiDataResponse;
import org.tongji.programming.dto.ApiResponse;
import org.tongji.programming.pojo.Post;
import org.tongji.programming.service.PostService;

import java.security.Principal;
import java.util.List;

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
        if (result != null && result.startsWith("Success-")) {
            var id = result.substring(8);
            return ApiDataResponse.success(id);
        } else {
            return ApiResponse.fail(4000, result);
        }
    }

    @Secured("ROLE_USER")
    @Operation(
            summary = "列出帖子"
    )
    @GetMapping
    public ApiDataResponse<List<Post>> listPost(
            Principal principal,
            @Parameter(description = "板块id") @RequestParam String boardId,
            @Parameter(description = "分页: 页面大小") @RequestParam(defaultValue = "20") int pageSize,
            @Parameter(description = "分页: 页面编号") @RequestParam(defaultValue = "1") int pageIndex
    ) {
        return ApiDataResponse.success(postService.getPosts(boardId, false));
    }


    @Autowired
    public void setPostService(PostService postService) {
        this.postService = postService;
    }
}
