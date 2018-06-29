package com.lawu.chick.service;

import java.util.Date;

import com.lawu.chick.service.bo.SignInRuleBO;
import com.lawu.chick.service.enums.StatusEnum;
import com.lawu.chick.service.param.CommonPageParam;
import com.lawu.chick.service.param.SignInRuleParam;
import com.lawu.framework.core.page.Page;

/**
 * @author zhangyong
 * @date 2018/4/26.
 */
public interface SignInRuleService {

    void editSignInRule(SignInRuleParam param);

    SignInRuleBO getSignRuleInfo( Long id);

    void editSignRuleStatus(Long id, StatusEnum status);

    Page<SignInRuleBO> getSignRuleList(CommonPageParam param);

    SignInRuleBO getSignRuleByTime(Date time);
}
