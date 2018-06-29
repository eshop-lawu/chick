package com.lawu.chick.service.converter;

import java.util.ArrayList;
import java.util.List;

import com.lawu.chick.repository.domain.EggDistributionRecordDO;
import com.lawu.chick.service.bo.EggDistributionRecordBO;
import com.lawu.chick.service.enums.DistributionStatusEnum;

/**
 * 金蛋分配记录转换类
 * @author jiangxinjun
 * @createDate 2018年5月8日
 * @updateDate 2018年5月8日
 */
public class EggDistributionRecordConverter {
    
    private EggDistributionRecordConverter() {}
    
    public static EggDistributionRecordBO convert(EggDistributionRecordDO source) {
        EggDistributionRecordBO rtn = null;
        if (source == null) {
            return rtn;
        }
        rtn = new EggDistributionRecordBO();
        rtn.setAllocatedChicks(source.getAllocatedChicks());
        rtn.setChicks(source.getChicks());
        rtn.setExpectedAllocations(source.getExpectedAllocations());
        rtn.setGmtAllocationsComplete(source.getGmtAllocationsComplete());
        rtn.setGmtGrantComplete(source.getGmtGrantComplete());
        rtn.setGrantChicks(source.getGrantChicks());
        rtn.setGrants(source.getGrants());
        rtn.setId(source.getId());
        rtn.setRealAllocations(source.getRealAllocations());
        rtn.setStatus(DistributionStatusEnum.getEnum(source.getStatus()));
        return rtn;
    }

    public static List<EggDistributionRecordBO> convert(List<EggDistributionRecordDO> source) {
        List<EggDistributionRecordBO> rtn = null;
        if (source == null || source.isEmpty()) {
            return rtn;
        }
        rtn = new ArrayList<>();
        for (EggDistributionRecordDO item : source) {
            rtn.add(convert(item));
        }
        return rtn;
    }
}
