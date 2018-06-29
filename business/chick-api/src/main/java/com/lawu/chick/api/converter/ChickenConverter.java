package com.lawu.chick.api.converter;

import com.lawu.chick.api.dto.ChickFeedReturnDTO;
import com.lawu.chick.api.dto.ChickenBaseInfoDTO;
import com.lawu.chick.api.dto.CureTaskDTO;
import com.lawu.chick.api.dto.CureTaskRtnDTO;
import com.lawu.chick.api.enums.ChickStatusGeneralEnum;
import com.lawu.chick.service.bo.ChickenBaseInfoBO;
import com.lawu.chick.service.bo.CureTaskBO;
import com.lawu.chick.service.bo.CureTaskRtnBO;
import com.lawu.chick.service.enums.PeriodTypeEnum;

import java.util.ArrayList;
import java.util.List;

/**
 * 小鸡转化类
 */
public class ChickenConverter {

    public static List<ChickenBaseInfoDTO> converterChickenBaseInfoDTOList(List<ChickenBaseInfoBO> chickenBaseInfoBOList) {
        if (chickenBaseInfoBOList == null) {
            return null;
        }
        List<ChickenBaseInfoDTO> chickenBaseInfoDTOList = new ArrayList<>();
        for (ChickenBaseInfoBO chickenBaseInfoBO : chickenBaseInfoBOList) {
            chickenBaseInfoDTOList.add(converterChickenBaseInfoDTO(chickenBaseInfoBO));
        }
        return chickenBaseInfoDTOList;
    }

    public static ChickenBaseInfoDTO converterChickenBaseInfoDTO(ChickenBaseInfoBO chickenBaseInfoBO) {
        if (chickenBaseInfoBO == null) {
            return null;
        }
        ChickenBaseInfoDTO chickenBaseInfoDTO = new ChickenBaseInfoDTO();
        chickenBaseInfoDTO.setId(chickenBaseInfoBO.getId());
        chickenBaseInfoDTO.setName(chickenBaseInfoBO.getName());
        chickenBaseInfoDTO.setNum(chickenBaseInfoBO.getNum());
        chickenBaseInfoDTO.setMemberNum(chickenBaseInfoBO.getMemberNum());
        chickenBaseInfoDTO.setPeriod(PeriodTypeEnum.getEnum(chickenBaseInfoBO.getPeriod()));
        chickenBaseInfoDTO.setStatus(ChickStatusGeneralEnum.getEnum(chickenBaseInfoBO.getStatus()));
        if (chickenBaseInfoBO.getPregnant()) {
            chickenBaseInfoDTO.setStatus(ChickStatusGeneralEnum.PRODUCE);
        }
        chickenBaseInfoDTO.setOutside(chickenBaseInfoBO.getOutside());
        chickenBaseInfoDTO.setJoyfulVal(chickenBaseInfoBO.getJoyfulVal());
        chickenBaseInfoDTO.setGrowthVal(chickenBaseInfoBO.getGrowthVal());
        chickenBaseInfoDTO.setFullVal(chickenBaseInfoBO.getFullVal());
        chickenBaseInfoDTO.setInHouse(chickenBaseInfoBO.getInHouse());
        chickenBaseInfoDTO.setCleannessVal(chickenBaseInfoBO.getCleannessVal());
        chickenBaseInfoDTO.setHouseEggs(chickenBaseInfoBO.getHouseEggs());
        return chickenBaseInfoDTO;
    }

    public static CureTaskRtnDTO convertCureTaskRtnDTO(CureTaskRtnBO cureTaskRtnBO) {
        CureTaskRtnDTO cureTaskRtnDTO = new CureTaskRtnDTO();
        if (cureTaskRtnBO == null) {
            return null;
        }
        List<CureTaskBO> cureTaskBOList = cureTaskRtnBO.getCureTaskBOList();
        List<CureTaskDTO> cureTaskDTOList = new ArrayList<>();
        for (CureTaskBO cureTaskBO : cureTaskBOList) {
            CureTaskDTO cureTaskDTO = new CureTaskDTO();
            cureTaskDTO.setChickenCureTaskTypeEnum(cureTaskBO.getChickenCureTaskTypeEnum());
            cureTaskDTO.setFinish(cureTaskBO.getFinish());
            cureTaskDTO.setNumber(cureTaskBO.getNumber());
            cureTaskDTOList.add(cureTaskDTO);
        }
        cureTaskRtnDTO.setCureTaskDTOList(cureTaskDTOList);
        cureTaskRtnDTO.setGoalTaskCount(cureTaskRtnBO.getGoalTaskCount());
        cureTaskRtnDTO.setFinishTaskCount(cureTaskRtnBO.getFinishTaskCount());
        return cureTaskRtnDTO;
    }

	public static ChickFeedReturnDTO convertChickFeedReturnDTO(ChickenBaseInfoBO bo) {
		ChickFeedReturnDTO dto =new ChickFeedReturnDTO();
		dto.setFullVal(bo.getFullVal());
		dto.setGrowthVal(bo.getGrowthVal());
		dto.setJoyfulVal(bo.getJoyfulVal());
		dto.setGiveFlag(bo.isGiveFlag());
		dto.setGiveFoodsCount(bo.getGiveFoodsCount());
		dto.setGiveFoodsName(bo.getGiveFoodsName());
		return dto;
	}
}
