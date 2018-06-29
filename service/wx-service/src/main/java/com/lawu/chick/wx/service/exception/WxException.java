package com.lawu.chick.wx.service.exception;

/**
 * @author Leach
 * @date 2018/4/25
 */
public class WxException extends Exception {

    public WxException(String message) {
        super(message);
    }

    public WxException(String message, Throwable cause) {
        super(message, cause);
    }
}
