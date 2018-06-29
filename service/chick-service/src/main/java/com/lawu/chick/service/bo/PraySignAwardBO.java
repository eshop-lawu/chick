package com.lawu.chick.service.bo;

import java.util.List;

/**
 * @Description
 * @author zhangrc
 * @date 2018年6月15日
 */
public class PraySignAwardBO {
	
	private Long signId;
	
	/**
	 * 是否签到
	 */
	private Boolean isSign;
	
	/**
	 * 是否领取
	 */
	private Boolean isDraw;
	
	/**
	 * 奖励列表
	 */
	List<AwardBO> awards;

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
	public List<AwardBO> getAwards() {
		return awards;
	}

	public void setAwards(List<AwardBO> awards) {
		this.awards = awards;
	}
	
	
	
	

}
