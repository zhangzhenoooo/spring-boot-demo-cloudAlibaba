package com.zhangz.demo.spring.cloud.product;

import org.apache.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableSwagger2
@DubboComponentScan
@MapperScan("com.zhangz.demo.spring.cloud.product.dao")
@ComponentScan(basePackages = {"com.zhangz.demo.spring.cloud.product","com.zhangz.demo.spring.cloud.common"
})
public class SpringBootDemoCloudAlibabaProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootDemoCloudAlibabaProductApplication.class, args);
    }

}
