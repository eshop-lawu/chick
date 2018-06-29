package com.lawu.chick.service.converter;

import java.util.ArrayList;
import java.util.List;

import com.lawu.chick.repository.domain.SignInRuleDO;
import com.lawu.chick.service.bo.SignInRuleBO;
import com.lawu.chick.service.enums.StatusEnum;

/**
 * @author zhangyong
 * @date 2018/4/26.
 */
public class SignInRuleConverter {
    public static SignInRuleBO convertBO(SignInRuleDO ruleDO) {
        if (ruleDO == null) {
            return null;
        }
        SignInRuleBO ruleBO = new SignInRuleBO();
        ruleBO.setId(ruleDO.getId());
        ruleBO.setProductNum(ruleDO.getProductNum());
        ruleBO.setGmtStart(ruleDO.getGmtStart());
        ruleBO.setGmtEnd(ruleDO.getGmtEnd());
        ruleBO.setProductCount(ruleDO.getProductCount());
        ruleBO.setStatus(StatusEnum.getEnum(ruleDO.getStatus()));
        ruleBO.setExtra(ruleDO.getExtra());
        return ruleBO;
    }

    public static List<SignInRuleBO> convertBOS(List<SignInRuleDO> ruleDOS) {

        if (ruleDOS.isEmpty()) {
            return new ArrayList<>();
        }
        List<SignInRuleBO> ruleBOS = new ArrayList<>();
        for (SignInRuleDO ruleDO : ruleDOS) {
            SignInRuleBO ruleBO = new SignInRuleBO();
            ruleBO.setId(ruleDO.getId());
            ruleBO.setProductNum(ruleDO.getProductNum());
            ruleBO.setGmtStart(ruleDO.getGmtStart());
            ruleBO.setGmtEnd(ruleDO.getGmtEnd());
            ruleBO.setStatus(StatusEnum.getEnum(ruleDO.getStatus()));
            ruleBO.setExtra(ruleDO.getExtra());
            ruleBO.setProductCount(ruleDO.getProductCount());
            ruleBOS.add(ruleBO);
        }
        return ruleBOS;
    }
}
