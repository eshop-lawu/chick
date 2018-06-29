package com.lawu.chick.service;

import com.lawu.chick.service.bo.RewewardConfigForwardBO;
import com.lawu.chick.service.bo.TaskRewardsConfigBO;
import com.lawu.chick.service.enums.TaskRewardsTypeEnum;
import com.lawu.chick.service.param.TaskRewardsConfigParam;
import com.lawu.chick.service.query.TaskRewardsConfigQuery;
import com.lawu.framework.core.page.Page;

import java.util.List;

/**
 * @author meishuquan
 * @date 2018/6/15.
 */
public interface TaskRewardsConfigService {

    /**
     * 保存任务奖励配置
     *
     * @param param
     * @author meishuquan
     */
    int saveTaskRewardsConfig(TaskRewardsConfigParam param);

    /**
     * 任务奖励配置列表
     *
     * @param query
     * @return
     * @author meishuquan
     */
    Page<TaskRewardsConfigBO> listTaskRewardsConfig(TaskRewardsConfigQuery query);

    /**
     * 任务奖励配置详情
     *
     * @param id
     * @return
     * @author meishuquan
     */
    TaskRewardsConfigBO getTaskRewardsConfig(Long id);

    /**
     * 定时任务更新配置到缓存
     *
     * @author meishuquan
     */
    void executeJob();

    /**
     * 判断任务奖励配置是否有效
     * @param typeEnum
     * @return
     */
    boolean isExistRewardsConfig(TaskRewardsTypeEnum typeEnum);

    /**
     * 获取任务缓存配置
     * @param typeEnum
     * @return
     */
    List<RewewardConfigForwardBO> getRewardsConfig(TaskRewardsTypeEnum typeEnum);

    /**
     * 处理奖励
     * @param userNum
     * @param openid
     * @param typeEnum
     * @param insert
     */
    void doHandleAfterTask(String userNum, String openid, TaskRewardsTypeEnum typeEnum,boolean insert);
}
