package com.lawu.chick.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.chick.repository.domain.EggExchangeRecordDO;
import com.lawu.chick.repository.domain.RedpacketSendRecordDO;
import com.lawu.chick.repository.domain.RedpacketSendRecordDOExample;
import com.lawu.chick.repository.domain.WxUserDO;
import com.lawu.chick.repository.domain.WxUserDOExample;
import com.lawu.chick.repository.mapper.EggExchangeRecordDOMapper;
import com.lawu.chick.repository.mapper.RedpacketSendRecordDOMapper;
import com.lawu.chick.repository.mapper.WxUserDOMapper;
import com.lawu.chick.service.RedpacketSendRecordService;
import com.lawu.chick.service.SysConfigService;
import com.lawu.chick.service.bo.EggConfigBO;
import com.lawu.chick.service.bo.RedpacketSendRecordBO;
import com.lawu.chick.service.converter.RedpacketSendRecordConverter;
import com.lawu.chick.service.enums.EggExchangeRecordStatusEnum;
import com.lawu.chick.service.enums.RedpacketSendRecordStatusEnum;
import com.lawu.chick.service.param.RedpacketSendRecordParam;
import com.lawu.chick.wx.service.PayService;
import com.lawu.chick.wx.service.bo.RedpackResultBO;
import com.lawu.chick.wx.service.param.RedpackParam;

/**
 * @author meishuquan
 * @date 2018/4/27.
 */
@Service
public class RedpacketSendRecordServiceImpl implements RedpacketSendRecordService {

    @Autowired
    private RedpacketSendRecordDOMapper redpacketSendRecordDOMapper;

    @Autowired
    private WxUserDOMapper wxUserDOMapper;

    @Autowired
    private EggExchangeRecordDOMapper eggExchangeRecordDOMapper;

    @Autowired
    private PayService payService;

