package com.lawu.chick.api.converter;

import java.util.ArrayList;
import java.util.List;

import com.lawu.chick.api.dto.AwardDTO;
import com.lawu.chick.api.dto.PrayAwardDTO;
import com.lawu.chick.api.dto.PraySignAwardDTO;
import com.lawu.chick.service.bo.AwardBO;
import com.lawu.chick.service.bo.PraySignAwardBO;

/**
 * @Description
 * @author zhangrc
 * @date 2018年6月15日
 */
public class PraySignRecordConverter {
	
	public static PraySignAwardDTO convertDTO(PraySignAwardBO praySignAwardBO) {
		PraySignAwardDTO praySignAwardDTO = new PraySignAwardDTO();
		praySignAwardDTO.setIsDraw(praySignAwardBO.getIsDraw());
		praySignAwardDTO.setIsSign(praySignAwardBO.getIsSign());
		praySignAwardDTO.setSignId(praySignAwardBO.getSignId());
		List<AwardDTO> awards = new ArrayList<>();
		if (praySignAwardBO.getAwards() != null) {
			List<AwardBO> list = praySignAwardBO.getAwards();
			for (AwardBO awardBO : list) {
				AwardDTO awardDTO = new AwardDTO();
				awardDTO.setAwardCount(awardBO.getAwardCount());
				awardDTO.setImgPath(awardBO.getImgPath());
				awardDTO.setIsExtra(awardBO.getIsExtra());
				awardDTO.setName(awardBO.getName());
				awards.add(awardDTO);
			}
		}
		praySignAwardDTO.setAwards(awards);
		return praySignAwardDTO;
	}

	public static PrayAwardDTO convertPrayAwardDTO(List<AwardBO> awardBOS) {
		PrayAwardDTO prayAwardDTO = new PrayAwardDTO();
		if (awardBOS.isEmpty()) {
			prayAwardDTO.setAwardDTOS(new ArrayList<>());
		}
		List<AwardDTO> awards = new ArrayList<>();
		AwardDTO awardDTO;
		for (AwardBO awardBO : awardBOS) {
			awardDTO = new AwardDTO();
			awardDTO.setAwardCount(awardBO.getAwardCount());
			awardDTO.setImgPath(awardBO.getImgPath());
			awardDTO.setIsExtra(awardBO.getIsExtra());
			awardDTO.setName(awardBO.getName());
			awards.add(awardDTO);
		}
		prayAwardDTO.setAwardDTOS(awards);
		return prayAwardDTO;
	}
}
