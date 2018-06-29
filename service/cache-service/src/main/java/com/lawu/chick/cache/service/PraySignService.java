package com.lawu.chick.cache.service;

import com.lawu.chick.cache.service.co.PraySignRuleCO;

/**
 * @author zhangyong
 * @date 2018/6/15.
 */
public interface PraySignService {
    Integer addPraySignCache(String memberNum,Integer signDay);

    void addSignRuleCache(PraySignRuleCO ruleCO);

    PraySignRuleCO getSignRuleCache();

    Integer getSignDayByNum(String memberNum);
}
