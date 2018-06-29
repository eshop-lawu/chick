package com.lawu.chick.wx.service.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Leach
 * @date 2018/4/27
 */
@ConfigurationProperties(prefix = "lawu.chick.wechat.pay")
public class WxPayProperties {
    /**
     * 设置微信公众号的appid
     */
    private String appId;

    /**
     * 微信支付商户号
     */
    private String mchId;

    /**
     * 微信支付商户密钥
     */
    private String mchKey;

    /**
     * apiclient_cert.p12文件的绝对路径，或者如果放在项目中，请以classpath:开头指定
     */
    private String keyPath;

    /**
     * 异步接收微信支付结果通知的回调地址，通知url必须为外网可访问的url，不能携带参数。
     */
    private String notifyUrl;

    /**
     * 是否启用红包发放
     */
    private boolean enableRedpack;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    public String getMchKey() {
        return mchKey;
    }

    public void setMchKey(String mchKey) {
        this.mchKey = mchKey;
    }

    public String getKeyPath() {
        return keyPath;
    }

    public void setKeyPath(String keyPath) {
        this.keyPath = keyPath;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public boolean isEnableRedpack() {
        return enableRedpack;
    }

    public void setEnableRedpack(boolean enableRedpack) {
        this.enableRedpack = enableRedpack;
    }
}
