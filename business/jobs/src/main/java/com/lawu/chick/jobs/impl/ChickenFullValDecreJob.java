package com.lawu.chick.jobs.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.lawu.chick.repository.domain.extend.ChickDOView;
import com.lawu.chick.service.ChickenService;
import com.lawu.jobsextend.AbstractWholePageJob;
import com.lawu.jobsextend.JobsExtendPageException;

/**  
 * 小鸡饱腹值递减查询放入缓存
 * @author lihj
 * @date 2018年4月26日
 */
public class ChickenFullValDecreJob extends AbstractWholePageJob<ChickDOView>{
	@Autowired
    private ChickenService chickenService;

	@Override
	public void executePage(List<ChickDOView> list) throws JobsExtendPageException {
		chickenService.addChickFullValToCache(list);
	}

	@Override
	public List<ChickDOView> queryPage(int offset, int pageSize) {
		return chickenService.getChickNotDiefullPeriod(offset, pageSize);
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
