package org.tongji.programming.enums;

/**
 * 帖子所在的位置，分为三种：
 * <ol>
 *     <li>第x周的整体问题</li>
 *     <li>第x周的某具体作业</li>
 *     <li>课程的整体问题</li>
 * </ol>
 *
 * @author cineazhan
 */
public enum PostLocation {
    /**
     * 第x周的整体问题
     */
    WEEKLY,
    /**
     * 第x周的某具体作业
     */
    HOMEWORK,
    /**
     * 课程的整体问题
     */
    COURSE,
    /**
     * 周汇总，并非数据库内的存储位置
     */
    WEEK_SUMMARY,
    /**
     * 课程汇总，并非数据库内的存储位置
     */
    COURSE_SUMMARY
}
