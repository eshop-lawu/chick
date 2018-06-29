package com.lawu.chick.service.converter;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSONArray;
import com.lawu.chick.cache.service.co.RewardsConfigCO;
import com.lawu.chick.cache.service.co.TaskRewardsConfigCO;
import com.lawu.chick.repository.domain.TaskRewardsConfigDO;
import com.lawu.chick.service.bo.RewardsConfigBO;
import com.lawu.chick.service.bo.TaskRewardsConfigBO;

/**
 * @author meishuquan
 * @date 2018/6/15.
 */
public class TaskRewardsConfigConverter {

    public static TaskRewardsConfigBO convertBO(TaskRewardsConfigDO taskRewardsConfigDO) {
        if (taskRewardsConfigDO == null) {
            return null;
        }

        TaskRewardsConfigBO taskRewardsConfigBO = new TaskRewardsConfigBO();
        taskRewardsConfigBO.setId(taskRewardsConfigDO.getId());
        taskRewardsConfigBO.setRewardsConfigBOS(new ArrayList<>());
        if (StringUtils.isNotEmpty(taskRewardsConfigDO.getRewardsConfig())) {
            taskRewardsConfigBO.setRewardsConfigBOS(JSONArray.parseArray(taskRewardsConfigDO.getRewardsConfig(), RewardsConfigBO.class));
        }
        taskRewardsConfigBO.setEffectiveTime(taskRewardsConfigDO.getEffectiveTime());
        taskRewardsConfigBO.setType(taskRewardsConfigDO.getType());
        taskRewardsConfigBO.setStatus(taskRewardsConfigDO.getStatus());
        taskRewardsConfigBO.setGmtModified(taskRewardsConfigDO.getGmtModified());
        taskRewardsConfigBO.setGmtCreate(taskRewardsConfigDO.getGmtCreate());
        return taskRewardsConfigBO;
    }

    public static List<TaskRewardsConfigBO> convertBOS(List<TaskRewardsConfigDO> taskRewardsConfigDOS) {
        List<TaskRewardsConfigBO> taskRewardsConfigBOS = new ArrayList<>();
        if (taskRewardsConfigDOS == null || taskRewardsConfigDOS.isEmpty()) {
            return taskRewardsConfigBOS;
        }

        for (TaskRewardsConfigDO taskRewardsConfigDO : taskRewardsConfigDOS) {
            taskRewardsConfigBOS.add(convertBO(taskRewardsConfigDO));
        }
        return taskRewardsConfigBOS;
    }

    public static TaskRewardsConfigCO convertCO(List<RewardsConfigBO> rewardsConfigBOS) {
        TaskRewardsConfigCO taskRewardsConfigCO = new TaskRewardsConfigCO();
        if (rewardsConfigBOS == null || rewardsConfigBOS.isEmpty()) {
            return taskRewardsConfigCO;
        }

        List<RewardsConfigCO> configCOS = new ArrayList<>();
        for (RewardsConfigBO configBO : rewardsConfigBOS) {
            RewardsConfigCO configCO = new RewardsConfigCO();
            configCO.setProductNum(configBO.getProductNum());
            configCO.setProductCount(configBO.getProductCount());
            configCOS.add(configCO);
        }
        taskRewardsConfigCO.setRewardsConfigCOS(configCOS);
        return taskRewardsConfigCO;
    }

}
