package com.zhangz.demo.spring.cloud.common.service;

import com.zhangz.demo.spring.cloud.common.entity.Product;

import java.util.List;

public interface ProductRpcService {

    List<Product> getProductByName(String productName);

    Product getProductById(String pid);
}
