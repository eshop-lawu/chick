package com.lawu.chick.api.dto;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author zhangyong
 * @date 2018/4/25.
 */
public class FriendListDTO {

    @ApiModelProperty(value = "好友编号")
    private String friendNum;

    @ApiModelProperty(value = "好友昵称")
    private String nickName;

    @ApiModelProperty(value = "好友头像")
    private String avatarUrl;

    @ApiModelProperty(value = "鸡蛋数量")
    private BigDecimal eggs;

    public String getFriendNum() {
        return friendNum;
    }

    public void setFriendNum(String friendNum) {
        this.friendNum = friendNum;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public BigDecimal getEggs() {
        return eggs;
    }

    public void setEggs(BigDecimal eggs) {
        this.eggs = eggs;
    }
}
