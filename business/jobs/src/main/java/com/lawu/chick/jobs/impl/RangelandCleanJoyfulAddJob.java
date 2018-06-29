/*package com.lawu.chick.jobs.impl;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.lawu.chick.cache.service.ChickJobCacheService;
import com.lawu.chick.cache.service.co.ChickenCacheOperateCO;
import com.lawu.chick.service.ChickenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

*//**
 * 小鸡位于清洁度>=60的农场每15分钟+1愉悦值 同步到数据库
 *//*
public class RangelandCleanJoyfulAddJob implements SimpleJob {

    private static Logger logger = LoggerFactory.getLogger(RangelandCleanJoyfulAddJob.class);

    @Autowired
    private ChickenService chickenService;

    @Autowired
    private ChickJobCacheService chickJobCacheService;

    @Override
    public void execute(ShardingContext shardingContext) {
        List<ChickenCacheOperateCO> list = chickJobCacheService.getRangelandCleanJoyfullCacheData();
        List<ChickenCacheOperateCO> failureList = new ArrayList<>();
        for (ChickenCacheOperateCO cache : list) {
            try {
                chickenService.doAddJoyful(cache);
            } catch (Exception ex) {
                logger.error("牧场清洁度大于N时定时加愉悦值job，单条(chickNum={0})执行异常！", cache.getChickNum());
                ex.getStackTrace();
                failureList.add(cache);
            }
        }
        chickJobCacheService.removeRangelandCleanJoyfullCacheData();
        //把错误的记录放入缓存
        if (failureList.size() > 0) {
            chickJobCacheService.addRangelandCleanJoyfullCache(failureList);
        }
    }

}
*/