package com.lawu.chick.repository.param;

public class ChickenDayHenhouseQueryParam {

    /**
     * 小鸡在牧场每多少分钟减一次愉悦值(分钟)
     */
    private int chickInRangelandAddJoyfulValMinute;

    private int offset;

    private int pageSize;

    public int getChickInRangelandAddJoyfulValMinute() {
        return chickInRangelandAddJoyfulValMinute;
    }

    public void setChickInRangelandAddJoyfulValMinute(int chickInRangelandAddJoyfulValMinute) {
        this.chickInRangelandAddJoyfulValMinute = chickInRangelandAddJoyfulValMinute;
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
