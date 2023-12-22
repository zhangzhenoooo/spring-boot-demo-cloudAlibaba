package com.zhangz.demo.spring.cloud.product.service;

/*
 * @Author：zhangz
 * 
 * @Package：com.zhangz.demo.spring.cloud.product.service
 * 
 * @Project：spring-cloud
 * 
 * @name：ShoppingCartService
 * 
 * @Date：2023/12/21 15:14
 * 
 * @Filename：ShoppingCartService
 */

import com.zhangz.demo.spring.cloud.product.dto.shoppingcart.ShoppingCartInfoDTO;
import com.zhangz.demo.spring.cloud.product.dto.shoppingcart.SkuItem;
import com.zhangz.spring.cloud.common.exception.BussinessException;

import java.util.List;

public interface ShoppingCartService {
    /**
     * 加入购物车
     * @param goodsId
     * @param number
     * @param skuItems
     */
    void add(long goodsId, int number, List<SkuItem> skuItems);

    ShoppingCartInfoDTO info();

    void empty() throws BussinessException;

    void addOrder(String propertyChildIds) throws BussinessException;
    
}
