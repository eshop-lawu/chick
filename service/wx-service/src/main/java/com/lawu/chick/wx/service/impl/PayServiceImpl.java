package com.lawu.chick.wx.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.binarywang.wxpay.bean.notify.WxPayOrderNotifyResult;
import com.github.binarywang.wxpay.bean.request.WxPaySendRedpackRequest;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
import com.github.binarywang.wxpay.bean.result.WxPayRedpackQueryResult;
import com.github.binarywang.wxpay.bean.result.WxPaySendRedpackResult;
import com.github.binarywang.wxpay.bean.result.WxPayUnifiedOrderResult;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.util.SignUtils;
import com.lawu.chick.wx.service.PayService;
import com.lawu.chick.wx.service.bo.OrderPayBO;
import com.lawu.chick.wx.service.bo.OrderPayResultBO;
import com.lawu.chick.wx.service.bo.RedpackResultBO;
import com.lawu.chick.wx.service.config.WxMiniProperties;
import com.lawu.chick.wx.service.config.WxPayProperties;
import com.lawu.chick.wx.service.converter.RedpackConverter;
import com.lawu.chick.wx.service.param.OrderParam;
import com.lawu.chick.wx.service.param.RedpackParam;

/**
 * @author Leach
 * @date 2018/2/5
 */
@Service
public class PayServiceImpl implements PayService {

    private static final String ERR_CODE_NOT_FOUND = "NOT_FOUND";

    private static final String SIGN_TYPE = "MD5";

    @Autowired
    private WxMiniProperties wxMiniProperties;

    @Autowired
    private WxPayProperties wxPayProperties;

    @Autowired
    private WxPayService wxPayService;

    @Override
    public RedpackResultBO sendRedpack(RedpackParam redpackParam) throws WxPayException {

        if (!wxPayProperties.isEnableRedpack()) {
            return null;
        }
        WxPaySendRedpackRequest wxPaySendRedpackRequest = RedpackConverter.convert(redpackParam);
        WxPaySendRedpackResult wxPaySendRedpackResult = wxPayService.sendRedpack(wxPaySendRedpackRequest);
        return RedpackConverter.convert(wxPaySendRedpackResult);
    }

    @Override
    public RedpackResultBO queryRedpack(String mchBillNo) throws WxPayException {
        if (!wxPayProperties.isEnableRedpack()) {
            return null;
        }
        WxPayRedpackQueryResult wxPayRedpackQueryResult;
        try {
            wxPayRedpackQueryResult = wxPayService.queryRedpack(mchBillNo);
            return RedpackConverter.convert(wxPayRedpackQueryResult);
        } catch (WxPayException e) {
            if (ERR_CODE_NOT_FOUND.equals(e.getErrCode())) {
                return null;
            }
            throw e;
        }
    }

    @Override
    public boolean isRedpackEnable() {
        return wxPayProperties.isEnableRedpack();
    }

    @Override
    public OrderPayBO unifiedOrder(OrderParam orderParam) throws WxPayException {
        WxPayUnifiedOrderRequest request = new WxPayUnifiedOrderRequest();
        request.setVersion("1.0");
        request.setBody("小鸡牧场-饲料");
        //request.setDetail("");
        request.setOutTradeNo(orderParam.getOrderNum());
        request.setTotalFee(orderParam.getTotalFee());
        request.setSpbillCreateIp(orderParam.getSpbillCreateIp());
        request.setTimeStart(orderParam.getOrderTime());
        request.setNotifyUrl(wxPayProperties.getNotifyUrl());
        // 小程序取值如下：JSAPI
        request.setTradeType("JSAPI");
        request.setOpenid(orderParam.getOpenid());


        WxPayUnifiedOrderResult wxPayUnifiedOrderResult = wxPayService.unifiedOrder(request);

        String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
        String nonceStr = String.valueOf(System.currentTimeMillis());
        String dataPackage = "prepay_id=" + wxPayUnifiedOrderResult.getPrepayId();

        OrderPayBO orderPayBO = new OrderPayBO();
        orderPayBO.setAppid(wxPayProperties.getAppId());
        orderPayBO.setTimeStamp(timestamp);
        orderPayBO.setNonceStr(nonceStr);
        orderPayBO.setDataPackage(dataPackage);
        orderPayBO.setSignType(SIGN_TYPE);

        // 签名
        Map<String, String> params = new HashMap<>();
        params.put("appId", wxPayProperties.getAppId());
        params.put("timeStamp", timestamp);
        params.put("nonceStr", nonceStr);
        params.put("package", dataPackage);
        params.put("signType", SIGN_TYPE);
        String sign = SignUtils.createSign(params, SIGN_TYPE, wxPayProperties.getMchKey(), false);
        orderPayBO.setPaySign(sign);

        return orderPayBO;
    }

    @Override
    public OrderPayResultBO parseOrderNotifyResult(String xmlData) throws WxPayException {
        WxPayOrderNotifyResult wxPayOrderNotifyResult = wxPayService.parseOrderNotifyResult(xmlData);

        if ("SUCCESS".equals(wxPayOrderNotifyResult.getReturnCode())) {
            OrderPayResultBO orderPayResultBO = new OrderPayResultBO();

            if ("SUCCESS".equals(wxPayOrderNotifyResult.getResultCode())) {
                orderPayResultBO.setSuccess(true);
            } else {
                orderPayResultBO.setSuccess(false);
                orderPayResultBO.setErrorMsg(wxPayOrderNotifyResult.getErrCodeDes());
            }
            orderPayResultBO.setOrderNum(wxPayOrderNotifyResult.getOutTradeNo());
            orderPayResultBO.setWxTransactionId(wxPayOrderNotifyResult.getTransactionId());
            return orderPayResultBO;
        }
        return null;
    }

}
