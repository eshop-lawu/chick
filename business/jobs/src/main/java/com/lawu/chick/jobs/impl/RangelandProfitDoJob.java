package com.lawu.chick.jobs.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.lawu.chick.cache.service.ChickJobCacheService;
import com.lawu.chick.cache.service.co.ChickenCacheOperateCO;
import com.lawu.chick.service.ChickenService;

/**
 * @author lihj
 * @date 2018年6月4日
 */
public class RangelandProfitDoJob implements SimpleJob {
	@Autowired
	private ChickenService chickenService;

	@Autowired
	private ChickJobCacheService chickJobCacheService;
	private static final Logger logger = LoggerFactory.getLogger(RangelandProfitDoJob.class);

	@Override
	public void execute(ShardingContext shardingContext) {
		List<ChickenCacheOperateCO> list = chickJobCacheService.getCacheRangelandProfit();
		logger.info("缓存中拿出来的数据是长度是"+list.size()+"         ----  "+JSON.toJSONString(list));
		List<ChickenCacheOperateCO> failureList = new ArrayList<>();
		for (ChickenCacheOperateCO cache : list) {
			try{
				chickJobCacheService.removegetCacheRangelandProfitSingle(cache);
				chickenService.doRangelandProfit(cache);
			} catch (Exception ex){
				failureList.add(cache);
				ex.printStackTrace();
			}
		}
		if (failureList.size() > 0) {
			chickJobCacheService.addCacheRangelandProfit(failureList);

		}
	}

}
