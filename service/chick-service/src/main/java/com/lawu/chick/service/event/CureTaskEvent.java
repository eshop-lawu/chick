package com.lawu.chick.service.event;

import com.lawu.chick.service.enums.ChickenCureTaskTypeEnum;
import com.lawu.chick.service.param.ChickenCureTaskCalcParam;
import com.lawu.framework.core.event.AsyncEvent;

public class CureTaskEvent extends AsyncEvent {

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

    public CureTaskEvent(Object source) {
        super(source);
    }

    public String getMemberNum() {
        return memberNum;
    }

    public ChickenCureTaskTypeEnum getChickenCureTaskTypeEnum() {
        return chickenCureTaskTypeEnum;
    }

    public Long getFriendMemberId() {
        return friendMemberId;
    }

    public void setMemberNum(String memberNum) {
        this.memberNum = memberNum;
    }

    public void setChickenCureTaskTypeEnum(ChickenCureTaskTypeEnum chickenCureTaskTypeEnum) {
        this.chickenCureTaskTypeEnum = chickenCureTaskTypeEnum;
    }

    public void setFriendMemberId(Long friendMemberId) {
        this.friendMemberId = friendMemberId;
    }
}
