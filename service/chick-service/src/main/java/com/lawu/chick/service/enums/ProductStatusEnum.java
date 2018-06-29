package com.lawu.chick.service.enums;

/**
 * 商品状态
 * @author jiangxinjun
 * @createDate 2018年4月25日
 * @updateDate 2018年4月25日
 */
public enum ProductStatusEnum {

    /**
     * 下架
     */
    DOWN((byte) 0),
    
    /**
     * 上架
     */
    UP((byte) 1);

    private Byte value;

    ProductStatusEnum(Byte value) {
        this.value = value;
    }

    public Byte getValue() {
        return value;
    }

    public static ProductStatusEnum getEnum(Byte value) {
        for (ProductStatusEnum item : ProductStatusEnum.values()) {
            if (item.getValue().equals(value)) {
                return item;
            }
        }
        return null;
    }
}
