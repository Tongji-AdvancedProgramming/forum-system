package org.tongji.programming.dto.PostService;

import lombok.Data;
import org.tongji.programming.pojo.Post;

import java.util.List;

/**
 * 获取帖子请求的返回，包含本帖子、回帖等信息
 *
 * @author cineazhan
 */
@Data
public class GetPostResponse {
    private Post post;
    private List<Post> followedPosts;
}
