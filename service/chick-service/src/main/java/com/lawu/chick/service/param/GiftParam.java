package com.lawu.chick.service.param;

import java.math.BigDecimal;

import com.lawu.chick.service.enums.GiftStatusEnum;
import com.lawu.chick.service.enums.GiftTypeEnum;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author meishuquan
 * @date 2018/5/16.
 */
public class GiftParam {

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "图片")
    private String imgPath;

    @ApiModelProperty(value = "价格")
    private BigDecimal price;

    @ApiModelProperty(value = "库存")
    private Integer inventory;

    @ApiModelProperty(value = "状态")
    private GiftStatusEnum statusEnum;

    @ApiModelProperty(value = "类型")
    private GiftTypeEnum typeEnum;

    @ApiModelProperty(value = "兑换鸡蛋数量")
    private BigDecimal eggQuantity;

    @ApiModelProperty(value = "描述")
    private String description;

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

    public Integer getInventory() {
        return inventory;
    }

    public void setInventory(Integer inventory) {
        this.inventory = inventory;
    }

    public GiftStatusEnum getStatusEnum() {
        return statusEnum;
    }

    public void setStatusEnum(GiftStatusEnum statusEnum) {
        this.statusEnum = statusEnum;
    }

    public GiftTypeEnum getTypeEnum() {
        return typeEnum;
    }

    public void setTypeEnum(GiftTypeEnum typeEnum) {
        this.typeEnum = typeEnum;
    }

    public BigDecimal getEggQuantity() {
        return eggQuantity;
    }

    public void setEggQuantity(BigDecimal eggQuantity) {
        this.eggQuantity = eggQuantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
