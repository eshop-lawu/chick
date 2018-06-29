package com.lawu.chick.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 支付信息DTO
 * @author jiangxinjun
 * @createDate 2018年4月27日
 * @updateDate 2018年4月28日
 */
@ApiModel("支付信息DTO")
public class PaymentInformationDTO {

    /**
     * 公众账号ID
     */
    @ApiModelProperty(value = "公众账号ID", required = true)
    private String appid;
    
    /**
     * 当前的时间戳
     */
    @ApiModelProperty(value = "当前的时间戳", required = true)
    private String timeStamp;

    /**
     * 随机字符串
     */
    @ApiModelProperty(value = "随机字符串", required = true)
    private String nonceStr;

    /**
     * 参数值
     */
    @ApiModelProperty(value = "参数值", required = true)
    private String dataPackage;

    /**
     * 签名类型
     */
    @ApiModelProperty(value = "签名类型", required = true)
    private String signType;

    /**
     * 签名
     */
    @ApiModelProperty(value = "签名", required = true)
    private String paySign;

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public String getDataPackage() {
        return dataPackage;
    }

    public void setDataPackage(String dataPackage) {
        this.dataPackage = dataPackage;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    public String getPaySign() {
        return paySign;
    }

    public void setPaySign(String paySign) {
        this.paySign = paySign;
    }
    
}
