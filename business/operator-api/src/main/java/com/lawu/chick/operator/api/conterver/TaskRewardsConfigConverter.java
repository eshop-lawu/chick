package com.lawu.chick.operator.api.conterver;

import java.util.ArrayList;
import java.util.List;

import com.lawu.chick.operator.api.dto.RewardsConfigDTO;
import com.lawu.chick.operator.api.dto.TaskRewardsConfigDTO;
import com.lawu.chick.service.bo.RewardsConfigBO;
import com.lawu.chick.service.bo.TaskRewardsConfigBO;
import com.lawu.chick.service.enums.StatusEnum;
import com.lawu.chick.service.enums.TaskRewardsTypeEnum;

/**
 * @author meishuquan
 * @date 2018/6/15.
 */
public class TaskRewardsConfigConverter {

    public static TaskRewardsConfigDTO convertDTO(TaskRewardsConfigBO taskRewardsConfigBO) {
        TaskRewardsConfigDTO taskRewardsConfigDTO = new TaskRewardsConfigDTO();
        if (taskRewardsConfigBO == null) {
            return taskRewardsConfigDTO;
        }

        taskRewardsConfigDTO.setId(taskRewardsConfigBO.getId());
        taskRewardsConfigDTO.setRewardsConfigDTOS(convertRewardsConfigDTO(taskRewardsConfigBO.getRewardsConfigBOS()));
        taskRewardsConfigDTO.setEffectiveTime(taskRewardsConfigBO.getEffectiveTime());
        taskRewardsConfigDTO.setTypeEnum(TaskRewardsTypeEnum.getEnum(taskRewardsConfigBO.getType()));
        taskRewardsConfigDTO.setTypeDes(TaskRewardsTypeEnum.getEnum(taskRewardsConfigBO.getType()).getName());
        taskRewardsConfigDTO.setStatusEnum(StatusEnum.getEnum(taskRewardsConfigBO.getStatus()));
        taskRewardsConfigDTO.setStatusDes(StatusEnum.getEnum(taskRewardsConfigBO.getStatus()).getName());
        taskRewardsConfigDTO.setGmtCreate(taskRewardsConfigBO.getGmtCreate());
        return taskRewardsConfigDTO;
    }

    public static List<TaskRewardsConfigDTO> convertDTOS(List<TaskRewardsConfigBO> taskRewardsConfigBOS) {
        List<TaskRewardsConfigDTO> taskRewardsConfigDTOS = new ArrayList<>();
        if (taskRewardsConfigBOS == null || taskRewardsConfigBOS.isEmpty()) {
            return taskRewardsConfigDTOS;
        }

        for (TaskRewardsConfigBO taskRewardsConfigBO : taskRewardsConfigBOS) {
            taskRewardsConfigDTOS.add(convertDTO(taskRewardsConfigBO));
        }
        return taskRewardsConfigDTOS;
    }

    private static List<RewardsConfigDTO> convertRewardsConfigDTO(List<RewardsConfigBO> rewardsConfigBOS) {
        List<RewardsConfigDTO> rewardsConfigDTOS = new ArrayList<>();
        if (rewardsConfigBOS == null || rewardsConfigBOS.isEmpty()) {
            return rewardsConfigDTOS;
        }

        for (RewardsConfigBO rewardsConfigBO : rewardsConfigBOS) {
            RewardsConfigDTO rewardsConfigDTO = new RewardsConfigDTO();
            rewardsConfigDTO.setProductNum(rewardsConfigBO.getProductNum());
            rewardsConfigDTO.setProductCount(rewardsConfigBO.getProductCount());
            rewardsConfigDTOS.add(rewardsConfigDTO);
        }
        return rewardsConfigDTOS;
    }

}
