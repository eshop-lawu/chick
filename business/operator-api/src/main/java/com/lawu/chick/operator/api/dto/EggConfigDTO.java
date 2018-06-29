package com.lawu.chick.operator.api.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author meishuquan
 * @date 2018/4/25.
 */
public class EggConfigDTO {

    @ApiModelProperty(value = "兑换红包所需鸡蛋数")
    private BigDecimal exchangeRedpacketEggs;

    @ApiModelProperty(value = "蛋窝最大鸡蛋数")
    private BigDecimal maxHouseEggs;

    @ApiModelProperty(value = "每次最大产蛋数")
    private BigDecimal maxLayEggs;

    @ApiModelProperty(value = "每天产蛋总数")
    private BigDecimal layEggsTotal;

    @ApiModelProperty(value = "鸡蛋配置生效时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date eggEffectiveTime;

    @ApiModelProperty(value = "微信红包商户名称")
    private String wxSendName;

    @ApiModelProperty(value = "微信红包祝福语")
    private String wxWishing;

    @ApiModelProperty(value = "微信红包活动名称")
    private String wxActName;

    @ApiModelProperty(value = "微信红包备注")
    private String wxRemark;

    /**
     * 最小的保底值
     */
    @ApiModelProperty(value = "最小的保底值")
    private BigDecimal minGuaranteedValue;

    /**
     * 最大的保底值
     */
    @ApiModelProperty(value = "最大的保底值")
    private BigDecimal maxGuaranteedValue;
    
    @ApiModelProperty(value = "红包金额")
    List<EggExchangeRedpacketDTO> redpacketDTOS;

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

    public Date getEggEffectiveTime() {
        return eggEffectiveTime;
    }

    public void setEggEffectiveTime(Date eggEffectiveTime) {
        this.eggEffectiveTime = eggEffectiveTime;
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

    public List<EggExchangeRedpacketDTO> getRedpacketDTOS() {
        return redpacketDTOS;
    }

    public void setRedpacketDTOS(List<EggExchangeRedpacketDTO> redpacketDTOS) {
        this.redpacketDTOS = redpacketDTOS;
    }
}
