package com.zhangz.springbootdemocloudalibabafile.service;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.exception.CosClientException;
import com.qcloud.cos.exception.CosServiceException;
import com.qcloud.cos.http.HttpMethodName;
import com.qcloud.cos.model.DeleteObjectRequest;
import com.qcloud.cos.model.GeneratePresignedUrlRequest;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.StorageClass;
import com.zhangz.springbootdemocloudalibabafile.dto.InvoiceFile;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.StopWatch;
import org.omg.CORBA.SystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Date;
import java.util.Objects;

@Component
@Slf4j
public class TenCosService {

    /**
     * 元数据-文件类型
     * <p>
     * 关于cos object用户自定义元数据，需要已<b>x-cos-meta-</b>开头，且key值无论大小写均会转化成小写
     * </p>
     */
    private static final String META_FILE_TYPE = "x-cos-meta-file-type";
    /**
     * 元数据-票据状态
     */
    private static final String META_STATUS = "x-cos-meta-status";
    /**
     * 元数据-开票日期
     */
    private static final String META_ISSUE_DATE = "x-cos-meta-issue-date";
    /**
     * 存储版式文件的桶名称
     */
    private String invoiceFileBucketName;

    /**
     * COS服务 APPID
     */

    private String cosAppId;

    /**
     * COS 客服端
     */
    private COSClient cosClient;

    @Value("${nontax3.vfs.tencent.cos.bucketName}")
    private String bucketName;

    @PostConstruct
    public void init() {
        final String[] split = bucketName.split("-");
        cosAppId = split[1];
        invoiceFileBucketName = split[0];
    }

    @Autowired
    public void setCosClient(COSClient cosClient) {
        this.cosClient = cosClient;
    }

    /**
     *
     * @param objectId object id
     * @return 是否存在
     * @see <a href="data/2022_09/8921010000_9267701.pdf">接口详情</a>
     */
    public boolean isExist(String path, String objectId) throws Exception {
        String key = this.getKey(path, objectId);
        try {
            ObjectMetadata objectMetadata = cosClient.getObjectMetadata(this.getBucketName(), key);
            StorageClass storageClassEnum = objectMetadata.getStorageClassEnum();
            return Objects.nonNull(storageClassEnum) && (StorageClass.Archive.equals(storageClassEnum) || StorageClass.Deep_Archive.equals(storageClassEnum));
        } catch (CosServiceException cse) {
            if (cse.getStatusCode() == 404) {
                return false;
            }
            log.error("uploadObject 对象判断存在异常，key:{},error code:{},error msg:{}", key, cse.getErrorCode(), cse.getMessage());
            throw new Exception(" 对象判断存在异常, " + cse.getMessage());
        }
    }

    /**
     * <h2>COS删除文件对像</h2>
     *
     * @param objectId COS Object ID
     * @throws SystemException 文件删除失败
     * @see <a href="https://cloud.tencent.com/document/product/436/65939">接口详情</a>
     */
    public void deleteObject(String path, String objectId) throws Exception {
        String key = this.getKey(path, objectId);
        DeleteObjectRequest deleteObjectRequest = new DeleteObjectRequest(this.getBucketName(), key);
        try {
            log.info("deleteObject 版式文件即将删除，key:{}", key);
            cosClient.deleteObject(deleteObjectRequest);
        } catch (CosClientException e) {
            if (e instanceof CosServiceException) {
                log.error("deleteObject 删除对象失败，requestId:{}", ((CosServiceException)e).getRequestId());
            }
            log.error("deleteObject 删除对象失败，key:{},error code:{},error msg:{}", key, e.getErrorCode(), e.getMessage());
            throw new Exception("删除对象失败");
        }
    }

    /**
     * <h2>根据url上传文件</h2>
     *
     * @param invoiceFile 版式文件
     * @param path        文件cos存储路径
     */
    public boolean uploadObjectByUrl(InvoiceFile invoiceFile, String path) throws SystemException {
        StopWatch sw = StopWatch.createStarted();
        GeneratePresignedUrlRequest request = new GeneratePresignedUrlRequest(this.getBucketName(), this.getKey(path, invoiceFile.getId()), HttpMethodName.PUT);
        request.setExpiration(new Date(System.currentTimeMillis() + 30 * 60 * 1000));
        URL url = cosClient.generatePresignedUrl(request);
        sw.suspend();
        final long requesWaste = sw.getTime();

        sw.resume();
        boolean uploadResult = this.upLoadFileByFile(url, invoiceFile);
        sw.stop();
        final long uploadWaste = sw.getTime();

        log.info("cos文件上传【{}-{}】,生成预签名地址耗时：{},上传操作耗时：{},总耗时：{}, \n 上传链接：{}", invoiceFile.getId(), invoiceFile.getFileType(), requesWaste, uploadWaste + requesWaste, uploadWaste,
            url);

        return uploadResult;
    }

