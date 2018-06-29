package com.lawu.chick.api.dto;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class RewewardConfigForwardRtnDTO {

    @ApiModelProperty(value = "商品编号")
    private List<RewewardConfigForwardDTO> rewewardConfigForwardDTOS;

    public List<RewewardConfigForwardDTO> getRewewardConfigForwardDTOS() {
        return rewewardConfigForwardDTOS;
    }

    public void setRewewardConfigForwardDTOS(List<RewewardConfigForwardDTO> rewewardConfigForwardDTOS) {
        this.rewewardConfigForwardDTOS = rewewardConfigForwardDTOS;
    }
}
