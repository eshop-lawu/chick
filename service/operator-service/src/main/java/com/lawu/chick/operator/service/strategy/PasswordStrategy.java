package com.lawu.chick.operator.service.strategy;

/**
 * @author zhangyong
 * @date 2017/4/20.
 */
public interface PasswordStrategy {
    String encode(String originalPassword);
}
