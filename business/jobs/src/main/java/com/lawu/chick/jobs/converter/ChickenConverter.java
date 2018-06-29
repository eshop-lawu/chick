package com.lawu.chick.jobs.converter;

import com.lawu.chick.service.bo.ChickenBaseInfoBO;
import com.lawu.chick.service.bo.ChickenGrowthBO;
import com.lawu.chick.service.param.ChickenFullvalLessParam;
import com.lawu.chick.service.param.ChickenGrowthParam;
import com.lawu.chick.service.param.ChickenSickParam;

import java.util.ArrayList;
import java.util.List;

public class ChickenConverter {
    public static List<ChickenSickParam> converterChickSickParamList(List<ChickenBaseInfoBO> chickenBaseInfoBOList) {
        if (chickenBaseInfoBOList == null) {
            return null;
        }
        List<ChickenSickParam> chickenSickParamList = new ArrayList<>();
        for (ChickenBaseInfoBO chickenBaseInfoBO : chickenBaseInfoBOList){
            ChickenSickParam chickenSickParam = converterChickSickDTO(chickenBaseInfoBO);
            chickenSickParamList.add(chickenSickParam);
        }
        return chickenSickParamList;
    }

    private static ChickenSickParam converterChickSickDTO(ChickenBaseInfoBO chickenBaseInfoBO) {
        if (chickenBaseInfoBO == null) {
            return null;
        }
        ChickenSickParam chickenSickParam = new ChickenSickParam();
        chickenSickParam.setId(chickenBaseInfoBO.getId());
        return chickenSickParam;
    }

	public static List<ChickenGrowthParam> convertChickenGrowthParam(List<ChickenGrowthBO> list) {
		if(null == list){
			return null;
		}
		List<ChickenGrowthParam> lt=new ArrayList<ChickenGrowthParam>();
		for(ChickenGrowthBO chick : list){
			ChickenGrowthParam param =new ChickenGrowthParam();
			param.setId(chick.getId());
			param.setMemberNum(chick.getMemberNum());
			param.setNum(chick.getNum());
			lt.add(param);
		}
		return lt;
	}

	public static List<ChickenFullvalLessParam> convertChickenFullvalLessParam(List<ChickenBaseInfoBO> list) {
		if(null == list){
			return null;
		}
		
		return null;
	}
}
