package com.lawu.chick.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.chick.cache.service.ChickBaseConfigCacheService;
import com.lawu.chick.cache.service.ChickJobCacheService;
import com.lawu.chick.cache.service.EggConfigCacheService;
import com.lawu.chick.cache.service.RangelandConfigCacheService;
import com.lawu.chick.cache.service.co.ChickBaseConfigCO;
import com.lawu.chick.cache.service.co.ChickenCacheOperateCO;
import com.lawu.chick.cache.service.co.EggConfigCache;
import com.lawu.chick.cache.service.co.HouseCleannessCO;
import com.lawu.chick.cache.service.co.RangelandConfigBaseCO;
import com.lawu.chick.id.worker.generate.impl.BizIdType;
import com.lawu.chick.id.worker.generate.impl.IdWorkerHelper;
import com.lawu.chick.repository.domain.ChickenDO;
import com.lawu.chick.repository.domain.ChickenDOExample;
import com.lawu.chick.repository.domain.ChickenDOExample.Criteria;
import com.lawu.chick.repository.domain.EggDistributionRecordDO;
import com.lawu.chick.repository.domain.EggDistributionRecordDOExample;
import com.lawu.chick.repository.domain.EggReleaseRecordDO;
import com.lawu.chick.repository.domain.RangelandDO;
import com.lawu.chick.repository.domain.RangelandDOExample;
import com.lawu.chick.repository.domain.WxUserDO;
import com.lawu.chick.repository.domain.WxUserDOExample;
import com.lawu.chick.repository.domain.extend.ChickDOView;
import com.lawu.chick.repository.domain.extend.ChickenInHouseDOView;
import com.lawu.chick.repository.mapper.ChickenDOMapper;
import com.lawu.chick.repository.mapper.EggDistributionRecordDOMapper;
import com.lawu.chick.repository.mapper.EggReleaseRecordDOMapper;
import com.lawu.chick.repository.mapper.RangelandDOMapper;
import com.lawu.chick.repository.mapper.WxUserDOMapper;
import com.lawu.chick.repository.mapper.extend.ChickenDOExtendMapper;
import com.lawu.chick.repository.mapper.extend.EggDistributionRecordDOExtendMapper;
import com.lawu.chick.repository.mapper.extend.MemberDOExtendMapper;
import com.lawu.chick.repository.param.ChickDealWithParam;
import com.lawu.chick.repository.param.ChickEggOperatorParam;
import com.lawu.chick.repository.param.ChickGrowthParam;
import com.lawu.chick.repository.param.ChickQueryFullValParam;
import com.lawu.chick.repository.param.ChickUpdateInfoParam;
import com.lawu.chick.repository.param.ChickenDayHenhouseQueryParam;
import com.lawu.chick.repository.param.ChickenReduceCleanessParam;
import com.lawu.chick.repository.param.CommonListPageParam;
import com.lawu.chick.repository.param.MemberEggOperatorParam;
import com.lawu.chick.repository.param.ReduceCleanParam;
import com.lawu.chick.repository.param.UpdateAssignedQuantityParam;
import com.lawu.chick.repository.param.UpdateIssueQuantityParam;
import com.lawu.chick.service.ChickenService;
import com.lawu.chick.service.EggDistributionRecordService;
import com.lawu.chick.service.RangelandEventRecordService;
import com.lawu.chick.service.SysConfigService;
import com.lawu.chick.service.WxUserService;
import com.lawu.chick.service.bo.ChickBaseConfigBO;
import com.lawu.chick.service.bo.ChickenBaseInfoBO;
import com.lawu.chick.service.bo.ChickenCleanessBO;
import com.lawu.chick.service.bo.ChickenGrowthBO;
import com.lawu.chick.service.bo.ChickenManagePageBO;
import com.lawu.chick.service.bo.RangelandConfigBaseBO;
import com.lawu.chick.service.bo.WxUserBO;
import com.lawu.chick.service.constants.EventTitleConstant;
import com.lawu.chick.service.converter.ChickenConverter;
import com.lawu.chick.service.enums.AdoptTypeEnum;
import com.lawu.chick.service.enums.ChickStatusEnum;
import com.lawu.chick.service.enums.DistributionStatusEnum;
import com.lawu.chick.service.enums.EventMiniTypeEnum;
import com.lawu.chick.service.enums.EventRecordAttrTypeEnum;
import com.lawu.chick.service.enums.EventRecordDirectionEnum;
import com.lawu.chick.service.enums.EventRecordFactorEnum;
import com.lawu.chick.service.enums.EventRecordSourceEnum;
import com.lawu.chick.service.enums.PeriodTypeEnum;
import com.lawu.chick.service.exception.ChickenCureExistOneException;
import com.lawu.chick.service.exception.DataNotExistException;
import com.lawu.chick.service.exception.IllegalOperationException;
import com.lawu.chick.service.exception.RestockingIsEmptyException;
import com.lawu.chick.service.exception.TreatIsEmptyException;
import com.lawu.chick.service.param.ChickenPageParam;
import com.lawu.chick.service.param.ChickenSickParam;
import com.lawu.chick.service.param.RangelandEventRecordParam;
import com.lawu.chick.service.param.RangelandEventTitleDataParam;
import com.lawu.framework.core.page.Page;
import com.lawu.utils.DateUtil;

/**
 * @author zhangrc
 * @Description
 * @date 2018年4月25日
 */
@Service
public class ChickenServiceImpl implements ChickenService {

	@Autowired
	private ChickenDOMapper chickenDOMapper;

	@Autowired
	private ChickenDOExtendMapper chickenDOExtendMapper;

	@Autowired
	private EggDistributionRecordDOMapper eggDistributionRecordDOMapper;

	@Autowired
	private EggDistributionRecordDOExtendMapper eggDistributionRecordDOExtendMapper;

	@Autowired
	private IdWorkerHelper idWorkerHelper;

	@Autowired
	private SysConfigService sysConfigService;

	@Autowired
	private ChickenServiceImpl chickenServiceImpl;

	@Autowired
	private ChickBaseConfigCacheService chickBaseConfigCacheService;

	@Autowired
	private EggConfigCacheService eggConfigCacheService;

	@Autowired
	private RangelandDOMapper rangelandDOMapper;

	@Autowired
	private ChickJobCacheService chickJobCacheService;

	@Autowired
	private RangelandEventRecordService rangelandEventRecordService;

	@Autowired
	private WxUserService wxUserService;

	@Autowired
	private WxUserDOMapper wxUserDOMapper;

	@Autowired
	private EggDistributionRecordService eggDistributionRecordService;

	@Autowired
	private EggReleaseRecordDOMapper eggReleaseRecordDOMapper;
	
	@Autowired
	private MemberDOExtendMapper memberDOExtendMapper;

