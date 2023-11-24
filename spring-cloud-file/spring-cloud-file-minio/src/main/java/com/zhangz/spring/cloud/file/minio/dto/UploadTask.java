package com.zhangz.spring.cloud.file.minio.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)

public class UploadTask implements Serializable {
    private String id;

    private String uploadId;

    private String fileIdentifier;

    private String fileName;

    private String bucketName;

    private String objectKey;

    private Long totalSize;

    private Long chunkSize;

    private int chunkNum;

}
