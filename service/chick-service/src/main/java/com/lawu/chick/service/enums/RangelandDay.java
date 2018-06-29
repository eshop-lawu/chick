
package com.lawu.chick.service.enums;

/**
 * 领养小鸡的渠道
 * 
 * @Description
 * @author zhangrc
 * @date 2018年4月25日
 */
public enum RangelandDay {
	
	/**
     * 白天
     */
	DAY((byte) 0x01, "白天"),
    
    /**
     * 晚上
     */
	NIGHT((byte) 0x02, "晚上");

    private Byte val;

    private String name;

    RangelandDay(Byte val, String name) {
        this.val = val;
        this.name = name;
    }

    public Byte getVal() {
        return val;
    }

    public String getName() {
        return name;
    }

    public static RangelandDay getEnum(Byte val) {
    	RangelandDay[] values = RangelandDay.values();
        for (RangelandDay object : values) {
            if (object.val.equals(val)) {
                return object;
            }
        }
        return null;
    }

}
