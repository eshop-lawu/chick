package com.lawu.chick.repository.param;
/**  
 * 喂养小鸡更新信息参数
 * @author lihj
 * @date 2018年4月28日
 */
public class ChickUpdateInfoParam {

	/**
	 * 小鸡num
	 */
	private String num;
	/**
	 * 用户编号
	 */
	private String memberNum;
	
	/**
	 * 愉悦值
	 */
	private int joyfulVal;
	
	/**
	 * 饱腹值
	 */
	private int fullVal;
	/**
	 * 最大饱腹值
	 */
	private int fullMaxVal;
	
	/**
	 * 成长值
	 */
	private int growthVal;
	/**
	 * 半熟期成长值
	 */
	private int halfGrowVal;
	/**
	 * 最大成长值
	 */
	private int growthMaxVal;
	/**
	 * 半熟期
	 */
	private int halfGrowth;
	/**
	 * 成熟期
	 */
	private int mature;
	/**
	 * 活动开始时间
	 */
	private String sdate;
	/**
	 * 活动结束时间
	 */
	private String edate;
	/**
	 * 活动状态
	 */
	private int statusActive;
	
	/**
	 * 睡眠状态
	 */
	private int statusSleep;
	
	/**
	 * 清洁度保持时间（单位天）
	 */
	private int keepCleanTime;
	
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getMemberNum() {
		return memberNum;
	}
	public void setMemberNum(String memberNum) {
		this.memberNum = memberNum;
	}
	public int getJoyfulVal() {
		return joyfulVal;
	}
	public void setJoyfulVal(int joyfulVal) {
		this.joyfulVal = joyfulVal;
	}
	public int getFullVal() {
		return fullVal;
	}
	public void setFullVal(int fullVal) {
		this.fullVal = fullVal;
	}
	public int getFullMaxVal() {
		return fullMaxVal;
	}
	public void setFullMaxVal(int fullMaxVal) {
		this.fullMaxVal = fullMaxVal;
	}
	public int getGrowthVal() {
		return growthVal;
	}
	public void setGrowthVal(int growthVal) {
		this.growthVal = growthVal;
	}
	public int getGrowthMaxVal() {
		return growthMaxVal;
	}
	public void setGrowthMaxVal(int growthMaxVal) {
		this.growthMaxVal = growthMaxVal;
	}
	public int getHalfGrowth() {
		return halfGrowth;
	}
	public void setHalfGrowth(int halfGrowth) {
		this.halfGrowth = halfGrowth;
	}
	public int getMature() {
		return mature;
	}
	public void setMature(int mature) {
		this.mature = mature;
	}

	public int getHalfGrowVal() {
		return halfGrowVal;
	}

	public void setHalfGrowVal(int halfGrowVal) {
		this.halfGrowVal = halfGrowVal;
	}
	public String getSdate() {
		return sdate;
	}
	public void setSdate(String sdate) {
		this.sdate = sdate;
	}
	public String getEdate() {
		return edate;
	}
	public void setEdate(String edate) {
		this.edate = edate;
	}
	public int getStatusActive() {
		return statusActive;
	}
	public void setStatusActive(int statusActive) {
		this.statusActive = statusActive;
	}
	public int getStatusSleep() {
		return statusSleep;
	}
	public void setStatusSleep(int statusSleep) {
		this.statusSleep = statusSleep;
	}
	public int getKeepCleanTime() {
		return keepCleanTime;
	}
	public void setKeepCleanTime(int keepCleanTime) {
		this.keepCleanTime = keepCleanTime;
	}
	
}
