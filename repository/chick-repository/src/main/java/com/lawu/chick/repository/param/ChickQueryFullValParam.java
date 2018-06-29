package com.lawu.chick.repository.param;
/**  
 * @author lihj
 * @date 2018年4月26日
 */
public class ChickQueryFullValParam {
	private int offset;
	private int pageSize;
	/**
	 * 小鸡每多少分钟下降一次饱腹值
	 */
	private int chickDeclineFullValMinute;

	public int getChickDeclineFullValMinute() {
		return chickDeclineFullValMinute;
	}

	public void setChickDeclineFullValMinute(int chickDeclineFullValMinute) {
		this.chickDeclineFullValMinute = chickDeclineFullValMinute;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
}
