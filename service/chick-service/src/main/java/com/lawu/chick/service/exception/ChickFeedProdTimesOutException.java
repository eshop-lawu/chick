package com.lawu.chick.service.exception;

/**
 * 小鸡喂商品限制
 * 
 * @author lihj
 * @date 2018年5月7日
 */
public class ChickFeedProdTimesOutException extends RuntimeException {

	public ChickFeedProdTimesOutException() {
		super();
	}

	public ChickFeedProdTimesOutException(String message) {
		super(message);
	}
}
