package com.lawu.chick.wx.service.bo;

/**
 * @author Leach
 * @date 2018/4/27
 */
public class OrderPayResultBO {

    private Boolean success;

    /**
     * 错误信息，如有
     */
    private String errorMsg;

    /**
     * 商户订单号
     */
    private String orderNum;

    /**
     * 微信支付订单号
     */
    private String wxTransactionId;


    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getWxTransactionId() {
        return wxTransactionId;
    }

    public void setWxTransactionId(String wxTransactionId) {
        this.wxTransactionId = wxTransactionId;
    }
}
