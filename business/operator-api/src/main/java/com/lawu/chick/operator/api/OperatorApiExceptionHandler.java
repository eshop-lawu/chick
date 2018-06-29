package com.lawu.chick.operator.api;

import org.apache.shiro.authz.UnauthenticatedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.lawu.chick.framework.web.impl.ResultCode;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

/**
 * 统一异常处理
 * @author Leach
 * @date 2017/5/9
 */
@RestControllerAdvice
public class OperatorApiExceptionHandler extends BaseController {


    private Logger logger = LoggerFactory.getLogger(OperatorApiExceptionHandler.class);

    @ExceptionHandler(value = UnauthenticatedException.class)
    public Result defaultUnauthenticatedHandler(UnauthenticatedException e) throws Exception {
        return failUnauthorized(ResultCode.USER_UNAUTHORIZED);
    }


}
