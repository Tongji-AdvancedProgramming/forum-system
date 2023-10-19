package org.tongji.programming.service.impl;

import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.transfer.TransferManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.tongji.programming.service.UploadService;

import java.io.InputStream;
import java.util.Date;

/**
 * @author cinea
 */
@Service
public class UploadServiceImpl implements UploadService {

    @Value("${forum.forum.cos-bucket}")
    private String bucket;

    @Value("${forum.forum.cos-prefix}")
    private String prefix;

    @Value("${forum.forum.cos-result-baseurl}")
    private String baseUrl;

    TransferManager transferManager;

    @Autowired
    public void setTransferManager(TransferManager transferManager) {
        this.transferManager = transferManager;
    }

    @Override
    public String uploadImage(String uploaderId, InputStream stream, String suffix, String contentType) {
        long timeStamp = new Date().getTime();
        String key = String.format("%s/%s/%d.%s", prefix, uploaderId, timeStamp, suffix);

        var meta = new ObjectMetadata();
        meta.setContentType(contentType);

        var putObjRequest = new PutObjectRequest(bucket, key, stream, meta);
        var upload = transferManager.upload(putObjRequest);

        try {
            var result = upload.waitForUploadResult();
            Assert.notNull(result.getKey(), "Upload Failed");
            return baseUrl + result.getKey();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
