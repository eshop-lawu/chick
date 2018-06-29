package com.lawu.chick.api.param;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 商品订单购买参数
 * 
 * @author jiangxinjun
 * @createDate 2018年4月27日
 * @updateDate 2018年4月27日
 */
@ApiModel("商品订单购买参数")
public class ProductOrderBuyExternalParam {

    /**
     * 商品编号
     */
    @NotBlank
    @ApiModelProperty(value = "商品编号", required = true)
    private String productNum;

    /**
     * 数量
     */
    @Min(value = 1L)
    @ApiModelProperty(value = "数量", required = true)
    private Integer quantity;

    public String getProductNum() {
        return productNum;
    }

    public void setProductNum(String productNum) {
        this.productNum = productNum;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    
}
