package com.lawu.chick.api.dto;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class ChickenBaseInfoRtnDTO {

    @ApiModelProperty(value = "小鸡信息")
    private List<ChickenBaseInfoDTO> chickenBaseInfoDTOList;

    @ApiModelProperty(value = "产蛋倒计时（分钟）")
    private int eggLayCutdown;

    public List<ChickenBaseInfoDTO> getChickenBaseInfoDTOList() {
        return chickenBaseInfoDTOList;
    }

    public void setChickenBaseInfoDTOList(List<ChickenBaseInfoDTO> chickenBaseInfoDTOList) {
        this.chickenBaseInfoDTOList = chickenBaseInfoDTOList;
    }

    public int getEggLayCutdown() {
        return eggLayCutdown;
    }

    public void setEggLayCutdown(int eggLayCutdown) {
        this.eggLayCutdown = eggLayCutdown;
    }
}
