package com.lawu.chick.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.chick.repository.domain.EggDistributionRecordDO;
import com.lawu.chick.repository.domain.EggDistributionRecordDOExample;
import com.lawu.chick.repository.domain.extend.CountEggProductionDO;
import com.lawu.chick.repository.mapper.EggDistributionRecordDOMapper;
import com.lawu.chick.repository.mapper.extend.ChickenDOExtendMapper;
import com.lawu.chick.service.EggDistributionRecordService;
import com.lawu.chick.service.bo.EggDistributionRecordBO;
import com.lawu.chick.service.converter.EggDistributionRecordConverter;
import com.lawu.chick.service.enums.DistributionStatusEnum;
import com.lawu.chick.service.query.EggDistributionRecordQueryParam;
import com.lawu.framework.core.page.Page;

/**
 * 金蛋分配记录实现类
 * @author jiangxinjun
 * @createDate 2018年5月7日
 * @updateDate 2018年5月7日
 */
@Service
public class EggDistributionRecordServiceImpl implements EggDistributionRecordService {
    
    @Autowired
    private EggDistributionRecordDOMapper eggDistributionRecordDOMapper;
    
    @Autowired
    private ChickenDOExtendMapper chickenDOExtendMapper;
    
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Long save(Long chicks, BigDecimal layEggsTotal) {
        EggDistributionRecordDO record = new EggDistributionRecordDO();
        record.setChicks(chicks);
        record.setAllocatedChicks(0L);
        record.setExpectedAllocations(layEggsTotal);
        record.setStatus(DistributionStatusEnum.ALLOCATION.getVal());
        record.setRealAllocations(BigDecimal.ZERO);
        record.setGrants(BigDecimal.ZERO);
        record.setGrantChicks(0L);
        record.setGmtModified(new Date());
        record.setGmtCreate(new Date());
        eggDistributionRecordDOMapper.insert(record);
        return record.getId();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void distributionCompleted(Long recordId) {
        // 最终再次统计分配数量
        CountEggProductionDO countEggProductionDO = chickenDOExtendMapper.countEggProduction();
        EggDistributionRecordDO record = new EggDistributionRecordDO();
        record.setId(recordId);
        record.setChicks(countEggProductionDO.getCount());
        record.setRealAllocations(countEggProductionDO.getLayEggs());
        record.setStatus(DistributionStatusEnum.ALLOCATED.getVal());
        record.setGmtAllocationsComplete(new Date());
        record.setGmtModified(new Date());
        eggDistributionRecordDOMapper.updateByPrimaryKeySelective(record);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void releaseCompleted(Long recordId) {
        EggDistributionRecordDO record = new EggDistributionRecordDO();
        record.setId(recordId);
        record.setStatus(DistributionStatusEnum.ISSUED.getVal());
        record.setGmtGrantComplete(new Date());
        record.setGmtModified(new Date());
        eggDistributionRecordDOMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public Page<EggDistributionRecordBO> page(EggDistributionRecordQueryParam param) {
        EggDistributionRecordDOExample example = new EggDistributionRecordDOExample();
        if (param.getStatus() != null) {
            example.createCriteria().andStatusEqualTo(param.getStatus().getVal());
        }
        long count = eggDistributionRecordDOMapper.countByExample(example);
        Page<EggDistributionRecordBO> rtn = new Page<>();
        rtn.setCurrentPage(param.getCurrentPage());
        rtn.setTotalCount((int) count);
        if (count <= 0 || count <= param.getOffset()) {
            return rtn;
        }
        RowBounds rowBounds = new RowBounds(param.getOffset(), param.getPageSize());
        List<EggDistributionRecordDO> list = eggDistributionRecordDOMapper.selectByExampleWithRowbounds(example, rowBounds);
        rtn.setRecords(EggDistributionRecordConverter.convert(list));
        return rtn;
    }

}
