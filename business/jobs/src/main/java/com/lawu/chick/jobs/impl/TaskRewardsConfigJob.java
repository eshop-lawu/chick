package com.lawu.chick.jobs.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.lawu.chick.service.TaskRewardsConfigService;

/**
 * @author meishuquan
 * @date 2018/6/15.
 */
public class TaskRewardsConfigJob implements SimpleJob {

    private static Logger logger = LoggerFactory.getLogger(TaskRewardsConfigJob.class);

    @Autowired
    private TaskRewardsConfigService taskRewardsConfigService;

    @Override
    public void execute(ShardingContext shardingContext) {
        logger.debug("------{}-{} starting------", this.getClass().getSimpleName(), shardingContext.getShardingItem());

        taskRewardsConfigService.executeJob();

        logger.debug("------{}-{} finished------", this.getClass().getSimpleName(), shardingContext.getShardingItem());
    }

}
