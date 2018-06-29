package com.lawu.chick.service.event;

import com.lawu.chick.service.MemberService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.lawu.chick.service.enums.ChickenCureTaskTypeEnum;
import com.lawu.chick.service.param.ChickenCureTaskCalcParam;

@Component
public class EventPublisher {

    private static Logger logger = LoggerFactory.getLogger(EventPublisher.class);

    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    private MemberService memberService;

    /**
     * 完成治愈任务
     *
     * @param memberNum               用户编号
     * @param chickenCureTaskTypeEnum 任务类型枚举
     * @param friendMemberNum          好友编号，喂养和打扫时比传
     */
    public void publishCureTaskEvent(String memberNum, ChickenCureTaskTypeEnum chickenCureTaskTypeEnum,String friendMemberNum) {
        Long friendMemberId = null;
        if (StringUtils.isNotBlank(friendMemberNum)) {
            friendMemberId = memberService.getMemberId(friendMemberNum);
            if (friendMemberId == null || friendMemberId == 0L) {
                logger.error("小鸡治愈任务帮好友打扫/喂食,查询好友ID为空！");
                return;
            }
        }
        CureTaskEvent cureTaskEvent = new CureTaskEvent(this);
        cureTaskEvent.setMemberNum(memberNum);
        cureTaskEvent.setChickenCureTaskTypeEnum(chickenCureTaskTypeEnum);
        cureTaskEvent.setFriendMemberId(friendMemberId);
        applicationContext.publishEvent(cureTaskEvent);
    }
}