    @Autowired
    private SysConfigService sysConfigService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long saveRedpacketSendRecord(RedpacketSendRecordParam param) {
        WxUserDOExample wxUserDOExample = new WxUserDOExample();
        wxUserDOExample.createCriteria().andMemberNumEqualTo(param.getUserNum());
        List<WxUserDO> wxUserDOS = wxUserDOMapper.selectByExample(wxUserDOExample);
        if (wxUserDOS.isEmpty() || StringUtils.isEmpty(wxUserDOS.get(0).getOpenid())) {
            return null;
        }

        //保存红包发放记录
        RedpacketSendRecordDO sendRecordDO = new RedpacketSendRecordDO();
        sendRecordDO.setEggExchangeRecordId(param.getEggExchangeRecordId());
        sendRecordDO.setUserNum(param.getUserNum());
        sendRecordDO.setOpenid(wxUserDOS.get(0).getOpenid());
        sendRecordDO.setMchBillno(param.getMchBillno());
        sendRecordDO.setTotalAmount(param.getTotalAmount());
        sendRecordDO.setStatus(param.getStatusEnum().getVal());
        sendRecordDO.setGmtModified(new Date());
        sendRecordDO.setGmtCreate(new Date());
        redpacketSendRecordDOMapper.insertSelective(sendRecordDO);

        //更新兑换记录为已发放
        EggExchangeRecordDO eggExchangeRecordDO = new EggExchangeRecordDO();
        eggExchangeRecordDO.setId(param.getEggExchangeRecordId());
        eggExchangeRecordDO.setStatus(EggExchangeRecordStatusEnum.SENT.getVal());
        eggExchangeRecordDO.setGmtSend(new Date());
        eggExchangeRecordDO.setGmtModified(new Date());
        eggExchangeRecordDOMapper.updateByPrimaryKeySelective(eggExchangeRecordDO);
        return sendRecordDO.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void sendRedpacket(Long id) {
        RedpacketSendRecordDO recordDO = redpacketSendRecordDOMapper.selectByPrimaryKey(id);
        EggConfigBO eggConfigBO = sysConfigService.getEggConfig();
        if (recordDO == null || eggConfigBO == null) {
            return;
        }
        RedpackParam param = new RedpackParam();
        param.setOpenid(recordDO.getOpenid());
        param.setMchBillno(recordDO.getMchBillno());
        param.setSendName(eggConfigBO.getWxSendName());
        param.setTotalAmount(recordDO.getTotalAmount());
        param.setTotalNum(1);
        param.setWishing(eggConfigBO.getWxWishing());
        param.setActName(eggConfigBO.getWxActName());
        param.setRemark(eggConfigBO.getWxRemark());
        // 虚拟物品兑奖
        param.setSceneId("PRODUCT_3");
        RedpackResultBO redpackResultBO;
        try {
            redpackResultBO = payService.sendRedpack(param);
            if (redpackResultBO == null) {
                return;
            }
        } catch (Exception e) {
            return;
        }

        RedpacketSendRecordDO sendRecordDO = new RedpacketSendRecordDO();
        sendRecordDO.setId(id);
        sendRecordDO.setReturnCode(redpackResultBO.getReturnCode());
        sendRecordDO.setReturnMsg(redpackResultBO.getReturnMsg());
        sendRecordDO.setResultCode(redpackResultBO.getResultCode());
        sendRecordDO.setErrCode(redpackResultBO.getErrCode());
        sendRecordDO.setErrCodeDes(redpackResultBO.getErrCodeDes());
        sendRecordDO.setSendListId(redpackResultBO.getSendListId());
        sendRecordDO.setGmtModified(new Date());
        redpacketSendRecordDOMapper.updateByPrimaryKeySelective(sendRecordDO);
    }

    @Override
    public List<RedpacketSendRecordBO> listRedpacketSendRecord(int offset, int pageSize) {
        RedpacketSendRecordDOExample example = new RedpacketSendRecordDOExample();
        List<Byte> statusList = new ArrayList<>();
        statusList.add(RedpacketSendRecordStatusEnum.SENDING.getVal());
        statusList.add(RedpacketSendRecordStatusEnum.SENT.getVal());
        statusList.add(RedpacketSendRecordStatusEnum.RFUND_ING.getVal());
        example.createCriteria().andStatusIn(statusList);
        RowBounds rowBounds = new RowBounds(offset, pageSize);
        List<RedpacketSendRecordDO> sendRecordDOS = redpacketSendRecordDOMapper.selectByExampleWithRowbounds(example, rowBounds);
        return RedpacketSendRecordConverter.convertBOS(sendRecordDOS);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateRedpacketSendResult(RedpacketSendRecordParam param) {
        //更新红包发放状态
        RedpacketSendRecordDO sendRecordDO = new RedpacketSendRecordDO();
        sendRecordDO.setId(param.getId());
        sendRecordDO.setStatus(param.getStatusEnum().getVal());
        sendRecordDO.setRcvTime(param.getRcvTime());
        sendRecordDO.setRefundTime(param.getRefundTime());
        sendRecordDO.setGmtModified(new Date());
        redpacketSendRecordDOMapper.updateByPrimaryKeySelective(sendRecordDO);

        //更新兑换记录发放状态
        EggExchangeRecordDO eggExchangeRecordDO = new EggExchangeRecordDO();
        eggExchangeRecordDO.setId(param.getEggExchangeRecordId());
        if (param.getStatusEnum() == RedpacketSendRecordStatusEnum.FAILED) {
            eggExchangeRecordDO.setStatus(EggExchangeRecordStatusEnum.FAIL.getVal());
        } else if (param.getStatusEnum() == RedpacketSendRecordStatusEnum.RECEIVED) {
            eggExchangeRecordDO.setStatus(EggExchangeRecordStatusEnum.RECEIVED.getVal());
        } else if (param.getStatusEnum() == RedpacketSendRecordStatusEnum.REFUND) {
            eggExchangeRecordDO.setStatus(EggExchangeRecordStatusEnum.REFUND.getVal());
        } else {
            return;
        }
        RedpacketSendRecordDO redpacketSendRecordDO = redpacketSendRecordDOMapper.selectByPrimaryKey(param.getId());
        eggExchangeRecordDO.setRemark(redpacketSendRecordDO.getErrCodeDes());
        eggExchangeRecordDO.setGmtModified(new Date());
        eggExchangeRecordDOMapper.updateByPrimaryKeySelective(eggExchangeRecordDO);
    }

}
