package org.tongji.programming.service.impl;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONWriter;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.meilisearch.sdk.Client;
import com.meilisearch.sdk.exceptions.MeilisearchException;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.tongji.programming.mapper.PostMapper;
import org.tongji.programming.pojo.Post;
import org.tongji.programming.service.SearchEngineService;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

/**
 * @author cinea
 */
@Service
public class SearchEngineServiceMeiliImpl implements SearchEngineService {
    @Value("${forum.search-engine.post}")
    String postIndex;

    Client meiliClient;
    PostMapper postMapper;

    @Autowired
    public SearchEngineServiceMeiliImpl(Client meiliClient, PostMapper postMapper) {
        this.meiliClient = meiliClient;
        this.postMapper = postMapper;
    }

    //    Deque<Integer> postAddDeque = new LinkedList<>();
    Deque<Integer> postUpdateDeque = new LinkedList<>();

    // every 30 seconds
    @Scheduled(cron = "*/30 * * * * ?")
    void updatePosts() throws MeilisearchException {
        var addPosts = new ArrayList<Post>();
        while (!postUpdateDeque.isEmpty()) {
            var postId = postUpdateDeque.poll();
            var post = postMapper.selectById(postId);
            if (post == null) {
                continue;
            }

            if ("1".equals(post.getPostIsDel())) {
                meiliClient.index(postIndex).deleteDocument(postId.toString());
                continue;
            }

            post.setPostTag01(null);
            post.setPostTag02(null);
            post.setPostTag03(null);
            post.setPostTag04(null);
            post.setPostTag05(null);
            post.setPostTag06(null);
            post.setPostTag07(null);
            post.setPostTag08(null);
            post.setPostTag09(null);
            post.setPostTag10(null);

            addPosts.add(post);
        }
        var addPostsDocument = JSON.toJSONString(addPosts);
        meiliClient.index(postIndex).updateDocumentsInBatches(addPostsDocument);
    }

    @Override
    public void addPost(Integer postId) {
        postUpdateDeque.add(postId);
    }

    @Override
    public void updatePost(Integer postId) {
        postUpdateDeque.add(postId);
    }

    @SneakyThrows
    @Override
    public void updateAllPost() {
        var posts = postMapper.selectList(
                new QueryWrapper<Post>().select("post_id")
        );
        for (var post : posts) {
            addPost(post.getPostId());
        }
        updatePosts();
    }
}
