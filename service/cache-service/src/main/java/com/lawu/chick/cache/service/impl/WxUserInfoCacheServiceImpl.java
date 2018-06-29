package com.lawu.chick.cache.service.impl;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.lawu.chick.cache.service.WxUserInfoCacheService;
import com.lawu.chick.cache.service.constants.KeyConstant;

@Service
public class WxUserInfoCacheServiceImpl implements WxUserInfoCacheService {
    
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    
    @Override
    public void setSessionKey(String openid, String sessionKey) {
        stringRedisTemplate.opsForValue().set(KeyConstant.REDIS_KEY_WX_SESSION_KEY.concat(openid), sessionKey, 60, TimeUnit.SECONDS);
    }

    @Override
    public String getSessionKey(String openid) {
        return stringRedisTemplate.opsForValue().get(KeyConstant.REDIS_KEY_WX_SESSION_KEY.concat(openid));
    }

}
