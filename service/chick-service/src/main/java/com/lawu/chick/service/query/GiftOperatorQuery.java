package com.lawu.chick.service.query;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.lawu.chick.service.enums.GiftStatusEnum;
import com.lawu.chick.service.enums.GiftTypeEnum;
import com.lawu.framework.core.page.AbstractPageParam;

/**
 * @author meishuquan
 * @date 2018/5/16.
 */
public class GiftOperatorQuery extends AbstractPageParam {

    private GiftTypeEnum typeEnum;

    private GiftStatusEnum statusEnum;

    private String name;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date beginTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endTime;

    public GiftTypeEnum getTypeEnum() {
        return typeEnum;
    }

    public void setTypeEnum(GiftTypeEnum typeEnum) {
        this.typeEnum = typeEnum;
    }

    public GiftStatusEnum getStatusEnum() {
        return statusEnum;
    }

    public void setStatusEnum(GiftStatusEnum statusEnum) {
        this.statusEnum = statusEnum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
