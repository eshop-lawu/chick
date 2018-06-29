package com.lawu.chick.repository.param;

/**
 * @author lihj
 * @date 2018年4月26日
 */
public class ChickGrowthParam {

	private Long id;
	/**
	 * 最大成长值
	 */
	private int maxGrowth;
	/**
	 * 半成熟值
	 */
	private int halfGrowVal;
	/**
	 * 半熟期
	 */
	private int halfGrowth;
	/**
	 * 每日加成长度
	 */
	private int addGrowth;

	/**
	 * 成熟期
	 */
	private int mature;
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getAddGrowth() {
		return addGrowth;
	}

	public void setAddGrowth(int addGrowth) {
		this.addGrowth = addGrowth;
	}

	public int getMaxGrowth() {
		return maxGrowth;
	}

	public void setMaxGrowth(int maxGrowth) {
		this.maxGrowth = maxGrowth;
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

}
