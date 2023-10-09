package org.tongji.programming.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.google.common.collect.ImmutableList;
import org.apache.ibatis.annotations.Param;
import org.tongji.programming.pojo.Course;
import org.tongji.programming.pojo.Homework;

import java.util.List;

/**
 * @author cinea
 */
public interface HomeworkMapper extends BaseMapper<Homework> {
}
