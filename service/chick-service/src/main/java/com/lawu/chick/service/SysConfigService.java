package com.lawu.chick.service;

import com.lawu.chick.service.bo.ChickBaseConfigBO;
import com.lawu.chick.service.bo.EggConfigBO;
import com.lawu.chick.service.bo.RangelandConfigBO;
import com.lawu.chick.service.bo.RangelandConfigBaseBO;
import com.lawu.chick.service.param.ChickBaseConfigParam;
import com.lawu.chick.service.param.EggConfigParam;
import com.lawu.chick.service.param.RangelandConfigParam;

/**
 * @author meishuquan
 * @date 2018/4/25.
 */
public interface SysConfigService {

    /**
     * 保存鸡蛋配置
     *
     * @param param
     * @author meishuquan
     */
    void saveEggConfig(EggConfigParam param);
	/**
	 * 初始化小鸡配置
	 * @param param
	 */
	void addChickBaseConfigInfo(ChickBaseConfigParam param);

    /**
     * 获取鸡蛋配置
     *
     * @return
     * @author meishuquan
     */
    EggConfigBO getEggConfig();

    /**
     * 查询小鸡配置（运营平台）
     * @return
     */
	ChickBaseConfigBO getChickBaseConfigInfo();
	
	/**
	 * 定时任务
	 */
	void executeJob();
	
	/**
	 * 缓存中获取小鸡配置数据
	 * @return
	 */
	ChickBaseConfigBO getCacheChickBaseInfo();
	
	
	/**
	 * 牧场配置
	 * 
	 * @param param
	 */
	void saveRangelandConfig(RangelandConfigParam param);
	
	/**
	 * 查询牧场配置
	 * @return
	 */
	RangelandConfigBO getRangelandConfig();
	
	
	/**
	 * 查询缓存牧场配置
	 * @return
	 */
	RangelandConfigBaseBO getRangelandCacheConfig();
	
	
}
