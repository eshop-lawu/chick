package com.lawu.chick.api.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author meishuquan
 * @date 2018/4/26.
 */
public class EggExchangeValDTO {

    @ApiModelProperty(value = "鸡蛋可兑换的红包数量")
    private Integer eggExchangeVal;

    public Integer getEggExchangeVal() {
        return eggExchangeVal;
    }

    public void setEggExchangeVal(Integer eggExchangeVal) {
        this.eggExchangeVal = eggExchangeVal;
    }
}
