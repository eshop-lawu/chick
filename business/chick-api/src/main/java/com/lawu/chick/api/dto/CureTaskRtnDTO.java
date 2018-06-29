package com.lawu.chick.api.dto;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;

public class CureTaskRtnDTO {

    @ApiModelProperty(value = "任务列表")
    private List<CureTaskDTO> cureTaskDTOList;

    @ApiModelProperty(value = "已完成任务数")
    private Integer finishTaskCount;

    @ApiModelProperty(value = "目标任务数")
    private Integer goalTaskCount;

    public List<CureTaskDTO> getCureTaskDTOList() {
        return cureTaskDTOList;
    }

    public void setCureTaskDTOList(List<CureTaskDTO> cureTaskDTOList) {
        this.cureTaskDTOList = cureTaskDTOList;
    }

    public Integer getFinishTaskCount() {
        return finishTaskCount;
    }

    public void setFinishTaskCount(Integer finishTaskCount) {
        this.finishTaskCount = finishTaskCount;
    }

    public Integer getGoalTaskCount() {
        return goalTaskCount;
    }

    public void setGoalTaskCount(Integer goalTaskCount) {
        this.goalTaskCount = goalTaskCount;
    }
}
