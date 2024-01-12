package com.zhangz.demo.spring.cloud.product.dto.order;

import com.zhangz.demo.spring.cloud.product.entity.OrderInfo;
import com.zhangz.demo.spring.cloud.product.vo.OrderInfoVO;
import lombok.Data;

import java.io.Serializable;

/*
 * @Author：zhangz
 * 
 * @Package：com.zhangz.demo.spring.cloud.product.dto.order
 * 
 * @Project：spring-cloud
 * 
 * @name：OrderDetailDTO
 * 
 * @Date：2024/1/12 10:53
 * 
 * @Filename：OrderDetailDTO
 */
@Data
public class OrderDetailDTO implements Serializable {
    private OrderInfo orderInfo;

}
