package com.lawu.chick.service.query;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.lawu.chick.service.enums.EggExchangeRecordStatusEnum;
import com.lawu.chick.service.enums.EggExchangeRecordTypeEnum;
import com.lawu.framework.core.page.AbstractPageParam;

/**
 * @author meishuquan
 * @date 2018/4/26.
 */
public class EggExchangeRecordOperatorQuery extends AbstractPageParam {

    private EggExchangeRecordTypeEnum typeEnum;

    private EggExchangeRecordStatusEnum statusEnum;

    private String giftName;

    private String name;

    private String mobile;

    private String expressNum;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date beginTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endTime;

    public EggExchangeRecordTypeEnum getTypeEnum() {
        return typeEnum;
    }

    public void setTypeEnum(EggExchangeRecordTypeEnum typeEnum) {
        this.typeEnum = typeEnum;
    }

    public EggExchangeRecordStatusEnum getStatusEnum() {
        return statusEnum;
    }

    public void setStatusEnum(EggExchangeRecordStatusEnum statusEnum) {
        this.statusEnum = statusEnum;
    }

    public String getGiftName() {
        return giftName;
    }

    public void setGiftName(String giftName) {
        this.giftName = giftName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getExpressNum() {
        return expressNum;
    }

    public void setExpressNum(String expressNum) {
        this.expressNum = expressNum;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
