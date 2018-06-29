
package com.lawu.chick.service.enums;

/**
 * @Description
 * @author zhangrc
 * @date 2018年4月25日
 */
public enum PeriodTypeEnum {
	
	/**
     * 成长期
     */
    GROWTH((byte) 0x00, "成长期"),
    
    /**
     * 半成长期
     */
    HALF_GROWTH((byte) 0x01, "半成长期"),
    
    /**
     * 成熟期
     */
    MATURE((byte) 0x02, "成熟期"),
    
    /**
     * 生病期
     */
    FAIL_ILL((byte) 0x03, "生病期"),
    
    /**
     * 治疗中
     */
    CURE((byte) 0x04, "治疗中"),
    
    /**
     * 死亡
     */
    DIE((byte) 0x05, "死亡");

    private Byte val;

    private String name;

    PeriodTypeEnum(Byte val, String name) {
        this.val = val;
        this.name = name;
    }

    public Byte getVal() {
        return val;
    }

    public String getName() {
        return name;
    }

    public static PeriodTypeEnum getEnum(Byte val) {
    	PeriodTypeEnum[] values = PeriodTypeEnum.values();
        for (PeriodTypeEnum object : values) {
            if (object.val.equals(val)) {
                return object;
            }
        }
        return null;
    }

}
