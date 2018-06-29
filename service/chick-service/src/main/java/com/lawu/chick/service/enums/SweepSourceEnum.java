
package com.lawu.chick.service.enums;

public enum SweepSourceEnum {

    OWNER((byte) 0x01, "自己"),

    FRIENDS((byte) 0x02, "好友");

    private Byte val;

    private String name;

    SweepSourceEnum(Byte val, String name) {
        this.val = val;
        this.name = name;
    }

    public Byte getVal() {
        return val;
    }

    public String getName() {
        return name;
    }

    public static SweepSourceEnum getEnum(Byte val) {
    	SweepSourceEnum[] values = SweepSourceEnum.values();
        for (SweepSourceEnum object : values) {
            if (object.val.equals(val)) {
                return object;
            }
        }
        return null;
    }

}
