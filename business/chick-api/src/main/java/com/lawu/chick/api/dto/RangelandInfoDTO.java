package com.lawu.chick.api.dto;

import java.math.BigDecimal;
import java.util.List;

import com.lawu.chick.service.enums.RangelandDay;

import io.swagger.annotations.ApiModelProperty;

/**  
 * 牧场信息dto
 * @author lihj
 * @date 2018年4月26日
 */
public class RangelandInfoDTO {

	@ApiModelProperty(value = "外部清洁度")
	private Integer externalCleanness;
	
	@ApiModelProperty(value = "鸡舍清洁度")
	private Integer houseCleanness;
	
	@ApiModelProperty(value = "牧场中小鸡信息")
	private List<ChickenBaseInfoDTO> chickInfo;
	
	@ApiModelProperty(value = "DAY白天|NIGHT晚上")
	private RangelandDay type;

	@ApiModelProperty(value = "是否有待办任务")
	private Boolean isHavingAgency;
	
	@ApiModelProperty(value = "总鸡蛋数量")
	private BigDecimal totalEggs;
	
	public Integer getExternalCleanness() {
		return externalCleanness;
	}

	public void setExternalCleanness(Integer externalCleanness) {
		this.externalCleanness = externalCleanness;
	}

	public Integer getHouseCleanness() {
		return houseCleanness;
	}

	public void setHouseCleanness(Integer houseCleanness) {
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

	public Boolean getHavingAgency() {
		return isHavingAgency;
	}

	public void setHavingAgency(Boolean havingAgency) {
		isHavingAgency = havingAgency;
	}

	public BigDecimal getTotalEggs() {
		return totalEggs;
	}

	public void setTotalEggs(BigDecimal totalEggs) {
		this.totalEggs = totalEggs;
	}
	
	
}
