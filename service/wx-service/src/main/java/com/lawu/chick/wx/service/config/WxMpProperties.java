package com.lawu.chick.wx.service.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Leach
 * @date 2018/6/4
 */
@ConfigurationProperties(prefix = "lawu.weixin-api.mp.auth")
public class WxMpProperties {


    private String appKey;

    private String redirectUrl;

    private String getUserInfoUrl;

    private String getSubscribeUserInfoUrl;

    private String appid;

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    public String getGetUserInfoUrl() {
        return getUserInfoUrl;
    }

    public void setGetUserInfoUrl(String getUserInfoUrl) {
        this.getUserInfoUrl = getUserInfoUrl;
    }

    public String getGetSubscribeUserInfoUrl() {
        return getSubscribeUserInfoUrl;
    }

    public void setGetSubscribeUserInfoUrl(String getSubscribeUserInfoUrl) {
        this.getSubscribeUserInfoUrl = getSubscribeUserInfoUrl;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }
}
