package com.lawu.chick.service.param;

import com.lawu.chick.service.enums.EventMiniTypeEnum;

public class RangelandEventTitleDataParam {

    /**
     * 具体操作类型
     */
    private EventMiniTypeEnum eventMiniTypeEnum;

    /**
     * 牧场清洁度
     */
    private String rangelandClean;

    /**
     * 鸡舍清洁度
     */
    private String houseClean;

    /**
     * 定时分钟数（冗余到事件title中）
     */
    private String timedMins;

    public EventMiniTypeEnum getEventMiniTypeEnum() {
        return eventMiniTypeEnum;
    }

    public void setEventMiniTypeEnum(EventMiniTypeEnum eventMiniTypeEnum) {
        this.eventMiniTypeEnum = eventMiniTypeEnum;
    }

    public String getRangelandClean() {
        return rangelandClean;
    }

    public void setRangelandClean(String rangelandClean) {
        this.rangelandClean = rangelandClean;
    }

    public String getTimedMins() {
        return timedMins;
    }

    public void setTimedMins(String timedMins) {
        this.timedMins = timedMins;
    }

    public String getHouseClean() {
        return houseClean;
    }

    public void setHouseClean(String houseClean) {
        this.houseClean = houseClean;
    }
}
