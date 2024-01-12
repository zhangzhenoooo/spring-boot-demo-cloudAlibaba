package com.zhangz.demo.spring.cloud.platform.config;

import com.zhangz.demo.spring.cloud.platform.dto.TenantConfigDTO;
import com.zhangz.demo.spring.cloud.platform.entity.ShopInfo;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Data
@Component
@ConfigurationProperties(prefix = "shopinfo")
public class ShopInfoConfig {
    private List<TenantConfigDTO> items = new ArrayList<>();
    private ShopInfo base;
}