package com.lawu.chick.service.exception;

/**
 * 只允许一直治疗中的小鸡
 */
public class ChickenCureExistOneException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ChickenCureExistOneException() {
        super();
    }

    public ChickenCureExistOneException(String message) {
        super(message);
    }

    
}
