package org.tongji.programming.service.impl;

import io.minio.MinioClient;
import org.imgscalr.Scalr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.tongji.programming.mapper.StudentInfoMapper;
import org.tongji.programming.pojo.StudentInfo;
import org.tongji.programming.service.StudentInfoService;

import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import java.awt.image.BufferedImage;
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

        BufferedImage sourceImage;

        try {
            sourceImage = ImageIO.read(fileStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        var targetImage = Scalr.resize(sourceImage, Scalr.Mode.AUTOMATIC, 256, 256);

        Iterator<ImageWriter> writers = ImageIO.getImageWritersByFormatName("jpeg");
        ImageWriter writer = writers.next();

        var param = writer.getDefaultWriteParam();
        param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
        param.setCompressionQuality(0.8f);


        return 0;
    }
}
