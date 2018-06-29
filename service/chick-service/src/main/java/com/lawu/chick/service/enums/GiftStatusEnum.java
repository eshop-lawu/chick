package com.lawu.chick.service.enums;

/**
 * @author meishuquan
 * @date 2018/5/16.
 */
public enum GiftStatusEnum {

    VALID((byte) 0x01, "有效"),
    INVALID((byte) 0x02, "无效");

    private Byte val;
    private String name;

    GiftStatusEnum(Byte val, String name) {
        this.val = val;
        this.name = name;
    }

    public static GiftStatusEnum getEnum(Byte val) {
        GiftStatusEnum[] values = GiftStatusEnum.values();
        for (GiftStatusEnum object : values) {
            if (object.val.equals(val)) {
                return object;
            }
        }
        return null;
    }

    public Byte getVal() {
        return val;
    }

    public String getName() {
        return name;
    }
}
