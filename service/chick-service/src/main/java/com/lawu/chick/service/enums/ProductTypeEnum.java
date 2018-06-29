package com.lawu.chick.service.enums;

/**
 * 商品类型
 * @author jiangxinjun
 * @createDate 2018年4月25日
 * @updateDate 2018年4月25日
 */
public enum ProductTypeEnum {

    /**
     * 小鸡
     */
    CHICK((byte) 0),
    
    /**
     * 小鸡道具
     */
    CHICKEN_PROPS((byte) 1);

    private Byte value;

    ProductTypeEnum(Byte value) {
        this.value = value;
    }

    public Byte getValue() {
        return value;
    }

    public static ProductTypeEnum getEnum(Byte value) {
        for (ProductTypeEnum item : ProductTypeEnum.values()) {
            if (item.getValue().equals(value)) {
                return item;
            }
        }
        return null;
    }

}
