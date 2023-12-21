package com.zhangz.demo.spring.cloud.product.dto.shoppingcart;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/*
 *@Author：zhangz
 *@Package：com.zhangz.demo.spring.cloud.product.dto.shoppingcart
 *@Project：spring-cloud
 *@name：ShoppingCartInfoDTO
 *@Date：2023/12/21  16:36
 *@Filename：ShoppingCartInfoDTO
 *@Desc：购物车详情
 */
@Data
public class ShoppingCartInfoDTO implements Serializable {
  
  private int number;
  private int  price;
  private List<ShoppingGoods> items;
}
