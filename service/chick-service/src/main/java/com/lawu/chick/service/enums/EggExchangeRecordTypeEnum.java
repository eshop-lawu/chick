
package com.lawu.chick.service.enums;

/**
 * @author meishuquan
 * @date 2018/4/26.
 */
public enum EggExchangeRecordTypeEnum {

    REDPACKET((byte) 0x00, "红包"),
    GIFT((byte) 0x01, "礼品");

    private Byte val;
    private String name;

    EggExchangeRecordTypeEnum(Byte val, String name) {
        this.val = val;
        this.name = name;
    }

    public Byte getVal() {
        return val;
    }

    public String getName() {
        return name;
    }

    public static EggExchangeRecordTypeEnum getEnum(Byte val) {
        EggExchangeRecordTypeEnum[] values = EggExchangeRecordTypeEnum.values();
        for (EggExchangeRecordTypeEnum object : values) {
            if (object.val.equals(val)) {
                return object;
            }
        }
        return null;
    }

}
