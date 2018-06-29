package com.lawu.chick.wx.service;

import com.github.binarywang.wxpay.exception.WxPayException;
import com.lawu.chick.wx.service.bo.OrderPayBO;
import com.lawu.chick.wx.service.bo.OrderPayResultBO;
import com.lawu.chick.wx.service.bo.RedpackResultBO;
import com.lawu.chick.wx.service.param.OrderParam;
import com.lawu.chick.wx.service.param.RedpackParam;

/**
 * @author Leach
 * @date 2018/2/5
 */
public interface PayService {

    /**
     * 红包发送
     * @param redpackParam
     * @return
     * @throws WxPayException
     */
    RedpackResultBO sendRedpack(RedpackParam redpackParam) throws WxPayException;

    /**
     * 查询红包发送结果
     * @param mchBillNo
     * @return
     * @throws WxPayException
     */
    RedpackResultBO queryRedpack(String mchBillNo) throws WxPayException;

    /**
     * 红包是否启用
     * @return
     */
    boolean isRedpackEnable();

    /**
     * 微信统一下单
     * @param orderParam
     * @return
     * @throws WxPayException
     */
    OrderPayBO unifiedOrder(OrderParam orderParam) throws WxPayException;

    /**
     *
     * @param xmlData
     * @return
     * @throws WxPayException
     */
    OrderPayResultBO parseOrderNotifyResult(String xmlData) throws WxPayException;
}
