package com.lawu.chick.api.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lawu.chick.service.enums.EventRecordDirectionEnum;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author meishuquan
 * @date 2018/5/10.
 */
public class EggRecordDTO {

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "ADD--增加，REDUCE--减少")
    private EventRecordDirectionEnum directionEnum;

    @ApiModelProperty(value = "值")
    private BigDecimal val;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date gmtCreate;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public EventRecordDirectionEnum getDirectionEnum() {
        return directionEnum;
    }

    public void setDirectionEnum(EventRecordDirectionEnum directionEnum) {
        this.directionEnum = directionEnum;
    }

    public BigDecimal getVal() {
        return val;
    }

    public void setVal(BigDecimal val) {
        this.val = val;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }
}
