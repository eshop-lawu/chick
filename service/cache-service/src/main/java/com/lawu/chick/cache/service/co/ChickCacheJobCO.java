package com.lawu.chick.cache.service.co;

import java.io.Serializable;
import java.util.Date;

/**  
 * 小鸡定时缓存数据
 * @author lihj
 * @date 2018年4月26日
 */
public class ChickCacheJobCO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7677782443029786142L;
	/**
	 * 小鸡所属用户编号
	 */
	private String memberNum;
	/**
	 * 小鸡编号
	 */
	private String chickNum;
	/**
	 * 状态，0活动，1睡眠，2休眠，3生产
	 */
	private Byte chickenStatus;
	/**
	 * 是否放养0否，1是
	 */
	private boolean isOutside;
	
	/**
	 * 时间
	 */
	private Date createDate;

	public String getMemberNum() {
		return memberNum;
	}

	public void setMemberNum(String memberNum) {
		this.memberNum = memberNum;
	}

	public String getChickNum() {
		return chickNum;
	}

	public void setChickNum(String chickNum) {
		this.chickNum = chickNum;
	}

	public Byte getChickenStatus() {
		return chickenStatus;
	}

	public void setChickenStatus(Byte chickenStatus) {
		this.chickenStatus = chickenStatus;
	}

	public boolean isOutside() {
		return isOutside;
	}

	public void setOutside(boolean isOutside) {
		this.isOutside = isOutside;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
}
