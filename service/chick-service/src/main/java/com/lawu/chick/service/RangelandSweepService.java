package com.lawu.chick.service;

import com.lawu.chick.service.bo.SweepAwardBO;
import com.lawu.chick.service.param.FriendSweepParam;
import com.lawu.chick.service.param.SweepParam;

/**
 * 打扫接口类
 * 
 * @Description
 * @author zhangrc
 * @date 2018年4月26日
 */
public interface RangelandSweepService {
	
	/**
	 * 自己打扫
	 * 
	 * @param param
	 */
	void ownerSweep(SweepParam param);
	
	/**
	 * 好友打扫
	 * 
	 * @param param
	 */
	SweepAwardBO friendSweep(FriendSweepParam param);

}
