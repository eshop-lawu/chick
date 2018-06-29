package com.lawu.chick.operator.api.dto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lawu.chick.service.enums.StatusEnum;
import com.lawu.chick.service.enums.TaskRewardsTypeEnum;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author meishuquan
 * @date 2018/6/15.
 */
public class TaskRewardsConfigDTO {

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "奖励信息")
    private List<RewardsConfigDTO> rewardsConfigDTOS;

    @ApiModelProperty(value = "生效时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date effectiveTime;

    @ApiModelProperty(value = "任务类型")
    private TaskRewardsTypeEnum typeEnum;

    @ApiModelProperty(value = "任务类型描述")
    private String typeDes;

    @ApiModelProperty(value = "任务状态")
    private StatusEnum statusEnum;

    @ApiModelProperty(value = "任务状态描述")
    private String statusDes;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date gmtCreate;

    @ApiModelProperty(value = "商品信息")
    private List<SignProductListDTO> productListDTOS;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<RewardsConfigDTO> getRewardsConfigDTOS() {
        return rewardsConfigDTOS;
    }

    public void setRewardsConfigDTOS(List<RewardsConfigDTO> rewardsConfigDTOS) {
        this.rewardsConfigDTOS = rewardsConfigDTOS;
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

    public String getTypeDes() {
        return typeDes;
    }

    public void setTypeDes(String typeDes) {
        this.typeDes = typeDes;
    }

    public StatusEnum getStatusEnum() {
        return statusEnum;
    }

    public void setStatusEnum(StatusEnum statusEnum) {
        this.statusEnum = statusEnum;
    }

    public String getStatusDes() {
        return statusDes;
    }

    public void setStatusDes(String statusDes) {
        this.statusDes = statusDes;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public List<SignProductListDTO> getProductListDTOS() {
        return productListDTOS;
    }

    public void setProductListDTOS(List<SignProductListDTO> productListDTOS) {
        this.productListDTOS = productListDTOS;
    }
}
