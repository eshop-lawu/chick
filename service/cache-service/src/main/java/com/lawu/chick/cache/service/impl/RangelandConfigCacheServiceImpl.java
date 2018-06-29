package com.lawu.chick.cache.service.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.lawu.chick.cache.service.RangelandConfigCacheService;
import com.lawu.chick.cache.service.co.RangelandConfigBaseCO;
import com.lawu.chick.cache.service.constants.KeyConstant;

/**
 * @Description
 * @author zhangrc
 * @date 2018年4月26日
 */
@Service
public class RangelandConfigCacheServiceImpl implements RangelandConfigCacheService {
	
	@SuppressWarnings("rawtypes")
	@Autowired
    private RedisTemplate redisTemplate;

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@SuppressWarnings("unchecked")
	@Override
	public void saveCacheRangelandConfig(RangelandConfigBaseCO co) {
		redisTemplate.opsForValue().set(KeyConstant.REDIS_KEY_RANGELAND_CONFIG, co);

		//删除所有小鸡清洁度规则
		String timesKey = KeyConstant.REDIS_KEY_ATTENUATION_JOYFUL_VAL_TIMES.concat("*");
		Set<String> keys = stringRedisTemplate.keys(timesKey);
		stringRedisTemplate.delete(keys);
		String millisecondsKey = KeyConstant.REDIS_KEY_ATTENUATION_JOYFUL_VAL_MILLIISECONDS;
		stringRedisTemplate.delete(millisecondsKey);
	}
	

    @Override
    public RangelandConfigBaseCO getCacheRangelandConfig() {
        return (RangelandConfigBaseCO) redisTemplate.opsForValue().get(KeyConstant.REDIS_KEY_RANGELAND_CONFIG);
    }


}
