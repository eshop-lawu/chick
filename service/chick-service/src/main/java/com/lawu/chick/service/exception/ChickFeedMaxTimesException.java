package com.lawu.chick.service.exception;

/**
 * 小鸡喂养次数达到上限
 * 
 * @author lihj
 * @date 2018年5月7日
 */
public class ChickFeedMaxTimesException extends RuntimeException {

	public ChickFeedMaxTimesException() {
		super();
	}

	public ChickFeedMaxTimesException(String message) {
		super(message);
	}
}
