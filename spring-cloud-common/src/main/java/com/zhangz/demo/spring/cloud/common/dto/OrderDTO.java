package com.zhangz.demo.spring.cloud.common.dto;

import com.zhangz.demo.spring.cloud.common.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO implements Serializable {

    private String oid;// 订单id
    private String uid;
    private List<Order> orderProducts;
 }