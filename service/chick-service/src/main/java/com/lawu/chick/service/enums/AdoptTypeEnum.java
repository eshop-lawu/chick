
package com.lawu.chick.service.enums;

/**
 * 领养小鸡的渠道
 * 
 * @Description
 * @author zhangrc
 * @date 2018年4月25日
 */
public enum AdoptTypeEnum {
	
	/**
     * 初始化
     */
    INIT((byte) 0x01, "初始化"),
    
    /**
     * 仓库激活
     */
    INVENTORY_ACTIVE((byte) 0x02, "仓库激活"),
    
    /**
     * 购买
     */
    BUY((byte) 0x03, "购买"),
    
    /**
     * 赠送
     */
    SEND((byte) 0x04, "赠送");

    private Byte val;

    private String name;

    AdoptTypeEnum(Byte val, String name) {
        this.val = val;
        this.name = name;
    }

    public Byte getVal() {
        return val;
    }

    public String getName() {
        return name;
    }

    public static AdoptTypeEnum getEnum(Byte val) {
    	AdoptTypeEnum[] values = AdoptTypeEnum.values();
        for (AdoptTypeEnum object : values) {
            if (object.val.equals(val)) {
                return object;
            }
        }
        return null;
    }

}
