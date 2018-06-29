package com.lawu.chick.service.enums;

/**
 * @author zhangyong
 * @date 2018/4/26.
 */
public enum StatusEnum {
    /**
     * 禁用
     */
    DISABLE((byte) 0x00, "禁用"),


    /**
     * 启用
     */
    ENABLE((byte) 0x01, "启用");

    private Byte value;

    private String name;

    StatusEnum(Byte value, String name) {
        this.value = value;
        this.name = name;
    }

    public Byte getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public static StatusEnum getEnum(Byte value) {
        for (StatusEnum item : StatusEnum.values()) {
            if (item.getValue().equals(value)) {
                return item;
            }
        }
        return null;
    }
}
