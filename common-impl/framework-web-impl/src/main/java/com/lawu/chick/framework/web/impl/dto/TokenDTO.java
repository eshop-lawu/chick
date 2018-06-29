package com.lawu.chick.framework.web.impl.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * 登录TokenDTO
 * @author jiangxinjun
 * @createDate 2018年4月24日
 * @updateDate 2018年4月24日
 */
public class TokenDTO {

    @ApiModelProperty(value = "用户编号，未注册时为空", required = false)
    private String userNum;

    @ApiModelProperty(value = "用户token，登录后每次请求必须带上该标志，未注册时为空", required = false)
    private String token;

    @ApiModelProperty(value = "微信openid", required = true)
    private String openid;

    @ApiModelProperty(value = "微信昵称", required = true)
    private String nickname;

    @ApiModelProperty(value = "微信头像", required = true)
    private String avatarUrl;

    /**
     * openid
     */
    @Deprecated
    @ApiModelProperty(value = "会话密钥", required = true, hidden = true)
    private String sessionKey;
    
    /**
     * 小鸡领养个数
     */
    @ApiModelProperty(value = "小鸡领养个数，老用户该值为0", required = true)
    private Integer chickAdoptionCount = 0;

    @ApiModelProperty(value = "是否新注册用户", required = true)
    private Boolean newReg = false;
    
    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    @Deprecated
    public String getSessionKey() {
        return sessionKey;
    }

    @Deprecated
    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    public Integer getChickAdoptionCount() {
        return chickAdoptionCount;
    }

    public void setChickAdoptionCount(Integer chickAdoptionCount) {
        this.chickAdoptionCount = chickAdoptionCount;
    }

    public Boolean getNewReg() {
        return newReg;
    }

    public void setNewReg(Boolean newReg) {
        this.newReg = newReg;
    }
}
