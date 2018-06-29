
package com.lawu.chick.service.enums;

/**
 * 小鸡事件表事件因子枚举类
 * 0时间 1喂养 2处于/未处于牧场 3处于/未处于鸡舍 4打扫 5放养
 */
public enum EventRecordFactorEnum {

    TIME((byte) 0x00, "时间"),

    FEED((byte) 0x01, "喂养"),

    ON_PASTURE((byte) 0x02, "处于/未处于牧场"),

    GROWTH((byte) 0x03, "处于/未处于鸡舍"),

    CLEAN((byte) 0x04, "打扫"),

    RESTOCKING((byte) 0x05, "放养"),

    IS_TREAT((byte) 0x06, "治疗"),

    EGG_OPERATE((byte) 0x07, "鸡蛋操作"),
    
    MASTER_FEED((byte)0x08,"主人喂养额外增加");

    private Byte val;

    private String name;

    EventRecordFactorEnum(Byte val, String name) {
        this.val = val;
        this.name = name;
    }

    public Byte getVal() {
        return val;
    }

    public String getName() {
        return name;
    }

    public static EventRecordFactorEnum getEnum(Byte val) {
        EventRecordFactorEnum[] values = EventRecordFactorEnum.values();
        for (EventRecordFactorEnum object : values) {
            if (object.val.equals(val)) {
                return object;
            }
        }
        return null;
    }

}
