package com.lawu.chick.api.authorization.impl;

/**
 * 登录用户类型
 * @author jiangxinjun
 * @createDate 2018年4月25日
 * @updateDate 2018年4月25日
 */
public enum UserLoginType {
    MEMBER(1); // 会员

    private int val;

    UserLoginType(int val) {
        this.val = val;
    }

    public int getVal() {
        return val;
    }
}
