package com.lawu.chick.service.exception;

/**
 * 数据不存在异常
 * @author jiangxinjun
 * @createDate 2018年4月27日
 * @updateDate 2018年4月27日
 */
public class DataNotExistException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public DataNotExistException() {
        super();
    }

    public DataNotExistException(String message) {
        super(message);
    }
}
