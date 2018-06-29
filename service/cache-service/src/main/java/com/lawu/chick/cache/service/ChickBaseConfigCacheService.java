package com.lawu.chick.cache.service;

import com.lawu.chick.cache.service.co.ChickBaseConfigCO;

/**
 * @author lihj
 * @date 2018年4月26日
 */
public interface ChickBaseConfigCacheService {

	/**
	 * 将小鸡配置初始化到缓存
	 * 
	 * @param config
	 */
	void saveCacheChickBaseInfo(ChickBaseConfigCO config);

	/**
	 * 缓存拿出小鸡配置
	 * 
	 * @return
	 */
	ChickBaseConfigCO getCacheChickBaseInfo();
}
