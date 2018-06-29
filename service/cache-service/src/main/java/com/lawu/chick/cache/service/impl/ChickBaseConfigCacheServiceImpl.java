package com.lawu.chick.cache.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.lawu.chick.cache.service.ChickBaseConfigCacheService;
import com.lawu.chick.cache.service.co.ChickBaseConfigCO;
import com.lawu.chick.cache.service.constants.KeyConstant;

/**
 * @author lihj
 * @date 2018年4月26日
 */
@Service
public class ChickBaseConfigCacheServiceImpl implements ChickBaseConfigCacheService {
	@Autowired
	private RedisTemplate redisTemplate;

	@Override
	public void saveCacheChickBaseInfo(ChickBaseConfigCO config) {
		redisTemplate.opsForValue().set(KeyConstant.REDIS_KEY_CHICK_BASE_CONFIG, config);
	}

	@Override
	public ChickBaseConfigCO getCacheChickBaseInfo() {
		return (ChickBaseConfigCO)redisTemplate.opsForValue().get(KeyConstant.REDIS_KEY_CHICK_BASE_CONFIG);
	}

	
}
