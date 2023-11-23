package com.zhangz.springbootdemocloudalibabafile.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author reolfan
 * @date 2018/8/1
 */
@Data
public class InvoiceFile implements Serializable {
    
    private String id;
    private String fileType;

    private byte[] file;

}
