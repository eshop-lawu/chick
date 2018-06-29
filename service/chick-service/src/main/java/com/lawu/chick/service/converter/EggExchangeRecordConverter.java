package com.lawu.chick.service.converter;

import java.util.ArrayList;
import java.util.List;

import com.lawu.chick.repository.domain.EggExchangeRecordDO;
import com.lawu.chick.service.bo.EggExchangeRecordBO;

/**
 * @author meishuquan
 * @date 2018/4/26.
 */
public class EggExchangeRecordConverter {

    public static EggExchangeRecordBO convertBO(EggExchangeRecordDO recordDO) {
        if (recordDO == null) {
            return null;
        }

        EggExchangeRecordBO recordBO = new EggExchangeRecordBO();
        recordBO.setId(recordDO.getId());
        recordBO.setNum(recordDO.getNum());
        recordBO.setMemberNum(recordDO.getMemberNum());
        recordBO.setType(recordDO.getType());
        recordBO.setEggQuantity(recordDO.getEggQuantity());
        recordBO.setAmount(recordDO.getAmount());
        recordBO.setGiftId(recordDO.getGiftId());
        recordBO.setGiftName(recordDO.getGiftName());
        recordBO.setGiftImgPath(recordDO.getGiftImgPath());
        recordBO.setStatus(recordDO.getStatus());
        recordBO.setName(recordDO.getName());
        recordBO.setMobile(recordDO.getMobile());
        recordBO.setAddress(recordDO.getAddress());
        recordBO.setExpressNum(recordDO.getExpressNum());
        recordBO.setRemark(recordDO.getRemark());
        recordBO.setGmtSend(recordDO.getGmtSend());
        recordBO.setGmtModified(recordDO.getGmtModified());
        recordBO.setGmtCreate(recordDO.getGmtCreate());
        return recordBO;
    }

    public static List<EggExchangeRecordBO> convertBOS(List<EggExchangeRecordDO> recordDOS) {
        List<EggExchangeRecordBO> recordBOS = new ArrayList<>();
        if (recordDOS == null || recordDOS.isEmpty()) {
            return recordBOS;
        }

        for (EggExchangeRecordDO recordDO : recordDOS) {
            recordBOS.add(convertBO(recordDO));
        }
        return recordBOS;
    }

}
