package com.lawu.chick.operator.service.dto.constants;

/**
 * @author meishuquan
 * @date 2017/5/3.
 */
public enum ModuleEnum {

    ALL((byte) 0x00),       //查询条件，所有
    CHICK((byte) 0x01),
    OPERATOR((byte) 0x02),
    SIGN((byte) 0x03),      //签到
    EGG((byte) 0x04),
    RANGELAND((byte) 0x05),//牧场
    PRODUCT((byte) 0x06),       //商品
    GIFT((byte) 0x07),       //礼品
    TASK_REWARDS((byte) 0x08);       //任务奖励

    public Byte val;

    ModuleEnum(Byte val) {
        this.val = val;
    }

    public static ModuleEnum getEnum(Byte val) {
        ModuleEnum[] values = ModuleEnum.values();
        for (ModuleEnum object : values) {
            if (object.val.equals(val)) {
                return object;
            }
        }
        return null;
    }
}
