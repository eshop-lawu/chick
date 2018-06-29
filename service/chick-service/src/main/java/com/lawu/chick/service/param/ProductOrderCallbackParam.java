package com.lawu.chick.service.param;

/**
 * 商品订单回调参数
 * 
 * @author jiangxinjun
 * @createDate 2018年4月27日
 * @updateDate 2018年4月27日
 */
public class ProductOrderCallbackParam {

    /**
     * 订单编号
     */
    private String orderNum;

    /**
     * 是否支付成功
     */
    private Boolean isSuccessful;
    
    /**
     * 第三方交易编号
     */
    private String transactionId;
    
    /**
     * 备注
     */
    private String remark;

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public Boolean getIsSuccessful() {
        return isSuccessful;
    }

    public void setIsSuccessful(Boolean isSuccessful) {
        this.isSuccessful = isSuccessful;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
    
}
