package com.lawu.chick.cache.service.impl;

import java.util.Calendar;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.lawu.chick.cache.service.PraySignService;
import com.lawu.chick.cache.service.co.PraySignRuleCO;
import com.lawu.chick.cache.service.constants.KeyConstant;

/**
 * @author zhangyong
 * @date 2018/6/15.
 */
@Service
public class PraySignServiceImpl implements PraySignService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public Integer addPraySignCache(String memberNum, Integer signDay) {
        String key = KeyConstant.REDIS_KEY_PRAY_SIGN.concat(memberNum);
        String oldVal = stringRedisTemplate.opsForValue().get(key);
        if (StringUtils.isEmpty(oldVal)) {
            //如果不存在放1
            stringRedisTemplate.opsForValue().set(key, "1");
            setPraySignExpireTime(key);
            return 1;
        } else {
            //val +1
            if (signDay != null && Integer.valueOf(oldVal) >= signDay) {
                stringRedisTemplate.opsForValue().set(key, "1");
                setPraySignExpireTime(key);
                return 1;
            }
            stringRedisTemplate.boundValueOps(key).increment(1);
            setPraySignExpireTime(key);
            return Integer.valueOf(oldVal) + 1;
        }
    }


    @Override
    public void addSignRuleCache(PraySignRuleCO ruleCO) {
        redisTemplate.opsForValue().set(KeyConstant.REDIS_KEY_PRAY_SIGN_RULE, ruleCO);
    }

    @Override
    public PraySignRuleCO getSignRuleCache() {
        return (PraySignRuleCO)redisTemplate.opsForValue().get(KeyConstant.REDIS_KEY_PRAY_SIGN_RULE);
    }

    @Override
    public Integer getSignDayByNum(String memberNum) {
        String key = KeyConstant.REDIS_KEY_PRAY_SIGN.concat(memberNum);
        String oldVal = stringRedisTemplate.opsForValue().get(key);
        if (StringUtils.isEmpty(oldVal)) {
            return 0;
        }
        return Integer.valueOf(oldVal);
    }

    /**
     * 设置过期时间
     * @param key
     */
    private void setPraySignExpireTime(String key) {
        Calendar tomorrow = Calendar.getInstance();
        tomorrow.add(Calendar.DAY_OF_MONTH, 2);
        tomorrow.set(Calendar.HOUR_OF_DAY, 0);
        tomorrow.set(Calendar.MINUTE, 0);
        tomorrow.set(Calendar.SECOND, 0);
        stringRedisTemplate.expireAt(key, tomorrow.getTime());
    }
}
