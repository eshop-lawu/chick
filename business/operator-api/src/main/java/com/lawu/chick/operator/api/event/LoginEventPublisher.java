package com.lawu.chick.operator.api.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.lawu.chick.operator.api.dto.param.LogEventParam;

/**
 * @author zhangrc
 * @date 2017/12/27
 */
@Component
public class LoginEventPublisher {

    @Autowired
    ApplicationContext applicationContext;

    public void publishLogEvent(LogEventParam param) {
       
        applicationContext.publishEvent(new LogEvent(this,  param));
    }

	

}
