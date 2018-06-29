package com.lawu.chick.repository.domain.extend;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author zhangyong
 * @date 2018/4/25.
 */
public class FriendDOView implements Serializable{

    private static final long serialVersionUID = 5989800791828663263L;
    private String friendNum;
    /**
     * 好友昵称
     */
    private String nickName;
    /**
     * 好友性别
     */
    private Integer gender;

    /**
     * 头像
     */
    private String avatarUrl;

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

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
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
