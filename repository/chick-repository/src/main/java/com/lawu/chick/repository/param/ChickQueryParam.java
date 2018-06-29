package com.lawu.chick.repository.param;

import com.lawu.framework.core.page.AbstractPageParam;

/**
 * @author lihj
 * @date 2018年4月26日
 */
public class ChickQueryParam {

    /**
     * 位于牧场清洁度多少开始加愉悦值
     */
    private int chickRangelandCleanVal;
    /**
     * 小鸡在牧场每多少分钟增加一次愉悦值(分钟)
     */
    private int chickInRangelandAddJoyfulValMinute;

    private int offset;

    private int pageSize;

    public int getChickRangelandCleanVal() {
        return chickRangelandCleanVal;
    }

    public void setChickRangelandCleanVal(int chickRangelandCleanVal) {
        this.chickRangelandCleanVal = chickRangelandCleanVal;
    }

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
