package com.lawu.chick.service.exception;

/**
 * 无可治疗的小鸡
 */
public class TreatIsEmptyException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public TreatIsEmptyException() {
        super();
    }

    public TreatIsEmptyException(String message) {
        super(message);
    }

}
