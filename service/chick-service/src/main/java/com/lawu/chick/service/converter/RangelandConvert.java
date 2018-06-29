package com.lawu.chick.service.converter;

import java.math.BigDecimal;
import java.util.List;

import com.lawu.chick.repository.domain.RangelandDO;
import com.lawu.chick.service.bo.RangelandInfoBO;
import com.lawu.chick.service.enums.RangelandDay;

/**  
 * @author lihj
 * @date 2018年4月26日
 */
public class RangelandConvert {

	public static RangelandInfoBO convertRangelandInfoBO(List<RangelandDO> rangelandDO,RangelandDay type) {
		BigDecimal totalEggs=BigDecimal.ZERO;
		RangelandInfoBO bo =new RangelandInfoBO();
		bo.setExternalCleanness(rangelandDO.get(0).getExternalCleanness());
		bo.setType(type);
		bo.setTotalEggs(totalEggs);
		return bo;
	}

}
