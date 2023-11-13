package org.tongji.programming.dto;

import lombok.*;
import org.jetbrains.annotations.NotNull;

/**
 * @author cinea
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ApiDataResponse<T> extends ApiResponse {
    private T data;

    public static <T> @NotNull ApiDataResponse<T> success(T data) {
        var r = new ApiDataResponse<T>();
        r.setCode(10000);
        r.setMsg("成功");
        r.setData(data);
        return r;
    }
}
