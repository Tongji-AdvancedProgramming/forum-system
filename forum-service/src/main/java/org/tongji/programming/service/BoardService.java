package org.tongji.programming.service;

import org.tongji.programming.dto.Board;
import org.tongji.programming.pojo.Post;

import java.util.List;

/**
 * 板块相关服务
 *
 * @author cineazhan
 */
public interface BoardService {
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
