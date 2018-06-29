package com.lawu.chick.service.converter;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.lawu.chick.cache.service.co.ChickBaseConfigCO;
import com.lawu.chick.cache.service.co.HouseCleannessCO;
import com.lawu.chick.cache.service.co.RangelandConfigBaseCO;
import com.lawu.chick.repository.domain.SysConfigDO;
import com.lawu.chick.service.bo.ChickBaseConfigBO;
import com.lawu.chick.service.bo.HouseCleannessBO;
import com.lawu.chick.service.bo.RangelandConfigBaseBO;
import com.lawu.chick.service.param.ChickBaseConfigParam;
import com.lawu.utils.DateUtil;

/**
 * @author lihj
 * @date 2018年4月25日
 */
public class ChickBaseConfigConverter {

	public static ChickBaseConfigBO convertChickBaseConfigBO(ChickBaseConfigParam param) {
		ChickBaseConfigBO chick =new ChickBaseConfigBO();
		chick.setChickDefauleName(param.getChickDefauleName());
		chick.setChickAdoptionCount(param.getChickAdoptionCount());
		chick.setChickMaxAdoptionCount(param.getChickMaxAdoptionCount());
		chick.setChickInitGrowthVal(param.getChickInitGrowthVal());
		chick.setChickMaxGrowthVal(param.getChickMaxGrowthVal());
		chick.setChickSemiMatureVal(param.getChickSemiMatureVal());
		chick.setOwnerFeedingGrowthVal(param.getOwnerFeedingGrowthVal());
		chick.setOwnerFeedingMaxDayTimes(param.getOwnerFeedingMaxDayTimes());
		chick.setChickInhouseReduFullMinute(param.getChickInhouseReduFullMinute());
		chick.setChickInhouseReduFullyVal(param.getChickInhouseReduFullyVal());
		chick.setChickInitFullVal(param.getChickInitFullVal());
		chick.setChickMaxFullVal(param.getChickMaxFullVal());
		chick.setChickDormancyMinFullVal(param.getChickDormancyMinFullVal());
		chick.setChickDeclineFullValMinute(param.getChickDeclineFullValMinute());
		chick.setChickDeclineFullVal(param.getChickDeclineFullVal());
		chick.setChickInitJoyfulVal(param.getChickInitJoyfulVal());
		/*chick.setChickDeclineJoyfulValMinute(param.getChickDeclineJoyfulValMinute());*/
		chick.setChickDeclineJoyfulVal(param.getChickDeclineJoyfulVal());
		chick.setChickInRangelandAddJoyfulValMinute(param.getChickInRangelandAddJoyfulValMinute());
		chick.setChickInRangelandAddJoyfulVal(param.getChickInRangelandAddJoyfulVal());
		chick.setChickStartActivitiesTime(param.getChickStartActivitiesTime());
		chick.setChickEndActivitiesTime(param.getChickEndActivitiesTime());
		chick.setChickEggProdTime(param.getChickEggProdTime());
		chick.setSettingStartTime(DateUtil.getDateTimeFormat(param.getSettingStartTime()));
		chick.setChickLayingEggsTime(param.getChickLayingEggsTime());
		chick.setChickWaitSick(param.getChickWaitSick());
		chick.setChickRangelandCleanVal(param.getChickRangelandCleanVal());
		chick.setChickDayGrowthVal(param.getChickDayGrowthVal());
		/*chick.setChickDeclineMinusJoyfulVal(param.getChickDeclineMinusJoyfulVal());*/
		chick.setFinishTaskInviteCount(param.getFinishTaskInviteCount());
		chick.setFinishTaskSalesCount(param.getFinishTaskSalesCount());
		chick.setFinishTaskHelpCount(param.getFinishTaskHelpCount());
		chick.setFinishTaskTradeCount(param.getFinishTaskTradeCount());
		chick.setFinishTaskLessTCount(param.getFinishTaskLessTCount());
		chick.setHelpFriendFeedCount(param.getHelpFriendFeedCount());
		chick.setGetHelpFriendFeedForage(param.getGetHelpFriendFeedForage());
		chick.setGetMaxHelpFriendFeedForage(param.getGetMaxHelpFriendFeedForage());
		chick.setChickHungerVal(param.getChickHungerVal());
		return chick;
	}

