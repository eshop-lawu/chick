package com.lawu.chick.jobs.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.lawu.chick.service.SysConfigService;

/**  
 * @author lihj
 * @date 2018年4月26日
 */
public class ChickSysConfigJob implements SimpleJob{
	private static Logger logger = LoggerFactory.getLogger(ChickSysConfigJob.class);
	
	@Autowired
	private SysConfigService sysConfigService;
	
	@Override
	public void execute(ShardingContext shardingContext) {
		logger.info("定时任务开始执行");
		sysConfigService.executeJob();
	}

}
