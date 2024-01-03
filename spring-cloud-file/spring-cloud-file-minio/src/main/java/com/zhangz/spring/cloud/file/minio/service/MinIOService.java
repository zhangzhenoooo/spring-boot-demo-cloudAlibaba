package com.zhangz.spring.cloud.file.minio.service;

 

import com.zhangz.spring.cloud.file.minio.dto.InitTaskParam;
import com.zhangz.spring.cloud.file.minio.dto.TaskInfoDTO;

import java.io.IOException;
 import java.util.Map;

public interface MinIOService {

    void upload(byte[] bytes, String objectId) throws IOException;

    byte[] downByPath(String filePath) throws Exception;

    boolean isExist(String path) throws Exception;

    boolean remove(String path) throws Exception;

    TaskInfoDTO initTask(InitTaskParam param);

    TaskInfoDTO getTaskInfo(String identifier);

    TaskInfoDTO getByIdentifier(String identifier);

    String genPreSignUploadUrl(String bucketName, String objectKey, Map<String, String> params);

    void merge(String identifier);
}
