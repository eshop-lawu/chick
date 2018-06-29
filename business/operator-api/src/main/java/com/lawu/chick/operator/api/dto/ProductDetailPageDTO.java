package com.lawu.chick.operator.api.dto;

import java.math.BigDecimal;

import com.lawu.chick.service.enums.ProductStatusEnum;
import com.lawu.chick.service.enums.ProductTypeEnum;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 商品DTO
 * @author jiangxinjun
 * @createDate 2018年4月25日
 * @updateDate 2018年4月25日
 */
@ApiModel(description = "商品DTO")
public class ProductDetailPageDTO {
    
    /**
     * 主键
     */
    @ApiModelProperty(value = "主键", required = true)
    private Long id;

    /**
     * 名称
     */
    @ApiModelProperty(value = "名称", required = true)
    private String name;

    /**
     * 图片路径
     */
    @ApiModelProperty(value = "图片路径", required = true)
    private String imgPath;

    /**
     * 价格
     */
    @ApiModelProperty(value = "价格", required = true)
    private BigDecimal price;
    
    /**
     * 类型
     */
    @ApiModelProperty(value = "类型", required = true, reference = "CHICK-小鸡|CHICKEN_PROPS-小鸡道具")
    private ProductTypeEnum type;
    
    /**
     * 状态
     */
    @ApiModelProperty(value = "状态", required = true, reference = "DOWN-下架|UP-上架")
    private ProductStatusEnum status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public ProductTypeEnum getType() {
        return type;
    }

    public void setType(ProductTypeEnum type) {
        this.type = type;
    }

    public ProductStatusEnum getStatus() {
        return status;
    }

    public void setStatus(ProductStatusEnum status) {
        this.status = status;
    }

}