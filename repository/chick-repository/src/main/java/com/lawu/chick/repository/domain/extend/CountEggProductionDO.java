package com.lawu.chick.repository.domain.extend;

import java.math.BigDecimal;

/**
 * 统计产蛋数量DO
 * @author jiangxinjun
 * @createDate 2018年5月7日
 * @updateDate 2018年5月7日
 */
public class CountEggProductionDO {
    
    /**
     * 产蛋的小鸡总数
     */
    private Long count;
    
    /**
     * 生产金蛋的总数量
     */
    private BigDecimal layEggs;

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public BigDecimal getLayEggs() {
        return layEggs;
    }

    public void setLayEggs(BigDecimal layEggs) {
        this.layEggs = layEggs;
    }
    
}
