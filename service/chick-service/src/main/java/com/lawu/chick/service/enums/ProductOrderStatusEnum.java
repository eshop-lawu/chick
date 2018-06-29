package com.lawu.chick.service.enums;

/**
 * 商品订单状态
 * @author jiangxinjun
 * @createDate 2018年4月27日
 * @updateDate 2018年4月27日
 */
public enum ProductOrderStatusEnum {

    /**
     * 待支付
     */
    UNPAID((byte) 0x01),
    
    /**
     * 交易成功
     */
    TRANSACTION_SUCCESS((byte) 0x02),
    
    /**
     * 交易成功
     */
    TRANSACTION_FAILED((byte) 0x03);

    private Byte value;

    ProductOrderStatusEnum(Byte value) {
        this.value = value;
    }

    public Byte getValue() {
        return value;
    }

    public static ProductOrderStatusEnum getEnum(Byte value) {
        for (ProductOrderStatusEnum item : ProductOrderStatusEnum.values()) {
            if (item.getValue().equals(value)) {
                return item;
            }
        }
        return null;
    }
}
