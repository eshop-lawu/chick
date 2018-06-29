package com.lawu.chick.api.converter;

import com.lawu.chick.api.dto.ChickHouseInfoDTO;
import com.lawu.chick.api.dto.RangelandInfoDTO;
import com.lawu.chick.service.bo.ChickHouseInfoBO;
import com.lawu.chick.service.bo.RangelandInfoBO;

/**  
 * @author lihj
 * @date 2018年4月26日
 */
public class RangelandConvert {

	public static RangelandInfoDTO convertRangelandInfoDTO(RangelandInfoBO bo) {
		RangelandInfoDTO dto =new RangelandInfoDTO();
		dto.setChickInfo(ChickenConverter.converterChickenBaseInfoDTOList(bo.getChickInfo()));
		dto.setExternalCleanness(bo.getExternalCleanness());
		dto.setHouseCleanness(bo.getHouseCleanness());
		dto.setType(bo.getType());
		dto.setTotalEggs(bo.getTotalEggs());
		return dto;
	}

	public static ChickHouseInfoDTO convertChickHouseInfoDTO(ChickHouseInfoBO bo) {
		ChickHouseInfoDTO dto = new ChickHouseInfoDTO();
		dto.setChickInfo(ChickenConverter.converterChickenBaseInfoDTOList(bo.getChickInfo()));
		dto.setExternalCleanness(bo.getExternalCleanness());
		dto.setHouseCleanness(bo.getHouseCleanness());
		dto.setLayEggs(bo.getLayEggs());
		dto.setType(bo.getType());
		dto.setMaxHouseEggs(bo.getMaxHouseEggs());
		return dto;
	}

}
