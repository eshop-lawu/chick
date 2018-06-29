package com.lawu.chick.service.param;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.lawu.chick.service.enums.StatusEnum;
import com.lawu.chick.service.enums.TaskRewardsTypeEnum;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author meishuquan
 * @date 2018/6/15.
 */
public class TaskRewardsConfigParam {

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "鸡蛋配置生效时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH")
    private Date effectiveTime;

    @ApiModelProperty(value = "任务类型")
    private TaskRewardsTypeEnum typeEnum;

    @ApiModelProperty(value = "任务状态")
    private StatusEnum statusEnum;

    @ApiModelProperty(value = "商品编号组")
    private String productNumGroup;

    @ApiModelProperty(value = "商品数量组")
    private String productCountGroup;

    @ApiModelProperty(value = "是否立即刷新到缓存")
    private Boolean immediatelyCache;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getEffectiveTime() {
        return effectiveTime;
    }

    public void setEffectiveTime(Date effectiveTime) {
        this.effectiveTime = effectiveTime;
    }

    public TaskRewardsTypeEnum getTypeEnum() {
        return typeEnum;
    }

    public void setTypeEnum(TaskRewardsTypeEnum typeEnum) {
        this.typeEnum = typeEnum;
    }

    public StatusEnum getStatusEnum() {
        return statusEnum;
    }

    public void setStatusEnum(StatusEnum statusEnum) {
        this.statusEnum = statusEnum;
    }

    public String getProductNumGroup() {
        return productNumGroup;
    }

    public void setProductNumGroup(String productNumGroup) {
        this.productNumGroup = productNumGroup;
    }

    public String getProductCountGroup() {
        return productCountGroup;
    }

    public void setProductCountGroup(String productCountGroup) {
        this.productCountGroup = productCountGroup;
    }

    public Boolean getImmediatelyCache() {
        return immediatelyCache;
    }

    public void setImmediatelyCache(Boolean immediatelyCache) {
        this.immediatelyCache = immediatelyCache;
    }
}
