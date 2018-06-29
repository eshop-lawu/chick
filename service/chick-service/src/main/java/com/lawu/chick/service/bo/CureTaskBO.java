package com.lawu.chick.service.bo;

import com.lawu.chick.service.enums.ChickenCureTaskTypeEnum;
import io.swagger.annotations.ApiModelProperty;

public class CureTaskBO {

    private ChickenCureTaskTypeEnum chickenCureTaskTypeEnum;

    private Boolean isFinish;

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
