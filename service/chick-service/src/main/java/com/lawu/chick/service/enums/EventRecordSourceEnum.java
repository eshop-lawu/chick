
package com.lawu.chick.service.enums;

public enum EventRecordSourceEnum {

    NONE((byte) 0x00, "系统"),

    OWNER((byte) 0x01, "主人"),

    FRIENDS((byte) 0x02, "好友");

    private Byte val;

    private String name;

    EventRecordSourceEnum(Byte val, String name) {
        this.val = val;
        this.name = name;
    }

    public Byte getVal() {
        return val;
    }

    public String getName() {
        return name;
    }

    public static EventRecordSourceEnum getEnum(Byte val) {
    	EventRecordSourceEnum[] values = EventRecordSourceEnum.values();
        for (EventRecordSourceEnum object : values) {
            if (object.val.equals(val)) {
                return object;
            }
        }
        return null;
    }

}
