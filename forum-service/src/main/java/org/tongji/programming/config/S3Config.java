package org.tongji.programming.config;


import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author cineazhan
 */
@Configuration
public class S3Config {
    @Value("${forum.s3.endpoint}")
    private String endpoint;

    @Value("${forum.s3.bucket}")
    private String bucket;

    @Value("${forum.s3.accessKey}")
    private String accessKey;

    @Value("${forum.s3.secretKey}")
    private String secretKey;

    @Bean
    public MinioClient minioClient() {
        return MinioClient.builder()
                .endpoint(endpoint)
                .credentials(accessKey, secretKey)
                .build();
    }
}
