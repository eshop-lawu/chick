package com.lawu.chick.operator.api.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.lawu.chick.service.enums.DistributionStatusEnum;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 鸡蛋分配记录DTO
 * @author jiangxinjun
 * @createDate 2018年5月8日
 * @updateDate 2018年5月8日
 */
@ApiModel(description = "鸡蛋分配记录DTO")
public class EggDistributionRecordDTO {
    
    /**
     * 主键
     */
    @ApiModelProperty(value = "主键", required = true)
    private Long id;

    /**
     * 分配的小鸡数量
     */
    @ApiModelProperty(value = "分配的小鸡数量", required = true)
    private Long chicks;

    /**
     * 已分配的小鸡数量
     */
    @ApiModelProperty(value = "已分配的小鸡数量", required = true)
    private Long allocatedChicks;

    /**
     * 预期分配鸡蛋数量
     */
    @ApiModelProperty(value = "预期分配鸡蛋数量", required = true)
    private BigDecimal expectedAllocations;

    /**
     * 实际分配鸡蛋数量
     */
    @ApiModelProperty(value = "实际分配鸡蛋数量", required = true)
    private BigDecimal realAllocations;

    /**
     * 发放的小鸡数量
     */
    @ApiModelProperty(value = "发放的小鸡数量", required = true)
    private Long grantChicks;

    /**
     * 发放鸡蛋数量
     */
    @ApiModelProperty(value = "发放鸡蛋数量", required = true)
    private BigDecimal grants;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态", required = true)
    private DistributionStatusEnum status;

    /**
     * 分配完成时间
     */
    @ApiModelProperty(value = "分配完成时间")
    private Date gmtAllocationsComplete;

    /**
     * 发放完成时间
     */
    @ApiModelProperty(value = "发放完成时间")
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