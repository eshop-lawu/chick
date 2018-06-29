package com.lawu.chick.repository.param;

import java.math.BigDecimal;

/**
 * 
 * @author jiangxinjun
 * @createDate 2018年4月27日
 * @updateDate 2018年4月27日
 */
public class ChickEggOperatorParam {

    /**
     * 小鸡编号
     */
    private String chickNum;

    /**
     * 鸡蛋数量
     */
    private BigDecimal eggs;
    
    /**
     * 鸡窝最大的鸡蛋数量
     */
    private BigDecimal maxHouseEggs;
    
    /**
     * 是否增加
     */
    private Boolean increase;
    
    public String getChickNum() {
        return chickNum;
    }

    public void setChickNum(String chickNum) {
        this.chickNum = chickNum;
    }

    public BigDecimal getEggs() {
        return eggs;
    }

    public void setEggs(BigDecimal eggs) {
        this.eggs = eggs;
    }

    public BigDecimal getMaxHouseEggs() {
        return maxHouseEggs;
    }

    public void setMaxHouseEggs(BigDecimal maxHouseEggs) {
        this.maxHouseEggs = maxHouseEggs;
    }

    public Boolean getIncrease() {
        return increase;
    }

    public void setIncrease(Boolean increase) {
        this.increase = increase;
    }
    
}
