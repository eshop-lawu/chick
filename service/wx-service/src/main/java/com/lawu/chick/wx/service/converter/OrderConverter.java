package com.lawu.chick.wx.service.converter;

import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
import com.lawu.chick.wx.service.param.OrderParam;

/**
 * @author Leach
 * @date 2018/4/27
 */
public class OrderConverter {

    public static WxPayUnifiedOrderRequest convert(OrderParam orderParam) {
        WxPayUnifiedOrderRequest request = new WxPayUnifiedOrderRequest();
        return request;
    }
}
