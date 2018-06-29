package com.lawu.chick.jobs.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.lawu.chick.cache.service.ChickBaseConfigCacheService;
import com.lawu.chick.cache.service.co.ChickBaseConfigCO;
import com.lawu.chick.jobs.converter.ChickenConverter;
import com.lawu.chick.service.ChickenService;
import com.lawu.chick.service.bo.ChickenBaseInfoBO;
import com.lawu.chick.service.param.ChickenSickParam;
import com.lawu.jobsextend.AbstractWholePageJob;
import com.lawu.jobsextend.JobsExtendPageException;
import com.lawu.utils.DateUtil;

/**
 * 小鸡定时改为活动
 * 
 * @author lihj
 * @date 2018年5月16日
 */
public class ChickenToActiveJob extends AbstractWholePageJob<ChickenSickParam> {

	@Autowired
	private ChickenService chickenService;

	@Autowired
	private ChickBaseConfigCacheService chickBaseConfigCacheService;

	@Override
	public boolean continueWhenSinglePageFail() {
		return true;
	}

	@Override
	public boolean isStatusData() {
		return true;
	}

	@Override
	public List<ChickenSickParam> queryPage(int offset, int pageSize) {
		ChickBaseConfigCO chickBaseConfigCO = chickBaseConfigCacheService.getCacheChickBaseInfo();
		String chickStartActivitiesTime = chickBaseConfigCO.getChickStartActivitiesTime().replace(":", "");
		String chickEndActivetime =chickBaseConfigCO.getChickEndActivitiesTime().replace(":","");
		int chickStartActivitiesVal =Integer.valueOf(chickStartActivitiesTime).intValue();
		int chickEndActivitiesVal =Integer.valueOf(chickEndActivetime).intValue();
		String nowTime = DateUtil.getDateFormat(new Date(),"HH:mm").replace(":","");
		int nowTimeVal = Integer.valueOf(nowTime).intValue();
		if(chickStartActivitiesVal<=nowTimeVal && nowTimeVal<=chickEndActivitiesVal){
			List<ChickenBaseInfoBO> chickenBaseInfoBOList = chickenService.getChickenToActiveList(offset,pageSize);
			return ChickenConverter.converterChickSickParamList(chickenBaseInfoBOList);
		}
		return null;
	}

	@Override
	public void executePage(List<ChickenSickParam> chickParam) throws JobsExtendPageException {
		chickenService.tobeActive(chickParam);
	}


}
