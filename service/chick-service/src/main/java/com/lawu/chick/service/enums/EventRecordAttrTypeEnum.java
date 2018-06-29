
package com.lawu.chick.service.enums;

/**
 * 小鸡事件表涉及属性类型枚举类
 */
public enum EventRecordAttrTypeEnum {

    NONE((byte) 0x00, "无"),

    CLEANLINESS((byte) 0x01, "清洁度"),

    JOYFULL((byte) 0x02, "愉悦值"),

    GROWTH((byte) 0x03, "成长值"),

    FULL((byte) 0x04, "饱腹值"),

    EGG((byte) 0x05, "鸡蛋");

    private Byte val;

    private String name;

    EventRecordAttrTypeEnum(Byte val, String name) {
        this.val = val;
        this.name = name;
    }

    public Byte getVal() {
        return val;
    }

    public String getName() {
        return name;
    }

    public static EventRecordAttrTypeEnum getEnum(Byte val) {
        EventRecordAttrTypeEnum[] values = EventRecordAttrTypeEnum.values();
        for (EventRecordAttrTypeEnum object : values) {
            if (object.val.equals(val)) {
                return object;
            }
        }
        return null;
    }

}
