package com.zhangz.demo.spring.cloud.product.service;

import com.zhangz.spring.cloud.common.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> getProductByName(String productName);

    Product getProductById(String pid);

    void reduceInventory(String pid, int num) throws Exception;
}
