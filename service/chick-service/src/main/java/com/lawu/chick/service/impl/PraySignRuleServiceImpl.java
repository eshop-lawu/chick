package com.lawu.chick.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.lawu.chick.cache.service.PraySignService;
import com.lawu.chick.cache.service.co.PraySignRuleCO;
import com.lawu.chick.repository.domain.PraySignRuleDO;
import com.lawu.chick.repository.mapper.PraySignRuleDOMapper;
import com.lawu.chick.service.PraySignRuleService;
import com.lawu.chick.service.bo.PraySignRuleBO;
import com.lawu.chick.service.enums.StatusEnum;
import com.lawu.chick.service.param.PraySignRuleParam;

/**
 * @author zhangyong
 * @date 2018/6/15.
 */
@Service
public class PraySignRuleServiceImpl implements PraySignRuleService {

    @Autowired
    private PraySignRuleDOMapper praySignRuleDOMapper;

    @Autowired
    private PraySignService praySignService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void editSignRule(PraySignRuleParam param) {
        String extra = JSONArray.toJSONString(param.getExtras());
        PraySignRuleDO ruleDO = new PraySignRuleDO();
        ruleDO.setGmtModified(new Date());
        ruleDO.setDay(param.getDay());
        ruleDO.setIsBasisChick(param.getIsBasisChick());
        ruleDO.setProductCount(param.getProductCount());
        ruleDO.setProductNum(param.getProductNum());
        ruleDO.setExtra(extra);
        ruleDO.setStatus(param.getStatus().getValue());
        if (param.getId() == null || param.getId() <= 0) {
            ruleDO.setGmtCreate(new Date());
            praySignRuleDOMapper.insertSelective(ruleDO);
        } else {
            ruleDO.setId(param.getId());
            praySignRuleDOMapper.updateByPrimaryKeySelective(ruleDO);
        }
       //加入更新缓存
        PraySignRuleCO ruleCO = new PraySignRuleCO();
        ruleCO.setId(param.getId());
        ruleCO.setProductNum(param.getProductNum());
        ruleCO.setProductCount(param.getProductCount());
        ruleCO.setExtra(extra);
        ruleCO.setDay(param.getDay());
        ruleCO.setBasisChick(param.getIsBasisChick());
        ruleCO.setStatus(param.getStatus().getValue());
        praySignService.addSignRuleCache(ruleCO);
    }

    @Override
    public PraySignRuleBO getPraySignRuleInfo() {
        List<PraySignRuleDO> ruleDOS = praySignRuleDOMapper.selectByExample(null);
        if (ruleDOS.isEmpty()) {
            return null;
        }
        PraySignRuleBO ruleBO = new PraySignRuleBO();
        ruleBO.setId(ruleDOS.get(0).getId());
        ruleBO.setProductCount(ruleDOS.get(0).getProductCount());
        ruleBO.setDay(ruleDOS.get(0).getDay());
        ruleBO.setBasisChick(ruleDOS.get(0).getIsBasisChick());
        ruleBO.setExtras(ruleDOS.get(0).getExtra());
        ruleBO.setProductNum(ruleDOS.get(0).getProductNum());
        ruleBO.setStatus(StatusEnum.getEnum(ruleDOS.get(0).getStatus()));
        return ruleBO;
    }


}
