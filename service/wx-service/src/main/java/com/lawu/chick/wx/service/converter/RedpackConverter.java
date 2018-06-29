package com.lawu.chick.wx.service.converter;

import com.github.binarywang.wxpay.bean.request.WxPaySendRedpackRequest;
import com.github.binarywang.wxpay.bean.result.WxPayRedpackQueryResult;
import com.github.binarywang.wxpay.bean.result.WxPaySendRedpackResult;
import com.lawu.chick.wx.service.bo.RedpackResultBO;
import com.lawu.chick.wx.service.bo.RedpackStatus;
import com.lawu.chick.wx.service.param.RedpackParam;

/**
 * @author Leach
 * @date 2018/1/2
 */
public class RedpackConverter {

    public static WxPaySendRedpackRequest convert(RedpackParam param) {
        WxPaySendRedpackRequest wxPaySendRedpackRequest = new WxPaySendRedpackRequest();
        wxPaySendRedpackRequest.setMchBillNo(param.getMchBillno());
        wxPaySendRedpackRequest.setSendName(param.getSendName());
        wxPaySendRedpackRequest.setReOpenid(param.getOpenid());
        wxPaySendRedpackRequest.setTotalAmount(param.getTotalAmount());
        wxPaySendRedpackRequest.setTotalNum(param.getTotalNum());
        wxPaySendRedpackRequest.setWishing(param.getWishing());
        wxPaySendRedpackRequest.setActName(param.getActName());
        wxPaySendRedpackRequest.setRemark(param.getRemark());
        wxPaySendRedpackRequest.setSceneId(param.getSceneId());
        return wxPaySendRedpackRequest;
    }

    public static RedpackResultBO convert(WxPaySendRedpackResult wxPaySendRedpackResult) {
        if (wxPaySendRedpackResult == null) {
            return null;
        }
        RedpackResultBO redpackResult = new RedpackResultBO();
        redpackResult.setReturnCode(wxPaySendRedpackResult.getReturnCode());
        redpackResult.setReturnMsg(wxPaySendRedpackResult.getReturnMsg());
        redpackResult.setResultCode(wxPaySendRedpackResult.getResultCode());
        redpackResult.setErrCode(wxPaySendRedpackResult.getErrCode());
        redpackResult.setSendListId(wxPaySendRedpackResult.getSendListid());
        redpackResult.setStatus(RedpackStatus.SENDING);
        return redpackResult;
    }

    public static RedpackResultBO convert(WxPayRedpackQueryResult wxPayRedpackQueryResult) {
        if (wxPayRedpackQueryResult == null) {
            return null;
        }
        RedpackResultBO redpackResult = new RedpackResultBO();
        redpackResult.setReturnCode(wxPayRedpackQueryResult.getReturnCode());
        redpackResult.setReturnMsg(wxPayRedpackQueryResult.getReturnMsg());
        redpackResult.setResultCode(wxPayRedpackQueryResult.getResultCode());
        redpackResult.setErrCode(wxPayRedpackQueryResult.getErrCode());
        redpackResult.setSendListId(wxPayRedpackQueryResult.getDetailId());
        redpackResult.setStatus(RedpackStatus.valueOf(wxPayRedpackQueryResult.getStatus()));
        redpackResult.setRefundTime(wxPayRedpackQueryResult.getRefundTime());
        return redpackResult;
    }

}
