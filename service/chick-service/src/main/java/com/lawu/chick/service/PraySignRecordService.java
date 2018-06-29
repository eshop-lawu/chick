package com.lawu.chick.service;

import java.util.List;

import com.lawu.chick.service.bo.AwardBO;
import com.lawu.chick.service.bo.PraySignAwardBO;

/**
 * 祈福签到记录
 * 
 * @Description
 * @author zhangrc
 * @date 2018年6月15日
 */
public interface PraySignRecordService {
	
	/**
	 * 查询签到信息
	 * 
	 * @param memberNum
	 * @return
	 */
	PraySignAwardBO praySignRecordInfo(String memberNum);
	
	/**
	 * 领取奖品
	 * @param memberNum
	 */
	void drawReward(String memberNum);

	/**
	 * 签到
	 * @param memberNum
	 * @return
	 */
	List<AwardBO> addPraySign(String memberNum);

	/**
	 * 判断是否签到
	 * @param memberNum
	 * @return
	 */
	Boolean getRecordByMemberNum(String memberNum);
}
