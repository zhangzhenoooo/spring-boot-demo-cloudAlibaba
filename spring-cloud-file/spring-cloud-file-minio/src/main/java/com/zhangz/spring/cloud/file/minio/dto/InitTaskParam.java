package com.zhangz.spring.cloud.file.minio.dto;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

 

@Data
@ToString
@Accessors(chain = true)
public class InitTaskParam {

    /**
     * 文件唯一标识(MD5)
     */
     private String identifier;

    /**
     * 文件大小（byte）
     */
     private Long totalSize;

    /**
     * 分片大小（byte）
     */
     private Long chunkSize;

    /**
     * 文件名称
     */
     private String fileName;
}
