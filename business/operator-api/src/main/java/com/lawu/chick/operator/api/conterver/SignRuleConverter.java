package com.lawu.chick.operator.api.conterver;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSONArray;
import com.lawu.chick.operator.api.dto.PraySignInRuleDTO;
import com.lawu.chick.operator.api.dto.PraySignRuleExtraDTO;
import com.lawu.chick.operator.api.dto.SignInRuleDTO;
import com.lawu.chick.operator.api.dto.SignRuleExtraDTO;
import com.lawu.chick.operator.api.dto.SignRulePageDTO;
import com.lawu.chick.service.bo.PraySignRuleBO;
import com.lawu.chick.service.bo.SignInRuleBO;

/**
 * @author zhangyong
 * @date 2018/4/26.
 */
public class SignRuleConverter {
    public static SignInRuleDTO convertDTO(SignInRuleBO signInRuleBO) {
        if(signInRuleBO == null){
            return new SignInRuleDTO();
        }
        SignInRuleDTO ruleDTO = new SignInRuleDTO();
        ruleDTO.setStatus(signInRuleBO.getStatus());
        ruleDTO.setGmtEnd(signInRuleBO.getGmtEnd());
        ruleDTO.setGmtStart(signInRuleBO.getGmtStart());
        ruleDTO.setId(signInRuleBO.getId());
        ruleDTO.setProductNum(signInRuleBO.getProductNum());
        ruleDTO.setProductCount(signInRuleBO.getProductCount());
        ruleDTO.setExtras(JSONArray.parseArray(signInRuleBO.getExtra(),SignRuleExtraDTO.class));
        return ruleDTO;
    }

    public static List<SignRulePageDTO> convertDTOS(List<SignInRuleBO> records) {
        if (records.isEmpty()) {
            return new ArrayList<>();
        }
        List<String> extras = new ArrayList<>();
        List<SignRulePageDTO> pageDTOS = new ArrayList<>();
        for (SignInRuleBO ruleBO : records) {
            SignRulePageDTO pageDTO = new SignRulePageDTO();
            pageDTO.setStatus(ruleBO.getStatus());
            pageDTO.setGmtEnd(ruleBO.getGmtEnd());
            pageDTO.setGmtStart(ruleBO.getGmtStart());
            pageDTO.setProductName(ruleBO.getProductName());
            pageDTO.setId(ruleBO.getId());
            pageDTO.setProductNum(ruleBO.getProductNum());
            pageDTO.setProductCount(ruleBO.getProductCount());
            List<SignRuleExtraDTO> extraDTOS = JSONArray.parseArray(ruleBO.getExtra(), SignRuleExtraDTO.class);
            for (SignRuleExtraDTO extraDTO : extraDTOS) {
                extras.add("&nbsp;满" + extraDTO.getSignDay() + "天&nbsp;奖励" + extraDTO.getProductCount() + "包" + extraDTO.getProductName() + "&nbsp;");
            }
            pageDTO.setExtras(StringUtils.join(extras, "；"));
            pageDTOS.add(pageDTO);
        }
        return pageDTOS;
    }

    public static PraySignInRuleDTO convertPrayDTO(PraySignRuleBO ruleBO) {
        if(ruleBO == null){
            return new PraySignInRuleDTO();
        }
        PraySignInRuleDTO ruleDTO = new PraySignInRuleDTO();
        ruleDTO.setStatus(ruleBO.getStatus());
        ruleDTO.setId(ruleBO.getId());
        ruleDTO.setProductNum(ruleBO.getProductNum());
        ruleDTO.setProductCount(ruleBO.getProductCount());
        ruleDTO.setIsBasisChick(ruleBO.getBasisChick());
        ruleDTO.setDay(ruleBO.getDay());
        ruleDTO.setExtras(JSONArray.parseArray(ruleBO.getExtras(),PraySignRuleExtraDTO.class));
        return ruleDTO;
    }
}
