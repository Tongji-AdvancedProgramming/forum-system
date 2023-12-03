package org.tongji.programming.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.tongji.programming.dto.ApiDataResponse;
import org.tongji.programming.dto.ApiResponse;
import org.tongji.programming.dto.ListPostResult;
import org.tongji.programming.dto.PostService.GetPostResponse;
import org.tongji.programming.helper.EncodingHelper;
import org.tongji.programming.helper.RequestInfoHelper;
import org.tongji.programming.service.PostService;
import org.tongji.programming.service.SearchEngineService;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
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
    SearchEngineService searchEngineService;

    @Autowired
    public PostController(PostService postService, SearchEngineService searchEngineService) {
        this.postService = postService;
        this.searchEngineService = searchEngineService;
    }

    @Secured("ROLE_USER")
    @Operation(
            summary = "发布帖子"
    )
    @PostMapping
    public ApiResponse addPost(
            Principal principal,
            HttpServletRequest request,
            @Parameter(description = "板块id") @RequestPart String boardId,
            @Parameter(description = "帖子标题") @RequestPart String title,
            @Parameter(description = "帖子内容") @RequestPart String content
    ) {
        if (EncodingHelper.containsIncompatibleGbkChars(content)) {
            return ApiResponse.fail(4000, "包含GBK编码不兼容的字符");
        }

        var ipAddr = RequestInfoHelper.getClientIpAddr(request);
        var result = postService.addPost(principal.getName(), ipAddr, boardId, title, content);
        if (result != null && result.startsWith("Success-")) {
            var id = result.substring(8);
            return ApiDataResponse.success(id);
        } else {
            return ApiResponse.fail(4000, result);
        }
    }

    @Secured("ROLE_USER")
    @Operation(
            summary = "发送回帖"
    )
    @PostMapping("/reply")
    public ApiResponse addReply(
            Principal principal,
            HttpServletRequest request,
            @RequestPart String postId,
            @RequestPart String replyContent
    ) {
        if (EncodingHelper.containsIncompatibleGbkChars(replyContent)) {
            return ApiResponse.fail(4000, "包含GBK编码不兼容的字符");
        }

        var ipAddr = RequestInfoHelper.getClientIpAddr(request);
        if (postService.addReply(principal.getName(), ipAddr, Integer.valueOf(postId), replyContent) != null) {
            return ApiResponse.success();
        } else {
            return ApiResponse.fail(5000, "发送失败");
        }
    }

    @Secured("ROLE_USER")
    @Operation(
            summary = "编辑帖子或回复"
    )
    @PutMapping
    public ApiResponse editPost(
            Principal principal,
            HttpServletRequest request,
            @Parameter(description = "帖子id") @RequestPart String postId,
            @Parameter(description = "帖子内容") @RequestPart String content
    ) {
        if (EncodingHelper.containsIncompatibleGbkChars(content)) {
            return ApiResponse.fail(4000, "包含GBK编码不兼容的字符");
        }

        var userId = principal.getName();
        var ipAddr = RequestInfoHelper.getClientIpAddr(request);
        var postIdInteger = Integer.valueOf(postId);

        if (postService.ensureEditPermission(userId, postIdInteger)) {
            postService.editPost(userId, ipAddr, postIdInteger, content);
            return ApiResponse.success();
        } else {
            return ApiResponse.fail(4003, "您无权编辑此帖子");
        }
    }

    @Secured("ROLE_TA")
    @Operation(
            summary = "设置帖子标签"
    )
    @PutMapping("/tag")
    public ApiResponse setPostTag(
            Principal principal,
            HttpServletRequest request,
            @Parameter(description = "帖子id") @RequestParam List<Integer> postId,
            @Parameter(description = "标签id") @RequestParam(required = false) int[] tag
    ) {
        // 如果tag为null，则将其设置为一个空数组
        if (tag == null) {
            tag = new int[0];
        }

        var userId = principal.getName();
        var ipAddr = RequestInfoHelper.getClientIpAddr(request);

        for (var pid : postId) {
            if (!postService.ensureEditPermission(userId, pid)) {
                return ApiResponse.fail(4003, "请求中存在无权设置标签的帖子");
            }
        }

        int[] finalTag = tag;
        postId.forEach(id -> postService.setPostTag(userId, ipAddr, id, finalTag));
        return ApiResponse.success();
    }

    @Secured("ROLE_TA")
    @Operation(
            summary = "设置帖子优先级"
    )
    @PutMapping("/priority")
    public ApiResponse setPostPriority(
            Principal principal,
            HttpServletRequest request,
            @Parameter(description = "帖子id") @RequestParam List<Integer> postId,
            @Parameter(description = "优先级") @RequestParam int priority
    ) {
        var userId = principal.getName();
        var ipAddr = RequestInfoHelper.getClientIpAddr(request);

        if (priority < 0 || priority > 9) {
            return ApiResponse.fail(4000, "优先级只能为0~9");
        }

        for (var pid : postId) {
            if (!postService.ensureEditPermission(userId, pid)) {
                return ApiResponse.fail(4003, "请求中存在无权设置优先级的帖子");
            }
        }

        postId.forEach(id -> postService.setPostPriority(userId, ipAddr, id, priority));
        return ApiResponse.success();
    }

    @Secured("ROLE_USER")
    @Operation(
            summary = "删除帖子或回复"
    )
    @DeleteMapping
    public ApiResponse deletePost(
            Principal principal,
            HttpServletRequest request,
            @Parameter(description = "帖子id") @RequestParam List<Integer> postId
    ) {
        var userId = principal.getName();
        var ipAddr = RequestInfoHelper.getClientIpAddr(request);

        for (var pid : postId) {
            if (!postService.ensureEditPermission(userId, pid)) {
                return ApiResponse.fail(4003, "请求中存在无权删除的帖子");
            }
        }

        postId.forEach(id -> postService.deletePost(userId, ipAddr, id));
        return ApiResponse.success();
    }

    @Secured("ROLE_USER")
    @Operation(
            summary = "列出帖子"
    )
    @GetMapping("/list")
    public ApiDataResponse<ListPostResult> listPost(
            Principal principal,
            @Parameter(description = "板块id") @RequestParam String boardId,
            @Parameter(description = "标签序号") @RequestParam String tags,
            @Parameter(description = "是否显示隐藏帖子") @RequestParam(defaultValue = "false") boolean showHidden,
            @Parameter(description = "分页: 页面大小") @RequestParam(defaultValue = "20") int pageSize,
            @Parameter(description = "分页: 页面编号") @RequestParam(defaultValue = "1") int pageIndex
    ) {
        var userId = principal.getName();
        if (!postService.ensureQueryPermission(userId, boardId)) {
            return ApiDataResponse.dataFail(4003, "您无权查看本板块");
        }

        tags = URLDecoder.decode(tags, StandardCharsets.UTF_8);
        return ApiDataResponse.success(
                ListPostResult.builder()
                        .posts(postService.getPosts(boardId, tags, showHidden, false, false, pageSize, pageIndex))
                        .totalCount(postService.getPostsTotalCount(boardId, tags, showHidden, false))
                        .build()
        );
    }

    @Secured("ROLE_USER")
    @Operation(
            summary = "显示帖子（含回帖等）",
            description = "用于在帖子页中使用，包含回帖等内容"
    )
    @GetMapping
    public ApiDataResponse<GetPostResponse> getPost(
            Principal principal,
            Authentication authentication,
            @RequestParam Integer postId,
            @RequestParam(defaultValue = "false") boolean showHidden
    ) {
        if (showHidden) {
            var authorities = authentication.getAuthorities();
            if (authorities.stream().noneMatch(a -> "ROLE_TA".equals(a.getAuthority()))) {
                var resp = new ApiDataResponse<GetPostResponse>();
                resp.setCode(4003);
                resp.setMsg("您无权查看隐藏帖子");
                return resp;
            }
        }

        var userId = principal.getName();
        if (!postService.ensureQueryPermission(userId, postId)) {
            return ApiDataResponse.dataFail(4003, "您无权查看本帖子");
        }

        return ApiDataResponse.success(postService.getPost(postId, showHidden));
    }

    @Secured("ROLE_USER")
    @Operation(
            summary = "查询帖子的父亲帖子",
            description = "用于跳转的时候准确跳转到父亲帖子"
    )
    @GetMapping("/parent")
    public ApiDataResponse<Integer> getParentPost(
            @RequestParam Integer postId
    ) {
        return ApiDataResponse.success(postService.getParentPost(postId));
    }

    @Secured("ROLE_TA")
    @Operation(
            summary = "立即刷新搜索引擎缓存"
    )
    @GetMapping("/flush")
    public ApiResponse flush(
    ) {
        searchEngineService.updateAllPost();
        return ApiResponse.success();
    }
}
