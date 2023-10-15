package org.tongji.programming.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.tongji.programming.pojo.Student;
import org.tongji.programming.pojo.StudentInfo;

/**
 * @author cinea
 */
public interface StudentInfoMapper extends BaseMapper<StudentInfo> {
    Student getStudentDefaultInfo(@Param("stuNo") String stuNo);
}
