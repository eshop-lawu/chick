package com.lawu.chick.service;

import com.lawu.chick.service.bo.PraySignRuleBO;
import com.lawu.chick.service.param.PraySignRuleParam;

/**
 * @author zhangyong
 * @date 2018/6/15.
 */
public interface PraySignRuleService {
    /**
     * 编辑签到信息
     * @param param
     */
    void editSignRule(PraySignRuleParam param);

    PraySignRuleBO getPraySignRuleInfo();
}
