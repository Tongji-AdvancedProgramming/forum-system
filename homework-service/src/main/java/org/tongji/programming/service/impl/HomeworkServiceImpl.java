package org.tongji.programming.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.minio.MinioClient;
import io.minio.StatObjectArgs;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.tongji.programming.enums.PostLocation;
import org.tongji.programming.helper.Md5Helper;
import org.tongji.programming.mapper.HomeworkMapper;
import org.tongji.programming.mapper.HomeworkUploadedMapper;
import org.tongji.programming.pojo.Homework;
import org.tongji.programming.pojo.HomeworkUploaded;
import org.tongji.programming.service.BoardService;
import org.tongji.programming.service.HomeworkService;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * @author cinea
 */
@Service
public class HomeworkServiceImpl implements HomeworkService {
    private final HomeworkMapper homeworkMapper;

    private final HomeworkUploadedMapper homeworkUploadedMapper;

    private final BoardService boardService;

    private final MinioClient minioClient;

    @Value("${forum.s3.bucket}")
    private String bucket;

    @Value("${forum.s3.prefix.homework-upload}")
    private String prefix;

    @Value("${forum.s3.baseUrl}")
    private String baseUrl;

    public HomeworkServiceImpl(HomeworkMapper homeworkMapper, HomeworkUploadedMapper homeworkUploadedMapper, BoardService boardService, MinioClient minioClient) {
        this.homeworkMapper = homeworkMapper;
        this.homeworkUploadedMapper = homeworkUploadedMapper;
        this.boardService = boardService;
        this.minioClient = minioClient;
    }

    @Override
    public Homework getHomework(String term, Integer hwId) {
        return homeworkMapper.selectOne(
                new QueryWrapper<Homework>().eq("hw_term", term).eq("hw_id", hwId)
        );
    }

    @Override
    public List<HomeworkUploaded> getHomeworkUploaded(String boardId, boolean withHidden) {
        var board = boardService.parseId(boardId);

        if (board.getLocation() == PostLocation.WEEKLY) {
            return homeworkUploadedMapper.selectHomeworkUploadedWeekly(board.getCourse().getCourseTerm(), board.getCourse().getCourseCode(), board.getWeek(), withHidden);
        }

        return new ArrayList<>(0);
    }

    @SneakyThrows
    @Override
    public boolean postHomework(HomeworkUploaded homeworkUploaded, File file, String contentType, String filename, Long fileSize) {
        var fileMd5 = Md5Helper.getMD5(new BufferedInputStream(new FileInputStream(file)));
        var objectName = prefix + '/' + homeworkUploaded.getHwupId() + '/' + filename;

        homeworkUploaded.setHwupFilemd5(fileMd5);
        homeworkUploaded.setHwupFilename(objectName);

        var putObjArgs = io.minio.PutObjectArgs.builder()
                .bucket(bucket)
                .object(objectName)
                .stream(new BufferedInputStream(new FileInputStream(file)), fileSize, -1)
                .contentType(contentType)
                .build();
        minioClient.putObject(putObjArgs);

        homeworkUploadedMapper.insert(homeworkUploaded);

        return true;
    }

    @Override
    public String postHomework(HomeworkUploaded homeworkUploaded) {

        // 如果出现文件名，检查正确性，并下载其内容和计算MD5
        if (homeworkUploaded.getHwupFilename() != null) {
            var objectName = homeworkUploaded.getHwupFilename();
            if (objectName.startsWith("forum/")) {
                objectName = objectName.substring(5);
            }
            try {
                var stat = minioClient.statObject(
                        StatObjectArgs.builder()
                                .bucket(bucket)
                                .object(objectName)
                                .build()
                );
                homeworkUploaded.setHwupFilemd5(stat.etag());
            } catch (Exception e) {
                return "无法从存储系统中寻找到文件，请检查文件名填写是否正确";
            }
        }

        var keyWrapper = new QueryWrapper<HomeworkUploaded>()
                .eq("hwup_term", homeworkUploaded.getHwupTerm())
                .eq("hwup_ccode", homeworkUploaded.getHwupCcode())
                .eq("hwup_id", homeworkUploaded.getHwupId());
        if (homeworkUploadedMapper.selectCount(keyWrapper) == 0) {
            homeworkUploadedMapper.insert(homeworkUploaded);
        } else {
            homeworkUploadedMapper.update(homeworkUploaded, keyWrapper);
        }
        return "";
    }
}
