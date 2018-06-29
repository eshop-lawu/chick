package com.lawu.chick.service;

/**
 * 
 * 好友帮助做任务奖励记录
 * 
 * @Description
 * @author zhangrc
 * @date 2018年4月27日
 */
public interface HelpAwardRecordService {
	
	/**
	 * 打扫奖励次数加一
	 * 
	 * @param memberNum
	 */
	void helpSweepAwardCache(String memberNum);
	
	/**
	 * 打扫奖励总数
	 * 
	 * @param memberNum
	 * @return
	 */
	Integer getSweepAwardCacheCount(String memberNum);

}
