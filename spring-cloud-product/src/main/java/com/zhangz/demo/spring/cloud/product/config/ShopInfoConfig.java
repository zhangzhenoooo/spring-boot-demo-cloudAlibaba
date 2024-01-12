package com.zhangz.demo.spring.cloud.product.config;

import com.zhangz.demo.spring.cloud.product.dto.TenantConfigDTO;
import com.zhangz.demo.spring.cloud.product.entity.ShopInfo;
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