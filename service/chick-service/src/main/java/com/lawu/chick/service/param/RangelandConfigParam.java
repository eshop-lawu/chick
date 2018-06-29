package com.lawu.chick.service.param;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author zhangrc
 * @date 2018/4/26.
 */
public class RangelandConfigParam {

	/**
	 * 农场每多少小时下降一次清洁度
	 */
	private int chickDeclineExternalValMinute;

	/**
	 * 鸡舍每多少小时下降一次清洁度
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
	 * 好友打扫加多少清洁度
	 */
	private int friendSweepExternalVal;
	
	/**
	 * 牧场配置生效时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH")
    private Date rangelandEffectiveTime;
    
    /**
     * 清洁度默认值（100）
     */
    private int rangelandMaxCleannessVal;
    
    /**
     * 打扫送的商品编号
     */
   // private String productNum;
    
	/**
	 * 是否立即刷新缓存0否、1是
	 */
	private int immediatelyCache;

	@ApiModelProperty(value = "清洁度组")
	private String houseCleannessGroup;

	@ApiModelProperty(value = "衰减愉悦值组")
	private String attenuationJoyfulValGroup;

	@ApiModelProperty(value = "衰减次数组")
	private String attenuationTimesGroup;

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


	/**
	 * @return the rangelandEffectiveTime
	 */
	public Date getRangelandEffectiveTime() {
		return rangelandEffectiveTime;
	}


	public void setRangelandEffectiveTime(Date rangelandEffectiveTime) {
		this.rangelandEffectiveTime = rangelandEffectiveTime;
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

	public int getImmediatelyCache() {
		return immediatelyCache;
	}


	public void setImmediatelyCache(int immediatelyCache) {
		this.immediatelyCache = immediatelyCache;
	}

	public String getHouseCleannessGroup() {
		return houseCleannessGroup;
	}

	public void setHouseCleannessGroup(String houseCleannessGroup) {
		this.houseCleannessGroup = houseCleannessGroup;
	}

	public String getAttenuationJoyfulValGroup() {
		return attenuationJoyfulValGroup;
	}

	public void setAttenuationJoyfulValGroup(String attenuationJoyfulValGroup) {
		this.attenuationJoyfulValGroup = attenuationJoyfulValGroup;
	}

	public String getAttenuationTimesGroup() {
		return attenuationTimesGroup;
	}

	public void setAttenuationTimesGroup(String attenuationTimesGroup) {
		this.attenuationTimesGroup = attenuationTimesGroup;
	}
}