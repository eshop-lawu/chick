package com.lawu.chick.repository.param;

public class ChickenCureTaskAddParam {
    /**
     * 用户编号
     */
    private String memberNum;

    /**
     * 治愈任务类型
     */
    private Byte chickenCureTaskType;

    /**
     * 帮助喂食和打扫的好友id字符串
     */
    private String friendMemberId;

    public String getMemberNum() {
        return memberNum;
    }

    public void setMemberNum(String memberNum) {
        this.memberNum = memberNum;
    }

    public Byte getChickenCureTaskType() {
        return chickenCureTaskType;
    }

    public void setChickenCureTaskType(Byte chickenCureTaskType) {
        this.chickenCureTaskType = chickenCureTaskType;
    }

    public String getFriendMemberId() {
        return friendMemberId;
    }

    public void setFriendMemberId(String friendMemberId) {
        this.friendMemberId = friendMemberId;
    }
}
