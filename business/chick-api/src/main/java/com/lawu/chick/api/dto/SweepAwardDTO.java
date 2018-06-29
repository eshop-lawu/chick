package com.lawu.chick.api.dto;

/**
 * @Description
 * @author zhangrc
 * @date 2018年5月22日
 */
public class SweepAwardDTO {
	
	
	  /**
	   * 是否赠送道具
	   */
	  private boolean giveFlag; 
	  
	  /**
	   * 赠送道具名称
	   */
	  private String giveFoodsName;
	  
	  /**
	   * 赠送道具数量
	   */
	  private int giveFoodsCount;

	/**
	 * @return the giveFlag
	 */
	public boolean isGiveFlag() {
		return giveFlag;
	}

	public void setGiveFlag(boolean giveFlag) {
		this.giveFlag = giveFlag;
	}

	/**
	 * @return the giveFoodsName
	 */
	public String getGiveFoodsName() {
		return giveFoodsName;
	}

	public void setGiveFoodsName(String giveFoodsName) {
		this.giveFoodsName = giveFoodsName;
	}

	/**
	 * @return the giveFoodsCount
	 */
	public int getGiveFoodsCount() {
		return giveFoodsCount;
	}

	public void setGiveFoodsCount(int giveFoodsCount) {
		this.giveFoodsCount = giveFoodsCount;
	}
	  
	  

}
