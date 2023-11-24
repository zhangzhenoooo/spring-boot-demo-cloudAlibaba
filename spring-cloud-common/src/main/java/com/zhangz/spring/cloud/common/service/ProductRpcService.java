package com.zhangz.spring.cloud.common.service;

import com.zhangz.spring.cloud.common.entity.Product;

import java.util.List;

public interface ProductRpcService {

    List<Product> getProductByName(String productName);

    Product getProductById(String pid);
}
