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

public enum UserLevelEnum {
    NORMAL(0, "普通用户"), VIP(1, "VIP"), VVIP(2, "VVIP"), VVVIP(3, "VVVIP"), VVVVIP(4, "大爷"), VVVVVIP(5, "上帝");

    private int state;
    private String desc;

    UserLevelEnum(int state, String desc) {
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
        for (UserLevelEnum value : UserLevelEnum.values()) {
            if (value.getState() == state) {
                return value.desc;
            }
        }
        return NORMAL.getDesc();
    }
}
