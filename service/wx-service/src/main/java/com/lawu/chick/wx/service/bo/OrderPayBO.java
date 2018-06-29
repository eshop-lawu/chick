package com.lawu.chick.wx.service.bo;

/**
 * @author Leach
 * @date 2018/4/27
 */
public class OrderPayBO {

    /**
     * 公众账号ID
     */
    private String appid;

    /**
     * 时间戳从1970年1月1日00:00:00至今的秒数,即当前的时间
     */
    private String timeStamp;

    /**
     * 随机字符串，长度为32个字符以下。
     */
    private String nonceStr;

    /**
     * 统一下单接口返回的 prepay_id 参数值，提交格式如：prepay_id=*
     */
    private String dataPackage;

    /**
     * 签名类型，默认为MD5，支持HMAC-SHA256和MD5。注意此处需与统一下单的签名类型一致
     */
    private String signType;

    /**
     * 签名
     */
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
