package org.tongji.programming.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.tongji.programming.dto.ApiDataResponse;
import org.tongji.programming.dto.Board;
import org.tongji.programming.pojo.Post;
import org.tongji.programming.service.BoardService;
import org.tongji.programming.service.PostService;

import java.util.List;

/**
 * 板块控制器类
 *
 * @author cinea
 */
@Tag(
        name = "Board",
        description = "论坛板块"
)
@RestController
@RequestMapping("/board")
public class BoardController {

    BoardService boardService;

    PostService postService;

    @Secured("ROLE_USER")
    @Operation(
            summary = "根据板块id获取板块信息"
    )
    @GetMapping
    public ApiDataResponse<Board> getBoardInfo(@RequestParam String id) {
        return ApiDataResponse.success(boardService.parseIdAndFetch(id));
    }

    @Secured("ROLE_USER")
    @Operation(
            summary = "根据板块id获取板块内的帖子s"
    )
    @GetMapping("/post")
    public ApiDataResponse<List<Post>> getBoardPosts(@RequestParam String id) {
        return ApiDataResponse.success(postService.getPosts(id, "[]", true, false, false));
    }

    @Autowired
    public void setBoardIdService(BoardService boardService) {
        this.boardService = boardService;
    }

    @Autowired
    public void setPostService(PostService postService) {
        this.postService = postService;
    }
}
