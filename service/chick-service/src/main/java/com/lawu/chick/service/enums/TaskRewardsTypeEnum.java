package com.lawu.chick.service.enums;

/**
 * @author meishuquan
 * @date 2018/6/15.
 */
public enum TaskRewardsTypeEnum {

    ATTENTION((byte) 0x01, "关注公众号"),

    INVITE((byte) 0x02, "邀请好友"),

    BRESSING((byte) 0x03, "祈福");

    private Byte val;

    private String name;

    TaskRewardsTypeEnum(Byte value, String name) {
        this.val = value;
        this.name = name;
    }

    public Byte getVal() {
        return val;
    }

    public String getName() {
        return name;
    }

    public static TaskRewardsTypeEnum getEnum(Byte value) {
        for (TaskRewardsTypeEnum item : TaskRewardsTypeEnum.values()) {
            if (item.getVal().equals(value)) {
                return item;
            }
        }
        return null;
    }
}