	@Autowired
	private RangelandConfigCacheService rangelandConfigCacheService;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void createChicken(String memberNum, AdoptTypeEnum typeEnum,int chickenCount) {
		// 小鸡配置
		ChickBaseConfigBO baseInfo = sysConfigService.getCacheChickBaseInfo();
		// 初始化牧场
		RangelandDOExample example = new RangelandDOExample();
		example.createCriteria().andMemberNumEqualTo(memberNum);
		int count = (int) rangelandDOMapper.countByExample(example);
		RangelandConfigBaseBO config = sysConfigService.getRangelandCacheConfig();
		if (count == 0) {
			RangelandDO rangelandDO = new RangelandDO();
			rangelandDO.setExternalCleanness(config.getRangelandMaxCleannessVal());
			rangelandDO.setGmtCreate(new Date());
			rangelandDO.setGmtModified(new Date());
			rangelandDO.setMemberNum(memberNum);
			rangelandDO.setExternalCleanTime(new Date());
			rangelandDO.setOutsideDuration(0);
			rangelandDOMapper.insertSelective(rangelandDO);
		}
		if (typeEnum == AdoptTypeEnum.INIT) {
			for (int i = 0; i < baseInfo.getChickAdoptionCount(); i++) { 
				initChickenInfo(memberNum, baseInfo,config.getRangelandMaxCleannessVal());
			}
		}else if (typeEnum == AdoptTypeEnum.BUY || typeEnum == AdoptTypeEnum.SEND) {
			for (int i = 0; i < chickenCount; i++) { 
				initChickenInfo(memberNum, baseInfo,config.getRangelandMaxCleannessVal());
			}
		} else if(typeEnum == AdoptTypeEnum.INVENTORY_ACTIVE){
			initChickenInfo(memberNum, baseInfo,config.getRangelandMaxCleannessVal());
		}

	}

/*	@Override
	public List<ChickenBaseInfoBO> getToBeSickChickList(int offset, int pageSize) {
		ChickBaseConfigCO chickBaseConfigCO = chickBaseConfigCacheService.getCacheChickBaseInfo();
		int chickWaitSickMins = chickBaseConfigCO.getChickWaitSick() * 24 * 60;
		List<ChickenDO> chickenDOList = chickenDOExtendMapper.getToBeSickChickList(PeriodTypeEnum.MATURE.getVal(),
				chickWaitSickMins, offset, pageSize);
		return ChickenConverter.converterChickenBaseInfoBOList(chickenDOList);
	}*/

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void doSick(ChickenSickParam chickenSickParam) {
		ChickenDO chickenDO = new ChickenDO();
		chickenDO.setPeriod(PeriodTypeEnum.FAIL_ILL.getVal());
		chickenDO.setGmtModified(new Date());
		chickenDO.setId(chickenSickParam.getId());
		chickenDOMapper.updateByPrimaryKeySelective(chickenDO);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void updateChickenName(String num, String name) {
		ChickenDO chickenDO = new ChickenDO();
		chickenDO.setName(name);
		chickenDO.setGmtModified(new Date());
		ChickenDOExample example = new ChickenDOExample();
		example.createCriteria().andNumEqualTo(num);
		chickenDOMapper.updateByExampleSelective(chickenDO, example);
	}

	/**
	 * 查询当前处于活动+非死亡的小鸡列表，进鸡舍
	 *
	 * @param offset
	 * @param pageSize
	 * @return
	 */
	@Override
	public List<ChickenBaseInfoBO> getToBeIntoHenhouseChickList(int offset, int pageSize) {
		ChickenDOExample chickenDOExample = new ChickenDOExample();
		chickenDOExample.createCriteria().andPeriodNotEqualTo(PeriodTypeEnum.DIE.getVal());
		RowBounds rowBounds = new RowBounds(offset, pageSize);
		List<ChickenDO> chickenDOList = chickenDOMapper.selectByExampleWithRowbounds(chickenDOExample, rowBounds);
		return ChickenConverter.converterChickenBaseInfoBOList(chickenDOList);
	}

	/**
	 * 小鸡定时进入鸡舍
	 *
	 * @param chickenSickParams
	 */
	@Override
	public void intoHenhouse(List<ChickenSickParam> chickenSickParams) {
		for (ChickenSickParam chickenSickParam : chickenSickParams) {
			ChickenDO chickenDO = new ChickenDO();
			chickenDO.setId(chickenSickParam.getId());
			chickenDO.setStatus(ChickStatusEnum.SLEEP.getVal());
			chickenDO.setIsOutside(false);
			chickenDO.setGmtModified(new Date());
			chickenDOMapper.updateByPrimaryKeySelective(chickenDO);
		}
	}

	/**
	 * 初始化小鸡
	 *
	 * @param memberNum
	 * @param baseInfo
	 */
	private void initChickenInfo(String memberNum, ChickBaseConfigBO baseInfo,int rangelandMaxCleannessVal) {

		ChickenDO record = new ChickenDO();
		record.setNum(idWorkerHelper.generate(BizIdType.CHICKEN));
		record.setFullVal(baseInfo.getChickInitFullVal());
		record.setGmtCreate(new Date());
		record.setGmtModified(new Date());
		record.setGrowthVal(baseInfo.getChickInitGrowthVal());
		record.setJoyfulVal(baseInfo.getChickInitJoyfulVal());
		record.setMemberNum(memberNum);
		record.setName(baseInfo.getChickDefauleName());
		record.setPeriod(PeriodTypeEnum.GROWTH.getVal());
		record.setFullPeriodTime(new Date());
		record.setJoyfulHouseCleanTime(new Date());
		record.setJoyfulRangelandCleanTime(new Date());
		record.setJoyfulRangelandSiteTime(new Date());
		record.setCleannessVal(rangelandMaxCleannessVal);
		record.setInhouseDuration(0);
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		String dateStr = sdf.format(new Date());
		boolean isInTheRange = true;
		DateFormat df = new SimpleDateFormat("HH:mm");
		Date dt1 = null, dt2 = null, dt3 = null;
		try {
			dt1 = df.parse(dateStr);
			dt2 = df.parse(baseInfo.getChickEndActivitiesTime());
			dt3 = df.parse(baseInfo.getChickStartActivitiesTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		// 将字符串转换为date类型
		if (dt1.getTime() > dt2.getTime() || dt1.getTime() < dt3.getTime()) {
			isInTheRange = false;
		}
		if (isInTheRange) {
			record.setIsOutside(true);
			record.setStatus(ChickStatusEnum.ACTIVITY.getVal());
		} else {
			record.setIsOutside(false);
			record.setStatus(ChickStatusEnum.SLEEP.getVal());
		}

		chickenDOMapper.insertSelective(record);
	}

	/**
	 * 所有小鸡信息列表查询接口，排除死亡
	 *
	 * @param memberNum
	 * @return
	 */
	@Override
	public List<ChickenBaseInfoBO> getChickenListByMemberNum(String memberNum) {
		ChickenDOExample example = new ChickenDOExample();
		example.createCriteria().andMemberNumEqualTo(memberNum).andPeriodLessThan(PeriodTypeEnum.DIE.getVal());
		example.setOrderByClause(" gmt_create asc ");
		List<ChickenDO> chickenDOList = chickenDOMapper.selectByExample(example);
		return ChickenConverter.converterChickenBaseInfoBOList(chickenDOList);
	}

	/**
	 * 放养小鸡
	 *
	 * @param memberNum
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void restocking(String memberNum) {
		// 把处于0成长期，1半成熟期，2成熟期+睡眠的小鸡放养并修改成活动
		ChickenDOExample example = new ChickenDOExample();
		List<Byte> periodList = new ArrayList<>();
		periodList.add(PeriodTypeEnum.GROWTH.getVal());
		periodList.add(PeriodTypeEnum.HALF_GROWTH.getVal());
		periodList.add(PeriodTypeEnum.MATURE.getVal());
		List<Byte> statusList = new ArrayList<>();
		statusList.add(ChickStatusEnum.SLEEP.getVal());
		statusList.add(ChickStatusEnum.ACTIVITY.getVal());
		example.createCriteria().andMemberNumEqualTo(memberNum).andStatusIn(statusList).andPeriodIn(periodList);
		ChickenDO chickenDO = new ChickenDO();
		chickenDO.setStatus(ChickStatusEnum.ACTIVITY.getVal());
		chickenDO.setIsOutside(true);
		chickenDO.setGmtModified(new Date());
		int record = chickenDOMapper.updateByExampleSelective(chickenDO, example);
		if (record == 0) {
			throw new RestockingIsEmptyException();
		}
		// 把处于3生病，4治疗中的小鸡标记为放养+不活动
		ChickenDOExample example1 = new ChickenDOExample();
		List<Byte> periodList1 = new ArrayList<>();
		periodList1.add(PeriodTypeEnum.FAIL_ILL.getVal());
		periodList1.add(PeriodTypeEnum.CURE.getVal());
		example1.createCriteria().andMemberNumEqualTo(memberNum).andPeriodIn(periodList1);
		ChickenDO chickenDO1 = new ChickenDO();
		chickenDO1.setIsOutside(true);
		chickenDO1.setGmtModified(new Date());
		chickenDOMapper.updateByExampleSelective(chickenDO1, example1);
		
		//更新小鸡处于牧场时间
		RangelandDO rangeland = new RangelandDO();
		rangeland.setOutsideTime(new Date());
		rangeland.setGmtModified(new Date());
		RangelandDOExample  rangelandDOExample = new RangelandDOExample();
		rangelandDOExample.createCriteria().andMemberNumEqualTo(memberNum);
		rangelandDOMapper.updateByExampleSelective(rangeland, rangelandDOExample);

		// 新增事件记录
		RangelandEventRecordParam rangelandEventRecordParam = new RangelandEventRecordParam();
		rangelandEventRecordParam.setTitle(EventTitleConstant.TITLE_RESTOCKING);
		rangelandEventRecordParam.setAttrTypeEnum(EventRecordAttrTypeEnum.NONE);
		rangelandEventRecordParam.setDirectionEnum(EventRecordDirectionEnum.NONE);
		rangelandEventRecordParam.setFactorEnum(EventRecordFactorEnum.RESTOCKING);
		rangelandEventRecordParam.setMemberNum(memberNum);
		rangelandEventRecordParam.setSourceEnum(EventRecordSourceEnum.NONE);
		rangelandEventRecordService.saveRangelandEventRecord(rangelandEventRecordParam);
	}

	/**
	 * 小鸡治疗，针对生病的小鸡
	 *
	 * @param memberNum
	 * @param chickenNum
	 * @param isTreat
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void treat(String memberNum, String chickenNum, Boolean isTreat) {
		ChickenDOExample example = new ChickenDOExample();
		example.createCriteria().andMemberNumEqualTo(memberNum).andPeriodEqualTo(PeriodTypeEnum.FAIL_ILL.getVal())
				.andNumEqualTo(chickenNum);
		RangelandEventRecordParam rangelandEventRecordParam = new RangelandEventRecordParam();
		ChickenDO chickenDO = new ChickenDO();
		if (isTreat) {
			ChickenDOExample exampleQuery = new ChickenDOExample();
			exampleQuery.createCriteria().andMemberNumEqualTo(memberNum).andPeriodEqualTo(PeriodTypeEnum.CURE.getVal());
			long count = chickenDOMapper.countByExample(exampleQuery);
			if (count > 0) {
				throw new ChickenCureExistOneException();
			}
			chickenDO.setPeriod(PeriodTypeEnum.CURE.getVal());
			rangelandEventRecordParam.setTitle(EventTitleConstant.TITLE_CURE);
		} else {
			chickenDO.setPeriod(PeriodTypeEnum.DIE.getVal());
			rangelandEventRecordParam.setTitle(EventTitleConstant.TITLE_GIVEUP_CURE);
		}
		chickenDO.setGmtModified(new Date());
		int record = chickenDOMapper.updateByExampleSelective(chickenDO, example);
		if (record == 0) {
			throw new TreatIsEmptyException();
		}
		// 新增事件记录
		rangelandEventRecordParam.setChickenNum(chickenNum);
		rangelandEventRecordParam.setAttrTypeEnum(EventRecordAttrTypeEnum.NONE);
		rangelandEventRecordParam.setDirectionEnum(EventRecordDirectionEnum.NONE);
		rangelandEventRecordParam.setFactorEnum(EventRecordFactorEnum.IS_TREAT);
		rangelandEventRecordParam.setMemberNum(memberNum);
		rangelandEventRecordParam.setSourceEnum(EventRecordSourceEnum.NONE);
		rangelandEventRecordService.saveRangelandEventRecord(rangelandEventRecordParam);
	}

	@Override
	public List<ChickenGrowthBO> getChickGrowthValLessThanConfig(int offset, int pageSize) {
		ChickenDOExample example = new ChickenDOExample();
		example.createCriteria().andGrowthValLessThan(sysConfigService.getCacheChickBaseInfo().getChickMaxGrowthVal());
		RowBounds rowBounds = new RowBounds(offset, pageSize);
		List<ChickenDO> list = chickenDOMapper.selectByExampleWithRowbounds(example, rowBounds);
		if (list.size() >= 0) {
			return ChickenConverter.convertChickenGrowthBO(list);
		}
		return null;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void addDayGrowth(ChickGrowthParam param) {
		chickenDOExtendMapper.addDayGrowth(param);
	}

    @Override
    public void intoDeliveryRoomAndLayEgg() {
        ChickBaseConfigCO chickBaseConfigCO = chickBaseConfigCacheService.getCacheChickBaseInfo();
        boolean isInSpecifiedTime = false;
        for (String item : chickBaseConfigCO.getChickEggProdTime()) {
            Date eggProdTime = DateUtil.formatDate(item, "HH:mm");
            Calendar eggProdTimeCalendar = Calendar.getInstance();
            eggProdTimeCalendar.setTime(eggProdTime);
            Calendar intoDeliveryRoomTime = Calendar.getInstance();
            // 设置时分
            intoDeliveryRoomTime.set(Calendar.HOUR_OF_DAY, eggProdTimeCalendar.get(Calendar.HOUR_OF_DAY));
            intoDeliveryRoomTime.set(Calendar.MINUTE, eggProdTimeCalendar.get(Calendar.MINUTE));
            intoDeliveryRoomTime.set(Calendar.SECOND, 0);
            intoDeliveryRoomTime.set(Calendar.MILLISECOND, 0);
            // 计算提前进入产房的时间
            intoDeliveryRoomTime.add(Calendar.MINUTE, -chickBaseConfigCO.getChickLayingEggsTime());
            Calendar now = Calendar.getInstance();
            long offest = Math.abs(now.getTimeInMillis() - intoDeliveryRoomTime.getTimeInMillis());
            // 误差控制在5分钟之内
            if (offest < 5 * 60 * 1000) {
                isInSpecifiedTime = true;
                break;
            }
        }
        if (!isInSpecifiedTime) {
            return;
        }

        while (true) {
            int affectedRows = chickenDOExtendMapper.enterProduction();
            if (affectedRows <= 0) {
                break;
            }
        }
        // 鸡蛋分配
        eggDistribution();
    }

    @Override
    public void eggDistribution() {
        BigDecimal totalJoyfulVal = chickenDOExtendMapper.totalJoyfulVal();
        EggConfigCache eggConfigCache = eggConfigCacheService.getCacheEggConfig();
        ChickenDOExample example = new ChickenDOExample();
        example.createCriteria().andIsPregnantEqualTo(true);

        long count = chickenDOMapper.countByExample(example);
        if (count <= 0) {
            return;
        }
        // 插入金蛋分配记录
        Long recordId = eggDistributionRecordService.save(count, eggConfigCache.getLayEggsTotal());
        
        for (int i = 0; i <= count; i = i + 100) {
            RowBounds rowBounds = new RowBounds(i, 100);
            List<ChickenDO> list = chickenDOMapper.selectByExampleWithRowbounds(example, rowBounds);
            if (list.isEmpty()) {
                break;
            }
            chickenServiceImpl.eggDistributionExecute(list, totalJoyfulVal, eggConfigCache, recordId);
        }

        // 金蛋分配完成
        eggDistributionRecordService.distributionCompleted(recordId);
    }

    @Transactional(rollbackFor = Exception.class)
    public void eggDistributionExecute(List<ChickenDO> list, BigDecimal totalJoyfulVal, EggConfigCache eggConfigCache,
            Long recordId) {
        BigDecimal maxSingleLayEggs = eggConfigCache.getMaxLayEggs();
        BigDecimal maxleLayEggs = eggConfigCache.getLayEggsTotal();
        BigDecimal realAllocations = BigDecimal.ZERO;
        ThreadLocalRandom random = ThreadLocalRandom.current();
        for (ChickenDO item : list) {
            ChickenDO record = new ChickenDO();
            // 计算生产的鸡蛋数量
            BigDecimal layEggs = new BigDecimal(item.getJoyfulValSnapshoot()).multiply(maxleLayEggs);
            // 当总愉悦值为0时，作为除数没有意义
            if (totalJoyfulVal.compareTo(BigDecimal.ZERO) > 0) {
                layEggs = layEggs.divide(totalJoyfulVal, 4, RoundingMode.DOWN);
            }
            if (eggConfigCache.getMinGuaranteedValue() != null && eggConfigCache.getMaxGuaranteedValue() != null
                    && eggConfigCache.getMaxGuaranteedValue().compareTo(BigDecimal.ZERO) > 0) {
                BigDecimal guaranteedValue = BigDecimal
                        .valueOf(random.nextDouble(eggConfigCache.getMinGuaranteedValue().doubleValue(),
                                eggConfigCache.getMaxGuaranteedValue().doubleValue()))
                        .setScale(4, RoundingMode.DOWN);
                layEggs = layEggs.add(guaranteedValue);
            }
            if (layEggs.compareTo(maxSingleLayEggs) > 0) {
                layEggs = maxSingleLayEggs;
            }
            realAllocations = realAllocations.add(layEggs);
            record.setId(item.getId());
            record.setLayEggs(layEggs);
            record.setGmtModified(new Date());
            chickenDOMapper.updateByPrimaryKeySelective(record);
        }
        UpdateAssignedQuantityParam param = new UpdateAssignedQuantityParam();
        param.setId(recordId);
        param.setAllocatedChicks(Long.valueOf(list.size()));
        param.setRealAllocations(realAllocations);
        eggDistributionRecordDOExtendMapper.updateAssignedQuantity(param);
    }

	@Override
	public List<ChickDOView> getChickNotDiefullPeriod(int offset, int pageSize) {
		ChickQueryFullValParam param = new ChickQueryFullValParam();
		param.setOffset(offset);
		param.setPageSize(pageSize);
		ChickBaseConfigBO config = sysConfigService.getCacheChickBaseInfo();
		param.setChickDeclineFullValMinute(config.getChickDeclineFullValMinute());
		List<ChickDOView> list = chickenDOExtendMapper.getChickFullValPeriod(param);
		for (ChickDOView view : list) {
			view.setVal(config.getChickDeclineFullVal());
		}
		return list;
	}

	@Override
	public void addChickFullValToCache(List<ChickDOView> list) {
		chickJobCacheService.addChickFullValToCache(ChickenConverter.converterChickenCacheCOList(list));
		for (ChickDOView view : list) {
			chickenDOExtendMapper.updateFullValDate(view.getChickNum());
		}
	}

	/**
	 * 查询清洁度大于60的牧场中满足时间15分钟条件累加愉悦值的小鸡列表
	 *
	 * @param param
	 * @return
	 */
/*	@Override
	public List<ChickDOView> getChickRangelandClean(ChickQueryParam param) {
		List<ChickDOView> chickDOViewList = chickenDOExtendMapper.getChickRangelandClean(param);
		return chickDOViewList;
	}*/

	/**
	 * 将清洁度大于60的牧场中满足时间15分钟条件累加愉悦值的小鸡信息放入缓存
	 *
	 * @param chickDOViewList
	 */
	@Override
	public void addRangelandCleanJoyfullCache(List<ChickDOView> chickDOViewList) {
		chickJobCacheService
				.addRangelandCleanJoyfullCache(ChickenConverter.converterChickenCacheCOList(chickDOViewList));
		// 更新小鸡表joyful_rangeland_clean_time
		//updateValTime(chickDOViewList, 1);
	}

	/**
	 * 查询清洁度小于于60的鸡舍中满足时间10分钟条件减愉悦值的小鸡列表
	 *
	 * @param param
	 * @return
	 */
/*	@Override
	public List<ChickDOView> getHenhouseCleanMinusJoyfullChickens(ChickenMinusJoyfullQueryParam param) {
		List<ChickDOView> chickDOViewList = chickenDOExtendMapper.getHenhouseCleanMinusJoyfullChickens(param);
		return chickDOViewList;
	}*/

	/**
	 * 将清洁度小于60的鸡舍中满足时间10分钟条件减愉悦值的小鸡信息放入缓存
	 *
	 * @param chickDOViewList
	 */
	@Override
	public void addHenhouseCleanJoyfullCache(List<ChickDOView> chickDOViewList) {
		chickJobCacheService
				.addHenhouseCleanJoyfullCache(ChickenConverter.converterChickenCacheCOList(chickDOViewList));
		updateValTime(chickDOViewList, 2);
	}

	@Override
	public List<ChickenBaseInfoBO> getAllChickNotDieChick(int offset, int pageSize) {
		ChickenDOExample chickenDOExample = new ChickenDOExample();
		chickenDOExample.createCriteria().andPeriodNotIn(Arrays.asList(PeriodTypeEnum.DIE.getVal()));
		RowBounds rowBounds = new RowBounds(offset, pageSize);
		List<ChickenDO> chickenDOList = chickenDOMapper.selectByExampleWithRowbounds(chickenDOExample, rowBounds);
		return ChickenConverter.converterChickenBaseInfoBOList(chickenDOList);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void doDecreFullVal(ChickenCacheOperateCO co) {
		ChickBaseConfigBO config = sysConfigService.getCacheChickBaseInfo();
		ChickDealWithParam param = new ChickDealWithParam();
		param.setLimitVal(0);
		param.setMemberNum(co.getMemberNum());
		param.setNum(co.getChickNum());
		param.setVal(co.getVal());
		param.setHungerVal(config.getChickHungerVal());//饥饿值
		param.setMinFull(config.getChickDormancyMinFullVal());//饿晕值
		chickenDOExtendMapper.doDecreaseFullVal(param);
		RangelandEventRecordParam event = new RangelandEventRecordParam();
		event.setAttrTypeEnum(EventRecordAttrTypeEnum.FULL);
		event.setChickenNum(co.getChickNum());
		event.setDirectionEnum(EventRecordDirectionEnum.REDUCE);
		event.setFactorEnum(EventRecordFactorEnum.FEED);
		event.setMemberNum(co.getMemberNum());
		event.setSourceEnum(EventRecordSourceEnum.NONE);
		event.setTitle(EventTitleConstant.TITLE_FULLVAL_REDUCE);
		event.setVal(BigDecimal.valueOf(co.getVal()));
		rangelandEventRecordService.saveRangelandEventRecord(event);
	}

	/**
	 * 查询未在牧区满足15分钟减愉悦值的小鸡列表
	 *
	 * @param param
	 * @return
	 */
	@Override
	public List<ChickDOView> getDayHenhouseMinusJoyfullChickens(ChickenDayHenhouseQueryParam param) {
		List<ChickDOView> chickDOViewList = chickenDOExtendMapper.getDayHenhouseMinusJoyfullChickens(param);
		return chickDOViewList;
	}

	/**
	 * 将白天未处牧区每15分钟-2愉悦值的小鸡信息放入缓存
	 * 
	 * @param chickDOViewList
	 */
	@Override
	public void addDayHenhouseJoyfullCache(List<ChickDOView> chickDOViewList) {
		chickJobCacheService.addDayHenhouseJoyfullCache(ChickenConverter.converterChickenCacheCOList(chickDOViewList));
		updateValTime(chickDOViewList, 3);
	}

    @Override
    public void eggProduction() {
        ChickBaseConfigCO chickBaseConfigCO = chickBaseConfigCacheService.getCacheChickBaseInfo();
        EggConfigCache eggConfigCache = eggConfigCacheService.getCacheEggConfig();
        boolean isInSpecifiedTime = false;
        for (String item : chickBaseConfigCO.getChickEggProdTime()) {
            Date eggProdDateTime = DateUtil.formatDate(item, "HH:mm");
            Calendar eggProdTimeCalendar = Calendar.getInstance();
            eggProdTimeCalendar.setTime(eggProdDateTime);
            Calendar eggProdTime = Calendar.getInstance();
            // 设置时分
            eggProdTime.set(Calendar.HOUR_OF_DAY, eggProdTimeCalendar.get(Calendar.HOUR_OF_DAY));
            eggProdTime.set(Calendar.MINUTE, eggProdTimeCalendar.get(Calendar.MINUTE));
            eggProdTime.set(Calendar.SECOND, 0);
            eggProdTime.set(Calendar.MILLISECOND, 0);
            // 计算提前进入产房的时间
            Calendar now = Calendar.getInstance();
            long offest = Math.abs(now.getTimeInMillis() - eggProdTime.getTimeInMillis());
            // 误差控制在5分钟之内
            if (offest < 5 * 60 * 1000) {
                // 小鸡进入产房
                isInSpecifiedTime = true;
                break;
            }
        }
        if (!isInSpecifiedTime) {
            return;
        }

        EggDistributionRecordDOExample eggDistributionRecordDOExample = new EggDistributionRecordDOExample();
        eggDistributionRecordDOExample.createCriteria().andStatusEqualTo(DistributionStatusEnum.ALLOCATED.getVal());
        eggDistributionRecordDOExample.setOrderByClause("gmt_allocations_complete desc");
        RowBounds eggDistributionRecordRowBounds = new RowBounds(0, 1);
        List<EggDistributionRecordDO> eggDistributionRecordDOList = eggDistributionRecordDOMapper
                .selectByExampleWithRowbounds(eggDistributionRecordDOExample, eggDistributionRecordRowBounds);
        if (eggDistributionRecordDOList.isEmpty()) {
            return;
        }
        EggDistributionRecordDO eggDistributionRecordDO = eggDistributionRecordDOList.get(0);

        ChickenDOExample example = new ChickenDOExample();
        example.createCriteria().andIsPregnantEqualTo(true);
        while (true) {
            RowBounds rowBounds = new RowBounds(0, 100);
            List<ChickenDO> list = chickenDOMapper.selectByExampleWithRowbounds(example, rowBounds);
            if (list.isEmpty()) {
                break;
            }
            chickenServiceImpl.eggProductionExcute(list, eggConfigCache, eggDistributionRecordDO.getId());
        }

        // 更新分配记录的发放状态为已发放
        eggDistributionRecordService.releaseCompleted(eggDistributionRecordDO.getId());
    }

	@Transactional(rollbackFor = Exception.class)
	public void eggProductionExcute(List<ChickenDO> list, EggConfigCache eggConfigCache, Long recordId) {
		BigDecimal realOptEggs = BigDecimal.ZERO;
		for (ChickenDO item : list) {
			// 小鸡产蛋,把分配的鸡蛋放入到蛋窝
			ChickEggOperatorParam chickEggOperatorParam = new ChickEggOperatorParam();
			chickEggOperatorParam.setEggs(item.getLayEggs());
			chickEggOperatorParam.setChickNum(item.getNum());
			chickEggOperatorParam.setIncrease(true);
			chickEggOperatorParam.setMaxHouseEggs(eggConfigCache.getMaxHouseEggs());
			chickenDOExtendMapper.eggOperator(chickEggOperatorParam);

			ChickenDOExample example = new ChickenDOExample();
			example.createCriteria().andNumEqualTo(item.getNum());
			List<ChickenDO> records = chickenDOMapper.selectByExample(example);
			ChickenDO chicken = records.get(0);
			
			if (chicken.getRealOptEggs().compareTo(BigDecimal.ZERO) > 0) {
    			// 插入鸡蛋发放记录
    			EggReleaseRecordDO eggReleaseRecordDO = new EggReleaseRecordDO();
    			eggReleaseRecordDO.setChickNum(item.getNum());
    			eggReleaseRecordDO.setMemberNum(item.getMemberNum());
    			eggReleaseRecordDO.setEggs(chicken.getRealOptEggs());
    			eggReleaseRecordDO.setGmtCreate(new Date());
    			eggReleaseRecordDOMapper.insert(eggReleaseRecordDO);
			}

			realOptEggs = realOptEggs.add(chicken.getRealOptEggs());

			//如果所有小鸡不在牧场，则出产房需更新小鸡在牧场时间
			int count = chickenDOExtendMapper.chicksIsInExternal(item.getMemberNum());
			if (count == 0) { 
				RangelandDO rangeland = new RangelandDO();
				rangeland.setOutsideTime(new Date());
				rangeland.setGmtModified(new Date());
				RangelandDOExample  rangelandDOExample = new RangelandDOExample();
				rangelandDOExample.createCriteria().andMemberNumEqualTo(item.getMemberNum());
				rangelandDOMapper.updateByExampleSelective(rangeland, rangelandDOExample);
			}
			
			// 更新小鸡状态为活动
			ChickenDO record = new ChickenDO();
			record.setId(item.getId());
			record.setIsPregnant(false);
			record.setGmtModified(new Date());
			chickenDOMapper.updateByPrimaryKeySelective(record);
			
			updateChickInHouse(item.getNum());
		}
		UpdateIssueQuantityParam param = new UpdateIssueQuantityParam();
		param.setGrant(realOptEggs); 
		param.setGrantChicks(Long.valueOf(list.size()));
		param.setId(recordId);
		eggDistributionRecordDOExtendMapper.updateIssueQuantity(param);
	}

	@Override
	public ChickenBaseInfoBO getChickenInfoByMembernumNum(String memberNum, String num) {
		ChickenDOExample example = new ChickenDOExample();
		example.createCriteria().andNumEqualTo(num).andMemberNumEqualTo(memberNum)
				.andPeriodNotIn(Arrays.asList(PeriodTypeEnum.DIE.getVal()));
		List<ChickenDO> list = chickenDOMapper.selectByExample(example);
		if (list.size() > 0) {
			return ChickenConverter.convertChickenBaseInfoBO(list.get(0));
		}
		return null;

	}

	/**
	 * 小鸡位于清洁度>=60的农场每15分钟+1愉悦值 将缓存同步数据库
	 */
/*	@Override
	@Transactional(rollbackFor = Exception.class)
	public void doAddJoyful(ChickenCacheOperateCO cache) {
		ChickDealWithParam param = new ChickDealWithParam();
		param.setMemberNum(cache.getMemberNum());
		param.setNum(cache.getChickNum());
		param.setVal(cache.getVal());
		chickenDOExtendMapper.doAddJoyful(param);

		RangelandEventRecordParam event = new RangelandEventRecordParam();
		event.setAttrTypeEnum(EventRecordAttrTypeEnum.JOYFULL);
		event.setChickenNum(cache.getChickNum());
		event.setDirectionEnum(EventRecordDirectionEnum.ADD);
		event.setFactorEnum(EventRecordFactorEnum.TIME);
		event.setMemberNum(cache.getMemberNum());
		event.setSourceEnum(EventRecordSourceEnum.NONE);
		event.setVal(BigDecimal.valueOf(cache.getVal()));
		event.setEventTime(cache.getDate());

		RangelandEventTitleDataParam rangelandEventTitleDataParam = new RangelandEventTitleDataParam();
		rangelandEventTitleDataParam.setEventMiniTypeEnum(EventMiniTypeEnum.RANGELAND_CLEAN_ADD_JOYFUL);
		rangelandEventTitleDataParam.setRangelandClean(new Integer(cache.getExternalCleanness()).toString());
		rangelandEventTitleDataParam.setTimedMins(new Integer(cache.getTimedMins()).toString());
		event.setRangelandEventTitleDataParam(rangelandEventTitleDataParam);
		rangelandEventRecordService.saveRangelandEventRecord(event);
	}*/

	/**
	 * 小鸡位于清洁度<60的鸡舍每10分钟-1愉悦值 将缓存同步数据库
	 */
/*	@Override
	@Transactional(rollbackFor = Exception.class)
	public void doMinusJoyfulForHouseClean(ChickenCacheOperateCO cache) {
		doMinusJoyful(cache);

		RangelandEventRecordParam event = new RangelandEventRecordParam();
		event.setAttrTypeEnum(EventRecordAttrTypeEnum.JOYFULL);
		event.setChickenNum(cache.getChickNum());
		event.setDirectionEnum(EventRecordDirectionEnum.REDUCE);
		event.setFactorEnum(EventRecordFactorEnum.TIME);
		event.setMemberNum(cache.getMemberNum());
		event.setSourceEnum(EventRecordSourceEnum.NONE);
		event.setVal(BigDecimal.valueOf(cache.getVal()));
		event.setEventTime(cache.getDate());

		RangelandEventTitleDataParam rangelandEventTitleDataParam = new RangelandEventTitleDataParam();
		rangelandEventTitleDataParam.setEventMiniTypeEnum(EventMiniTypeEnum.HOUSE_CLEAN_MINUS_JOYFUL);
		rangelandEventTitleDataParam.setHouseClean(new Integer(cache.getHouseCleanness()).toString());
		rangelandEventTitleDataParam.setTimedMins(new Integer(cache.getTimedMins()).toString());
		event.setRangelandEventTitleDataParam(rangelandEventTitleDataParam);
		rangelandEventRecordService.saveRangelandEventRecord(event);

	}*/

	/**
	 * 白天未处牧区每15分钟-2愉悦值 将缓存同步数据库
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void doMinusJoyfulForDayHouse(ChickenCacheOperateCO cache) {
		doMinusJoyful(cache);

		RangelandEventRecordParam event = new RangelandEventRecordParam();
		event.setAttrTypeEnum(EventRecordAttrTypeEnum.JOYFULL);
		event.setChickenNum(cache.getChickNum());
		event.setDirectionEnum(EventRecordDirectionEnum.REDUCE);
		event.setFactorEnum(EventRecordFactorEnum.TIME);
		event.setMemberNum(cache.getMemberNum());
		event.setSourceEnum(EventRecordSourceEnum.NONE);
		event.setVal(BigDecimal.valueOf(cache.getVal()));
		event.setEventTime(cache.getDate());

		RangelandEventTitleDataParam rangelandEventTitleDataParam = new RangelandEventTitleDataParam();
		rangelandEventTitleDataParam.setEventMiniTypeEnum(EventMiniTypeEnum.DAY_HOUSE_CLEAN_MINUS_JOYFUL);
		rangelandEventTitleDataParam.setTimedMins(new Integer(cache.getTimedMins()).toString());
		event.setRangelandEventTitleDataParam(rangelandEventTitleDataParam);

		rangelandEventRecordService.saveRangelandEventRecord(event);
	}

	/**
	 * 缓存数据同步数据库，减愉悦值
	 * 
	 * @param cache
	 */
	private void doMinusJoyful(ChickenCacheOperateCO cache) {
		ChickDealWithParam param = new ChickDealWithParam();
		param.setMemberNum(cache.getMemberNum());
		param.setNum(cache.getChickNum());
		param.setVal(cache.getVal());
		param.setLimitVal(0);
		chickenDOExtendMapper.doMinusJoyful(param);
	}

	/**
	 * 更新小鸡几个事件的开始事件
	 *
	 * @param chickDOViewList
	 * @param flag
	 */
	private void updateValTime(List<ChickDOView> chickDOViewList, int flag) {
		for (ChickDOView chickDOView : chickDOViewList) {
			ChickenDO chickenDO = new ChickenDO();
			switch (flag) {
			case 1:
				chickenDO.setJoyfulRangelandCleanTime(new Date());
				break;
			case 2:
				chickenDO.setJoyfulHouseCleanTime(new Date());
				break;
			case 3:
				chickenDO.setJoyfulRangelandSiteTime(new Date());
				break;
			}
			chickenDO.setGmtModified(new Date());
			ChickenDOExample chickenDOExample = new ChickenDOExample();
			chickenDOExample.createCriteria().andMemberNumEqualTo(chickDOView.getMemberNum())
					.andNumEqualTo(chickDOView.getChickNum());
			chickenDOMapper.updateByExampleSelective(chickenDO, chickenDOExample);
		}
	}

	@Override
	public void feedChickUpdateChickInfo(ChickUpdateInfoParam feedUpdate) {
		chickenDOExtendMapper.feedChickUpdateChickInfo(feedUpdate);
	}

	@Override
	public Page<ChickenManagePageBO> managPage(ChickenPageParam param) {
		Page<ChickenManagePageBO> rtn = new Page<>();
		ChickenDOExample example = new ChickenDOExample();
		Criteria criteria = example.createCriteria();
		if (param.getIsOutside() != null) {
			criteria.andIsOutsideEqualTo(param.getIsOutside());
		}
		if (param.getPeriodType() != null) {
			criteria.andPeriodEqualTo(param.getPeriodType().getVal());
		}
		if (param.getStatusEnum() != null) {
			criteria.andStatusEqualTo(param.getStatusEnum().getVal());
		}
		if (StringUtils.isNotBlank(param.getCheckName())) {
			criteria.andNameLike("%" + param.getCheckName() + "%");
		}
		List<String> strArry = new ArrayList<>();
		if (StringUtils.isNotBlank(param.getNickname())) {
			WxUserDOExample wxExample = new WxUserDOExample();
			wxExample.createCriteria().andNicknameEqualTo(param.getNickname());
			List<WxUserDO> list = wxUserDOMapper.selectByExample(wxExample);
			for (WxUserDO wxUserDO : list) {
				strArry.add(wxUserDO.getMemberNum());
			}
			if (strArry.size() > 0) {
				criteria.andMemberNumIn(strArry);
			}
		}
		long count = chickenDOMapper.countByExample(example);
		rtn.setTotalCount((int) count);
		rtn.setCurrentPage(param.getCurrentPage());
		if (count <= 0 || count <= param.getOffset()) {
			return rtn;
		}
		RowBounds rowBounds = new RowBounds(param.getOffset(), param.getPageSize());
		List<ChickenDO> list = chickenDOMapper.selectByExampleWithRowbounds(example, rowBounds);
		List<ChickenManagePageBO> boList = new ArrayList<>();
		for (ChickenDO chickenDO : list) {
			WxUserDOExample wxexample = new WxUserDOExample();
			wxexample.createCriteria().andMemberNumEqualTo(chickenDO.getMemberNum());
			WxUserBO wxUserBO = wxUserService.findByNum(chickenDO.getMemberNum());
			ChickenManagePageBO bo = ChickenConverter.convertManage(chickenDO);
			bo.setMemberName(wxUserBO == null ? "" : wxUserBO.getNickname());
			boList.add(bo);
		}
		rtn.setRecords(boList);
		return rtn;
	}

	@Override
	public ChickenBaseInfoBO getChickenByChickenNum(String memberNum, String chickenNum) {
		ChickenDOExample example = new ChickenDOExample();
		example.createCriteria().andNumEqualTo(chickenNum).andMemberNumEqualTo(memberNum);
		List<ChickenDO> chickenDOList = chickenDOMapper.selectByExample(example);
		return ChickenConverter.convertChickenBaseInfoBO(
				chickenDOList == null || chickenDOList.isEmpty() ? null : chickenDOList.get(0));
	}

	@Override
	public List<ChickenBaseInfoBO> getChickenToActiveList(int offset, int pageSize) {
		ChickenDOExample example = new ChickenDOExample();
		example.createCriteria().andStatusEqualTo(ChickStatusEnum.SLEEP.getVal())
				.andPeriodNotIn(Arrays.asList(PeriodTypeEnum.DIE.getVal()));
		List<ChickenDO> chickenDOList = chickenDOMapper.selectByExample(example);
		return ChickenConverter.converterChickenBaseInfoBOList(chickenDOList);
	}

	@Override
	public void tobeActive(List<ChickenSickParam> chickParam) {
		for (ChickenSickParam chickenSickParam : chickParam) {
			ChickenDO chickenDO = new ChickenDO();
			chickenDO.setId(chickenSickParam.getId());
			chickenDO.setStatus(ChickStatusEnum.ACTIVITY.getVal());
			chickenDO.setGmtModified(new Date());
			chickenDOMapper.updateByPrimaryKeySelective(chickenDO);
		}
	}

	
	@Override
	public int getChickenCount(String memberNum) {
		ChickenDOExample chickenDOExample = new ChickenDOExample();
    	chickenDOExample.createCriteria().andMemberNumEqualTo(memberNum);
    	int chickCount = (int)chickenDOMapper.countByExample(chickenDOExample);
		return chickCount;
	}
	
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void receiveEggs(String memberNum, String chickNum) {
        ChickenDOExample example = new ChickenDOExample();
        example.createCriteria().andNumEqualTo(chickNum);
        List<ChickenDO> list = chickenDOMapper.selectByExample(example);
        if (list.isEmpty()) {
            throw new DataNotExistException("小鸡数据不存在");
        }
        ChickenDO chicken = list.get(0);
        if (!chicken.getMemberNum().equals(memberNum)) {
            throw new IllegalOperationException("非法操作鸡蛋");
        }
        MemberEggOperatorParam memberEggOperatorParam = new MemberEggOperatorParam();
        memberEggOperatorParam.setMemberNum(memberNum);
        memberEggOperatorParam.setEggs(chicken.getHouseEggs());
        memberEggOperatorParam.setIncrease(true);
        memberDOExtendMapper.eggOperator(memberEggOperatorParam);
        
        ChickEggOperatorParam param = new ChickEggOperatorParam();
        param.setChickNum(chicken.getNum());
        param.setEggs(chicken.getHouseEggs());
        param.setIncrease(false);
        chickenDOExtendMapper.eggOperator(param);

        // 记录事件
        RangelandEventRecordParam event = new RangelandEventRecordParam();
        event.setAttrTypeEnum(EventRecordAttrTypeEnum.EGG);
        event.setDirectionEnum(EventRecordDirectionEnum.ADD);
        event.setFactorEnum(EventRecordFactorEnum.EGG_OPERATE);
        event.setMemberNum(memberNum);
        event.setSourceEnum(EventRecordSourceEnum.OWNER);
        event.setTitle(EventTitleConstant.TITLE_EGG_TAKE);
        event.setVal(chicken.getHouseEggs());
        rangelandEventRecordService.saveRangelandEventRecord(event);
    }

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void updateChickInHouse(String chickNum) {
		ChickenDOExample example = new ChickenDOExample();
		example.createCriteria().andNumEqualTo(chickNum);
		List<ChickenDO> chickenDOS = chickenDOMapper.selectByExample(example);
		if(chickenDOS.isEmpty()){
			return;
		}
		chickenDOExtendMapper.updateChickInHouse(chickNum);
	}

	@Override
	public List<ChickenBaseInfoBO> listAttenuationJoyfulVal(int offset, int pageSize) {
		RangelandConfigBaseCO configBaseCO = rangelandConfigCacheService.getCacheRangelandConfig();
		if (configBaseCO == null || configBaseCO.getHouseCleannessCOS() == null || configBaseCO.getHouseCleannessCOS().isEmpty()) {
			return null;
		}
		Collections.sort(configBaseCO.getHouseCleannessCOS(), new Comparator<HouseCleannessCO>() {
			@Override
			public int compare(HouseCleannessCO o1, HouseCleannessCO o2) {
				return o2.getHouseCleanness() - o1.getHouseCleanness();
			}
		});
		ChickenDOExample example = new ChickenDOExample();
		example.createCriteria().andCleannessValBetween(0, configBaseCO.getHouseCleannessCOS().get(0).getHouseCleanness());
		RowBounds rowBounds = new RowBounds(offset, pageSize);
		List<ChickenDO> chickenDOList = chickenDOMapper.selectByExampleWithRowbounds(example, rowBounds);
		return ChickenConverter.converterChickenBaseInfoBOList(chickenDOList);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void attenuationJoyfulVal(ChickenBaseInfoBO chickenBaseInfoBO) {
		RangelandConfigBaseCO configBaseCO = rangelandConfigCacheService.getCacheRangelandConfig();
		ChickBaseConfigCO chickBaseConfigCO = chickBaseConfigCacheService.getCacheChickBaseInfo();
		if (configBaseCO == null || configBaseCO.getHouseCleannessCOS() == null || configBaseCO.getHouseCleannessCOS().isEmpty() || chickBaseConfigCO == null) {
			return;
		}
		Collections.sort(configBaseCO.getHouseCleannessCOS(), new Comparator<HouseCleannessCO>() {
			@Override
			public int compare(HouseCleannessCO o1, HouseCleannessCO o2) {
				return o2.getHouseCleanness() - o1.getHouseCleanness();
			}
		});
		int houseCleanness = 0;
		int attenuationJoyfulVal = 0;
		int attenuationTimes = 0;
		for (HouseCleannessCO houseCleannessCO : configBaseCO.getHouseCleannessCOS()) {
			if (chickenBaseInfoBO.getCleannessVal() <= houseCleannessCO.getHouseCleanness()) {
				houseCleanness = houseCleannessCO.getHouseCleanness();
				attenuationJoyfulVal = houseCleannessCO.getAttenuationJoyfulVal();
				attenuationTimes = houseCleannessCO.getAttenuationTimes();
			}
		}
		int cacheAttenuationTimes = chickJobCacheService.getAttenuationJoyfulValTimes(chickenBaseInfoBO.getNum() + "_" + houseCleanness);
		if (cacheAttenuationTimes > attenuationTimes) {
			return;
		}

		ChickDealWithParam param = new ChickDealWithParam();
		param.setNum(chickenBaseInfoBO.getNum());
		param.setMemberNum(chickenBaseInfoBO.getMemberNum());
		param.setVal(chickBaseConfigCO.getChickInhouseReduFullyVal() * attenuationJoyfulVal);
		param.setLimitVal(0);
		chickenDOExtendMapper.doMinusJoyful(param);

		RangelandEventRecordParam eventRecordParam = new RangelandEventRecordParam();
		eventRecordParam.setAttrTypeEnum(EventRecordAttrTypeEnum.JOYFULL);
		eventRecordParam.setDirectionEnum(EventRecordDirectionEnum.REDUCE);
		eventRecordParam.setFactorEnum(EventRecordFactorEnum.CLEAN);
		eventRecordParam.setSourceEnum(EventRecordSourceEnum.NONE);
		eventRecordParam.setMemberNum(chickenBaseInfoBO.getMemberNum());
		eventRecordParam.setChickenNum(chickenBaseInfoBO.getNum());
		eventRecordParam.setVal(BigDecimal.valueOf(chickBaseConfigCO.getChickInhouseReduFullyVal() * attenuationJoyfulVal));
		RangelandEventTitleDataParam titleDataParam = new RangelandEventTitleDataParam();
		titleDataParam.setEventMiniTypeEnum(EventMiniTypeEnum.HOUSE_CLEAN_MINUS_JOYFUL);
		titleDataParam.setHouseClean(String.valueOf(houseCleanness));
		titleDataParam.setTimedMins(String.valueOf(chickBaseConfigCO.getChickInhouseReduFullMinute()));
		eventRecordParam.setRangelandEventTitleDataParam(titleDataParam);
		rangelandEventRecordService.saveRangelandEventRecord(eventRecordParam);
	}


	@Override
	public Boolean isInHouse(String chickNum) {
		ChickenDOExample example = new ChickenDOExample();
		example.createCriteria().andNumEqualTo(chickNum);
		List<ChickenDO> chickenDOS = chickenDOMapper.selectByExample(example);
		if (chickenDOS.isEmpty()) {
			return null;
		}
		ChickenDO chickenDO = chickenDOS.get(0);
		//未放养、睡眠、饿晕、生产的小鸡在鸡窝
		if (!chickenDO.getIsOutside() || ChickStatusEnum.HUNGERHALO.getVal().equals(chickenDO.getStatus()) || ChickStatusEnum.SLEEP.getVal().equals(chickenDO.getStatus()) ||
				chickenDO.getIsPregnant()) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public List<ChickenCleanessBO> getChickenInHouseReduceCleanessList(ChickenReduceCleanessParam param) {

		List<ChickenInHouseDOView> houseDOViews = chickenDOExtendMapper.getChickenInHouseReduceCleanessList(param);
		if (houseDOViews.isEmpty()) {
			return new ArrayList<>();
		}
		List<ChickenCleanessBO> cleanessBOS = new ArrayList<>();
		ChickenCleanessBO cleanessBO;
		for (ChickenInHouseDOView doView : houseDOViews) {
			cleanessBO = new ChickenCleanessBO();
			cleanessBO.setId(doView.getId());
			cleanessBO.setCleannessVal(param.getCleannessVal());
			cleanessBO.setNum(doView.getNum());
			cleanessBO.setMemberNum(doView.getMemberNum());
			cleanessBOS.add(cleanessBO);
		}
		return cleanessBOS;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void reduceCleaness(ChickenCleanessBO cleanBO) {
		ReduceCleanParam param = new ReduceCleanParam();
		param.setId(cleanBO.getId());
		param.setCleannessVal(cleanBO.getCleannessVal());
		param.setInhouseTime(new Date());
		chickenDOExtendMapper.reduceCleaness(param);
	}

	@Override
	public List<ChickenCleanessBO> getChickenInHouseList(CommonListPageParam param) {
		List<ChickenInHouseDOView> houseDOViews = chickenDOExtendMapper.getChickenInHouseList(param);
		if (houseDOViews.isEmpty()) {
			return new ArrayList<>();
		}
		List<ChickenCleanessBO> cleaneBOS = new ArrayList<>();
		ChickenCleanessBO cleanBO;
		for (ChickenInHouseDOView doView : houseDOViews) {
			cleanBO = new ChickenCleanessBO();
			cleanBO.setId(doView.getId());
			cleaneBOS.add(cleanBO);
		}
		return cleaneBOS;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void updateChickInHouseTime(Long id) {
		chickenDOExtendMapper.updateChickInHouseTime(id);
	}
	
	
	@Override
	public Boolean chicksIsInExternal(String memberNum) {
		int count = chickenDOExtendMapper.chicksIsInExternal(memberNum);
		return count>0;
	}

	@Override
	public List<ChickDOView> getChickInRangelandAndIsClean(int offset, int pageSize) {
		ChickBaseConfigCO chickBaseConfigCO = chickBaseConfigCacheService.getCacheChickBaseInfo();
		ChickenDayHenhouseQueryParam param =new ChickenDayHenhouseQueryParam();
		param.setOffset(offset);
		param.setPageSize(pageSize);
		param.setChickInRangelandAddJoyfulValMinute(chickBaseConfigCO.getChickInRangelandAddJoyfulValMinute());
		List<ChickDOView> chickDOViewList = chickenDOExtendMapper.getChickInRangelandAndIsClean(param);
		return chickDOViewList;
	}

	@Override
	public List<ChickDOView> getChickInRangelandAndIsHunger(int offset, int pageSize) {
		ChickBaseConfigCO chickBaseConfigCO = chickBaseConfigCacheService.getCacheChickBaseInfo();
		ChickenDayHenhouseQueryParam param =new ChickenDayHenhouseQueryParam();
		param.setOffset(offset);
		param.setPageSize(pageSize);
		param.setChickInRangelandAddJoyfulValMinute(chickBaseConfigCO.getChickInRangelandAddJoyfulValMinute());
		List<ChickDOView> chickDOViewList = chickenDOExtendMapper.getChickInRangelandAndIsHunger(param);
		return chickDOViewList;
	}

	@Override
	public void addCacheRangelandProfit(List<ChickDOView> lt) {
		chickJobCacheService.addCacheRangelandProfit(ChickenConverter.converterChickenCacheCOList(lt));
		for(ChickDOView view :lt){
			ChickenDO chickenDO = new ChickenDO();
			chickenDO.setJoyfulRangelandCleanTime(new Date());
			ChickenDOExample chickenDOExample = new ChickenDOExample();
			chickenDOExample.createCriteria().andMemberNumEqualTo(view.getMemberNum())
			.andNumEqualTo(view.getChickNum());
			chickenDOMapper.updateByExampleSelective(chickenDO, chickenDOExample);
		}
	}

	@Override
	public Integer getChickCount(String memberNum) {
		ChickenDOExample chickenDOExample = new ChickenDOExample();
		chickenDOExample.createCriteria().andMemberNumEqualTo(memberNum);
		return (int) chickenDOMapper.countByExample(chickenDOExample);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void doRangelandProfit(ChickenCacheOperateCO cache) {
		ChickDealWithParam param = new ChickDealWithParam();
		param.setMemberNum(cache.getMemberNum());
		param.setNum(cache.getChickNum());
		param.setVal(cache.getVal());
		param.setLimitVal(0);
		chickenDOExtendMapper.doAddJoyful(param);
		RangelandEventRecordParam event = new RangelandEventRecordParam();
		event.setAttrTypeEnum(EventRecordAttrTypeEnum.JOYFULL);
		event.setChickenNum(cache.getChickNum());
		event.setDirectionEnum(EventRecordDirectionEnum.ADD);
		event.setFactorEnum(EventRecordFactorEnum.TIME);
		event.setMemberNum(cache.getMemberNum());
		event.setSourceEnum(EventRecordSourceEnum.NONE);
		event.setVal(BigDecimal.valueOf(cache.getVal()));
		event.setEventTime(cache.getDate());
		event.setTitle(EventTitleConstant.TITLE_RANGELAND_JOYFUL_ADD);
	/*	RangelandEventTitleDataParam rangelandEventTitleDataParam = new RangelandEventTitleDataParam();
		rangelandEventTitleDataParam.setEventMiniTypeEnum(EventMiniTypeEnum.DAY_HOUSE_CLEAN_MINUS_JOYFUL);
		rangelandEventTitleDataParam.setTimedMins(new Integer(cache.getTimedMins()).toString());
		event.setRangelandEventTitleDataParam(rangelandEventTitleDataParam);*/
		rangelandEventRecordService.saveRangelandEventRecord(event);
		
	}
	
}
