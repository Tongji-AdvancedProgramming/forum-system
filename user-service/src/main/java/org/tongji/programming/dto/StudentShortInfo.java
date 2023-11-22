package org.tongji.programming.dto;

import lombok.Data;

/**
 * 前端内使用的简短用户信息
 *
 * @author cinea
 */
@Data
public class StudentShortInfo {
    private String nickName;
    private String realName;
    private String description;
    private String stuNo;
    private String major;
    private int role;
}
