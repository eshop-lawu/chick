package com.lawu.chick.api.dto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author zhangyong
 * @date 2018/4/27.
 */
public class SignRecordListDTO {

    private List<SignRuleExtraListDTO> extras;

    @ApiModelProperty(value = "开始时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date gmtStart;

    @ApiModelProperty(value = "结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date gmtEnd;

    @ApiModelProperty(value = "签到奖励商品名称")
    private String productName;

    @ApiModelProperty(value = "签到奖励商品图片")
    private String imgPath;

    @ApiModelProperty(value = "签到奖励数量")
    private Integer count;

    private List<SignRecordDTO> recordDTOS;

    public List<SignRuleExtraListDTO> getExtras() {
        return extras;
    }

    public void setExtras(List<SignRuleExtraListDTO> extras) {
        this.extras = extras;
    }

    public Date getGmtStart() {
        return gmtStart;
    }

    public void setGmtStart(Date gmtStart) {
        this.gmtStart = gmtStart;
    }

    public Date getGmtEnd() {
        return gmtEnd;
    }

    public void setGmtEnd(Date gmtEnd) {
        this.gmtEnd = gmtEnd;
    }

    public List<SignRecordDTO> getRecordDTOS() {
        return recordDTOS;
    }

    public void setRecordDTOS(List<SignRecordDTO> recordDTOS) {
        this.recordDTOS = recordDTOS;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
