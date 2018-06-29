package com.lawu.chick.external.api.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.binarywang.wxpay.exception.WxPayException;
import com.lawu.chick.service.ProductOrderService;
import com.lawu.chick.service.param.ProductOrderCallbackParam;
import com.lawu.chick.wx.service.PayService;
import com.lawu.chick.wx.service.bo.OrderPayResultBO;
import com.lawu.framework.web.BaseController;

import io.swagger.annotations.Api;

/**
 * @author Leach
 * @date 2018/4/27
 */
@Api(tags = "wxPayNotify")
@RestController
@RequestMapping(value = "wxPayNotify/")
public class WxPayNotifyController extends BaseController {


    private Logger logger = LoggerFactory.getLogger(WxPayNotifyController.class);

    @Autowired
    private PayService payService;
    
    @Autowired
    private ProductOrderService productOrderService;

    @PostMapping("/order")
    public String parseOrderNotifyResult(@RequestBody String xmlData) throws WxPayException {
        OrderPayResultBO orderPayResultBO = payService.parseOrderNotifyResult(xmlData);
        if (orderPayResultBO != null) {
            ProductOrderCallbackParam param = new ProductOrderCallbackParam();
            param.setIsSuccessful(orderPayResultBO.getSuccess());
            param.setOrderNum(orderPayResultBO.getOrderNum());
            param.setRemark(orderPayResultBO.getErrorMsg());
            param.setTransactionId(orderPayResultBO.getWxTransactionId());
            productOrderService.payCallback(param);
            // 回调成功
            return "<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>";
        }
        // 失败
        logger.info(xmlData);
        return null;
    }
}
