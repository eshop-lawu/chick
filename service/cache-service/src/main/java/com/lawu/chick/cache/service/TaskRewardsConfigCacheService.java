package com.lawu.chick.cache.service;

import com.lawu.chick.cache.service.co.TaskRewardsConfigCO;

/**
 * @author meishuquan
 * @date 2018/6/15.
 */
public interface TaskRewardsConfigCacheService {

    /**
     * 保存任务配置
     *
     * @param taskType
     * @param configCO
     * @author meishuquan
     */
    void saveCacheTaskRewardsConfig(String taskType, TaskRewardsConfigCO configCO);

    /**
     * 根据任务类型查询任务配置
     *
     * @param taskType
     * @return
     * @author meishuquan
     */
    TaskRewardsConfigCO getCacheTaskRewardsConfig(String taskType);

}