	public static ChickBaseConfigBO convertSysConfigDO(List<SysConfigDO> list) {
		ChickBaseConfigBO chick =new ChickBaseConfigBO();
		if(null==list || list.size()==0){
			return chick;
		}
		ChickBaseConfigBO bo =JSON.parseObject(list.get(0).getChickenConfig(),ChickBaseConfigBO.class);
		return bo;
	}

	public static ChickBaseConfigCO convertChickBaseConfigCO(ChickBaseConfigBO bo) {
		ChickBaseConfigCO chick =new ChickBaseConfigCO();
		chick.setChickDefauleName(bo.getChickDefauleName());
		chick.setChickAdoptionCount(bo.getChickAdoptionCount());
		chick.setChickMaxAdoptionCount(bo.getChickMaxAdoptionCount());
		chick.setChickInitGrowthVal(bo.getChickInitGrowthVal());
		chick.setChickMaxGrowthVal(bo.getChickMaxGrowthVal());
		chick.setChickSemiMatureVal(bo.getChickSemiMatureVal());
		chick.setOwnerFeedingGrowthVal(bo.getOwnerFeedingGrowthVal());
		chick.setOwnerFeedingMaxDayTimes(bo.getOwnerFeedingMaxDayTimes());
		chick.setChickInhouseReduFullMinute(bo.getChickInhouseReduFullMinute());
		chick.setChickInhouseReduFullyVal(bo.getChickInhouseReduFullyVal());
		chick.setChickInitFullVal(bo.getChickInitFullVal());
		chick.setChickMaxFullVal(bo.getChickMaxFullVal());
		chick.setChickDormancyMinFullVal(bo.getChickDormancyMinFullVal());
		chick.setChickDeclineFullValMinute(bo.getChickDeclineFullValMinute());
		chick.setChickDeclineFullVal(bo.getChickDeclineFullVal());
		chick.setChickInitJoyfulVal(bo.getChickInitJoyfulVal());
		chick.setChickDeclineJoyfulVal(bo.getChickDeclineJoyfulVal());
		chick.setChickInRangelandAddJoyfulValMinute(bo.getChickInRangelandAddJoyfulValMinute());
		chick.setChickInRangelandAddJoyfulVal(bo.getChickInRangelandAddJoyfulVal());
		chick.setChickStartActivitiesTime(bo.getChickStartActivitiesTime());
		chick.setChickEndActivitiesTime(bo.getChickEndActivitiesTime());
		chick.setChickEggProdTime(bo.getChickEggProdTime());
		chick.setSettingStartTime(bo.getSettingStartTime());
		chick.setChickLayingEggsTime(bo.getChickLayingEggsTime());
		chick.setChickWaitSick(bo.getChickWaitSick());
		chick.setChickRangelandCleanVal(bo.getChickRangelandCleanVal());
		chick.setChickDayGrowthVal(bo.getChickDayGrowthVal());
		chick.setFinishTaskInviteCount(bo.getFinishTaskInviteCount());
		chick.setFinishTaskSalesCount(bo.getFinishTaskSalesCount());
		chick.setFinishTaskHelpCount(bo.getFinishTaskHelpCount());
		chick.setFinishTaskTradeCount(bo.getFinishTaskTradeCount());
		chick.setFinishTaskLessTCount(bo.getFinishTaskLessTCount());
		chick.setHelpFriendFeedCount(bo.getHelpFriendFeedCount());
		chick.setGetHelpFriendFeedForage(bo.getGetHelpFriendFeedForage());
		chick.setGetMaxHelpFriendFeedForage(bo.getGetMaxHelpFriendFeedForage());
		chick.setChickHungerVal(bo.getChickHungerVal());
		return chick;
	}

