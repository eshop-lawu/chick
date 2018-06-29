package com.lawu.chick.api.dto;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;

/**
 * @Description
 * @author zhangrc
 * @date 2018年6月15日
 */
public class PraySignAwardDTO {
	
	@ApiModelProperty(value = "签到id")
	private Long signId;
	
	/**
	 * 是否签到
	 */
	@ApiModelProperty(value = "是否签到")
	private Boolean isSign;
	
	/**
	 * 是否领取
	 */
	@ApiModelProperty(value = "是否领取")
	private Boolean isDraw;
	
	/**
	 * 奖励列表
	 */
	private List<AwardDTO> awards;

	@ApiModelProperty(value = "连续签到天数")
	private Integer signDay;
	

	/**
	 * @return the signId
	 */
	public Long getSignId() {
		return signId;
	}

	public void setSignId(Long signId) {
		this.signId = signId;
	}

	/**
	 * @return the isSign
	 */
	public Boolean getIsSign() {
		return isSign;
	}

	public void setIsSign(Boolean isSign) {
		this.isSign = isSign;
	}

	/**
	 * @return the isDraw
	 */
	public Boolean getIsDraw() {
		return isDraw;
	}

	public void setIsDraw(Boolean isDraw) {
		this.isDraw = isDraw;
	}

	/**
	 * @return the awards
	 */
	public List<AwardDTO> getAwards() {
		return awards;
	}

	public void setAwards(List<AwardDTO> awards) {
		this.awards = awards;
	}

	public Integer getSignDay() {
		return signDay;
	}

	public void setSignDay(Integer signDay) {
		this.signDay = signDay;
	}
}
