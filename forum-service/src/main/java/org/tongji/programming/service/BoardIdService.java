package org.tongji.programming.service;

import org.tongji.programming.dto.Board;

/**
 * 系统使用一个Board ID来标识板块并筛选展示的帖子。
 * <p>
 * 这个服务用来解析这些板块的信息
 *
 * @author cineazhan
 */
public interface BoardIdService {
    /**
     * 解析Board Id（但不获取数据）
     */
    Board parseId(String id);

    /**
     * 解析Board Id，并获取数据
     */
    Board parseIdAndFetch(String id);

    /**
     * 由数据库中的hwId生成所属的板块Id
     */
    String generateIdFromHwId(String hwId, String term, String courseCode);
}
