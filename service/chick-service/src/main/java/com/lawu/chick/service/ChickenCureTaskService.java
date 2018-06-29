package com.lawu.chick.service;

import com.lawu.chick.service.bo.CureTaskRtnBO;
import com.lawu.chick.service.param.ChickenCureTaskCalcParam;

public interface ChickenCureTaskService {

    /**
     * 处理小鸡治愈任务进度
     *
     * @param chickenCureTaskCalcParam
     */
    void dealCureTaskProcess(ChickenCureTaskCalcParam chickenCureTaskCalcParam);

    /**
     * 查询小鸡治愈任务列表
     *
     * @param currentUserNum
     * @param chickenNum
     * @return
     */
    CureTaskRtnBO getTreatTaskList(String currentUserNum,String chickenNum);
}
