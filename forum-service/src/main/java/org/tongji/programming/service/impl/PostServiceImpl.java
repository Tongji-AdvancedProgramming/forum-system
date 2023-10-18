package org.tongji.programming.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tongji.programming.mapper.PostMapper;
import org.tongji.programming.pojo.Post;
import org.tongji.programming.service.PostService;

import java.util.List;

/**
 * @author cinea
 */
@Service
public class PostServiceImpl implements PostService {

    private PostMapper postMapper;

    @Override
    public List<Post> getCoursePosts(String term, String courseCode, boolean showHidden) {
        return postMapper.getCoursePosts(term, courseCode, showHidden);
    }

    @Override
    public List<Post> getWeekPosts(String term, String courseCode, int week, boolean showHidden) {
        return postMapper.getWeekPosts(term, courseCode, week, showHidden);
    }

    @Override
    public List<Post> getHomeworkPosts(String term, String courseCode, int homeworkId, boolean showHidden) {
        return postMapper.getHomeworkPosts(term, courseCode, homeworkId, showHidden);
    }

    @Autowired
    public void setPostMapper(PostMapper postMapper) {
        this.postMapper = postMapper;
    }
}