	public static ChickBaseConfigBO convertCacheChickBaseConfigBO(ChickBaseConfigCO co) {
		ChickBaseConfigBO chick =new ChickBaseConfigBO();
		chick.setChickDefauleName(co.getChickDefauleName());
		chick.setChickAdoptionCount(co.getChickAdoptionCount());
		chick.setChickMaxAdoptionCount(co.getChickMaxAdoptionCount());
		chick.setChickInitGrowthVal(co.getChickInitGrowthVal());
		chick.setChickMaxGrowthVal(co.getChickMaxGrowthVal());
		chick.setChickSemiMatureVal(co.getChickSemiMatureVal());
		chick.setOwnerFeedingGrowthVal(co.getOwnerFeedingGrowthVal());
		chick.setOwnerFeedingMaxDayTimes(co.getOwnerFeedingMaxDayTimes());
		chick.setChickInhouseReduFullMinute(co.getChickInhouseReduFullMinute());
		chick.setChickInhouseReduFullyVal(co.getChickInhouseReduFullyVal());
		chick.setChickInitFullVal(co.getChickInitFullVal());
		chick.setChickMaxFullVal(co.getChickMaxFullVal());
		chick.setChickDormancyMinFullVal(co.getChickDormancyMinFullVal());
		chick.setChickDeclineFullValMinute(co.getChickDeclineFullValMinute());
		chick.setChickDeclineFullVal(co.getChickDeclineFullVal());
		chick.setChickInitJoyfulVal(co.getChickInitJoyfulVal());
		chick.setChickDeclineJoyfulVal(co.getChickDeclineJoyfulVal());
		chick.setChickInRangelandAddJoyfulValMinute(co.getChickInRangelandAddJoyfulValMinute());
		chick.setChickInRangelandAddJoyfulVal(co.getChickInRangelandAddJoyfulVal());
		chick.setChickStartActivitiesTime(co.getChickStartActivitiesTime());
		chick.setChickEndActivitiesTime(co.getChickEndActivitiesTime());
		chick.setChickEggProdTime(co.getChickEggProdTime());
		chick.setSettingStartTime(co.getSettingStartTime());
		chick.setChickLayingEggsTime(co.getChickLayingEggsTime());
		chick.setChickWaitSick(co.getChickWaitSick());
		chick.setChickRangelandCleanVal(co.getChickRangelandCleanVal());
		chick.setChickDayGrowthVal(co.getChickDayGrowthVal());
		chick.setFinishTaskInviteCount(co.getFinishTaskInviteCount());
		chick.setFinishTaskSalesCount(co.getFinishTaskSalesCount());
		chick.setFinishTaskHelpCount(co.getFinishTaskHelpCount());
		chick.setFinishTaskTradeCount(co.getFinishTaskTradeCount());
		chick.setFinishTaskLessTCount(co.getFinishTaskLessTCount());
		chick.setHelpFriendFeedCount(co.getHelpFriendFeedCount());
		chick.setGetHelpFriendFeedForage(co.getGetHelpFriendFeedForage());
		chick.setGetMaxHelpFriendFeedForage(co.getGetMaxHelpFriendFeedForage());
		chick.setChickHungerVal(co.getChickHungerVal());
		return chick;
	}

	public static RangelandConfigBaseCO convertCacheChickBaseConfigCO(RangelandConfigBaseBO bo) {
		RangelandConfigBaseCO config = new RangelandConfigBaseCO();
		config.setChickDeclineExternalVal(bo.getChickDeclineExternalVal());
		config.setChickDeclineExternalValMinute(bo.getChickDeclineExternalValMinute());
		config.setChickDeclineHouseVal(bo.getChickDeclineHouseVal());
		config.setChickDeclineHouseValMinute(bo.getChickDeclineHouseValMinute());
		config.setFriendSweepExternalVal(bo.getFriendSweepExternalVal());
		config.setRangelandMaxCleannessVal(bo.getRangelandMaxCleannessVal());
		List<HouseCleannessCO> houseCleannessCOS = new ArrayList<>();
		for (HouseCleannessBO houseCleannessBO : bo.getHouseCleannessBOS()) {
			HouseCleannessCO houseCleannessCO = new HouseCleannessCO();
			houseCleannessCO.setHouseCleanness(houseCleannessBO.getHouseCleanness());
			houseCleannessCO.setAttenuationJoyfulVal(houseCleannessBO.getAttenuationJoyfulVal());
			houseCleannessCO.setAttenuationTimes(houseCleannessBO.getAttenuationTimes());
			houseCleannessCOS.add(houseCleannessCO);
		}
		config.setHouseCleannessCOS(houseCleannessCOS);
		return config;
	}

