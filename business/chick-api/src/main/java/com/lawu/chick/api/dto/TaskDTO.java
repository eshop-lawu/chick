package com.lawu.chick.api.dto;

import com.lawu.chick.service.enums.TaskRewardsTypeEnum;
import io.swagger.annotations.ApiModelProperty;

public class TaskDTO {

    @ApiModelProperty(value = "任务类型（ATTENTION-关注公众号|INVITE-邀请好友|BRESSING-祈福）")
    private TaskRewardsTypeEnum taskRewardsTypeEnum;

    @ApiModelProperty(value = "是否完成")
    private Boolean isFinish;

    public TaskRewardsTypeEnum getTaskRewardsTypeEnum() {
        return taskRewardsTypeEnum;
    }

    public void setTaskRewardsTypeEnum(TaskRewardsTypeEnum taskRewardsTypeEnum) {
        this.taskRewardsTypeEnum = taskRewardsTypeEnum;
    }

    public Boolean getFinish() {
        return isFinish;
    }

    public void setFinish(Boolean finish) {
        isFinish = finish;
    }
}
