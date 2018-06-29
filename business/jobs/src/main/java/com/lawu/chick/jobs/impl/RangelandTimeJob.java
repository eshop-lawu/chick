package com.lawu.chick.jobs.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.lawu.chick.service.RangelandService;
import com.lawu.chick.service.bo.RangelandCleannessBO;
import com.lawu.chick.service.enums.SiteTypeEnum;
import com.lawu.chick.service.param.RangelandCleannessPageParam;
import com.lawu.jobsextend.AbstractPageJob;

/**
 * 小鸡处于牧场时间累计
 * 
 * @Description
 * @author zhangrc
 * @date 2018年6月04日
 */
public class RangelandTimeJob extends AbstractPageJob<RangelandCleannessBO> {

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
		rangelandService.chickInRangelandTime(bo.getMemberNum());
		
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
