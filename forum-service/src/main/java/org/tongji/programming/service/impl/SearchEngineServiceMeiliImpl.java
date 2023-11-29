package org.tongji.programming.service.impl;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.meilisearch.sdk.Client;
import com.meilisearch.sdk.exceptions.MeilisearchException;
import lombok.SneakyThrows;
import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.HtmlCleaner;
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
    static HtmlCleaner htmlCleaner;

    static {
        CleanerProperties cleanerProps = new CleanerProperties();
        cleanerProps.setPruneTags("script,style,pre,code");
        htmlCleaner = new HtmlCleaner(cleanerProps);
    }

    @Value("${forum.search-engine.post}")
    String postIndex;
    Client meiliClient;
    PostMapper postMapper;
    Deque<Integer> postDeque = new LinkedList<>();

    @Autowired
    public SearchEngineServiceMeiliImpl(Client meiliClient, PostMapper postMapper) {
        this.meiliClient = meiliClient;
        this.postMapper = postMapper;
    }

    void preProcessPost(Post post) {
        // 去除标签数据
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

        // 清理content中的HTML标签和代码块
        post.setPostContent(
                htmlCleaner.clean(post.getPostContent()).getText().toString()
        );
    }

    // every 30 seconds
    @Scheduled(cron = "*/30 * * * * ?")
    void updatePosts() throws MeilisearchException {
        var addPosts = new ArrayList<Post>();
        while (!postDeque.isEmpty()) {
            var postId = postDeque.poll();
            var post = postMapper.selectById(postId);
            if (post == null) {
                continue;
            }

            if ("1".equals(post.getPostIsDel())) {
                meiliClient.index(postIndex).deleteDocument(postId.toString());
                continue;
            }

            preProcessPost(post);

            addPosts.add(post);
        }
        var addPostsDocument = JSON.toJSONString(addPosts);
        meiliClient.index(postIndex).updateDocumentsInBatches(addPostsDocument);
    }

    @Override
    public void addPost(Integer postId) {
        postDeque.add(postId);
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
