package com.lawu.chick.cache.service;

import com.lawu.chick.cache.service.enums.RedisDataTypeEnum;

public interface SerializeUtilCacheService {

	String get(String redisKey, RedisDataTypeEnum keyType, int serializeFlag);
}
