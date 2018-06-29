package com.lawu.chick.service.param;

import io.swagger.annotations.ApiModelProperty;

public class TreatParam {

    @ApiModelProperty(value = "小鸡编号", required = true)
    private String chickenNum;

    @ApiModelProperty(value = "是否治疗", required = true)
    private Boolean isTreat;

    public String getChickenNum() {
        return chickenNum;
    }

    public void setChickenNum(String chickenNum) {
        this.chickenNum = chickenNum;
    }

    public Boolean getIsTreat() {
        return isTreat;
    }

    public void setIsTreat(Boolean isTreat) {
        this.isTreat = isTreat;
    }
}
