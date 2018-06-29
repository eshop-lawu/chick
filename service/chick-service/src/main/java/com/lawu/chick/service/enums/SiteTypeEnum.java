
package com.lawu.chick.service.enums;

/**
 * @Description
 * @author zhangrc
 * @date 2018年4月25日
 */
public enum SiteTypeEnum {
	
	/**
     * 外部
     */
    EXTERNAL((byte) 0x01, "外部"),
    
    /**
     * 鸡舍
     */
    HOUSE((byte) 0x02, "鸡舍");

    private Byte val;

    private String name;

    SiteTypeEnum(Byte val, String name) {
        this.val = val;
        this.name = name;
    }

    public Byte getVal() {
        return val;
    }

    public String getName() {
        return name;
    }

    public static SiteTypeEnum getEnum(Byte val) {
    	SiteTypeEnum[] values = SiteTypeEnum.values();
        for (SiteTypeEnum object : values) {
            if (object.val.equals(val)) {
                return object;
            }
        }
        return null;
    }

}
