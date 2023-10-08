package org.tongji.programming.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.tongji.programming.pojo.Student;

/**
 * 登录结果
 * @author cinea
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {
    private Student data;
    private boolean infoCompletionNeeded;
    private boolean success;
}
