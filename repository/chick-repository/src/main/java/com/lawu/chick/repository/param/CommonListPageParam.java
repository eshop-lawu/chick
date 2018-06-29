package com.lawu.chick.repository.param;

/**
 * @author zhangyong
 * @date 2018/6/4.
 */
public class CommonListPageParam {
    private Integer offset;

    private Integer pageSize;

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }
}
