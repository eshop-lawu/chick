package com.lawu.chick.service.bo;

import java.math.BigDecimal;

/**
 * @author meishuquan
 * @date 2018/4/28.
 */
public class EggExchangeRedpacketBO {

    private Integer id;

    private BigDecimal minPrice;

    private BigDecimal maxPrice;

    private BigDecimal priceRate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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
