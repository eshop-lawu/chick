package com.lawu.chick.jobs.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.lawu.chick.cache.service.ChickJobCacheService;
import com.lawu.chick.cache.service.co.ChickenCacheOperateCO;
import com.lawu.chick.service.ChickenService;

/**  
 * 处理缓存中小鸡饱腹值递减任务
 * @author lihj
 * @date 2018年4月27日
 */
public class ChickenFullValDoDecreJob implements SimpleJob{

	@Autowired
	private ChickenService chickenService;
	@Autowired
	private ChickJobCacheService chickJobCacheService;
	
	Logger logger = LoggerFactory.getLogger(ChickenFullValDoDecreJob.class);
	
	@Override
	public void execute(ShardingContext shardingContext) {
		List<ChickenCacheOperateCO> list = chickJobCacheService.getChickFullValCacheData();
		List<ChickenCacheOperateCO> errorList = new ArrayList<ChickenCacheOperateCO>();
		for (ChickenCacheOperateCO cache : list) {
			try{
				chickJobCacheService.removeChickFullValCacheDataSingle(cache);
				chickenService.doDecreFullVal(cache);
			}catch (Exception e) {
				logger.error(e.getMessage());
				errorList.add(cache);
			}
		}
		if(errorList.size()>0){
			chickJobCacheService.addChickFullValToCache(errorList);
		}
		
	}

}
