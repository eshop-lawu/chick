package com.lawu.chick.service.bo;

/**
 * @Description
 * @author zhangrc
 * @date 2018年6月15日
 */
public class AwardBO {
	
	/**
	 * 奖品名称
	 */
	private String name;
	
	/**
	 * 奖品图片
	 */
	private String imgPath;
	
	/**
	 * 奖品数量
	 */
	private int awardCount;
	
	/**
	 * 是否是额外奖励
	 */
	private Boolean isExtra;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the imgPath
	 */
	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	/**
	 * @return the awardCount
	 */
	public int getAwardCount() {
		return awardCount;
	}

	public void setAwardCount(int awardCount) {
		this.awardCount = awardCount;
	}

	/**
	 * @return the isExtra
	 */
	public Boolean getIsExtra() {
		return isExtra;
	}

	public void setIsExtra(Boolean isExtra) {
		this.isExtra = isExtra;
	}
	
	

}
