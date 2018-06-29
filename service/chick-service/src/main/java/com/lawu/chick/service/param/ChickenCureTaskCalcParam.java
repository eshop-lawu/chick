package com.lawu.chick.service.param;

import com.lawu.chick.service.enums.ChickenCureTaskTypeEnum;

public class ChickenCureTaskCalcParam {

    /**
     * 用户编号
     */
    private String memberNum;

    /**
     * 治愈任务类型
     */
    private ChickenCureTaskTypeEnum chickenCureTaskTypeEnum;

    /**
     * 当前治愈任务类型为帮助好友打扫和喂食时--->必传
     */
    private Long friendMemberId;

    public String getMemberNum() {
        return memberNum;
    }

    public void setMemberNum(String memberNum) {
        this.memberNum = memberNum;
    }

    public ChickenCureTaskTypeEnum getChickenCureTaskTypeEnum() {
        return chickenCureTaskTypeEnum;
    }

    public void setChickenCureTaskTypeEnum(ChickenCureTaskTypeEnum chickenCureTaskTypeEnum) {
        this.chickenCureTaskTypeEnum = chickenCureTaskTypeEnum;
    }

    public Long getFriendMemberId() {
        return friendMemberId;
    }

    public void setFriendMemberId(Long friendMemberId) {
        this.friendMemberId = friendMemberId;
    }
}
