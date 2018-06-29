package com.lawu.chick.service.param;

import com.lawu.chick.service.enums.ChickStatusEnum;
import com.lawu.chick.service.enums.PeriodTypeEnum;
import com.lawu.framework.core.page.AbstractPageParam;

/**
 * @Description
 * @author zhangrc
 * @date 2018年4月28日
 */
public class ChickenPageParam extends AbstractPageParam{
	
	
	/**
	 *
	 * 所处时期，0成长期，1半成熟期，2成熟期，3生病，4治疗中，5死亡 chicken.period
	 *
	 * @mbg.generated
	 */
	private PeriodTypeEnum periodType;

	/**
	 *
	 * 状态，0活动，1睡眠，2休眠，3生产 chicken.status
	 *
	 * @mbg.generated
	 */
	private ChickStatusEnum statusEnum;

	/**
	 *
	 * 是否放养，0否，1是 chicken.is_outside
	 *
	 * @mbg.generated
	 */
	private Boolean isOutside;
	
	/**
	 * 用户昵称
	 */
	private String nickname;
	
	/**
	 * 小鸡名称
	 */
	private String checkName;

	/**
	 * @return the periodType
	 */
	public PeriodTypeEnum getPeriodType() {
		return periodType;
	}

	public void setPeriodType(PeriodTypeEnum periodType) {
		this.periodType = periodType;
	}

	/**
	 * @return the statusEnum
	 */
	public ChickStatusEnum getStatusEnum() {
		return statusEnum;
	}

	public void setStatusEnum(ChickStatusEnum statusEnum) {
		this.statusEnum = statusEnum;
	}

	/**
	 * @return the isOutside
	 */
	public Boolean getIsOutside() {
		return isOutside;
	}

	public void setIsOutside(Boolean isOutside) {
		this.isOutside = isOutside;
	}

	/**
	 * @return the nickname
	 */
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	/**
	 * @return the checkName
	 */
	public String getCheckName() {
		return checkName;
	}

	public void setCheckName(String checkName) {
		this.checkName = checkName;
	}
	
	
	
	
	

}
