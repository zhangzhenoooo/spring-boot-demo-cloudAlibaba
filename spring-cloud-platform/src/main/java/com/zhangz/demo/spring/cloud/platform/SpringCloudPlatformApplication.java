package com.zhangz.demo.spring.cloud.platform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication 
@EnableSwagger2
@ComponentScan(basePackages = {
        "com.zhangz.spring.cloud.file.minio", "com.zhangz.demo.spring.cloud.platform"
})
public class SpringCloudPlatformApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringCloudPlatformApplication.class, args);
    }

}
