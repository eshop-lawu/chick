package com.lawu.chick.service.bo;

import java.util.List;

/**
 * @author zhangrc
 * @date 2018/4/26.
 */
public class RangelandConfigBaseBO {

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
	private List<HouseCleannessBO> houseCleannessBOS;

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


	public List<HouseCleannessBO> getHouseCleannessBOS() {
		return houseCleannessBOS;
	}

	public void setHouseCleannessBOS(List<HouseCleannessBO> houseCleannessBOS) {
		this.houseCleannessBOS = houseCleannessBOS;
	}
}