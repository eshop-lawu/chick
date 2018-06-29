package com.lawu.chick.operator.api.dto;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author meishuquan
 * @date 2018/4/28.
 */
public class EggExchangeRedpacketDTO {

    @ApiModelProperty(value = "最小值")
    private BigDecimal minPrice;

    @ApiModelProperty(value = "最大值")
    private BigDecimal maxPrice;

    @ApiModelProperty(value = "所占比例")
    private BigDecimal priceRate;

    public BigDecimal getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(BigDecimal minPrice) {
        this.minPrice = minPrice;
    }

    public BigDecimal getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(BigDecimal maxPrice) {
        this.maxPrice = maxPrice;
    }

    public BigDecimal getPriceRate() {
        return priceRate;
    }

    public void setPriceRate(BigDecimal priceRate) {
        this.priceRate = priceRate;
    }
}
