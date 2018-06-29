package com.lawu.chick.api.dto;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author meishuquan
 * @date 2018/5/16.
 */
public class GiftListDTO {

    @ApiModelProperty(value = "礼品列表")
    private List<GiftDTO> giftDTOS;

    public List<GiftDTO> getGiftDTOS() {
        return giftDTOS;
    }

    public void setGiftDTOS(List<GiftDTO> giftDTOS) {
        this.giftDTOS = giftDTOS;
    }
}
