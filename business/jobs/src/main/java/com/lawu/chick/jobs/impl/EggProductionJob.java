package com.lawu.chick.jobs.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.lawu.chick.service.ChickenService;

/**
 * 小鸡产蛋,把分配的鸡蛋放入到蛋窝
 * 
 * @author jiangxinjun
 * @createDate 2018年4月27日
 * @updateDate 2018年4月27日
 */
public class EggProductionJob implements SimpleJob {
    
    private static final Logger logger = LoggerFactory.getLogger(EggProductionJob.class);
    
    @Autowired
    private ChickenService chickenService;
    
    @Override
    public void execute(ShardingContext shardingContext) {
        logger.debug("start eggProductionJob");
        chickenService.eggProduction();
        logger.debug("end eggProductionJob");
    }

}
