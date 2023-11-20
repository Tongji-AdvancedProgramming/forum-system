package org.tongji.programming.service.impl;

import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.tongji.programming.service.UploadService;

import java.io.InputStream;
import java.util.Date;

/**
 * @author cinea
 */
@Service
public class UploadServiceImpl implements UploadService {

    @Value("${forum.s3.bucket}")
    private String bucket;

    @Value("${forum.s3.prefix.upload}")
    private String prefix;

    @Value("${forum.s3.baseUrl}")
    private String baseUrl;

    MinioClient minioClient;

    @Autowired
    public UploadServiceImpl(MinioClient minioClient) {
        this.minioClient = minioClient;
    }

    @Override
    public String uploadImage(String uploaderId, InputStream stream, String suffix, String contentType, long contentLength) {
        long timeStamp = new Date().getTime();
        String key = String.format("%s/%s/%d.%s", prefix, uploaderId, timeStamp, suffix);

        var putObjArgs = PutObjectArgs.builder()
                .bucket(bucket)
                .object(key)
                .stream(stream, contentLength, -1)
                .contentType(contentType)
                .build();

        try {
            minioClient.putObject(putObjArgs);
            return baseUrl + key;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
