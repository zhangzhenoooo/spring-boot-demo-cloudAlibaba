package com.zhangz.demo.spring.cloud.product.dto;

import com.zhangz.demo.spring.cloud.product.entity.ShopDetail;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/*
 * @Author：zhangz
 * 
 * @Package：com.zhangz.demo.spring.cloud.product.dto
 * 
 * @Project：spring-cloud
 * 
 * @name：ShopInfoVO
 * 
 * @Date：2023/12/8 16:36
 * 
 * @Filename：ShopInfoVO
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShopInfoVO implements Serializable {
    private String extJson;
    private ShopDetail info;
}
