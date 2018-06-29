package com.lawu.chick.operator.api.dto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @Description
 * @author zhangrc
 * @date 2018年4月26日
 */
public class RangelandConfigDTO{
	
	/**
	 * 农场每多少分钟下降一次清洁度
	 */
	private int chickDeclineExternalValMinute;

	/**
	 * 鸡舍每多少分钟下降一次清洁度
	 */
	private int chickDeclineHouseValMinute;

	/**
	 * 农场每下降一次清洁度值
	 */
	private int chickDeclineExternalVal;

	/**
	 * 鸡舍每下降一次清洁度值
	 */
	private int chickDeclineHouseVal;
	
	/**
	 * 牧场配置生效时间
	 */
	 @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date RangelandEffectiveTime;
    
    /**
	 * 好友打扫加多少清洁度
	 */
	private int friendSweepExternalVal;
	
	/**
     * 清洁度默认值（100）
     */
    private int rangelandMaxCleannessVal;
    
	/**
	 * 鸡窝清洁度配置
	 */
    private List<HouseCleannessDTO> houseCleannessDTOS;

	/**
	 * @return the rangelandEffectiveTime
	 */
	public Date getRangelandEffectiveTime() {
		return RangelandEffectiveTime;
	}

	public void setRangelandEffectiveTime(Date rangelandEffectiveTime) {
		RangelandEffectiveTime = rangelandEffectiveTime;
	}

	/**
	 * @return the chickDeclineExternalValMinute
	 */
	public int getChickDeclineExternalValMinute() {
		return chickDeclineExternalValMinute;
	}

	public void setChickDeclineExternalValMinute(int chickDeclineExternalValMinute) {
		this.chickDeclineExternalValMinute = chickDeclineExternalValMinute;
	}

	/**
	 * @return the chickDeclineHouseValMinute
	 */
	public int getChickDeclineHouseValMinute() {
		return chickDeclineHouseValMinute;
	}

	public void setChickDeclineHouseValMinute(int chickDeclineHouseValMinute) {
		this.chickDeclineHouseValMinute = chickDeclineHouseValMinute;
	}

	/**
	 * @return the chickDeclineExternalVal
	 */
	public int getChickDeclineExternalVal() {
		return chickDeclineExternalVal;
	}

	public void setChickDeclineExternalVal(int chickDeclineExternalVal) {
		this.chickDeclineExternalVal = chickDeclineExternalVal;
	}

	/**
	 * @return the chickDeclineHouseVal
	 */
	public int getChickDeclineHouseVal() {
		return chickDeclineHouseVal;
	}

	public void setChickDeclineHouseVal(int chickDeclineHouseVal) {
		this.chickDeclineHouseVal = chickDeclineHouseVal;
	}

	/**
	 * @return the friendSweepExternalVal
	 */
	public int getFriendSweepExternalVal() {
		return friendSweepExternalVal;
	}

	public void setFriendSweepExternalVal(int friendSweepExternalVal) {
		this.friendSweepExternalVal = friendSweepExternalVal;
	}

	/**
	 * @return the rangelandMaxCleannessVal
	 */
	public int getRangelandMaxCleannessVal() {
		return rangelandMaxCleannessVal;
	}

	public void setRangelandMaxCleannessVal(int rangelandMaxCleannessVal) {
		this.rangelandMaxCleannessVal = rangelandMaxCleannessVal;
	}


	public List<HouseCleannessDTO> getHouseCleannessDTOS() {
		return houseCleannessDTOS;
	}

	public void setHouseCleannessDTOS(List<HouseCleannessDTO> houseCleannessDTOS) {
		this.houseCleannessDTOS = houseCleannessDTOS;
	}
}
