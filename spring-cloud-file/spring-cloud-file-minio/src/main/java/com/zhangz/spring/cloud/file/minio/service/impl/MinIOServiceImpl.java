package com.zhangz.spring.cloud.file.minio.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Pair;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.util.DateUtils;
import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import com.zhangz.spring.cloud.file.minio.config.FileConfig;
import com.zhangz.spring.cloud.file.minio.config.MimeTypeEnum;
import com.zhangz.spring.cloud.file.minio.config.MinioProperties;
import com.zhangz.spring.cloud.file.minio.dto.InitTaskParam;
import com.zhangz.spring.cloud.file.minio.dto.TaskInfoDTO;
import com.zhangz.spring.cloud.file.minio.dto.TaskRecordDTO;
import com.zhangz.spring.cloud.file.minio.dto.UploadTask;
import com.zhangz.spring.cloud.file.minio.service.MinIOService;

import com.zhangz.spring.cloud.file.minio.service.UploadTaskService;
import com.zhangz.spring.cloud.file.minio.utils.UUIDUtils;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSystemException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Component
@Service
public class MinIOServiceImpl implements MinIOService {
    @Resource
    private FileObject baseFolder;
    
    @Resource
    private MinioClient minioClient;

    @Resource
    private FileConfig fileConfig;
 

    @Resource
    private MinioProperties minioProperties;
    @Resource
    private AmazonS3 amazonS3;

    @Resource
    private UploadTaskService uploadTaskService;

    @Override
    public void upload(byte[] bytes, String filePath) throws IOException {
        String fileType = filePath.substring(filePath.lastIndexOf(".") + 1);

        ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
        PutObjectArgs putObjectArgs = PutObjectArgs.builder().contentType(MimeTypeEnum.getContentType(fileType)).stream(inputStream, inputStream.available(), -1)
            .bucket(fileConfig.getMinioBucketName()).object(filePath).build();
        try {
            minioClient.putObject(putObjectArgs);
        } catch (Exception e) {
            log.error("minio上传失败", e);
            throw new IOException(e.getMessage());
        }
    }

    @Override
    public byte[] downByPath(String filePath) throws Exception {
        try {

            if (StringUtils.isEmpty(filePath)) {
                log.error("文件路径为空");
                return new byte[0];
            }
            return download(filePath);
        } catch (Exception e) {
            log.error("文件【{}】下载失败", filePath, e);
            throw new Exception("文件下载异常");
        }
    }

    @Override
    public boolean isExist(String path) throws FileSystemException {
        try {
            FileObject fileObject = baseFolder.resolveFile(path);
            return fileObject.exists();
        } catch (FileSystemException e) {
            throw new FileSystemException("查询异常，连接cos异常");
        }
    }

    @Override
    public boolean remove(String path) throws FileSystemException {
        try {
            FileObject fileObject = baseFolder.resolveFile(path);
            fileObject.delete();
            return true;
        } catch (FileSystemException e) {
            throw new FileSystemException( "删除版式文件失败");
        }
    }

    @Override
    public TaskInfoDTO initTask(InitTaskParam param) {
        Date currentDate = new Date();
        String bucketName = minioProperties.getBucket();
        String fileName = param.getFileName();
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
        String key = StrUtil.format("{}/{}.{}", DateUtils.format(currentDate, "YYYY-MM-dd"), UUIDUtils.randomUUID() , suffix);
        String contentType =  MimeTypeEnum.getContentType(suffix);
        // 可在 ObjectMetadata 中设置加密方式等
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(contentType);

        // 大对象分块上传
        InitiateMultipartUploadResult initiateMultipartUploadResult =
            amazonS3.initiateMultipartUpload(new InitiateMultipartUploadRequest(bucketName, key).withObjectMetadata(objectMetadata));
        String uploadId = initiateMultipartUploadResult.getUploadId();

        UploadTask task = new UploadTask();
        int chunkNum = (int)Math.ceil(param.getTotalSize() * 1.0 / param.getChunkSize());
        task.setId(UUIDUtils.randomUUID()).setBucketName(minioProperties.getBucket()).setChunkNum(chunkNum).setChunkSize(param.getChunkSize()).setTotalSize(param.getTotalSize())
            .setFileIdentifier(param.getIdentifier()).setFileName(fileName).setObjectKey(key).setUploadId(uploadId);
        uploadTaskService.save(task);
 
        // 获取分片上传链接
        Set<Pair<Integer,String>> urls = new HashSet<>(chunkNum);
        for (int i = 0; i < chunkNum; i++) {
            Map<String, String> params = new HashMap<>();
            params.put("partNumber", String.valueOf(i));
            params.put("uploadId", uploadId);
            String url = genPreSignUploadUrl(task.getBucketName(), task.getObjectKey(), params);
            urls.add(Pair.of(i,url));
        }

        return new TaskInfoDTO().setSignUploadUrs(urls).setFinished(false).setTaskRecord(TaskRecordDTO.convertFromEntity(task)).setPath(getPath(bucketName, key));
    }

