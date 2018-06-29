package com.lawu.chick.repository.param;

import java.util.Date;

/**
 * @author zhangyong
 * @date 2018/6/4.
 */
public class ReduceCleanParam {

    private Long id;

    /**
     * 减少的清洁度
     */
    private Integer cleannessVal;

    /**
     * 大于120分钟减少清洁度后，小鸡处于鸡窝重新计时
     */
    private Date inhouseTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCleannessVal() {
        return cleannessVal;
    }

    public void setCleannessVal(Integer cleannessVal) {
        this.cleannessVal = cleannessVal;
    }

    public Date getInhouseTime() {
        return inhouseTime;
    }

    public void setInhouseTime(Date inhouseTime) {
        this.inhouseTime = inhouseTime;
    }
}
