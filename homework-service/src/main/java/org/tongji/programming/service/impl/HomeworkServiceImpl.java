package org.tongji.programming.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;
import org.tongji.programming.mapper.HomeworkMapper;
import org.tongji.programming.pojo.Homework;
import org.tongji.programming.service.HomeworkService;

/**
 * @author cinea
 */
@Service
public class HomeworkServiceImpl implements HomeworkService {
    private final HomeworkMapper homeworkMapper;

    public HomeworkServiceImpl(HomeworkMapper homeworkMapper) {
        this.homeworkMapper = homeworkMapper;
    }

    @Override
    public Homework getHomework(String term, Integer hwId) {
        return homeworkMapper.selectOne(
                new QueryWrapper<Homework>().eq("hw_term", term).eq("hw_id", hwId)
        );
    }
}
