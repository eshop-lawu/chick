package com.lawu.chick.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawu.chick.cache.service.PeriodCacheService;
import com.lawu.chick.service.PeriodBehaviorCtrlService;
import com.lawu.chick.service.enums.ChickenAttrEnum;

/**
 * @author Leach
 * @date 2018/4/27
 */
@Service
public class PeriodBehaviorCtrlServiceImpl implements PeriodBehaviorCtrlService {

    private static final String KEY_USE_PRODUCT = "PRODUCT_";
    private static final String KEY_FEED_MASTER = "FEED_MASTER_";
    private static final String KEY_FEED_FRIEND = "FEED_FRIEND";
    private static final String KEY_GROWTH = "GROWTH_";
    private static final String KEY_CLEAN_FRIEND = "CLEAN_FRIEND_";
    private static final String KEY_HELP = "HELP_";

    @Autowired
    private PeriodCacheService periodCacheService;

    @Override
    public int useProduct(String chickenNum, ChickenAttrEnum attr, String productNum) {
        return periodCacheService.increaseDaily(KEY_USE_PRODUCT.concat(chickenNum).concat(productNum).concat(attr.toString()), 1);
    }

    @Override
    public int feedByMaster(String chickenNum, ChickenAttrEnum attr) {
        return periodCacheService.increaseDaily(KEY_FEED_MASTER.concat(chickenNum).concat(attr.toString()), 1);
    }

    @Override
    public int feedByFriend(String chickenNum, ChickenAttrEnum attr) {
        return periodCacheService.increaseDaily(KEY_FEED_FRIEND.concat(chickenNum).concat(attr.toString()), 1);
    }

    @Override
    public int increaseGrowth(String chickenNum, int value) {
        return periodCacheService.increaseDaily(KEY_GROWTH.concat(chickenNum), value);
    }

    @Override
    public int cleanByFriend(String memberNum, String helpMemberNum) {
        return periodCacheService.increaseDaily(KEY_CLEAN_FRIEND.concat(memberNum).concat(helpMemberNum), 1);
    }

    @Override
    public int help(String currentMemberNum) {
        return periodCacheService.increaseDaily(KEY_HELP.concat(currentMemberNum), 1);
    }
}
