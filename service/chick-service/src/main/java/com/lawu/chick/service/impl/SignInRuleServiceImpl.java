package com.lawu.chick.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.lawu.chick.repository.domain.SignInRuleDO;
import com.lawu.chick.repository.domain.SignInRuleDOExample;
import com.lawu.chick.repository.mapper.SignInRuleDOMapper;
import com.lawu.chick.service.SignInRuleService;
import com.lawu.chick.service.bo.SignInRuleBO;
import com.lawu.chick.service.converter.SignInRuleConverter;
import com.lawu.chick.service.enums.StatusEnum;
import com.lawu.chick.service.param.CommonPageParam;
import com.lawu.chick.service.param.SignInRuleParam;
import com.lawu.framework.core.page.Page;
import com.lawu.utils.DateUtil;

/**
 * @author zhangyong
 * @date 2018/4/26.
 */
@Service
public class SignInRuleServiceImpl implements SignInRuleService {

    @Autowired
    private SignInRuleDOMapper signInRuleDOMapper;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void editSignInRule(SignInRuleParam param) {
        SignInRuleDO ruleDO = new SignInRuleDO();
        ruleDO.setGmtStart(DateUtil.formatDate(param.getGmtStart(), "yyyy-MM-dd"));
        ruleDO.setGmtEnd(DateUtil.formatDate(param.getGmtEnd(), "yyyy-MM-dd"));
        ruleDO.setProductNum(param.getProductNum());
        ruleDO.setStatus(param.getStatus().getValue());
        ruleDO.setGmtModified(new Date());
        ruleDO.setProductCount(param.getProductCount());
        ruleDO.setExtra(JSONArray.toJSONString(param.getExtras()));
        if (param.getId() == null || param.getId() <= 0) {
            ruleDO.setGmtCreate(new Date());
            signInRuleDOMapper.insertSelective(ruleDO);
        } else {
            ruleDO.setId(param.getId());
            signInRuleDOMapper.updateByPrimaryKeySelective(ruleDO);
        }
    }

    @Override
    public SignInRuleBO getSignRuleInfo( Long id) {
        SignInRuleDO ruleDO = signInRuleDOMapper.selectByPrimaryKey(id);
        return SignInRuleConverter.convertBO(ruleDO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void editSignRuleStatus(Long id, StatusEnum status) {
        SignInRuleDO ruleDO = new SignInRuleDO();
        ruleDO.setId(id);
        ruleDO.setStatus(status.getValue());
        signInRuleDOMapper.updateByPrimaryKeySelective(ruleDO);
    }

    @Override
    public Page<SignInRuleBO> getSignRuleList(CommonPageParam param) {
        RowBounds rowBounds = new RowBounds(param.getOffset(), param.getPageSize());
        List<SignInRuleDO> ruleDOS = signInRuleDOMapper.selectByExampleWithRowbounds(null, rowBounds);
        Page<SignInRuleBO> page = new Page<>();
        page.setCurrentPage(param.getCurrentPage());
        page.setTotalCount((int) signInRuleDOMapper.countByExample(null));
        page.setRecords(SignInRuleConverter.convertBOS(ruleDOS));
        return page;
    }

    @Override
    public SignInRuleBO getSignRuleByTime(Date time) {
        SignInRuleDOExample example = new SignInRuleDOExample();
        example.createCriteria().andGmtEndGreaterThanOrEqualTo(DateUtil.formatDate(DateUtil.getDateFormat(time), "yyyy-MM-dd")).
                andGmtStartLessThan(DateUtil.getDayAfter(time)).andStatusEqualTo(StatusEnum.ENABLE.getValue());
        List<SignInRuleDO> ruleDOS = signInRuleDOMapper.selectByExample(example);
        return ruleDOS.isEmpty() ? null : SignInRuleConverter.convertBO(ruleDOS.get(0));
    }
}
