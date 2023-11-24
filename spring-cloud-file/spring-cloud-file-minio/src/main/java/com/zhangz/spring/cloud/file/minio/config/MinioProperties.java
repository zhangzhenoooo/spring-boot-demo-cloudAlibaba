package com.zhangz.spring.cloud.file.minio.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class MinioProperties {

    @Value("${file.minio.region}")
    private String endpoint;

    @Value("${file.minio.accessKey}")
    private String accessKey;

    @Value("${file.minio.secretKey}")
    private String accessSecret;

    @Value("${file.minio.bucketName}")
    private String bucket;
}
