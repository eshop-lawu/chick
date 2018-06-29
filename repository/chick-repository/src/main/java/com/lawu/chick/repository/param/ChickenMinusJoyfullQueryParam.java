package com.lawu.chick.repository.param;

public class ChickenMinusJoyfullQueryParam {

    /**
     * 位于鸡舍清洁度多少减加愉悦值
     */
    private int chickRangelandCleanVal;
    /**
     * 小鸡愉悦度多少分钟下降一次(单位分钟)
     */
    private int chickDeclineJoyfulValMinute;

    private int offset;

    private int pageSize;

    public int getChickRangelandCleanVal() {
        return chickRangelandCleanVal;
    }

    public void setChickRangelandCleanVal(int chickRangelandCleanVal) {
        this.chickRangelandCleanVal = chickRangelandCleanVal;
    }

    public int getChickDeclineJoyfulValMinute() {
        return chickDeclineJoyfulValMinute;
    }

    public void setChickDeclineJoyfulValMinute(int chickDeclineJoyfulValMinute) {
        this.chickDeclineJoyfulValMinute = chickDeclineJoyfulValMinute;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
