package com.lawu.chick.cache.service.co;

import java.io.Serializable;
import java.util.Date;

public class ChickenCacheOperateCO implements Serializable {

    private static final long serialVersionUID = 3056052662772504390L;
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
	 * 鸡舍清洁度
	 */
	private int houseCleanness;
	/**
	 * 外部清洁度
	 */
	private int externalCleanness;
    /**
     * 愉悦值、饱腹值
     */
    private int Val;

    private Date date;

    /**
     * 定时分钟数（冗余到事件title中）
     */
    private int timedMins;
    
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

    public int getHouseCleanness() {
        return houseCleanness;
    }

    public void setHouseCleanness(int houseCleanness) {
        this.houseCleanness = houseCleanness;
    }

    public int getExternalCleanness() {
        return externalCleanness;
    }

    public void setExternalCleanness(int externalCleanness) {
        this.externalCleanness = externalCleanness;
    }

    public int getVal() {
        return Val;
    }

    public void setVal(int val) {
        Val = val;
    }

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

    public int getTimedMins() {
        return timedMins;
    }

    public void setTimedMins(int timedMins) {
        this.timedMins = timedMins;
    }
}
