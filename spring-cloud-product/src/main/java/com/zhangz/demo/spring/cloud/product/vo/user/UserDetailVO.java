package com.zhangz.demo.spring.cloud.product.vo.user;

/*
 *@Author：zhangz
 *@Package：com.zhangz.demo.spring.cloud.product.vo
 *@Project：spring-cloud
 *@name：UserDetailVO
 *@Date：2023/12/27  14:39
 *@Filename：UserDetailVO
 */


import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailVO implements Serializable {
  private UserInfoVO base;
  private UserLevelVO userLevel;
}
