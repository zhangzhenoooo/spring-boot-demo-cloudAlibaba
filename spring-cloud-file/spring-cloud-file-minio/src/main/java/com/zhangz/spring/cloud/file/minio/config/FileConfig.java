package com.zhangz.spring.cloud.file.minio.config;

import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class FileConfig {
    @Value("${file.minio.accessKey}")
    private String minioAccessKey;
    @Value("${file.minio.bucketName}")
    private String minioBucketName;
    @Value("${file.minio.region}")
    private String minioRegion;
    @Value("${file.minio.secretKey}")
    private String minioSecretKey;

    @Bean
    public MinioClient minioClient() {
        MinioClient minioClient = MinioClient.builder().endpoint(minioRegion).credentials(minioAccessKey, minioSecretKey).build();
        createBucket(minioClient, minioBucketName);
        minioClient.setTimeout(5 * 1000, 10 * 1000, 3 * 1000);
        return minioClient;
    }

    /**
     * 初始化bucket
     */
    private void createBucket(MinioClient minioClient, String bucketName) {
        boolean isExist = false;
        try {
            BucketExistsArgs bucketExistsArgs = BucketExistsArgs.builder().bucket(bucketName).build();
            isExist = minioClient.bucketExists(bucketExistsArgs);
            if (!isExist) {
                MakeBucketArgs makeBucketArgs = MakeBucketArgs.builder().bucket(bucketName).build();
                minioClient.makeBucket(makeBucketArgs);
            }
        } catch (Exception e) {
            log.error("创建桶【{}】失败", bucketName, e);
        }
    }

    public String getMinioBucketName() {
        return minioBucketName;
    }

}
