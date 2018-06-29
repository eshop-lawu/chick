package com.lawu.chick.service.param;

import java.util.List;

import com.lawu.chick.service.enums.StatusEnum;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author zhangyong
 * @date 2018/4/26.
 */
public class SignInRuleParam {

    @ApiModelProperty(value = "商品编号")
    private String productNum;

    private List<SignRuleExtraParam> extras;

    @ApiModelProperty(value = "开始时间",required = true)
    private String gmtStart;

    @ApiModelProperty(value = "结束时间",required = true)
    private String gmtEnd;

    private Long id;

    private StatusEnum status;

    private Integer productCount;

    public String getProductNum() {
        return productNum;
    }

    public void setProductNum(String productNum) {
        this.productNum = productNum;
    }

    public List<SignRuleExtraParam> getExtras() {
        return extras;
    }

    public void setExtras(List<SignRuleExtraParam> extras) {
        this.extras = extras;
    }

    public String getGmtStart() {
        return gmtStart;
    }

    public void setGmtStart(String gmtStart) {
        this.gmtStart = gmtStart;
    }

    public String getGmtEnd() {
        return gmtEnd;
    }

    public void setGmtEnd(String gmtEnd) {
        this.gmtEnd = gmtEnd;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public Integer getProductCount() {
        return productCount;
    }

    public void setProductCount(Integer productCount) {
        this.productCount = productCount;
    }
}
