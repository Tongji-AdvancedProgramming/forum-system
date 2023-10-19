package org.tongji.programming.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

/**
 * @author cinea
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse {
    private int code;
    private String msg;

    public static @NotNull ApiResponse success() {
        return ApiResponse.builder().code(10000).msg("成功").build();
    }

    public static @NotNull ApiResponse fail(int code, String msg) {
        return ApiResponse.builder().code(code).msg(msg).build();
    }
}
