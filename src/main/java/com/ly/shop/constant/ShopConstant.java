package com.ly.shop.constant;

public class ShopConstant {

    // session key名
    public static final String SESSION_KEY_NAME = "user";
    // 版本号
    public static final Integer VERSION = 1;

    /**
     * 用户状态：0：正常 1：锁定   2：禁用
     */
    public static interface isDeleteStatus {
        int NORMAL = 0;
        int LOCKED = 1;
        int DISABLE = 2;
    }
}
