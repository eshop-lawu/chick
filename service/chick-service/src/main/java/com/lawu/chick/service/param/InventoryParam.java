package com.lawu.chick.service.param;

import com.lawu.chick.service.enums.InventoryDetailDirectionEnum;
import com.lawu.chick.service.enums.InventoryDetailTypeEnum;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author meishuquan
 * @date 2018/5/15.
 */
public class InventoryParam {

    @ApiModelProperty(value = "库存id")
    private Long inventoryId;

    @ApiModelProperty(value = "会员编号")
    private String memberNum;

    @ApiModelProperty(value = "商品编号")
    private String productNum;

    @ApiModelProperty(value = "商品数量")
    private Integer quantity;

    @ApiModelProperty(value = "好友编号")
    private String friendNum;

    @ApiModelProperty(value = "触发类型")
    private InventoryDetailTypeEnum typeEnum;

    @ApiModelProperty(value = "触发流向")
    private InventoryDetailDirectionEnum directionEnum;

    public Long getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(Long inventoryId) {
        this.inventoryId = inventoryId;
    }

    public String getMemberNum() {
        return memberNum;
    }

    public void setMemberNum(String memberNum) {
        this.memberNum = memberNum;
    }

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

    public String getFriendNum() {
        return friendNum;
    }

    public void setFriendNum(String friendNum) {
        this.friendNum = friendNum;
    }

    public InventoryDetailTypeEnum getTypeEnum() {
        return typeEnum;
    }

    public void setTypeEnum(InventoryDetailTypeEnum typeEnum) {
        this.typeEnum = typeEnum;
    }

    public InventoryDetailDirectionEnum getDirectionEnum() {
        return directionEnum;
    }

    public void setDirectionEnum(InventoryDetailDirectionEnum directionEnum) {
        this.directionEnum = directionEnum;
    }
}
