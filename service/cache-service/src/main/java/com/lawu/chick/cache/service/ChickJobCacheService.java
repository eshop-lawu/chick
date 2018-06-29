package com.lawu.chick.cache.service;

import java.util.List;

import com.lawu.chick.cache.service.co.ChickenCacheOperateCO;

public interface ChickJobCacheService {

    /**
     * 清洁度大于60的牧场 & 满足时间15分钟的小鸡信息，加入缓存
     *
     * @param chickenCacheOperateCOList
     */
    void addRangelandCleanJoyfullCache(List<ChickenCacheOperateCO> chickenCacheOperateCOList);

    /**
     * 饱腹值递减的小鸡信息加入缓存
     * @param chickenCacheOperateCOList
     */
	void addChickFullValToCache(List<ChickenCacheOperateCO> chickenCacheOperateCOList);

    /**
     * 将清洁度小于60的鸡舍中满足时间10分钟条件减愉悦值的小鸡信息放入缓存
     *
     * @param chickenCacheOperateCOList
     */
    void addHenhouseCleanJoyfullCache(List<ChickenCacheOperateCO> chickenCacheOperateCOList);
	/**
	 * 获取缓存中饱腹值需要递减的小鸡集合,一次获取100条
	 * @return
	 */
	List<ChickenCacheOperateCO> getChickFullValCacheData();
	
	/**
	 * 移除已经饱腹值减去的小鸡缓存
	 */
	void removeChickFullValCacheData();

    void removeChickFullValCacheDataSingle(ChickenCacheOperateCO co);

    /**
     * 将白天未处牧区每15分钟-2愉悦值的小鸡信息放入缓存
     * @param chickenCacheOperateCOList
     */
    void addDayHenhouseJoyfullCache(List<ChickenCacheOperateCO> chickenCacheOperateCOList);

    /**
     * 获取清洁度大于60的牧场 & 满足时间15分钟的小鸡信息缓存
     * @return
     */
    List<ChickenCacheOperateCO> getRangelandCleanJoyfullCacheData();
    
    /**
     * 删除清洁度大于60的牧场 & 满足时间15分钟的小鸡信息缓存
     */
    void removeRangelandCleanJoyfullCacheData();
    /**
     * 获取小鸡位于清洁度<60的鸡舍每10分钟-1愉悦值 的小鸡信息缓存
     * @return
     */
    List<ChickenCacheOperateCO> getHouseCleanJoyfullCacheData();
    /**
     * 移除缓存中小鸡位于清洁度<60的鸡舍每10分钟-1愉悦值 的小鸡信息缓存
     */
    void removeHouseCleanJoyfullCacheData();
    /**
     * 获取将白天未处牧区每15分钟-2愉悦值的小鸡信息缓存信息
     *
     * @return
     */
    List<ChickenCacheOperateCO> getDayHenhouseJoyfullCacheData();
    /**
     * 移除缓存中白天未处牧区每15分钟-2愉悦值的小鸡信息缓存信息
     */
    void removeDayHenhouseJoyfullCacheData();
    /**
     * 移除缓存中白天未处牧区每15分钟-2愉悦值的小鸡信息缓存信息
     */
    void removeDayHenhouseJoyfullCacheDataSingle(ChickenCacheOperateCO co);

    /**
     * 获取衰减愉悦值次数
     *
     * @param suffix
     * @return
     */
    int getAttenuationJoyfulValTimes(String suffix);

    /**
     * 清空衰减愉悦值次数
     *
     * @param num
     */
    void removeAttenuationJoyfulValTimes(String num);

    /**
     * 获取衰减愉悦值毫秒数
     *
     * @return
     */
    Long getAttenuationJoyfulValMilliseconds();

    /**
     * 设置衰减愉悦值毫秒数
     */
    void setAttenuationJoyfulValMilliseconds();
	 /**
     * 牧场收益--放入缓存
     * @param converterChickenCacheCOList
     */
	void addCacheRangelandProfit(List<ChickenCacheOperateCO> converterChickenCacheCOList);
	
	/**
	 * 牧场收益--查询出来
	 * @return
	 */
	List<ChickenCacheOperateCO> getCacheRangelandProfit();
	
	void removegetCacheRangelandProfit();

    void removegetCacheRangelandProfitSingle(ChickenCacheOperateCO co);
}
