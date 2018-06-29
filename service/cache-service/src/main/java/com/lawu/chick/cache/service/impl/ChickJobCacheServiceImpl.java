package com.lawu.chick.cache.service.impl;

import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.lawu.chick.cache.service.ChickJobCacheService;
import com.lawu.chick.cache.service.RangelandConfigCacheService;
import com.lawu.chick.cache.service.co.ChickenCacheOperateCO;
import com.lawu.chick.cache.service.constants.KeyConstant;

@Service
public class ChickJobCacheServiceImpl implements ChickJobCacheService {

	@Autowired
	private RedisTemplate redisTemplate;

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Autowired
	private RangelandConfigCacheService rangelandConfigCacheService;

	@Override
	public void addRangelandCleanJoyfullCache(List<ChickenCacheOperateCO> chickenCacheOperateCOList) {
		redisTemplate.opsForList().rightPushAll(KeyConstant.REDIS_KEY_RANGELAND_CLEAN_JOYFULL,
				chickenCacheOperateCOList);
	}

	@Override
	public void addChickFullValToCache(List<ChickenCacheOperateCO> chickenCacheOperateCOList) {
		redisTemplate.opsForList().rightPushAll(KeyConstant.REDIS_KEY_CHICKEN_FULLVAL, chickenCacheOperateCOList);
	}

	@Override
	public void addHenhouseCleanJoyfullCache(List<ChickenCacheOperateCO> chickenCacheOperateCOList) {
		redisTemplate.opsForList().rightPushAll(KeyConstant.REDIS_KEY_HENHOUSE_CLEAN_JOYFULL,
				chickenCacheOperateCOList);
	}

	@Override
	public List<ChickenCacheOperateCO> getChickFullValCacheData() {
		return redisTemplate.opsForList().range(KeyConstant.REDIS_KEY_CHICKEN_FULLVAL, 0, 1000);
	}

	@Override
	public void removeChickFullValCacheData() {
		List<ChickenCacheOperateCO> list = redisTemplate.opsForList().range(KeyConstant.REDIS_KEY_CHICKEN_FULLVAL, 0,
				1000);
		for (ChickenCacheOperateCO co : list) {
			redisTemplate.opsForList().remove(KeyConstant.REDIS_KEY_CHICKEN_FULLVAL, 0, co);
		}
	}

	@Override
	public void removeChickFullValCacheDataSingle(ChickenCacheOperateCO co) {
		redisTemplate.opsForList().remove(KeyConstant.REDIS_KEY_CHICKEN_FULLVAL, 0, co);
	}

	@Override
	public void addDayHenhouseJoyfullCache(List<ChickenCacheOperateCO> chickenCacheOperateCOList) {
		redisTemplate.opsForList().rightPushAll(KeyConstant.REDIS_KEY_DAY_HENHOUSE_JOYFULL, chickenCacheOperateCOList);
	}

	@Override
	public List<ChickenCacheOperateCO> getRangelandCleanJoyfullCacheData() {
		return redisTemplate.opsForList().range(KeyConstant.REDIS_KEY_RANGELAND_CLEAN_JOYFULL, 0, 1000);
	}

	@Override
	public void removeRangelandCleanJoyfullCacheData() {
		List<ChickenCacheOperateCO> list = redisTemplate.opsForList()
				.range(KeyConstant.REDIS_KEY_RANGELAND_CLEAN_JOYFULL, 0, 1000);
		for (ChickenCacheOperateCO co : list) {
			redisTemplate.opsForList().remove(KeyConstant.REDIS_KEY_RANGELAND_CLEAN_JOYFULL, 0, co);
		}
	}

	@Override
	public List<ChickenCacheOperateCO> getHouseCleanJoyfullCacheData() {
		return redisTemplate.opsForList().range(KeyConstant.REDIS_KEY_HENHOUSE_CLEAN_JOYFULL, 0, 1000);
	}

