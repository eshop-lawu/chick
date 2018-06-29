package com.lawu.chick.cache.service.impl;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.lawu.chick.cache.service.PeriodCacheService;
import com.lawu.chick.cache.service.constants.KeyConstant;

/**
 * @author Leach
 * @date 2018/4/28
 */
@Service
public class PeriodCacheServiceImpl implements PeriodCacheService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public int increaseDaily(String keySuffix, int incValue) {
        String key = KeyConstant.REDIS_KEY_PERIOD_CTRL.concat(keySuffix);
        Long valAfterInc = stringRedisTemplate.boundValueOps(key).increment(incValue);

        Long expire = stringRedisTemplate.getExpire(key);
        if (expire == null || expire == -1) {

            Calendar tomorrow = Calendar.getInstance();
            tomorrow.add(Calendar.DAY_OF_MONTH, 1);
            tomorrow.set(Calendar.HOUR_OF_DAY, 0);
            tomorrow.set(Calendar.MINUTE, 0);
            tomorrow.set(Calendar.SECOND, 0);
            stringRedisTemplate.expireAt(key,tomorrow.getTime());
        }

        return valAfterInc.intValue();
    }
}
