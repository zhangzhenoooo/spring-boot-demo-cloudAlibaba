package com.zhangz.demo.spring.cloud.product.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/*
 * @Author：zhangz
 * 
 * @Package：com.zhangz.demo.spring.cloud.product.vo.order
 * 
 * @Project：spring-cloud
 * 
 * @name：OrderedVO
 * 
 * @Date：2023/12/22 11:35
 * 
 * @Filename：OrderedVO
 */
@Data
public class OrderedVO implements Serializable {
    private List<OrderInfoVO> orderList;
    private Map<String, List<OrderedGoodsVO>> goodsMap;
}
