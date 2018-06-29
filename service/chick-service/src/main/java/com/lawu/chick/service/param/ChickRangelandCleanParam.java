package com.lawu.chick.service.param;
/**  
 * @author lihj
 * @date 2018年4月26日
 */
public class ChickRangelandCleanParam {

	/**
	 * 小鸡初始愉悦值(100)
	 */
	private int chickInitJoyfulVal;
	/**
	 * 位于牧场清洁度多少开始加愉悦值
	 */
	private int chickRangelandCleanVal;
	/**
	 * 小鸡在牧场每多少分钟增加一次愉悦值(分钟)
	 */
	private int chickInRangelandAddJoyfulValMinute;

	/**
	 * 小鸡在牧场每次增加多少愉悦值(默认1)
	 */
	private int chickInRangelandAddJoyfulVal;

	public int getChickRangelandCleanVal() {
		return chickRangelandCleanVal;
	}

	public void setChickRangelandCleanVal(int chickRangelandCleanVal) {
		this.chickRangelandCleanVal = chickRangelandCleanVal;
	}

	public int getChickInRangelandAddJoyfulValMinute() {
		return chickInRangelandAddJoyfulValMinute;
	}

	public void setChickInRangelandAddJoyfulValMinute(int chickInRangelandAddJoyfulValMinute) {
		this.chickInRangelandAddJoyfulValMinute = chickInRangelandAddJoyfulValMinute;
	}

	public int getChickInRangelandAddJoyfulVal() {
		return chickInRangelandAddJoyfulVal;
	}

	public void setChickInRangelandAddJoyfulVal(int chickInRangelandAddJoyfulVal) {
		this.chickInRangelandAddJoyfulVal = chickInRangelandAddJoyfulVal;
	}

	public int getChickInitJoyfulVal() {
		return chickInitJoyfulVal;
	}

	public void setChickInitJoyfulVal(int chickInitJoyfulVal) {
		this.chickInitJoyfulVal = chickInitJoyfulVal;
	}
	
	
}