    @Override
    public TaskInfoDTO getTaskInfo(String identifier) {
        UploadTask task = uploadTaskService.selectByIdentifier(identifier);

        if (task == null) {
            return null;
        }

        TaskInfoDTO result = new TaskInfoDTO().setFinished(true).setTaskRecord(TaskRecordDTO.convertFromEntity(task)).setPath(getPath(task.getBucketName(), task.getObjectKey()));

        boolean doesObjectExist = amazonS3.doesObjectExist(task.getBucketName(), task.getObjectKey());
        if (!doesObjectExist) {
            // 未上传完，返回已上传的分片
            ListPartsRequest listPartsRequest = new ListPartsRequest(task.getBucketName(), task.getObjectKey(), task.getUploadId());
            PartListing partListing = amazonS3.listParts(listPartsRequest);
            result.setFinished(false).getTaskRecord().setExitPartList(partListing.getParts());
        }
        return result;
    }

    @Override
    public TaskInfoDTO getByIdentifier(String identifier) {
        UploadTask uploadTask = uploadTaskService.selectByIdentifier(identifier);
        return BeanUtil.copyProperties(uploadTask, TaskInfoDTO.class);
    }

    @Override
    public String genPreSignUploadUrl(String bucketName, String objectKey, Map<String, String> params) {
        Date currentDate = new Date();
        Date expireDate = DateUtil.offsetMillisecond(currentDate, (int)(60 * 10 * 1000L)); // 过期时间

        GeneratePresignedUrlRequest request = new GeneratePresignedUrlRequest(minioProperties.getBucket(), objectKey).withExpiration(expireDate).withMethod(HttpMethod.PUT);
        if (params != null) {
            params.forEach((key, val) -> request.addRequestParameter(key, val));
        }
        URL preSignedUrl = amazonS3.generatePresignedUrl(request);
        return preSignedUrl.toString();
    }

    @Override
    public void merge(String identifier) {
        UploadTask task = uploadTaskService.selectByIdentifier(identifier);
        if (task == null) {
            throw new RuntimeException("分片任务不存");
        }

        ListPartsRequest listPartsRequest = new ListPartsRequest(task.getBucketName(), task.getObjectKey(), task.getUploadId());
        PartListing partListing = amazonS3.listParts(listPartsRequest);
        List<PartSummary> parts = partListing.getParts();
        if (task.getChunkNum() != parts.size()) {
            // 已上传分块数量与记录中的数量不对应，不能合并分块
            throw new RuntimeException("分片缺失，请重新上传");
        }

        CompleteMultipartUploadRequest completeMultipartUploadRequest =
            new CompleteMultipartUploadRequest().withUploadId(task.getUploadId()).withKey(task.getObjectKey()).withBucketName(task.getBucketName())
                .withPartETags(parts.stream().map(partSummary -> new PartETag(partSummary.getPartNumber(), partSummary.getETag())).collect(Collectors.toList()));
        CompleteMultipartUploadResult result = amazonS3.completeMultipartUpload(completeMultipartUploadRequest);

    }

    public String getPath(String bucket, String objectKey) {
        return StrUtil.format("{}/{}/{}", minioProperties.getEndpoint(), bucket, objectKey);
    }

    private byte[] download(String path) throws FileSystemException, org.apache.commons.vfs2.FileSystemException {
        FileObject fileObject = baseFolder.resolveFile(path);
        byte[] bytes = new byte[0];
        if (fileObject.exists()) {
            // 获取文件的类型
            String extension = fileObject.getName().getExtension();
            // 读取文本内容
            bytes = inputStreamToByteArray(fileObject.getContent().getInputStream());
        }
        return bytes;
    }

    /**
     * inputStream转byte数组
     *
     * @param inputStream 输入流对象
     * @return byte数组
     */
    public static byte[] inputStreamToByteArray(InputStream inputStream) {
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            byte[] buffer = new byte[1024];
            int num;
            while ((num = inputStream.read(buffer)) != -1) {
                byteArrayOutputStream.write(buffer, 0, num);
            }
            byteArrayOutputStream.flush();
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            log.error("inputStream to byte[] 转换异常", e);
        }
        return new byte[] {};
    }

    /**
     * 获取minio文件的下载或者预览地址
     * 取决于调用本方法的方法中的PutObjectOptions对象有没有设置contentType
     *
     * @param bucketName: 桶名
     * @param fileName:   文件名
     */
    @SneakyThrows(Exception.class)
    private String getPreviewFileUrl(String bucketName, String fileName) {
        return minioClient.presignedGetObject(bucketName, fileName);
    }

}
