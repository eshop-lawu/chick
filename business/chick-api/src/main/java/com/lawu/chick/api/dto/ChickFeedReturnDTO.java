package com.lawu.chick.api.dto;

import io.swagger.annotations.ApiModelProperty;

/**  
 * 喂养小鸡返回值
 * @author lihj
 * @date 2018年5月16日
 */
public class ChickFeedReturnDTO {

	@ApiModelProperty(value="愉悦值")
    private Integer joyfulVal;

	@ApiModelProperty(value="成长值")
    private Integer growthVal;

	@ApiModelProperty(value="饱腹值")
    private Integer fullVal;

	@ApiModelProperty(value="是否赠送")
	private boolean giveFlag; 
	
	@ApiModelProperty(value="赠送道具名称")
	private String giveFoodsName;
	
	@ApiModelProperty(value="赠送道具数量")
	private int giveFoodsCount;
	
	public Integer getJoyfulVal() {
		return joyfulVal;
	}

	public void setJoyfulVal(Integer joyfulVal) {
		this.joyfulVal = joyfulVal;
	}

	public Integer getGrowthVal() {
		return growthVal;
	}

	public void setGrowthVal(Integer growthVal) {
		this.growthVal = growthVal;
	}

	public Integer getFullVal() {
		return fullVal;
	}

	public void setFullVal(Integer fullVal) {
		this.fullVal = fullVal;
	}

	public boolean isGiveFlag() {
		return giveFlag;
	}

	public void setGiveFlag(boolean giveFlag) {
		this.giveFlag = giveFlag;
	}

	public String getGiveFoodsName() {
		return giveFoodsName;
	}

	public void setGiveFoodsName(String giveFoodsName) {
		this.giveFoodsName = giveFoodsName;
	}

	public int getGiveFoodsCount() {
		return giveFoodsCount;
	}

	public void setGiveFoodsCount(int giveFoodsCount) {
		this.giveFoodsCount = giveFoodsCount;
	}
	
	
}
