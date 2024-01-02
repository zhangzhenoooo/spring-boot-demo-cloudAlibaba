package com.zhangz.spring.cloud.file.minio.service.impl;

import com.zhangz.spring.cloud.file.minio.dto.UploadTask;
import com.zhangz.spring.cloud.file.minio.service.UploadTaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/*
 *@Author：zhangz
 *@Package：com.zhangz.spring.cloud.file.minio.service.impl
 *@Project：spring-cloud
 *@name：UploadTaskServiceImpl
 *@Date：2024/1/2  11:45
 *@Filename：UploadTaskServiceImpl
 */

@Service
@Slf4j
public class UploadTaskServiceImpl implements UploadTaskService {
    @Override
    public UploadTask selectByIdentifier(String identifier) {
        return null;
    }

    @Override
    public void save(UploadTask task) {

    }
}
