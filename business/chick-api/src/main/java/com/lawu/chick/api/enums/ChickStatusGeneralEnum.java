
package com.lawu.chick.api.enums;

/**
 *  小鸡状态综合枚举
 */
public enum ChickStatusGeneralEnum {

	/**
     * 活动
     */
    ACTIVITY((byte) 0x00, "活动"),

    /**
     * 睡眠
     */
    SLEEP((byte) 0x01, "睡眠"),

    /**
     * 饥饿
     */
    HUNGER((byte) 0x02, "饥饿"),

    /**
     * 饿晕
     */
    HUNGERHALO((byte) 0x03, "饿晕"),

    /**
     * 生产
     */
    PRODUCE((byte) 0x04, "生产");

    private Byte val;

    private String name;

    ChickStatusGeneralEnum(Byte val, String name) {
        this.val = val;
        this.name = name;
    }

    public Byte getVal() {
        return val;
    }

    public String getName() {
        return name;
    }

    public static ChickStatusGeneralEnum getEnum(Byte val) {
    	ChickStatusGeneralEnum[] values = ChickStatusGeneralEnum.values();
        for (ChickStatusGeneralEnum object : values) {
            if (object.val.equals(val)) {
                return object;
            }
        }
        return null;
    }

}
