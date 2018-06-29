package com.lawu.chick.service.bo;

import java.math.BigDecimal;
import java.util.Date;

import com.lawu.chick.service.enums.DistributionStatusEnum;

/**
 * 鸡蛋分配记录BO
 * @author jiangxinjun
 * @createDate 2018年5月8日
 * @updateDate 2018年5月8日
 */
public class EggDistributionRecordBO {
    
    /**
     * 主键
     */
    private Long id;

    /**
     * 分配的小鸡数量
     */
    private Long chicks;

    /**
     * 已分配的小鸡数量
     */
    private Long allocatedChicks;

    /**
     * 预期分配鸡蛋数量
     */
    private BigDecimal expectedAllocations;

    /**
     * 实际分配鸡蛋数量
     */
    private BigDecimal realAllocations;

    /**
     * 发放的小鸡数量
     */
    private Long grantChicks;

    /**
     * 发放鸡蛋数量
     */
    private BigDecimal grants;

    /**
     * 状态
     */
    private DistributionStatusEnum status;

    /**
     * 分配完成时间
     */
    private Date gmtAllocationsComplete;

    /**
     * 发放完成时间
     */
    private Date gmtGrantComplete;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getChicks() {
        return chicks;
    }

    public void setChicks(Long chicks) {
        this.chicks = chicks;
    }

    public Long getAllocatedChicks() {
        return allocatedChicks;
    }

    public void setAllocatedChicks(Long allocatedChicks) {
        this.allocatedChicks = allocatedChicks;
    }

    public BigDecimal getExpectedAllocations() {
        return expectedAllocations;
    }

    public void setExpectedAllocations(BigDecimal expectedAllocations) {
        this.expectedAllocations = expectedAllocations;
    }

    public BigDecimal getRealAllocations() {
        return realAllocations;
    }

    public void setRealAllocations(BigDecimal realAllocations) {
        this.realAllocations = realAllocations;
    }

    public Long getGrantChicks() {
        return grantChicks;
    }

    public void setGrantChicks(Long grantChicks) {
        this.grantChicks = grantChicks;
    }

    public BigDecimal getGrants() {
        return grants;
    }

    public void setGrants(BigDecimal grants) {
        this.grants = grants;
    }

    public DistributionStatusEnum getStatus() {
        return status;
    }

    public void setStatus(DistributionStatusEnum status) {
        this.status = status;
    }

    public Date getGmtAllocationsComplete() {
        return gmtAllocationsComplete;
    }

    public void setGmtAllocationsComplete(Date gmtAllocationsComplete) {
        this.gmtAllocationsComplete = gmtAllocationsComplete;
    }

    public Date getGmtGrantComplete() {
        return gmtGrantComplete;
    }

    public void setGmtGrantComplete(Date gmtGrantComplete) {
        this.gmtGrantComplete = gmtGrantComplete;
    }

}