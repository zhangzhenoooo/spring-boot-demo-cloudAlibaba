package com.zhangz.spring.cloud.file.minio.dto;

import cn.hutool.core.lang.Pair;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Set;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class TaskInfoDTO {

    /**
     * 是否完成上传（是否已经合并分片）
     */
    private boolean finished;

    /**
     * 文件地址
     */
    private String path;

    /**
     * 上传记录
     */
    private TaskRecordDTO taskRecord;

    private String objectKey;

    private String bucketName;

    private String uploadId;
    // 分片上传链接
    Set<Pair<Integer,String>> signUploadUrs;

}