	@Override
	public void removeHouseCleanJoyfullCacheData() {
		List<ChickenCacheOperateCO> list = redisTemplate.opsForList()
				.range(KeyConstant.REDIS_KEY_HENHOUSE_CLEAN_JOYFULL, 0, 1000);
		for (ChickenCacheOperateCO co : list) {
			redisTemplate.opsForList().remove(KeyConstant.REDIS_KEY_HENHOUSE_CLEAN_JOYFULL, 0, co);
		}
	}

	@Override
	public List<ChickenCacheOperateCO> getDayHenhouseJoyfullCacheData() {
		return redisTemplate.opsForList().range(KeyConstant.REDIS_KEY_DAY_HENHOUSE_JOYFULL, 0, 1000);
	}

	@Override
	public void removeDayHenhouseJoyfullCacheData() {
		List<ChickenCacheOperateCO> list = redisTemplate.opsForList().range(KeyConstant.REDIS_KEY_DAY_HENHOUSE_JOYFULL,
				0, 1000);
		for (ChickenCacheOperateCO co : list) {
			redisTemplate.opsForList().remove(KeyConstant.REDIS_KEY_DAY_HENHOUSE_JOYFULL, 0, co);
		}
	}

	@Override
	public void removeDayHenhouseJoyfullCacheDataSingle(ChickenCacheOperateCO co) {
		redisTemplate.opsForList().remove(KeyConstant.REDIS_KEY_DAY_HENHOUSE_JOYFULL, 0, co);
	}

	@Override
	public int getAttenuationJoyfulValTimes(String suffix) {
		String key = KeyConstant.REDIS_KEY_ATTENUATION_JOYFUL_VAL_TIMES.concat(suffix);
		Long attenuationJoyfulValTimes = stringRedisTemplate.opsForValue().increment(key, 1);
		return attenuationJoyfulValTimes.intValue();
	}

	@Override
	public void removeAttenuationJoyfulValTimes(String num) {
		String key = KeyConstant.REDIS_KEY_ATTENUATION_JOYFUL_VAL_TIMES.concat(num).concat("_*");
		Set<String> keys = stringRedisTemplate.keys(key);
		stringRedisTemplate.delete(keys);
	}

	@Override
	public Long getAttenuationJoyfulValMilliseconds() {
		String key = KeyConstant.REDIS_KEY_ATTENUATION_JOYFUL_VAL_MILLIISECONDS;
		String milliseconds = stringRedisTemplate.opsForValue().get(key);
		if (StringUtils.isEmpty(milliseconds)) {
			stringRedisTemplate.opsForValue().set(key, String.valueOf(System.currentTimeMillis()));
			return System.currentTimeMillis();
		}
		return Long.valueOf(milliseconds);
	}

	@Override
	public void setAttenuationJoyfulValMilliseconds() {
		String key = KeyConstant.REDIS_KEY_ATTENUATION_JOYFUL_VAL_MILLIISECONDS;
		stringRedisTemplate.opsForValue().set(key, String.valueOf(System.currentTimeMillis()));
	}

	@Override
	public void addCacheRangelandProfit(List<ChickenCacheOperateCO> converterChickenCacheCOList) {
		redisTemplate.opsForList().rightPushAll(KeyConstant.REDIS_KEY_DAY_RANGELAND_PROFIT,
				converterChickenCacheCOList);
	}

	@Override
	public List<ChickenCacheOperateCO> getCacheRangelandProfit() {
		return redisTemplate.opsForList().range(KeyConstant.REDIS_KEY_DAY_RANGELAND_PROFIT, 0, 1000);
	}

	@Override
	public void removegetCacheRangelandProfit() {
		List<ChickenCacheOperateCO> list = redisTemplate.opsForList().range(KeyConstant.REDIS_KEY_DAY_RANGELAND_PROFIT,
				0, 1000);
		for (ChickenCacheOperateCO co : list) {
			redisTemplate.opsForList().remove(KeyConstant.REDIS_KEY_DAY_RANGELAND_PROFIT, 0, co);
		}
	}

	@Override
	public void removegetCacheRangelandProfitSingle(ChickenCacheOperateCO co) {
		redisTemplate.opsForList().remove(KeyConstant.REDIS_KEY_DAY_RANGELAND_PROFIT, 0, co);
	}

}
