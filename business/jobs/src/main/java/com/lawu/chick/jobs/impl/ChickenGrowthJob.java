/*package com.lawu.chick.jobs.impl;

import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.lawu.chick.jobs.converter.ChickenConverter;
import com.lawu.chick.repository.param.ChickGrowthParam;
import com.lawu.chick.service.ChickenService;
import com.lawu.chick.service.RangelandEventRecordService;
import com.lawu.chick.service.SysConfigService;
import com.lawu.chick.service.bo.ChickenGrowthBO;
import com.lawu.chick.service.constants.EventTitleConstant;
import com.lawu.chick.service.enums.EventRecordAttrTypeEnum;
import com.lawu.chick.service.enums.EventRecordDirectionEnum;
import com.lawu.chick.service.enums.EventRecordFactorEnum;
import com.lawu.chick.service.enums.EventRecordSourceEnum;
import com.lawu.chick.service.enums.PeriodTypeEnum;
import com.lawu.chick.service.param.ChickenGrowthParam;
import com.lawu.chick.service.param.RangelandEventRecordParam;
import com.lawu.jobsextend.AbstractPageJob;

*//**  
 * 小鸡成长值累加任务
 * @author lihj
 * @date 2018年4月26日
 *//*
public class ChickenGrowthJob extends AbstractPageJob<ChickenGrowthParam>{
	private static Logger logger = LoggerFactory.getLogger(ChickenGrowthJob.class);
    
	@Autowired
    private ChickenService chickenService;
	@Autowired
	private SysConfigService sysConfigService;
	@Autowired
	private RangelandEventRecordService rangelandEventRecordService;
	
	@Override
	public List<ChickenGrowthParam> queryPage(int offset, int pageSize) {
		List<ChickenGrowthBO> list = chickenService.getChickGrowthValLessThanConfig(offset,pageSize);
		return ChickenConverter.convertChickenGrowthParam(list);
	}


	@Override
	public boolean continueWhenSinglePageFail() {
		return true;
	}

	@Override
	public boolean isStatusData() {
		return false;
	}

	@Override
	public void executeSingle(ChickenGrowthParam param) {
		try{
			ChickGrowthParam chick = new ChickGrowthParam();
			chick.setId(param.getId());
			chick.setAddGrowth(sysConfigService.getCacheChickBaseInfo().getChickDayGrowthVal());
			chick.setMaxGrowth(sysConfigService.getCacheChickBaseInfo().getChickMaxGrowthVal());
			chick.setHalfGrowth(PeriodTypeEnum.HALF_GROWTH.getVal().intValue());
			chick.setMature(PeriodTypeEnum.MATURE.getVal().intValue());
			chick.setHalfGrowVal(sysConfigService.getCacheChickBaseInfo().getChickSemiMatureVal());
			chickenService.addDayGrowth(chick);
			RangelandEventRecordParam event = new RangelandEventRecordParam();
			event.setAttrTypeEnum(EventRecordAttrTypeEnum.GROWTH);
			event.setChickenNum(param.getNum());
			event.setDirectionEnum(EventRecordDirectionEnum.ADD);
			event.setFactorEnum(EventRecordFactorEnum.FEED);
			event.setMemberNum(param.getMemberNum());
			event.setSourceEnum(EventRecordSourceEnum.NONE);
			event.setTitle(EventTitleConstant.TITLE_GROWTHVAL_ADD);
			event.setVal(BigDecimal.valueOf(sysConfigService.getCacheChickBaseInfo().getChickDayGrowthVal()));
			rangelandEventRecordService.saveRangelandEventRecord(event);
		}catch(Exception e){
			logger.info("小鸡每日成长值累加错误"+param.getId());
		}
	}

}
*/