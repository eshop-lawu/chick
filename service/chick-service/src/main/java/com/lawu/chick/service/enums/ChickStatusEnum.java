
package com.lawu.chick.service.enums;

/**
 * @Description
 * @author zhangrc
 * @date 2018年4月25日
 */
public enum ChickStatusEnum {
	
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
    HUNGERHALO((byte) 0x03, "饿晕");
    
    private Byte val;

    private String name;

    ChickStatusEnum(Byte val, String name) {
        this.val = val;
        this.name = name;
    }

    public Byte getVal() {
        return val;
    }

    public String getName() {
        return name;
    }

    public static ChickStatusEnum getEnum(Byte val) {
    	ChickStatusEnum[] values = ChickStatusEnum.values();
        for (ChickStatusEnum object : values) {
            if (object.val.equals(val)) {
                return object;
            }
        }
        return null;
    }

}
