package com.zhangz.demo.spring.cloud.platform.dto;

/*
 * @Author：zhangz
 * 
 * @Package：com.zhangz.demo.spring.cloud.platform.dto
 * 
 * @Project：spring-cloud
 * 
 * @name：FileUploadRep
 * 
 * @Date：2024/1/11 14:53
 * 
 * @Filename：FileUploadRep
 */

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FileUploadRep implements Serializable {
    private String stageUrl;
    private String preViewUrl;
}
