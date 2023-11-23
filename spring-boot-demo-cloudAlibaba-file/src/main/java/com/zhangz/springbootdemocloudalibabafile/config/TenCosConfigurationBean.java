package com.zhangz.springbootdemocloudalibabafile.config;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.region.Region;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author zhangz
 */
@Configuration
@Slf4j
public class TenCosConfigurationBean implements ApplicationContextAware {
   
    private ApplicationContext applicationContext;

    /**
     * <h2>COS客户端注册成Bean</h2>
     *
     * @param secretId   secretId
     * @param secretKey  secretKey
     * @param regionName 分区名称
     */
    @Bean
    public COSClient cosClient(@Value("${file.tencent.cos.secretId}") String secretId,
                               @Value("${file.tencent.cos.secretKey}") String secretKey,
                               @Value("${file.tencent.cos.region}") String regionName) {
        COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
        ClientConfig clientConfig = new ClientConfig(new Region(regionName));
        clientConfig.setConnectionTimeout(60 * 1000);
        clientConfig.setSocketTimeout(30 * 1000);
        clientConfig.setConnectionRequestTimeout(30 * 1000);
        return new COSClient(cred, clientConfig);
    }

 

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
