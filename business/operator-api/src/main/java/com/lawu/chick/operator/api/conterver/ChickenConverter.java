package com.lawu.chick.operator.api.conterver;

import java.util.ArrayList;
import java.util.List;

import com.lawu.chick.operator.api.dto.ChickenManagePageDTO;
import com.lawu.chick.service.bo.ChickenManagePageBO;

public class ChickenConverter {

    public static List<ChickenManagePageDTO> converterList(List<ChickenManagePageBO> chickenBOList) {
        if (chickenBOList == null) {
            return null;
        }
        List<ChickenManagePageDTO> list = new ArrayList<>();
        for (ChickenManagePageBO chickenManagePageBO : chickenBOList) {
            list.add(convertManage(chickenManagePageBO));
        }
        return list;
    }

   
	
	public static ChickenManagePageDTO convertManage(ChickenManagePageBO chickenManageBO) {
		if (chickenManageBO == null) {
            return null;
        }
		ChickenManagePageDTO chickenManagePageDTO = new ChickenManagePageDTO();
		chickenManagePageDTO.setId(chickenManageBO.getId());
		chickenManagePageDTO.setName(chickenManageBO.getName());
		chickenManagePageDTO.setNum(chickenManageBO.getNum());
		chickenManagePageDTO.setGmtCreate(chickenManageBO.getGmtCreate());
		chickenManagePageDTO.setJoyfulVal(chickenManageBO.getJoyfulVal());
		chickenManagePageDTO.setGrowthVal(chickenManageBO.getGrowthVal());
		chickenManagePageDTO.setFullVal(chickenManageBO.getFullVal());
		chickenManagePageDTO.setLayEggs(chickenManageBO.getLayEggs());
		chickenManagePageDTO.setLifeStartTime(chickenManageBO.getLifeStartTime());
		chickenManagePageDTO.setIsOutside(chickenManageBO.getIsOutside());
		chickenManagePageDTO.setLifeStartTime(chickenManageBO.getLifeStartTime());
		chickenManagePageDTO.setPeriodType(chickenManageBO.getPeriodType());
		chickenManagePageDTO.setStatusEnum(chickenManageBO.getStatusEnum());
		chickenManagePageDTO.setMemberName(chickenManageBO.getMemberName());
        return chickenManagePageDTO;
	}
}
