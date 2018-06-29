package com.lawu.chick.operator.api.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author meishuquan
 * @date 2018/6/15.
 */
public class RewardsConfigDTO {

    @ApiModelProperty(value = "商品编号")
    private String productNum;

    @ApiModelProperty(value = "商品数量")
    private Integer productCount;

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
}
