package org.tongji.programming.service.impl;

import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import org.imgscalr.Scalr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.tongji.programming.helper.ImageHelper;
import org.tongji.programming.mapper.StudentInfoMapper;
import org.tongji.programming.pojo.StudentInfo;
import org.tongji.programming.service.StudentInfoService;

import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

/**
 * @author cineazhan
 */
@Service
public class StudentInfoServiceImpl implements StudentInfoService {

    StudentInfoMapper studentInfoMapper;

    MinioClient minioClient;
    /**
     * 新用户的默认头像
     */
    @Value("${forum.user.default-avatar}")
    private String defaultAvatar;

    @Value("${forum.s3.bucket}")
    private String s3Bucket;

    @Value("${forum.s3.prefix.avatar}")
    private String s3AvatarPrefix;

    @Value("${forum.s3.baseUrl}")
    private String s3BaseUrl;

    @Autowired
    public StudentInfoServiceImpl(StudentInfoMapper studentInfoMapper, MinioClient minioClient) {
        this.studentInfoMapper = studentInfoMapper;
        this.minioClient = minioClient;
    }

    @Override
    public StudentInfo getByStuNo(String stuNo) {
        var result = studentInfoMapper.selectById(stuNo);
        // 如果不存在，就生成一个默认的补上。
        if (result == null) {
            var student = studentInfoMapper.getStudentDefaultInfo(stuNo);
            if (student == null) {
                return null;
            }
            var defaultOne = new StudentInfo();
            defaultOne.setStuNo(stuNo);
            defaultOne.setNickname(student.getStuName());
            defaultOne.setAvatar(defaultAvatar);
            studentInfoMapper.insert(defaultOne);
            result = defaultOne;
        }
        return result;
    }

    @Override
    public int setByStuNo(StudentInfo studentInfo) {
        return studentInfoMapper.updateById(studentInfo);
    }

    @Override
    public int uploadStudentAvatar(String stuNo, InputStream fileStream, String fileType, long fileSize) {

        String key = String.format("%s/%s", s3AvatarPrefix, stuNo);
        var putObjArgs = PutObjectArgs.builder()
                .bucket(s3Bucket)
                .object(key)
                .stream(fileStream, fileSize, -1)
                .contentType(fileType)
                .build();

        try {
            minioClient.putObject(putObjArgs);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return 0;
    }
}
