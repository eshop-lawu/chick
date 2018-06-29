package com.lawu.chick.service.exception;

/**
 * 微信授权异常
 */
public class WxAuthException extends RuntimeException {

	public WxAuthException() {
		super();
	}

	public WxAuthException(String message) {
		super(message);
	}
}
