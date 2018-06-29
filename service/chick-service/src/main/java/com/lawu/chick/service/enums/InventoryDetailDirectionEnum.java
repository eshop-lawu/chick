
package com.lawu.chick.service.enums;

public enum InventoryDetailDirectionEnum {

    ADD((byte) 0x01, "增加"),

    REDUCE((byte) 0x02, "减少");

    private Byte val;

    private String name;

    InventoryDetailDirectionEnum(Byte val, String name) {
        this.val = val;
        this.name = name;
    }

    public Byte getVal() {
        return val;
    }

    public String getName() {
        return name;
    }

    public static InventoryDetailDirectionEnum getEnum(Byte val) {
        InventoryDetailDirectionEnum[] values = InventoryDetailDirectionEnum.values();
        for (InventoryDetailDirectionEnum object : values) {
            if (object.val.equals(val)) {
                return object;
            }
        }
        return null;
    }

}
