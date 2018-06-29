package com.lawu.chick.service.bo;

import java.util.List;

public class CureTaskRtnBO {

    private List<CureTaskBO> cureTaskBOList;

    /**
     * 已完成任务数
     */
    private Integer finishTaskCount;

    /**
     * 目标任务数
     */
    private Integer goalTaskCount;

    public List<CureTaskBO> getCureTaskBOList() {
        return cureTaskBOList;
    }

    public void setCureTaskBOList(List<CureTaskBO> cureTaskBOList) {
        this.cureTaskBOList = cureTaskBOList;
    }

    public int getFinishTaskCount() {
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
