package com.lawu.chick.id.worker.generate.impl;

/**
 * 业务ID类型
 * @author Leach
 * @date 2017/10/24
 */
public enum BizIdType {
    /**
     *  会员
     */
    MEMBER("M"),
    
    CHICKEN("C"),
    
    PRODUCT("P"),

    EXCHANGE("E"),

    /**
     *  用户红包
     */
    REDPACKET("UR"),
    
    /**
     *  商品订单
     */
    ORDER("O");

    private String prefix;

    BizIdType(String prefix) {
        this.prefix = prefix;
    }

    public String getPrefix() {
        return prefix;
    }
}
