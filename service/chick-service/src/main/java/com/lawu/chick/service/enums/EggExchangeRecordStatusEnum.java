
package com.lawu.chick.service.enums;

/**
 * @author meishuquan
 * @date 2018/4/26.
 */
public enum EggExchangeRecordStatusEnum {

    PENDING((byte) 0x00, "待处理"),
    FAIL((byte) 0x01, "发放失败"),
    SENT((byte) 0x02, "已发放"),
    RECEIVED((byte) 0x03, "已到账"),
    REFUND((byte) 0x04, "已退款");

    private Byte val;
    private String name;

    EggExchangeRecordStatusEnum(Byte val, String name) {
        this.val = val;
        this.name = name;
    }

    public Byte getVal() {
        return val;
    }

    public String getName() {
        return name;
    }

    public static EggExchangeRecordStatusEnum getEnum(Byte val) {
        EggExchangeRecordStatusEnum[] values = EggExchangeRecordStatusEnum.values();
        for (EggExchangeRecordStatusEnum object : values) {
            if (object.val.equals(val)) {
                return object;
            }
        }
        return null;
    }

}
