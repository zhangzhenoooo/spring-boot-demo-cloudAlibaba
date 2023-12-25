package com.zhangz.demo.spring.cloud.product.constant;

/*
 * @Author：zhangz
 * 
 * @Package：com.zhangz.demo.spring.cloud.product.entity
 * 
 * @Project：spring-cloud
 * 
 * @name：OrderStatusEnum
 * 
 * @Date：2023/12/22 11:14
 * 
 * @Filename：OrderStatusEnum
 */

public enum CyTableStatusEnum {
     NEED_CHECK(0,"待下单"),
    CHECKED(1,"已经下单"),
    COOKING(2,"制作中"),
    SERVED(3,"已上菜"),
    PAYED(4,"已支付"),
    ;

  private int state;
    private String desc;

    CyTableStatusEnum(int state, String desc) {
        this.state = state;
        this.desc = desc;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
