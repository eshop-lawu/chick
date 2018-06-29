package com.lawu.chick.jobs.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.lawu.chick.service.RedpacketSendRecordService;
import com.lawu.chick.service.bo.RedpacketSendRecordBO;
import com.lawu.chick.service.enums.RedpacketSendRecordStatusEnum;
import com.lawu.chick.service.param.RedpacketSendRecordParam;
import com.lawu.chick.wx.service.PayService;
import com.lawu.chick.wx.service.bo.RedpackResultBO;
import com.lawu.chick.wx.service.bo.RedpackStatus;
import com.lawu.jobsextend.AbstractPageJob;
import com.lawu.utils.DateUtil;


/**
 * @author meishuquan
 * @date 2018/4/27.
 */
public class QueryRedpacketJob extends AbstractPageJob<RedpacketSendRecordBO> {

    @Autowired
    private RedpacketSendRecordService redpacketSendRecordService;

    @Autowired
    private PayService payService;

    @Override
    public void executeSingle(RedpacketSendRecordBO recordBO) {
        if (!payService.isRedpackEnable()) {
            return;
        }
        RedpackResultBO redpackResultBO;
        try {
            redpackResultBO = payService.queryRedpack(recordBO.getMchBillno());
            if (redpackResultBO == null) {
                redpacketSendRecordService.sendRedpacket(recordBO.getId());
                return;
            }
        } catch (Exception e) {
            return;
        }

        RedpacketSendRecordParam param = new RedpacketSendRecordParam();
        param.setId(recordBO.getId());
        param.setEggExchangeRecordId(recordBO.getEggExchangeRecordId());
        param.setRcvTime(StringUtils.isEmpty(redpackResultBO.getRcvTime()) ? null : DateUtil.getDateTimeFormat((redpackResultBO.getRcvTime())));
        param.setRefundTime(StringUtils.isEmpty(redpackResultBO.getRefundTime()) ? null : DateUtil.getDateTimeFormat((redpackResultBO.getRefundTime())));
        RedpackStatus redpackStatus = redpackResultBO.getStatus();
        if (RedpackStatus.SENDING.equals(redpackStatus)) {
            param.setStatusEnum(RedpacketSendRecordStatusEnum.SENDING);
        } else if (RedpackStatus.SENT.equals(redpackStatus)) {
            param.setStatusEnum(RedpacketSendRecordStatusEnum.SENT);
        } else if (RedpackStatus.FAILED.equals(redpackStatus)) {
            param.setStatusEnum(RedpacketSendRecordStatusEnum.FAILED);
        } else if (RedpackStatus.RECEIVED.equals(redpackStatus)) {
            param.setStatusEnum(RedpacketSendRecordStatusEnum.RECEIVED);
        } else if (RedpackStatus.RFUND_ING.equals(redpackStatus)) {
            param.setStatusEnum(RedpacketSendRecordStatusEnum.RFUND_ING);
        } else if (RedpackStatus.REFUND.equals(redpackStatus)) {
            param.setStatusEnum(RedpacketSendRecordStatusEnum.REFUND);
        } else {
            return;
        }
        redpacketSendRecordService.updateRedpacketSendResult(param);
    }

    @Override
    public boolean isStatusData() {
        return true;
    }

    @Override
    public boolean continueWhenSinglePageFail() {
        return true;
    }

    @Override
    public List<RedpacketSendRecordBO> queryPage(int offset, int pageSize) {
        List<RedpacketSendRecordBO> recordBOS = redpacketSendRecordService.listRedpacketSendRecord(offset, pageSize);
        return recordBOS;
    }

}
