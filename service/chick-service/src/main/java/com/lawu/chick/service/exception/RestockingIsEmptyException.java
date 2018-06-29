package com.lawu.chick.service.exception;

/**
 * 无可放养的小鸡
 */
public class RestockingIsEmptyException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public RestockingIsEmptyException() {
        super();
    }

    public RestockingIsEmptyException(String message) {
        super(message);
    }
    
}
