package com.lawu.chick.service.bo;

import com.lawu.chick.service.enums.TaskRewardsTypeEnum;

public class TaskBO {

    private TaskRewardsTypeEnum taskRewardsTypeEnum;

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
