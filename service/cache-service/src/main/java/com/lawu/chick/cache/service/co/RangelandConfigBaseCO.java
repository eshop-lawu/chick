package com.lawu.chick.cache.service.co;

import java.io.Serializable;
import java.util.List;

/**
 * @author zhangrc
 * @date 2018/4/26.
 */
public class RangelandConfigBaseCO implements Serializable {

	private static final long serialVersionUID = 1L;

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
	 * 好友可打扫总数
	 */
	private int sweepCount;
	
	/**
	 * 好友打扫最多可获得草料包数
	 */
	private int forageCount;
	
	/**
     * 清洁度默认值（100）
     */
    private int rangelandMaxCleannessVal;
    
    
    /**
     * 打扫送的商品编号
     */
    private String productNum;

	/**
	 * 鸡窝清洁度配置
	 */
    private List<HouseCleannessCO> houseCleannessCOS;

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
	 * @return the sweepCount
	 */
	public int getSweepCount() {
		return sweepCount;
	}


	public void setSweepCount(int sweepCount) {
		this.sweepCount = sweepCount;
	}


	/**
	 * @return the forageCount
	 */
	public int getForageCount() {
		return forageCount;
	}


	public void setForageCount(int forageCount) {
		this.forageCount = forageCount;
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


	/**
	 * @return the productNum
	 */
	public String getProductNum() {
		return productNum;
	}


	public void setProductNum(String productNum) {
		this.productNum = productNum;
	}

	public List<HouseCleannessCO> getHouseCleannessCOS() {
		return houseCleannessCOS;
	}

	public void setHouseCleannessCOS(List<HouseCleannessCO> houseCleannessCOS) {
		this.houseCleannessCOS = houseCleannessCOS;
	}
}