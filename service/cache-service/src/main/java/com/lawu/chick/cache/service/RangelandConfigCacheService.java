package com.lawu.chick.cache.service;

import com.lawu.chick.cache.service.co.RangelandConfigBaseCO;

/**
 * @Description
 * @author zhangrc
 * @date 2018年4月26日
 */
public interface RangelandConfigCacheService {
	
	/**
	 * 保存牧场配置
	 * @param co
	 */
	void saveCacheRangelandConfig(RangelandConfigBaseCO co);
	
	/**
	 * 获取牧场配置
	 * @return
	 */
	RangelandConfigBaseCO getCacheRangelandConfig();

}
