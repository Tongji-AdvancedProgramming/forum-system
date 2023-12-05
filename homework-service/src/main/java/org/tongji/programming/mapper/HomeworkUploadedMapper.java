package org.tongji.programming.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.tongji.programming.pojo.Homework;
import org.tongji.programming.pojo.HomeworkUploaded;

import java.util.List;

/**
 * @author cineazhan
 */
public interface HomeworkUploadedMapper extends BaseMapper<HomeworkUploaded> {
    List<HomeworkUploaded> selectHomeworkUploadedWeekly(@Param("term") String term, @Param("courseCode") String courseCode, @Param("week") Integer week, @Param("withHidden") boolean withHidden);
}
