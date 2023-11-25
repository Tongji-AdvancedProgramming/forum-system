package org.tongji.programming.service.impl;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.tongji.programming.dto.StudentShortInfo;
import org.tongji.programming.mapper.StudentInfoMapper;
import org.tongji.programming.pojo.StudentInfo;
import org.tongji.programming.service.StudentInfoService;

import java.io.InputStream;
import java.util.concurrent.ExecutionException;

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

    @Value("${forum.s3.prefix.cardBg}")
    private String s3CardBgPrefix;

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
            defaultOne.setDescription("");
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
    public void uploadStudentAvatar(String stuNo, InputStream fileStream, String fileType, long fileSize) {

        uploadStudentAssets(stuNo, fileStream, fileType, fileSize, s3AvatarPrefix);

    }

    @Override
    public void uploadStudentCardBackground(String stuNo, InputStream fileStream, String fileType, long fileSize) {

        uploadStudentAssets(stuNo, fileStream, fileType, fileSize, s3CardBgPrefix);

    }

    private void uploadStudentAssets(String stuNo, InputStream fileStream, String fileType, long fileSize, String prefix) {
        String key = String.format("%s/%s", prefix, stuNo);
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
    }

    private final LoadingCache<String, StudentShortInfo> shortInfoCache = CacheBuilder.newBuilder()
            .maximumSize(150)
            .build(new CacheLoader<>() {
                @Override
                public @NotNull StudentShortInfo load(@NotNull String key) {
                    return studentInfoMapper.getStudentShortInfo(key);
                }
            });

    @Override
    public void setNickName(String userId, String newNickName) {
        var si = new StudentInfo();
        si.setStuNo(userId);
        si.setNickname(newNickName);
        studentInfoMapper.updateById(si);
        shortInfoCache.refresh(userId);
    }

    @Override
    public void setSignature(String userId, String newSignature) {
        var si = new StudentInfo();
        si.setStuNo(userId);
        si.setDescription(newSignature);
        studentInfoMapper.updateById(si);
        shortInfoCache.refresh(userId);
    }

    @Override
    public StudentShortInfo getStudentShortInfo(String id) {
        try {
            return shortInfoCache.get(id);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}
