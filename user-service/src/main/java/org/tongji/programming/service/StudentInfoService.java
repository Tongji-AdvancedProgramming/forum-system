package org.tongji.programming.service;

import org.tongji.programming.dto.StudentShortInfo;
import org.tongji.programming.pojo.StudentInfo;

import java.io.InputStream;

/**
 * @author cineazhan
 */
public interface StudentInfoService {
    StudentInfo getByStuNo(String stuNo);

    int setByStuNo(StudentInfo studentInfo);

    void uploadStudentAvatar(String stuNo, InputStream fileStream, String fileType, long fileSize);

    void uploadStudentCardBackground(String stuNo, InputStream fileStream, String fileType, long fileSize);

    void setNickName(String userId, String newNickName);

    void setSignature(String userId, String newSignature);

    StudentShortInfo getStudentShortInfo(String id);
}
