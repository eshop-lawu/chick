package com.lawu.chick.service.enums;

/**
 * @author meishuquan
 * @date 2018/5/16.
 */
public enum GiftTypeEnum {

    GOODS((byte) 0x01, "实物"),
    VIRTUAL_GOODS((byte) 0x02, "虚拟物品");

    private Byte val;
    private String name;

    GiftTypeEnum(Byte val, String name) {
        this.val = val;
        this.name = name;
    }

    public static GiftTypeEnum getEnum(Byte val) {
        GiftTypeEnum[] values = GiftTypeEnum.values();
        for (GiftTypeEnum object : values) {
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
