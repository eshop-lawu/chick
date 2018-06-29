package com.lawu.chick.api.dto;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class TaskRtnDTO {

    @ApiModelProperty(value = "任务列表")
    private List<TaskDTO> taskDTOList;

    public List<TaskDTO> getTaskDTOList() {
        return taskDTOList;
    }

    public void setTaskDTOList(List<TaskDTO> taskDTOList) {
        this.taskDTOList = taskDTOList;
    }
}
