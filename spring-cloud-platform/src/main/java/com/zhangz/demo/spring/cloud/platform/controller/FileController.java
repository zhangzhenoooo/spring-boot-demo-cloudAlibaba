package com.zhangz.demo.spring.cloud.platform.controller;


import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileTypeUtil;
import com.zhangz.demo.spring.cloud.common.api.CommonResult;
import com.zhangz.demo.spring.cloud.common.exception.BussinessException;
import com.zhangz.spring.cloud.file.minio.config.MinioProperties;
import com.zhangz.spring.cloud.file.minio.service.MinIOService;
import com.zhangz.spring.cloud.file.minio.utils.UUIDUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.Date;

/*
 * @Author：zhangz
 * 
 * @Package：com.zhangz.demo.springcloudplatform.controller
 * 
 * @Project：spring-cloud
 * 
 * @name：GoodsController
 * 
 * @Date：2023/12/28 11:03
 * 
 * @Filename：GoodsController
 */
@RestController
@Slf4j
@RequestMapping("/api/file")
@Api(tags = "文件上传")
@RefreshScope
public class FileController {

    @Resource
    private MinIOService minIOService;
    @Resource
    private MinioProperties minioProperties;

    @ApiOperation(value = "上传文件", notes = "上传文件")
    @PostMapping("/upload")
    @ResponseBody
    public CommonResult upload(@RequestParam("file") MultipartFile file, String shopid) throws BussinessException {
        String url = null;
        try {
            String objectId = getObjectId(shopid, FileTypeUtil.getType(file.getInputStream()));
            minIOService.upload(file.getBytes(), objectId);
            url = minioProperties.getBucket() + '/' + objectId;
        } catch (Exception e) {
            log.error("文件上传失败", e);
            throw new BussinessException("系统异常");
        }
        return CommonResult.success(url);
    }

    private String getObjectId(String shopid, String fileType) {
        String format = DateUtil.format(new Date(), "yyyy-MM-dd");
        String objectId = "goodsFile/" + format.replaceAll("-", "/") + "/" + UUIDUtils.randomUUID() + "." + fileType;
        return objectId;
    }
}
