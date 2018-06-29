package com.lawu.chick.jobs.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.lawu.chick.service.RangelandService;
import com.lawu.chick.service.bo.RangelandCleannessBO;
import com.lawu.chick.service.enums.SiteTypeEnum;
import com.lawu.chick.service.param.RangelandCleannessPageParam;
import com.lawu.chick.service.param.RangelandCleannessParam;
import com.lawu.jobsextend.AbstractPageJob;

/**
 * 定时产生农场垃圾（降低清洁度三小时执行一次）
 * 
 * @Description
 * @author zhangrc
 * @date 2018年4月26日
 */
public class ExternalCleannessJob extends AbstractPageJob<RangelandCleannessBO> {

	@Autowired 
	private RangelandService rangelandService;

	@Override
	public List<RangelandCleannessBO> queryPage(int offset, int pageSize) {
		RangelandCleannessPageParam param = new RangelandCleannessPageParam();
		param.setOffset(offset);
		param.setPageSize(pageSize);
		param.setTypeEnum(SiteTypeEnum.EXTERNAL);
		List<RangelandCleannessBO> bo = rangelandService.getRangelandIds(param);
		return bo;
	}

	
	@Override
	public void executeSingle(RangelandCleannessBO bo) {
		RangelandCleannessParam param = new RangelandCleannessParam();
		param.setExternalCleanTime(bo.getExternalCleanTime());
		param.setId(bo.getId());
		rangelandService.reduceCleanness(param);
		
	}

	
	@Override
	public boolean continueWhenSinglePageFail() {
		return false;
	}

	
	@Override
	public boolean isStatusData() {
		return false;
	}

	
}
