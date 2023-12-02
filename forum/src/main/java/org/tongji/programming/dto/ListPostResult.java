package org.tongji.programming.dto;

import lombok.Builder;
import lombok.Data;
import org.tongji.programming.pojo.Post;

import java.util.List;

/**
 * @author cinea
 */
@Data
@Builder
public class ListPostResult {
    private Integer totalCount;
    private List<Post> posts;
}
