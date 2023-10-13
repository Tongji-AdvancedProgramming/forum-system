package org.tongji.programming.service;

import org.tongji.programming.pojo.StudentInfo;

/**
 * @author cineazhan
 */
public interface StudentInfoService {
    StudentInfo getByStuNo(String stuNo);

    int setByStuNo(StudentInfo studentInfo);
}
