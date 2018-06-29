package com.lawu.chick.api.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lawu.chick.service.enums.EggExchangeRecordStatusEnum;
import com.lawu.chick.service.enums.EggExchangeRecordTypeEnum;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author meishuquan
 * @date 2018/4/26.
 */
public class EggExchangeRecordDTO {

    @ApiModelProperty(value = "兑换类型：REDPACKET--红包，GIFT--礼品")
    private EggExchangeRecordTypeEnum typeEnum;

    @ApiModelProperty(value = "兑换金额")
    private BigDecimal amount;

    @ApiModelProperty(value = "状态：PENDING--待处理，FAIL--发放失败，SENT--已发放，RECEIVED--已到账，REFUND--已退款")
    private EggExchangeRecordStatusEnum statusEnum;

    @ApiModelProperty(value = "物流单号")
    private String expressNum;

    @ApiModelProperty(value = "兑换时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date gmtCreate;

    @ApiModelProperty(value = "礼品名称")
    private String giftName;

    @ApiModelProperty(value = "礼品图片")
    private String giftImgPath;

    public EggExchangeRecordTypeEnum getTypeEnum() {
        return typeEnum;
    }

    public void setTypeEnum(EggExchangeRecordTypeEnum typeEnum) {
        this.typeEnum = typeEnum;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public EggExchangeRecordStatusEnum getStatusEnum() {
        return statusEnum;
    }

    public void setStatusEnum(EggExchangeRecordStatusEnum statusEnum) {
        this.statusEnum = statusEnum;
    }

    public String getExpressNum() {
        return expressNum;
    }

    public void setExpressNum(String expressNum) {
        this.expressNum = expressNum;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public String getGiftName() {
        return giftName;
    }

    public void setGiftName(String giftName) {
        this.giftName = giftName;
    }

    public String getGiftImgPath() {
        return giftImgPath;
    }

    public void setGiftImgPath(String giftImgPath) {
        this.giftImgPath = giftImgPath;
    }
}
