package com.lawu.chick.jobs.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.lawu.chick.service.ChickenService;

/**
 * 小鸡进入产房的鸡蛋进行分配
 * @author jiangxinjun
 * @createDate 2018年4月26日
 * @updateDate 2018年4月26日
 */
public class EggDistributionJob implements SimpleJob {
    
    private static final Logger logger = LoggerFactory.getLogger(EggDistributionJob.class);
    
    @Autowired
    private ChickenService chickenService;
    
    @Override
    public void execute(ShardingContext shardingContext) {
        logger.debug("start eggDistributionJob");
        chickenService.intoDeliveryRoomAndLayEgg();
        logger.debug("end eggDistributionJob");
    }

}
