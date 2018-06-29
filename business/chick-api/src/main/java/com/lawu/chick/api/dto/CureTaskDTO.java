package com.lawu.chick.api.dto;

import com.lawu.chick.service.enums.ChickenCureTaskTypeEnum;
import io.swagger.annotations.ApiModelProperty;

public class CureTaskDTO {

    @ApiModelProperty(value = "治愈任务(INVISIT-邀请好友, SALES-消费, FEEDCLEAN-喂食和打扫, TRADE-鸡蛋兑换)")
    private ChickenCureTaskTypeEnum chickenCureTaskTypeEnum;

    @ApiModelProperty(value = "是否完成")
    private Boolean isFinish;

    @ApiModelProperty(value = "数量")
    private int number;

    public ChickenCureTaskTypeEnum getChickenCureTaskTypeEnum() {
        return chickenCureTaskTypeEnum;
    }

    public void setChickenCureTaskTypeEnum(ChickenCureTaskTypeEnum chickenCureTaskTypeEnum) {
        this.chickenCureTaskTypeEnum = chickenCureTaskTypeEnum;
    }

    public Boolean getFinish() {
        return isFinish;
    }

    public void setFinish(Boolean finish) {
        isFinish = finish;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
