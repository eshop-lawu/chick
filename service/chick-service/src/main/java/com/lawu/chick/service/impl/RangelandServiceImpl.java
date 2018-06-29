package com.lawu.chick.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.chick.repository.domain.RangelandDO;
import com.lawu.chick.repository.domain.RangelandDOExample;
import com.lawu.chick.repository.mapper.RangelandDOMapper;
import com.lawu.chick.repository.mapper.extend.RangelandDOExtendMapper;
import com.lawu.chick.repository.param.CleannessParam;
import com.lawu.chick.service.ChickenService;
import com.lawu.chick.service.RangelandEventRecordService;
import com.lawu.chick.service.RangelandService;
import com.lawu.chick.service.SysConfigService;
import com.lawu.chick.service.bo.RangelandCleannessBO;
import com.lawu.chick.service.bo.RangelandConfigBaseBO;
import com.lawu.chick.service.bo.RangelandInfoBO;
import com.lawu.chick.service.constants.EventTitleConstant;
import com.lawu.chick.service.converter.RangelandConvert;
import com.lawu.chick.service.enums.EventRecordAttrTypeEnum;
import com.lawu.chick.service.enums.EventRecordDirectionEnum;
import com.lawu.chick.service.enums.EventRecordFactorEnum;
import com.lawu.chick.service.enums.EventRecordSourceEnum;
import com.lawu.chick.service.enums.RangelandDay;
import com.lawu.chick.service.enums.SiteTypeEnum;
import com.lawu.chick.service.param.RangelandCleannessPageParam;
import com.lawu.chick.service.param.RangelandCleannessParam;
import com.lawu.chick.service.param.RangelandEventRecordParam;
import com.lawu.utils.DateUtil;

/**
 * @Description
 * @author zhangrc
 * @date 2018年4月26日
 */
@Service
public class RangelandServiceImpl implements RangelandService {

    @Autowired
    private RangelandDOMapper rangelandDOMapper;

    @Autowired
    private RangelandDOExtendMapper rangelandDOExtendMapper;

    @Autowired
    private SysConfigService sysConfigService;
    
    @Autowired
    private RangelandEventRecordService rangelandEventRecordService;

    @Autowired
    private ChickenService chickenService;
    
	@Override
	public List<RangelandCleannessBO> getRangelandIds(RangelandCleannessPageParam param) {
		
		RangelandDOExample example = new RangelandDOExample();
		example.createCriteria().andExternalCleannessGreaterThan(0);
		RowBounds rowBounds = new RowBounds(param.getOffset(), param.getPageSize());
		List<RangelandDO>  list = rangelandDOMapper.selectByExampleWithRowbounds(example, rowBounds);
		
		List<RangelandCleannessBO> listBO = new ArrayList<>();
		for (RangelandDO rangelandDO : list) {
			RangelandCleannessBO bo = new RangelandCleannessBO();
			bo.setId(rangelandDO.getId());
			bo.setExternalCleanTime(rangelandDO.getExternalCleanTime());
			bo.setMemberNum(rangelandDO.getMemberNum());
			listBO.add(bo);
		}
		return listBO;
	}

	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void reduceCleanness(RangelandCleannessParam cparam) {
		RangelandConfigBaseBO sysConfig = sysConfigService.getRangelandCacheConfig();
		CleannessParam param = new CleannessParam();
		param.setId(cparam.getId());
		param.setCleanness(sysConfig.getChickDeclineExternalVal());
		RangelandDO rangelandDO = rangelandDOMapper.selectByPrimaryKey(cparam.getId());
		if (rangelandDO.getOutsideDuration() != null
				&& rangelandDO.getOutsideDuration() >= sysConfig.getChickDeclineExternalValMinute()) {
			rangelandDOExtendMapper.reduceCleanness(param);
			//插入事件记录
			RangelandEventRecordParam event = new RangelandEventRecordParam();
			event.setAttrTypeEnum(EventRecordAttrTypeEnum.CLEANLINESS);
			event.setDirectionEnum(EventRecordDirectionEnum.REDUCE);
			event.setFactorEnum(EventRecordFactorEnum.CLEAN);
			event.setMemberNum(rangelandDO.getMemberNum());
			event.setSourceEnum(EventRecordSourceEnum.NONE);
			event.setTitle(EventTitleConstant.TITLE_RANGELAND_EXTERNAL_CLEANNES_NONE);
			event.setVal(BigDecimal.valueOf(sysConfig.getRangelandMaxCleannessVal()));
			rangelandEventRecordService.saveRangelandEventRecord(event);
		}

	}


	@Override
	public RangelandInfoBO getMyRangelandInfo(String memberNum) {
		RangelandDOExample example = new RangelandDOExample();
		example.createCriteria().andMemberNumEqualTo(memberNum);
		List<RangelandDO> list = rangelandDOMapper.selectByExample(example);
		if(list.size()>0){
			String startDate = sysConfigService.getCacheChickBaseInfo().getChickStartActivitiesTime();
			String endDate = sysConfigService.getCacheChickBaseInfo().getChickEndActivitiesTime();
			Long stime=DateUtil.getDateTimeFormat(DateUtil.getDate()+" "+startDate+":00").getTime();
			Long etime=DateUtil.getDateTimeFormat(DateUtil.getDate()+" "+endDate+":00").getTime();
			RangelandDay type =RangelandDay.NIGHT;
			if(stime<=new Date().getTime()&&new Date().getTime()<=etime){
				type=RangelandDay.DAY;
			}
			return RangelandConvert.convertRangelandInfoBO(list,type);
		}
		return null;
	}

	@Override
	public int getCleanness(SiteTypeEnum typeEnum, String memberNum) {
		RangelandDOExample example = new RangelandDOExample();
		example.createCriteria().andMemberNumEqualTo(memberNum);
		List<RangelandDO> list = rangelandDOMapper.selectByExample(example);
		return list.get(0).getExternalCleanness();
		
		
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void friendSweepCleanness(String memberNum) {
		RangelandDOExample example = new RangelandDOExample();
		example.createCriteria().andMemberNumEqualTo(memberNum);
		RangelandConfigBaseBO config = sysConfigService.getRangelandCacheConfig();
		RangelandDO rangeland = new RangelandDO();
		rangeland.setExternalCleanness(config.getRangelandMaxCleannessVal());
		rangeland.setOutsideDuration(0);
		rangeland.setOutsideTime(new Date());
		rangelandDOMapper.updateByExampleSelective(rangeland, example);
	}


	@Override
	@Transactional(rollbackFor = Exception.class)
	public void chickInRangelandTime(String memberNum) {
		
		Boolean flag = chickenService.chicksIsInExternal(memberNum);
		//如果牧场存在小鸡，时间累计
		if(flag){
			rangelandDOExtendMapper.chickInRangelandTime(memberNum);
		}
		
	}
}
