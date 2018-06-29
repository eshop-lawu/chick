package com.lawu.chick.service.bo;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author meishuquan
 * @date 2018/4/25.
 */
public class EggConfigBaseBO {

    private BigDecimal exchangeRedpacketEggs;

    private BigDecimal maxHouseEggs;

    private BigDecimal maxLayEggs;

    private BigDecimal layEggsTotal;

    private String wxSendName;

    private String wxWishing;

    private String wxActName;

    private String wxRemark;
    
    /**
     * 最小的保底值
     */
    private BigDecimal minGuaranteedValue;

    /**
     * 最大的保底值
     */
    private BigDecimal maxGuaranteedValue;

    private List<EggExchangeRedpacketBO> redpacketBOS;

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

    public String getWxSendName() {
        return wxSendName;
    }

    public void setWxSendName(String wxSendName) {
        this.wxSendName = wxSendName;
    }

    public String getWxWishing() {
        return wxWishing;
    }

    public void setWxWishing(String wxWishing) {
        this.wxWishing = wxWishing;
    }

    public String getWxActName() {
        return wxActName;
    }

    public void setWxActName(String wxActName) {
        this.wxActName = wxActName;
    }

    public String getWxRemark() {
        return wxRemark;
    }

    public void setWxRemark(String wxRemark) {
        this.wxRemark = wxRemark;
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

    public List<EggExchangeRedpacketBO> getRedpacketBOS() {
        return redpacketBOS;
    }

    public void setRedpacketBOS(List<EggExchangeRedpacketBO> redpacketBOS) {
        this.redpacketBOS = redpacketBOS;
    }
}
