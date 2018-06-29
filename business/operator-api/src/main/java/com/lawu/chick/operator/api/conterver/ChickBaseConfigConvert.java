package com.lawu.chick.operator.api.conterver;

import java.util.ArrayList;
import java.util.List;

import com.lawu.chick.operator.api.dto.ChickBaseConfigDTO;
import com.lawu.chick.operator.api.dto.HouseCleannessDTO;
import com.lawu.chick.operator.api.dto.RangelandConfigDTO;
import com.lawu.chick.service.bo.ChickBaseConfigBO;
import com.lawu.chick.service.bo.HouseCleannessBO;
import com.lawu.chick.service.bo.RangelandConfigBO;
import com.lawu.utils.DateUtil;

/**  
 * @author lihj
 * @date 2018年4月25日
 */
public class ChickBaseConfigConvert {

	public static ChickBaseConfigDTO convertChickBaseConfigDTO(ChickBaseConfigBO bo) {
		ChickBaseConfigDTO chick =new ChickBaseConfigDTO();
		if(null ==bo){
			chick.setSettingStartTime("");
			return chick;
		}
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
		chick.setChickHungerVal(bo.getChickHungerVal());
		List<String> lt =bo.getChickEggProdTime();
		if(null!=lt){
			List<String> list = new ArrayList<>();
			for(String str:lt){
				list.add(str.split(":")[0]);
			}
			chick.setChickEggProdTime(list);
		}
		if(null != bo.getSettingStartTime()){
			chick.setSettingStartTime(DateUtil.getDateTimeFormat(bo.getSettingStartTime()));
		}
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
		return chick;
	}

	/**
	 * @param bo
	 * @return
	 */
	public static RangelandConfigDTO convertRangelandConfigDTO(RangelandConfigBO bo) {
		if(bo == null){
			return new RangelandConfigDTO();
		}
		RangelandConfigDTO config = new RangelandConfigDTO();
		config.setChickDeclineExternalVal(bo.getChickDeclineExternalVal());
		config.setChickDeclineExternalValMinute(bo.getChickDeclineExternalValMinute());
		config.setChickDeclineHouseVal(bo.getChickDeclineHouseVal());
		config.setChickDeclineHouseValMinute(bo.getChickDeclineHouseValMinute());
		config.setRangelandEffectiveTime(bo.getRangelandEffectiveTime());
		config.setFriendSweepExternalVal(bo.getFriendSweepExternalVal());
		config.setRangelandMaxCleannessVal(bo.getRangelandMaxCleannessVal());
		config.setHouseCleannessDTOS(convertHouseCleannessDTO(bo.getHouseCleannessBOS()));
		return config;
	}

	public static List<HouseCleannessDTO> convertHouseCleannessDTO(List<HouseCleannessBO> houseCleannessBOS) {
		List<HouseCleannessDTO> houseCleannessDTOS = new ArrayList<>();
		if (houseCleannessBOS == null || houseCleannessBOS.isEmpty()) {
			return houseCleannessDTOS;
		}

		for (HouseCleannessBO houseCleannessBO : houseCleannessBOS) {
			HouseCleannessDTO houseCleannessDTO = new HouseCleannessDTO();
			houseCleannessDTO.setHouseCleanness(houseCleannessBO.getHouseCleanness());
			houseCleannessDTO.setAttenuationJoyfulVal(houseCleannessBO.getAttenuationJoyfulVal());
			houseCleannessDTO.setAttenuationTimes(houseCleannessBO.getAttenuationTimes());
			houseCleannessDTOS.add(houseCleannessDTO);
		}
		return houseCleannessDTOS;
	}

}
