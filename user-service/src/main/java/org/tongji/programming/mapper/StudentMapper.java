package org.tongji.programming.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.tongji.programming.pojo.Student;

/**
 * @author cinea
 */
public interface StudentMapper extends BaseMapper<Student> {
    Student selectIdPassLevel(@Param("id") String id);

    Student selectCourses(@Param("id") String id);
}
