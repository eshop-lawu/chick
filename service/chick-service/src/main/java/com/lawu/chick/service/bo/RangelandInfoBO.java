package com.lawu.chick.service.bo;

import java.math.BigDecimal;
import java.util.List;

import com.lawu.chick.service.enums.RangelandDay;

import io.swagger.annotations.ApiModelProperty;

/**  
 * 牧场信息dto
 * @author lihj
 * @date 2018年4月26日
 */
public class RangelandInfoBO {

	@ApiModelProperty(value = "外部清洁度")
	private int externalCleanness;
	
	@ApiModelProperty(value = "鸡舍清洁度")
	private int houseCleanness;
	
	@ApiModelProperty(value = "牧场中小鸡信息")
	private List<ChickenBaseInfoBO> chickInfo;
	
	@ApiModelProperty(value = "DAY白天|NIGHT晚上")
	private RangelandDay type;
	@ApiModelProperty(value = "蛋窝数量")
	private BigDecimal totalEggs;

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

	public List<ChickenBaseInfoBO> getChickInfo() {
		return chickInfo;
	}

	public void setChickInfo(List<ChickenBaseInfoBO> chickInfo) {
		this.chickInfo = chickInfo;
	}

	public RangelandDay getType() {
		return type;
	}

	public void setType(RangelandDay type) {
		this.type = type;
	}

	public BigDecimal getTotalEggs() {
		return totalEggs;
	}

	public void setTotalEggs(BigDecimal totalEggs) {
		this.totalEggs = totalEggs;
	}
	
}
