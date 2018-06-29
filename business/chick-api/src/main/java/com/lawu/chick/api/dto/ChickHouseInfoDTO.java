package com.lawu.chick.api.dto;

import java.math.BigDecimal;
import java.util.List;

import com.lawu.chick.service.enums.RangelandDay;

import io.swagger.annotations.ApiModelProperty;

/**  
 * 鸡舍信息查询
 * @author lihj
 * @date 2018年4月27日
 */
public class ChickHouseInfoDTO {
	@ApiModelProperty(value = "外部清洁度")
	private int externalCleanness;
	
	@ApiModelProperty(value = "鸡舍清洁度")
	private int houseCleanness;
	
	@ApiModelProperty(value = "牧场中小鸡信息")
	private List<ChickenBaseInfoDTO> chickInfo;
	
	@ApiModelProperty(value = "白天|晚上")
	private RangelandDay type;
	
	@ApiModelProperty(value = "蛋窝数量")
	private BigDecimal layEggs;

	@ApiModelProperty(value="蛋窝最大数量")
	private BigDecimal maxHouseEggs;
	
	public int getExternalCleanness() {
		return externalCleanness;
	}

	public void setExternalCleanness(int externalCleanness) {
		this.externalCleanness = externalCleanness;
	}

	public int getHouseCleanness() {
		return houseCleanness;
	}

	public void setHouseCleanness(int houseCleanness) {
		this.houseCleanness = houseCleanness;
	}

	public List<ChickenBaseInfoDTO> getChickInfo() {
		return chickInfo;
	}

	public void setChickInfo(List<ChickenBaseInfoDTO> chickInfo) {
		this.chickInfo = chickInfo;
	}

	public RangelandDay getType() {
		return type;
	}

	public void setType(RangelandDay type) {
		this.type = type;
	}

	public BigDecimal getLayEggs() {
		return layEggs;
	}

	public void setLayEggs(BigDecimal layEggs) {
		this.layEggs = layEggs;
	}

	public BigDecimal getMaxHouseEggs() {
		return maxHouseEggs;
	}

	public void setMaxHouseEggs(BigDecimal maxHouseEggs) {
		this.maxHouseEggs = maxHouseEggs;
	}

}
