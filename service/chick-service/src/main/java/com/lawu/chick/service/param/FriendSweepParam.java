package com.lawu.chick.service.param;

/**
 * @Description
 * @author zhangrc
 * @date 2018年4月26日
 */
public class FriendSweepParam {
	
	/**
	 * 打扫用户编号
	 */
	private String friendNum;
	
	/**
	 * 被打扫用户编号
	 */
	private String sweepNum;

	/**
	 * @return the friendNum
	 */
	public String getFriendNum() {
		return friendNum;
	}

	public void setFriendNum(String friendNum) {
		this.friendNum = friendNum;
	}

	/**
	 * @return the sweepNum
	 */
	public String getSweepNum() {
		return sweepNum;
	}

	public void setSweepNum(String sweepNum) {
		this.sweepNum = sweepNum;
	}

}