    /**
     * <h2>根据url下载文件</h2>
     *
     * @param path          文件cos存储路径
     */
    public InvoiceFile downloadByUrl(String path, InvoiceFile invoiceFile) {
        StopWatch sw = StopWatch.createStarted();
        GeneratePresignedUrlRequest request = new GeneratePresignedUrlRequest(this.getBucketName(), this.getKey(path, invoiceFile.getId()), HttpMethodName.GET);
        request.setExpiration(new Date(System.currentTimeMillis() + 30 * 60 * 1000));
        URL url = cosClient.generatePresignedUrl(request);
        sw.suspend();
        final long requestWaste = sw.getTime();

        sw.resume();
        byte[] file = this.downloadFileByFile(url);
        sw.suspend();
        final long uploadWaste = sw.getTime();

        sw.resume();
        if (Objects.isNull(file)) {
            return null;
        }
        
        invoiceFile.setFile(file);
        
        sw.stop();
        long waste = sw.getTime();
        log.info("cos文件下载【{}-{}】,生成预签名地址耗时：{},下载操作耗时：{},总耗时：{},\n 下载链接：{}", invoiceFile.getId(), invoiceFile.getFileType(), requestWaste, uploadWaste, waste, url);

        return invoiceFile;
    }

    private boolean upLoadFileByFile(URL url, InvoiceFile invoiceFile) {
        InputStream in = null;
        DataOutputStream out = null;

        boolean result = false;
        try {
            in = new ByteArrayInputStream(invoiceFile.getFile());
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("PUT");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(10000);
            out = new DataOutputStream(connection.getOutputStream());
            // 写入要上传的数据
            byte[] buff = new byte[1024];
            int len;
            while ((len = in.read(buff)) != -1) {
                out.write(buff, 0, len);
            }

            log.debug("COS文件上传 响应， response code :{}，msg:{}", connection.getResponseCode(), connection.getResponseMessage());
            result = Objects.equals(200, connection.getResponseCode());
        } catch (ProtocolException e) {
            log.error("COS文件URL上传 传输协议异常", e);
        } catch (IOException e) {
            log.error("COS文件URL上传 文件传输异常", e);
        } finally {
            this.close(in, out);
        }
        return result;
    }

    private byte[] downloadFileByFile(URL url) {
        InputStream in = null;
        ByteArrayOutputStream out = null;
        try {
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            // 设置超时间为3秒
            connection.setConnectTimeout(3 * 1000);
            // 得到输入流
            in = connection.getInputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            out = new ByteArrayOutputStream();
            while ((len = in.read(buffer)) != -1) {
                out.write(buffer, 0, len);
            }
            out.close();
            log.debug("COS文件上传 响应， response code :{}，msg:{}", connection.getResponseCode(), connection.getResponseMessage());
            return out.toByteArray();
        } catch (IOException e) {
            log.error("COS文件URL下载失败", e);
        } finally {
            this.close(in, out);
        }
        return null;
    }

    private void close(InputStream in, OutputStream out) {
        if (Objects.nonNull(in)) {
            try {
                in.close();
            } catch (IOException e) {
                log.error("COS文件URL操作 关闭输入流异常", e);
            }
        }
        if (Objects.nonNull(out)) {
            try {
                out.close();
            } catch (IOException e) {
                log.error("COS文件URL操作 关闭输出流异常", e);
            }
        }
    }

    /**
     * <h2>获取桶名称</h2>
     * 调用COS SDK时，使用的桶名格式为<b>BucketName-APPID</b>
     *
     * @return SDK可用的桶名称
     */
    private String getBucketName() {
        return this.invoiceFileBucketName + "-" + this.cosAppId;
    }

    private String getKey(String path, String ojectId) {
        return path + "/" + ojectId;
    }
}
