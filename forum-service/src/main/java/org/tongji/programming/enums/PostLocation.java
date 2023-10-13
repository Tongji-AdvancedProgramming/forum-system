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
    COURSE
}
