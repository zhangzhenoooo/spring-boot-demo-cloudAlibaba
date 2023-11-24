package com.zhangz.spring.cloud.file.minio.service;

import com.zhangz.spring.cloud.file.minio.dto.UploadTask;

public interface UploadTaskService {
    
    UploadTask selectByIdentifier(String identifier);

    void save(UploadTask task);
}
