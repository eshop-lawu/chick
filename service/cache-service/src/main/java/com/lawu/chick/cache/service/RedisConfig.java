package com.lawu.chick.cache.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * redis配置
 * @author jiangxinjun
 * @createDate 2018年4月26日
 * @updateDate 2018年4月26日
 */
@Configuration
public class RedisConfig {
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Bean
    public RedisTemplate setRedisTemplate(RedisTemplate redisTemplate) {
        RedisSerializer stringSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setHashKeySerializer(stringSerializer);
        return redisTemplate;
    }
}
