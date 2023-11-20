package org.tongji.programming.service;

import org.tongji.programming.pojo.StudentInfo;

import java.io.InputStream;

/**
 * @author cineazhan
 */
public interface StudentInfoService {
    StudentInfo getByStuNo(String stuNo);

    int setByStuNo(StudentInfo studentInfo);

    int uploadStudentAvatar(String stuNo, InputStream fileStream, String fileType, long fileSize);
}
