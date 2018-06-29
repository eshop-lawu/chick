package com.lawu.chick.jobs.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.lawu.chick.id.worker.generate.impl.BizIdType;
import com.lawu.chick.id.worker.generate.impl.IdWorkerHelper;
import com.lawu.chick.service.EggExchangeRecordService;
import com.lawu.chick.service.RedpacketSendRecordService;
import com.lawu.chick.service.bo.EggExchangeRecordBO;
import com.lawu.chick.service.enums.RedpacketSendRecordStatusEnum;
import com.lawu.chick.service.param.RedpacketSendRecordParam;
import com.lawu.jobsextend.AbstractPageJob;

/**
 * @author meishuquan
 * @date 2018/4/27.
 */
public class SendRedpacketJob extends AbstractPageJob<EggExchangeRecordBO> {

    private static int executeCount = 0;

    @Autowired
    private EggExchangeRecordService eggExchangeRecordService;

    @Autowired
    private IdWorkerHelper idWorkerHelper;

    @Autowired
    private RedpacketSendRecordService redpacketSendRecordService;

    @Override
    public void executeSingle(EggExchangeRecordBO recordBO) {
        String mchBillno = idWorkerHelper.generate(BizIdType.REDPACKET);
        RedpacketSendRecordParam sendRecordParam = new RedpacketSendRecordParam();
        sendRecordParam.setEggExchangeRecordId(recordBO.getId());
        sendRecordParam.setUserNum(recordBO.getMemberNum());
        sendRecordParam.setMchBillno(mchBillno);
        sendRecordParam.setTotalAmount(recordBO.getAmount().multiply(BigDecimal.valueOf(1000)).intValue());
        sendRecordParam.setStatusEnum(RedpacketSendRecordStatusEnum.SENDING);
        Long redpacketSendRecordId = redpacketSendRecordService.saveRedpacketSendRecord(sendRecordParam);
        if (redpacketSendRecordId == null || redpacketSendRecordId <= 0) {
            return;
        }
        redpacketSendRecordService.sendRedpacket(redpacketSendRecordId);
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
    public List<EggExchangeRecordBO> queryPage(int offset, int pageSize) {
        if (executeCount > 0) {
            executeCount = 0;
            return null;
        }
        executeCount++;
        List<EggExchangeRecordBO> recordBOS = eggExchangeRecordService.listEggExchangeRedpacket(offset, pageSize);
        return recordBOS;
    }

}
