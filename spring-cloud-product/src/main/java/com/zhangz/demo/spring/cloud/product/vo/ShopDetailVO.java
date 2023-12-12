package com.zhangz.demo.spring.cloud.product.vo;

import com.zhangz.demo.spring.cloud.product.dto.GoodSelectItem;
import com.zhangz.demo.spring.cloud.product.entity.GoodCategoryItem;
import com.zhangz.demo.spring.cloud.product.entity.GoodDetail;
import com.zhangz.demo.spring.cloud.product.entity.Logistics;
import com.zhangz.demo.spring.cloud.product.entity.ShopDetail;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/*
 *@Author：zhangz
 *@Package：com.zhangz.demo.spring.cloud.product.vo
 *@Project：spring-cloud
 *@name：ShopDetailVO
 *@Date：2023/12/11  16:06
 *@Filename：ShopDetailVO
 */
@Data
public class ShopDetailVO implements Serializable {
  private GoodDetail basicInfo;
  private GoodCategoryItem category;
  private String content;
  private Logistics logistics;
  private List<Object> pics;
  private List<String> pics2;
  private String extJson = "{}";
  private String extJson2 = "{}";
  private Set<Object> properties;
   
  private List<GoodSelectItem> skuList;
  private List<Object> subPics;
}
