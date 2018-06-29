
package com.lawu.chick.service.enums;

public enum EventRecordDirectionEnum {

    NONE((byte) 0x00, "活动"),

    ADD((byte) 0x01, "增加"),

    REDUCE((byte) 0x02, "减少");

    private Byte val;

    private String name;

    EventRecordDirectionEnum(Byte val, String name) {
        this.val = val;
        this.name = name;
    }

    public Byte getVal() {
        return val;
    }

    public String getName() {
        return name;
    }

    public static EventRecordDirectionEnum getEnum(Byte val) {
    	EventRecordDirectionEnum[] values = EventRecordDirectionEnum.values();
        for (EventRecordDirectionEnum object : values) {
            if (object.val.equals(val)) {
                return object;
            }
        }
        return null;
    }

}
