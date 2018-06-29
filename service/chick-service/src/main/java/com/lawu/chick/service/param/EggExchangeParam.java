package com.lawu.chick.service.param;

import java.math.BigDecimal;

import com.lawu.chick.service.enums.EggExchangeRecordStatusEnum;
import com.lawu.chick.service.enums.EggExchangeRecordTypeEnum;

/**
 * @author meishuquan
 * @date 2018/4/26.
 */
public class EggExchangeParam extends EggExchangeGiftParam {

    private String memberNum;

    private EggExchangeRecordTypeEnum typeEnum;

    private BigDecimal eggQuantity;

    private EggExchangeRecordStatusEnum statusEnum;

    private BigDecimal amount;

    private String giftName;

    private String giftImgPath;

    public String getMemberNum() {
        return memberNum;
    }

    public void setMemberNum(String memberNum) {
        this.memberNum = memberNum;
    }

    public EggExchangeRecordTypeEnum getTypeEnum() {
        return typeEnum;
    }

    public void setTypeEnum(EggExchangeRecordTypeEnum typeEnum) {
        this.typeEnum = typeEnum;
    }

    public BigDecimal getEggQuantity() {
        return eggQuantity;
    }

    public void setEggQuantity(BigDecimal eggQuantity) {
        this.eggQuantity = eggQuantity;
    }

    public EggExchangeRecordStatusEnum getStatusEnum() {
        return statusEnum;
    }

    public void setStatusEnum(EggExchangeRecordStatusEnum statusEnum) {
        this.statusEnum = statusEnum;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
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
