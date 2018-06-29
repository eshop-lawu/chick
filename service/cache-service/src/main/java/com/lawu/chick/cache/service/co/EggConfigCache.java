package com.lawu.chick.cache.service.co;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author meishuquan
 * @date 2018/4/25.
 */
public class EggConfigCache implements Serializable {

    private static final long serialVersionUID = -3502725542585850055L;

    private BigDecimal exchangeRedpacketEggs;

    private BigDecimal maxHouseEggs;

    private BigDecimal maxLayEggs;

    private BigDecimal layEggsTotal;
    
    /**
     * 最小的保底值
     */
    private BigDecimal minGuaranteedValue;

    /**
     * 最大的保底值
     */
    private BigDecimal maxGuaranteedValue;

    public BigDecimal getExchangeRedpacketEggs() {
        return exchangeRedpacketEggs;
    }

    public void setExchangeRedpacketEggs(BigDecimal exchangeRedpacketEggs) {
        this.exchangeRedpacketEggs = exchangeRedpacketEggs;
    }

    public BigDecimal getMaxHouseEggs() {
        return maxHouseEggs;
    }

    public void setMaxHouseEggs(BigDecimal maxHouseEggs) {
        this.maxHouseEggs = maxHouseEggs;
    }

    public BigDecimal getMaxLayEggs() {
        return maxLayEggs;
    }

    public void setMaxLayEggs(BigDecimal maxLayEggs) {
        this.maxLayEggs = maxLayEggs;
    }

    public BigDecimal getLayEggsTotal() {
        return layEggsTotal;
    }

    public void setLayEggsTotal(BigDecimal layEggsTotal) {
        this.layEggsTotal = layEggsTotal;
    }

    public BigDecimal getMinGuaranteedValue() {
        return minGuaranteedValue;
    }

    public void setMinGuaranteedValue(BigDecimal minGuaranteedValue) {
        this.minGuaranteedValue = minGuaranteedValue;
    }

    public BigDecimal getMaxGuaranteedValue() {
        return maxGuaranteedValue;
    }

    public void setMaxGuaranteedValue(BigDecimal maxGuaranteedValue) {
        this.maxGuaranteedValue = maxGuaranteedValue;
    }

}
