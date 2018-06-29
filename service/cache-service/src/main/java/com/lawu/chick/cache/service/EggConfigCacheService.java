package com.lawu.chick.cache.service;

import com.lawu.chick.cache.service.co.EggConfigCache;

/**
 * @author meishuquan
 * @date 2018/4/25.
 */
public interface EggConfigCacheService {

    /**
     * 保存鸡蛋配置
     *
     * @param config
     * @author meishuquan
     */
    void saveCacheEggConfig(EggConfigCache config);

    /**
     * 获取鸡蛋配置
     *
     * @return
     * @author meishuquan
     */
    EggConfigCache getCacheEggConfig();

}
