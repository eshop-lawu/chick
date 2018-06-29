package com.lawu.chick.cache.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.lawu.chick.cache.service.TaskRewardsConfigCacheService;
import com.lawu.chick.cache.service.co.TaskRewardsConfigCO;
import com.lawu.chick.cache.service.constants.KeyConstant;

/**
 * @author meishuquan
 * @date 2018/6/15.
 */
@Service
public class TaskRewardsConfigCacheServiceImpl implements TaskRewardsConfigCacheService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void saveCacheTaskRewardsConfig(String taskType, TaskRewardsConfigCO configCO) {
        String key = KeyConstant.REDIS_KEY_TASK_REWARDS_CONFIG.concat(taskType);
        redisTemplate.opsForValue().set(key, configCO);
    }

    @Override
    public TaskRewardsConfigCO getCacheTaskRewardsConfig(String taskType) {
        String key = KeyConstant.REDIS_KEY_TASK_REWARDS_CONFIG.concat(taskType);
        return (TaskRewardsConfigCO) redisTemplate.opsForValue().get(key);
    }

}
