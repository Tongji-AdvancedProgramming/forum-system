package org.tongji.programming.service;

import java.io.InputStream;

/**
 * 上传图片、文件等的服务类
 *
 * @author cinea
 */
public interface UploadService {
    /**
     * 上传图片
     *
     * @param uploaderId    上传者身份标识
     * @param stream        文件流
     * @param contentLength
     * @return 图片URL
     */
    String uploadImage(String uploaderId, InputStream stream, String suffix, String contentType, long contentLength);
}
