package com.lawu.chick.repository.param;

import java.math.BigDecimal;

/**
 * 更新分配数量参数
 * @author jiangxinjun
 * @createDate 2018年5月7日
 * @updateDate 2018年5月7日
 */
public class UpdateAssignedQuantityParam {
    
    /**
     * 记录id
     */
    private Long id;
    
    /**
     * 分配的小鸡数量
     */
    private Long allocatedChicks;
    
    /**
     * 实际分配金蛋数量
     */
    private BigDecimal realAllocations;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAllocatedChicks() {
        return allocatedChicks;
    }

    public void setAllocatedChicks(Long allocatedChicks) {
        this.allocatedChicks = allocatedChicks;
    }

    public BigDecimal getRealAllocations() {
        return realAllocations;
    }

    public void setRealAllocations(BigDecimal realAllocations) {
        this.realAllocations = realAllocations;
    }
    
}
