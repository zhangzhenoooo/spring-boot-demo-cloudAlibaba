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

public enum OrderStatusEnum {
    IN_CART(0, "待下单"), ORDERED(1, "订单已下单"), PAYED(2, "订单已支付");

    private int state;
    private String desc;

    OrderStatusEnum(int state, String desc) {
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

    public static String getDescByState(int state) {
        for (OrderStatusEnum value : OrderStatusEnum.values()) {
            if (value.getState() == state) {
                return value.desc;
            }
        }
        return null;
    }
}
