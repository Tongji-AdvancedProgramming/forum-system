package org.tongji.programming.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.tongji.programming.enums.PostLocation;
import org.tongji.programming.pojo.Course;
import org.tongji.programming.pojo.Homework;
import org.tongji.programming.pojo.Term;

/**
 * 板块
 * @author cinea
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Board {
    /**
     * ID串
     */
    String id;

    /**
     * 板块位置
     */
    PostLocation location;

    /**
     * 所属课程
     */
    Course course;

    /**
     * 周次
     */
    int week;

    /**
     * 所属作业
     */
    Homework homework;
}
