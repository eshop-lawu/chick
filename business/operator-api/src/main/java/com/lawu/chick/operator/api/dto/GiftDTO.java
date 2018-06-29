package com.lawu.chick.operator.api.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lawu.chick.service.enums.GiftStatusEnum;
import com.lawu.chick.service.enums.GiftTypeEnum;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author meishuquan
 * @date 2018/5/16.
 */
public class GiftDTO {

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

    @ApiModelProperty(value = "礼品状态")
    private GiftStatusEnum statusEnum;

    @ApiModelProperty(value = "礼品状态描述")
    private String statusDes;

    @ApiModelProperty(value = "礼品类型")
    private GiftTypeEnum typeEnum;

    @ApiModelProperty(value = "礼品类型描述")
    private String typeDes;

    @ApiModelProperty(value = "兑换鸡蛋数量")
    private BigDecimal eggQuantity;

    @ApiModelProperty(value = "礼品描述")
    private String description;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date gmtCreate;

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

    public String getStatusDes() {
        return statusDes;
    }

    public void setStatusDes(String statusDes) {
        this.statusDes = statusDes;
    }

    public GiftTypeEnum getTypeEnum() {
        return typeEnum;
    }

    public void setTypeEnum(GiftTypeEnum typeEnum) {
        this.typeEnum = typeEnum;
    }

    public String getTypeDes() {
        return typeDes;
    }

    public void setTypeDes(String typeDes) {
        this.typeDes = typeDes;
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

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }
}
