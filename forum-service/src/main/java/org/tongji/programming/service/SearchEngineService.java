package org.tongji.programming.service;

/**
 * 搜索引擎的相关方法
 *
 * @author cinea
 */
public interface SearchEngineService {
    /**
     * 向增加队列中插入一条Post
     */
    void addPost(Integer postId);

    /**
     * 【立即更新】插入全部帖子
     */
    void updateAllPost();
}

