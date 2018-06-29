package com.lawu.chick.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.lawu.chick.repository.domain.SignInRecordDO;
import com.lawu.chick.repository.domain.SignInRecordDOExample;
import com.lawu.chick.repository.mapper.SignInRecordDOMapper;
import com.lawu.chick.service.InventoryService;
import com.lawu.chick.service.SignInRecordService;
import com.lawu.chick.service.SignInRuleService;
import com.lawu.chick.service.bo.SignInRuleBO;
import com.lawu.chick.service.bo.SignRecordListBO;
import com.lawu.chick.service.converter.SignInRecordConverter;
import com.lawu.chick.service.enums.InventoryDetailDirectionEnum;
import com.lawu.chick.service.enums.InventoryDetailTypeEnum;
import com.lawu.chick.service.param.InventoryParam;
import com.lawu.chick.service.param.SignExtraParam;
import com.lawu.chick.service.param.SignRuleExtraParam;
import com.lawu.utils.DateUtil;

/**
 * @author zhangyong
 * @date 2018/4/27.
 */
@Service
public class SignInRecordServiceImpl implements SignInRecordService {

    @Autowired
    private SignInRecordDOMapper signInRecordDOMapper;

    @Autowired
    private SignInRuleService signInRuleService;

    @Autowired
    private InventoryService inventoryService;

    @Override
    public Boolean getRecordByMemberNum(String userNum) {
        SignInRecordDOExample example = new SignInRecordDOExample();
        example.createCriteria().andMemberNumEqualTo(userNum).
                andGmtCreateGreaterThanOrEqualTo(DateUtil.getNowDate()).
                andGmtCreateLessThan(DateUtil.getDayAfter(DateUtil.getNowDate()));
        List<SignInRecordDO> recordDOS = signInRecordDOMapper.selectByExample(example);

        return !recordDOS.isEmpty();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean sign(String userNum) {
        SignInRuleBO rule = signInRuleService.getSignRuleByTime(new Date());
        if (rule == null) {
            return false;
        }
        SignInRecordDO recordDO = new SignInRecordDO();
        recordDO.setRuleId(rule.getId());
        recordDO.setGmtCreate(new Date());
        recordDO.setProductNum(rule.getProductNum());
        recordDO.setMemberNum(userNum);
        signInRecordDOMapper.insertSelective(recordDO);
        //添加到仓库
        InventoryParam inventoryParam = new InventoryParam();
        inventoryParam.setMemberNum(userNum);
        inventoryParam.setProductNum(rule.getProductNum());
        inventoryParam.setQuantity(rule.getProductCount());
        inventoryParam.setTypeEnum(InventoryDetailTypeEnum.SIGN_AWARD);
        inventoryParam.setDirectionEnum(InventoryDetailDirectionEnum.ADD);
        inventoryService.addInventory(inventoryParam);

        return true;
    }

    @Override
    public Boolean getSignAward(String userNum, Integer signDay) {
        SignInRuleBO rule = signInRuleService.getSignRuleByTime(new Date());
        if (rule == null) {
            return false;
        }
        if (signDay == null || signDay <= 1) {
            signDay = 1;
        }
        SignInRecordDOExample example = new SignInRecordDOExample();
        example.createCriteria().andMemberNumEqualTo(userNum).andGmtCreateGreaterThan(rule.getGmtStart()).
                andGmtCreateLessThan(DateUtil.getDayAfter(rule.getGmtEnd()));
        RowBounds rowBounds = new RowBounds(signDay - 1, 1);
        List<SignInRecordDO> recordDOS = signInRecordDOMapper.selectByExampleWithRowbounds(example, rowBounds);
        if (recordDOS.isEmpty() || StringUtils.isNotEmpty(recordDOS.get(0).getExtraProductNum())) {
            return false;
        }
        List<SignRuleExtraParam> extraParams = JSONArray.parseArray(rule.getExtra(), SignRuleExtraParam.class);
        for (SignRuleExtraParam extraParam : extraParams) {
            if (extraParam.getSignDay().equals(signDay)) {
                SignInRecordDO recordDO = new SignInRecordDO();
                recordDO.setId(recordDOS.get(0).getId());
                recordDO.setExtraProductNum(extraParam.getProductNum());
                signInRecordDOMapper.updateByPrimaryKeySelective(recordDO);

                InventoryParam inventoryParam = new InventoryParam();
                inventoryParam.setMemberNum(userNum);
                inventoryParam.setProductNum(extraParam.getProductNum());
                inventoryParam.setQuantity(extraParam.getProductCount());
                inventoryParam.setTypeEnum(InventoryDetailTypeEnum.SIGN_AWARD);
                inventoryParam.setDirectionEnum(InventoryDetailDirectionEnum.ADD);
                inventoryService.addInventory(inventoryParam);
                break;
            }
        }
        return true;
    }

    @Override
    public SignRecordListBO getSignRecord(String userNum) {
        SignInRuleBO rule = signInRuleService.getSignRuleByTime(new Date());
        if (rule == null) {
            return null;
        }
        SignRecordListBO recordListBO = new SignRecordListBO();
        recordListBO.setGmtStart(rule.getGmtStart());
        recordListBO.setGmtEnd(rule.getGmtEnd());
        recordListBO.setExtras(rule.getExtra());
        recordListBO.setProductNum(rule.getProductNum());
        recordListBO.setCount(rule.getProductCount());
        SignInRecordDOExample example = new SignInRecordDOExample();
        example.createCriteria().andMemberNumEqualTo(userNum).andGmtCreateGreaterThan(rule.getGmtStart()).
                andGmtCreateLessThan(DateUtil.getDayAfter(rule.getGmtEnd()));
        List<SignInRecordDO> recordDOS = signInRecordDOMapper.selectByExample(example);
        recordListBO.setRecordBOS(SignInRecordConverter.convertBOS(recordDOS));
        return recordListBO;
    }

    @Override
    public Boolean isGetSignExtraAward(SignExtraParam param) {
        if (param.getSignDay() == null || param.getSignDay() <= 1) {
            param.setSignDay(1);
        }
        SignInRecordDOExample example = new SignInRecordDOExample();
        example.createCriteria().andMemberNumEqualTo(param.getUserNum()).andGmtCreateGreaterThan(param.getGmtStart()).
                andGmtCreateLessThan(DateUtil.getDayAfter(param.getGmtEnd()));
        RowBounds rowBounds = new RowBounds(param.getSignDay() - 1, 1);
        List<SignInRecordDO> recordDOS = signInRecordDOMapper.selectByExampleWithRowbounds(example, rowBounds);
        if (recordDOS.isEmpty() || StringUtils.isEmpty(recordDOS.get(0).getExtraProductNum())) {
            return false;
        }
        return true;
    }


}
