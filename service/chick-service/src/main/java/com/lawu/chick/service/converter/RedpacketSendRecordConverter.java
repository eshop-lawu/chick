package com.lawu.chick.service.converter;

import java.util.ArrayList;
import java.util.List;

import com.lawu.chick.repository.domain.RedpacketSendRecordDO;
import com.lawu.chick.service.bo.RedpacketSendRecordBO;

/**
 * @author meishuquan
 * @date 2018/4/27.
 */
public class RedpacketSendRecordConverter {


    public static RedpacketSendRecordBO convertBO(RedpacketSendRecordDO recordDO) {
        if (recordDO == null) {
            return null;
        }

        RedpacketSendRecordBO recordBO = new RedpacketSendRecordBO();
        recordBO.setId(recordDO.getId());
        recordBO.setEggExchangeRecordId(recordDO.getEggExchangeRecordId());
        recordBO.setUserNum(recordDO.getUserNum());
        recordBO.setOpenid(recordDO.getOpenid());
        recordBO.setReturnCode(recordDO.getReturnCode());
        recordBO.setReturnMsg(recordDO.getReturnMsg());
        recordBO.setResultCode(recordDO.getResultCode());
        recordBO.setErrCode(recordDO.getErrCode());
        recordBO.setErrCodeDes(recordDO.getErrCodeDes());
        recordBO.setMchBillno(recordDO.getMchBillno());
        recordBO.setSendListId(recordDO.getSendListId());
        recordBO.setTotalAmount(recordDO.getTotalAmount());
        recordBO.setStatus(recordDO.getStatus());
        recordBO.setRcvTime(recordDO.getRcvTime());
        recordBO.setRefundTime(recordDO.getRefundTime());
        recordBO.setGmtModified(recordDO.getGmtModified());
        recordBO.setGmtCreate(recordDO.getGmtCreate());
        return recordBO;
    }

    public static List<RedpacketSendRecordBO> convertBOS(List<RedpacketSendRecordDO> recordDOS) {
        List<RedpacketSendRecordBO> recordBOS = new ArrayList<>();
        if (recordDOS == null || recordDOS.isEmpty()) {
            return recordBOS;
        }

        for (RedpacketSendRecordDO recordDO : recordDOS) {
            recordBOS.add(convertBO(recordDO));
        }
        return recordBOS;
    }

}
