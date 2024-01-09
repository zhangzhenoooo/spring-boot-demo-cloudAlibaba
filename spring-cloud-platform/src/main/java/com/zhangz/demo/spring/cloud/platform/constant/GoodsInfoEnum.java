package com.zhangz.demo.spring.cloud.platform.constant;

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

public enum GoodsInfoEnum {
    IN_CART(0, "上架"), ORDERED(1, "下架");

    private int state;
    private String desc;

    GoodsInfoEnum(int state, String desc) {
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
        for (GoodsInfoEnum value : GoodsInfoEnum.values()) {
            if (value.getState() == state) {
                return value.desc;
            }
        }
        return null;
    }
}
