
package com.lawu.chick.service.enums;

/**
 * @author meishuquan
 * @date 2018/5/15.
 */
public enum InventoryDetailTypeEnum {

    BUY((byte) 0x01, "购买"),

    HELP_FRIEND_AWARD((byte) 0x02, "帮助好友奖励"),

    SIGN_AWARD((byte) 0x03, "签到奖励"),

    OWNER_WEAR((byte) 0x04, "自己损耗"),

    HELP_FRIEND_WEAR((byte) 0x05, "帮助好友耗损"),

    ATTENTION((byte) 0x06, "关注公众号"),

    INVITE((byte) 0x07, "邀请好友");

    private Byte val;

    private String name;

    InventoryDetailTypeEnum(Byte val, String name) {
        this.val = val;
        this.name = name;
    }

    public Byte getVal() {
        return val;
    }

    public String getName() {
        return name;
    }

    public static InventoryDetailTypeEnum getEnum(Byte val) {
        InventoryDetailTypeEnum[] values = InventoryDetailTypeEnum.values();
        for (InventoryDetailTypeEnum object : values) {
            if (object.val.equals(val)) {
                return object;
            }
        }
        return null;
    }

}
