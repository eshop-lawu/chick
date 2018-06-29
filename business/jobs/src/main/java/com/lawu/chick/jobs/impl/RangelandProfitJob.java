package com.lawu.chick.jobs.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.gexin.fastjson.JSON;
import com.lawu.chick.cache.service.ChickBaseConfigCacheService;
import com.lawu.chick.cache.service.co.ChickBaseConfigCO;
import com.lawu.chick.repository.domain.extend.ChickDOView;
import com.lawu.chick.service.ChickenService;
import com.lawu.jobsextend.AbstractWholePageJob;
import com.lawu.jobsextend.JobsExtendPageException;

/**
 * 牧场收益  --加入缓存
 * @author lihj
 * @date 2018年6月4日
 */
public class RangelandProfitJob extends AbstractWholePageJob<ChickDOView>{
   
	@Autowired
    private ChickenService chickenService;
	
    @Autowired
    private ChickBaseConfigCacheService chickBaseConfigCacheService;
    
    private static final Logger logger = LoggerFactory.getLogger(RangelandProfitJob.class);
    
	@Override
	public void executePage(List<ChickDOView> lt) throws JobsExtendPageException {
		logger.info("加入缓存中的数据长度是"+lt.size()+"     ====== "+JSON.toJSONString(lt));
		chickenService.addCacheRangelandProfit(lt);
	}

	@Override
	public List<ChickDOView> queryPage(int offset, int pageSize) {
		ChickBaseConfigCO chickBaseConfigCO = chickBaseConfigCacheService.getCacheChickBaseInfo();
		List<ChickDOView> lt = new ArrayList<ChickDOView>();
		//查询在牧场中的非饥饿状态下并且没有牧场没有垃圾的鸡
		List<ChickDOView> lt1 = chickenService.getChickInRangelandAndIsClean(offset,pageSize);
		for(ChickDOView view : lt1){
			view.setVal(chickBaseConfigCO.getChickInRangelandAddJoyfulVal());
			lt.add(view);
		}
		//查询牧场中饥饿的鸡并且没有牧场没有垃圾的鸡、查询牧场中非饥饿并且牧场中有垃圾的鸡
		List<ChickDOView> lt2 = chickenService.getChickInRangelandAndIsHunger(offset,pageSize);
		for(ChickDOView view : lt2){
			BigDecimal b =new BigDecimal(chickBaseConfigCO.getChickInRangelandAddJoyfulVal());
			view.setVal(b.multiply(new BigDecimal("0.5")).intValue());
			lt.add(view);
		}
		return lt;
	}

	@Override
	public boolean continueWhenSinglePageFail() {
		return true;
	}

	@Override
	public boolean isStatusData() {
		return false;
	}

}
