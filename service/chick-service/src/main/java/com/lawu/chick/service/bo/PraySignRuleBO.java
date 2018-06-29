package com.lawu.chick.service.bo;

import com.lawu.chick.service.enums.StatusEnum;

/**
 * @author zhangyong
 * @date 2018/6/15.
 */
public class PraySignRuleBO {

    private Long id;

    private String productNum;

    private Integer productCount;

    private Boolean isBasisChick;

    private Integer day;

    private StatusEnum status;

    private String extras;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductNum() {
        return productNum;
    }

    public void setProductNum(String productNum) {
        this.productNum = productNum;
    }

    public Integer getProductCount() {
        return productCount;
    }

    public void setProductCount(Integer productCount) {
        this.productCount = productCount;
    }

    public Boolean getBasisChick() {
        return isBasisChick;
    }

    public void setBasisChick(Boolean basisChick) {
        isBasisChick = basisChick;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public String getExtras() {
        return extras;
    }

    public void setExtras(String extras) {
        this.extras = extras;
    }
}
