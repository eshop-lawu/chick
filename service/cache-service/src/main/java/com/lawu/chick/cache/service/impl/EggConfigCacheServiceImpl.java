package com.lawu.chick.cache.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.lawu.chick.cache.service.EggConfigCacheService;
import com.lawu.chick.cache.service.co.EggConfigCache;
import com.lawu.chick.cache.service.constants.KeyConstant;

/**
 * @author meishuquan
 * @date 2018/4/25.
 */
@Service
public class EggConfigCacheServiceImpl implements EggConfigCacheService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void saveCacheEggConfig(EggConfigCache config) {
        redisTemplate.opsForValue().set(KeyConstant.REDIS_KEY_EGG_CONFIG, config);
    }

    @Override
    public EggConfigCache getCacheEggConfig() {
        return (EggConfigCache) redisTemplate.opsForValue().get(KeyConstant.REDIS_KEY_EGG_CONFIG);
    }

}
