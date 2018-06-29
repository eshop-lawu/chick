package com.lawu.chick.operator.service.impl;


import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawu.chick.operator.repository.domain.LogDO;
import com.lawu.chick.operator.repository.domain.LogDOExample;
import com.lawu.chick.operator.repository.mapper.LogDOMapper;
import com.lawu.chick.operator.service.LogService;
import com.lawu.chick.operator.service.bo.LogBO;
import com.lawu.chick.operator.service.converter.LogConverter;
import com.lawu.chick.operator.service.dto.constants.ModuleEnum;
import com.lawu.chick.operator.service.dto.constants.OperationTypeEnum;
import com.lawu.chick.operator.service.dto.param.ListLogParam;
import com.lawu.chick.operator.service.dto.param.LogParam;
import com.lawu.framework.core.page.Page;

/**
 * @author meishuquan
 * @date 2017/5/3.
 */
@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private LogDOMapper logDOMapper;

    @Override
    public void saveLog(LogParam logParam) {
        LogDO logDO = new LogDO();
        logDO.setAccount(logParam.getAccount());
        logDO.setOperationType(logParam.getTypeEnum().val);
        logDO.setModule(logParam.getModuleEnum().val);
        logDO.setBusinessId(logParam.getBusinessId());
        logDO.setChangeTitle(logParam.getChangeTitle());
        logDO.setChangeContent(logParam.getChangeContent());
        logDO.setGmtCreate(new Date());
        logDOMapper.insertSelective(logDO);
    }

    @Override
    public LogBO getLogById(Long id) {
        LogDO logDO = logDOMapper.selectByPrimaryKey(id);
        return LogConverter.convertBO(logDO);
    }

    @Override
    public Page<LogBO> listLog(ListLogParam listLogParam) {
        LogDOExample example = new LogDOExample();
        example.setOrderByClause("id desc");
        LogDOExample.Criteria criteria = example.createCriteria();
        if (listLogParam.getTypeEnum().val.byteValue() != OperationTypeEnum.ALL.val) {
            criteria.andOperationTypeEqualTo(listLogParam.getTypeEnum().val);
        }
        if (listLogParam.getModuleEnum().val.byteValue() != ModuleEnum.ALL.val) {
            criteria.andModuleEqualTo(listLogParam.getModuleEnum().val);
        }

        RowBounds rowBounds = new RowBounds(listLogParam.getOffset(), listLogParam.getPageSize());

        Page<LogBO> page = new Page<>();
        page.setCurrentPage(listLogParam.getCurrentPage());
        page.setTotalCount((int) logDOMapper.countByExample(example));

        List<LogDO> logDOList = logDOMapper.selectByExampleWithRowbounds(example, rowBounds);
        page.setRecords(LogConverter.convertBO(logDOList));
        return page;
    }
}