	public static RangelandConfigBaseBO convertCacheChickBaseConfigBO(RangelandConfigBaseCO co) {
		RangelandConfigBaseBO config = new RangelandConfigBaseBO();
		config.setChickDeclineExternalValMinute(co.getChickDeclineExternalValMinute());
		config.setChickDeclineExternalVal(co.getChickDeclineExternalVal());
		config.setChickDeclineHouseVal(co.getChickDeclineHouseVal());
		config.setChickDeclineHouseValMinute(co.getChickDeclineHouseValMinute());
		config.setFriendSweepExternalVal(co.getFriendSweepExternalVal());
		config.setRangelandMaxCleannessVal(co.getRangelandMaxCleannessVal());
		List<HouseCleannessBO> houseCleannessBOS = new ArrayList<>();
		for (HouseCleannessCO houseCleannessCO : co.getHouseCleannessCOS()) {
			HouseCleannessBO houseCleannessBO = new HouseCleannessBO();
			houseCleannessBO.setHouseCleanness(houseCleannessCO.getHouseCleanness());
			houseCleannessBO.setAttenuationJoyfulVal(houseCleannessCO.getAttenuationJoyfulVal());
			houseCleannessBO.setAttenuationTimes(houseCleannessCO.getAttenuationTimes());
			houseCleannessBOS.add(houseCleannessBO);
		}
		config.setHouseCleannessBOS(houseCleannessBOS);
		return config;
	}

	public static ChickBaseConfigCO convertChickBaseConfigCOParam(ChickBaseConfigParam bo) {
		ChickBaseConfigCO chick =new ChickBaseConfigCO();
		chick.setChickDefauleName(bo.getChickDefauleName());
		chick.setChickAdoptionCount(bo.getChickAdoptionCount());
		chick.setChickMaxAdoptionCount(bo.getChickMaxAdoptionCount());
		chick.setChickInitGrowthVal(bo.getChickInitGrowthVal());
		chick.setChickMaxGrowthVal(bo.getChickMaxGrowthVal());
		chick.setChickSemiMatureVal(bo.getChickSemiMatureVal());
		chick.setOwnerFeedingGrowthVal(bo.getOwnerFeedingGrowthVal());
		chick.setOwnerFeedingMaxDayTimes(bo.getOwnerFeedingMaxDayTimes());
		chick.setChickInhouseReduFullMinute(bo.getChickInhouseReduFullMinute());
		chick.setChickInhouseReduFullyVal(bo.getChickInhouseReduFullyVal());
		chick.setChickInitFullVal(bo.getChickInitFullVal());
		chick.setChickMaxFullVal(bo.getChickMaxFullVal());
		chick.setChickDormancyMinFullVal(bo.getChickDormancyMinFullVal());
		chick.setChickDeclineFullValMinute(bo.getChickDeclineFullValMinute());
		chick.setChickDeclineFullVal(bo.getChickDeclineFullVal());
		chick.setChickInitJoyfulVal(bo.getChickInitJoyfulVal());
		/*chick.setChickDeclineJoyfulValMinute(bo.getChickDeclineJoyfulValMinute());*/
		chick.setChickDeclineJoyfulVal(bo.getChickDeclineJoyfulVal());
		chick.setChickInRangelandAddJoyfulValMinute(bo.getChickInRangelandAddJoyfulValMinute());
		chick.setChickInRangelandAddJoyfulVal(bo.getChickInRangelandAddJoyfulVal());
		chick.setChickStartActivitiesTime(bo.getChickStartActivitiesTime());
		chick.setChickEndActivitiesTime(bo.getChickEndActivitiesTime());
		chick.setChickEggProdTime(bo.getChickEggProdTime());
		chick.setChickLayingEggsTime(bo.getChickLayingEggsTime());
		chick.setChickWaitSick(bo.getChickWaitSick());
		chick.setChickRangelandCleanVal(bo.getChickRangelandCleanVal());
		chick.setChickDayGrowthVal(bo.getChickDayGrowthVal());
		/*chick.setChickDeclineMinusJoyfulVal(bo.getChickDeclineMinusJoyfulVal());*/
		chick.setFinishTaskInviteCount(bo.getFinishTaskInviteCount());
		chick.setFinishTaskSalesCount(bo.getFinishTaskSalesCount());
		chick.setFinishTaskHelpCount(bo.getFinishTaskHelpCount());
		chick.setFinishTaskTradeCount(bo.getFinishTaskTradeCount());
		chick.setFinishTaskLessTCount(bo.getFinishTaskLessTCount());
		chick.setHelpFriendFeedCount(bo.getHelpFriendFeedCount());
		chick.setGetHelpFriendFeedForage(bo.getGetHelpFriendFeedForage());
		chick.setGetMaxHelpFriendFeedForage(bo.getGetMaxHelpFriendFeedForage());
		chick.setChickHungerVal(bo.getChickHungerVal());
		return chick;
	}
	
}
