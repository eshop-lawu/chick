package com.lawu.chick.repository.param;

/**
 * @author zhangyong
 * @date 2018/6/4.
 */
public class ChickenReduceCleanessParam {

    private Integer offset;

    private Integer pageSize;

    /**
     * 累计在鸡窝时长
     */
    private Integer inhouseDuration;

    /**
     * 减少的清洁度
     */
    private Integer cleannessVal;

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getInhouseDuration() {
        return inhouseDuration;
    }

    public void setInhouseDuration(Integer inhouseDuration) {
        this.inhouseDuration = inhouseDuration;
    }

    public Integer getCleannessVal() {
        return cleannessVal;
    }

    public void setCleannessVal(Integer cleannessVal) {
        this.cleannessVal = cleannessVal;
    }
}
