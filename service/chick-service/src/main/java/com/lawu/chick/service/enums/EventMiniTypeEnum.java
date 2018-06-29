
package com.lawu.chick.service.enums;

public enum EventMiniTypeEnum {

    RANGELAND_CLEAN_ADD_JOYFUL("牧场清洁度大于多少时定时多久加愉悦值"),
    HOUSE_CLEAN_MINUS_JOYFUL("鸡舍洁度小于多少时定时多久减愉悦值"),
    DAY_HOUSE_CLEAN_MINUS_JOYFUL("白天小鸡未处牧区定时多久减愉悦值");

    private String name;

    EventMiniTypeEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
