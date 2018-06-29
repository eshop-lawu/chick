package com.lawu.chick.service.event;

import com.lawu.chick.service.ChickenCureTaskService;
import com.lawu.chick.service.param.ChickenCureTaskCalcParam;
import com.lawu.framework.core.event.AsyncEventHandle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CureTaskEventHandle implements AsyncEventHandle<CureTaskEvent> {

    @Autowired
    private ChickenCureTaskService chickenCureTaskService;

    @Override
    public void execute(CureTaskEvent cureTaskEvent) {
        ChickenCureTaskCalcParam chickenCureTaskCalcParam = new ChickenCureTaskCalcParam();
        chickenCureTaskCalcParam.setMemberNum(cureTaskEvent.getMemberNum());
        chickenCureTaskCalcParam.setChickenCureTaskTypeEnum(cureTaskEvent.getChickenCureTaskTypeEnum());
        chickenCureTaskCalcParam.setFriendMemberId(cureTaskEvent.getFriendMemberId());
        chickenCureTaskService.dealCureTaskProcess(chickenCureTaskCalcParam);
    }
}
