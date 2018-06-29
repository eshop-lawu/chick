package com.lawu.chick.service;

import java.util.List;

import com.lawu.chick.service.bo.RangelandCleannessBO;
import com.lawu.chick.service.bo.RangelandInfoBO;
import com.lawu.chick.service.enums.SiteTypeEnum;
import com.lawu.chick.service.param.RangelandCleannessPageParam;
import com.lawu.chick.service.param.RangelandCleannessParam;

/**
 * 牧场接口类
 * 
 * @Description
 * @author zhangrc
 * @date 2018年4月26日
 */
public interface RangelandService {
	
	/**
	 * 分页查询牧场ids
	 * @param param
	 * @return
	 */
	List<RangelandCleannessBO> getRangelandIds(RangelandCleannessPageParam param);
	
	/**
	 * 定时任务降低清洁度
	 * 
	 * @param id
	 */
	void reduceCleanness(RangelandCleannessParam param);
	
	
	/**
	 * 获取清洁度
	 * 
	 * @param typeEnum
	 * @param memberNum
	 * @return
	 */
	int getCleanness(SiteTypeEnum typeEnum,String memberNum);

	/**
	 * 查询牧场信息
	 * @param memberNum
	 * @return
	 */
	RangelandInfoBO getMyRangelandInfo(String memberNum);
	

	/**
	 * 好友帮助打扫
	 * 
	 * @param memberNum
	 */
	void friendSweepCleanness(String memberNum);
	
	/**
	 * 小鸡在牧场时间累计定时任务
	 * 
	 * @param memberNum
	 */
	void chickInRangelandTime(String memberNum);
}
