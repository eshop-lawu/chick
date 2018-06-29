package com.lawu.chick.operator.api.conterver;

import java.util.ArrayList;
import java.util.List;

import com.lawu.chick.operator.api.dto.EggDistributionRecordDTO;
import com.lawu.chick.service.bo.EggDistributionRecordBO;

/**
 * 金蛋分配记录转换类
 * @author jiangxinjun
 * @createDate 2018年5月8日
 * @updateDate 2018年5月8日
 */
public class EggDistributionRecordConverter {
    
    private EggDistributionRecordConverter() {}
    
    public static EggDistributionRecordDTO convert(EggDistributionRecordBO source) {
        EggDistributionRecordDTO rtn = null;
        if (source == null) {
            return rtn;
        }
        rtn = new EggDistributionRecordDTO();
        rtn.setAllocatedChicks(source.getAllocatedChicks());
        rtn.setChicks(source.getChicks());
        rtn.setExpectedAllocations(source.getExpectedAllocations());
        rtn.setGmtAllocationsComplete(source.getGmtAllocationsComplete());
        rtn.setGmtGrantComplete(source.getGmtGrantComplete());
        rtn.setGrantChicks(source.getGrantChicks());
        rtn.setGrants(source.getGrants());
        rtn.setId(source.getId());
        rtn.setRealAllocations(source.getRealAllocations());
        rtn.setStatus(source.getStatus());
        return rtn;
    }

    public static List<EggDistributionRecordDTO> convert(List<EggDistributionRecordBO> source) {
        List<EggDistributionRecordDTO> rtn = new ArrayList<>();
        if (source == null || source.isEmpty()) {
            return rtn;
        }
        for (EggDistributionRecordBO item : source) {
            rtn.add(convert(item));
        }
        return rtn;
    }
}
