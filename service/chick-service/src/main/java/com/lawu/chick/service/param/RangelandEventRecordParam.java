package com.lawu.chick.service.param;

import java.math.BigDecimal;
import java.util.Date;

import com.lawu.chick.service.enums.EventRecordAttrTypeEnum;
import com.lawu.chick.service.enums.EventRecordDirectionEnum;
import com.lawu.chick.service.enums.EventRecordFactorEnum;
import com.lawu.chick.service.enums.EventRecordSourceEnum;

/**
 * @Description
 * @author zhangrc
 * @date 2018年4月26日
 */
public class RangelandEventRecordParam {
	
	/**
	 * 小鸡事件表涉及属性类型枚举类
	 */
	private EventRecordAttrTypeEnum attrTypeEnum;
	
	/**
	 * 增加减少
	 */
	private EventRecordDirectionEnum directionEnum;
	
	/**
	 * 事件因子枚举
	 */
	private EventRecordFactorEnum factorEnum;
	
	/**
	 * 来源
	 */
	private EventRecordSourceEnum sourceEnum;
	
	/**
	 * 用户编号
	 */
	private String memberNum;
	
	
	/**
	 * 好友编号
	 */
	private String friendNum;
	
	/**
	 * 好友编号
	 */
	private String chickenNum;
	
	/**
	 * 值
	 */
	private BigDecimal val;
	
	/**
	 * 标题
	 */
	private String title;

	private Date eventTime;

	/**
	 * title属性值
	 */
	private RangelandEventTitleDataParam rangelandEventTitleDataParam;

	/**
	 * @return the attrTypeEnum
	 */
	public EventRecordAttrTypeEnum getAttrTypeEnum() {
		return attrTypeEnum;
	}

	public void setAttrTypeEnum(EventRecordAttrTypeEnum attrTypeEnum) {
		this.attrTypeEnum = attrTypeEnum;
	}

	/**
	 * @return the directionEnum
	 */
	public EventRecordDirectionEnum getDirectionEnum() {
		return directionEnum;
	}

	public void setDirectionEnum(EventRecordDirectionEnum directionEnum) {
		this.directionEnum = directionEnum;
	}

	/**
	 * @return the factorEnum
	 */
	public EventRecordFactorEnum getFactorEnum() {
		return factorEnum;
	}

	public void setFactorEnum(EventRecordFactorEnum factorEnum) {
		this.factorEnum = factorEnum;
	}

	/**
	 * @return the sourceEnum
	 */
	public EventRecordSourceEnum getSourceEnum() {
		return sourceEnum;
	}

	public void setSourceEnum(EventRecordSourceEnum sourceEnum) {
		this.sourceEnum = sourceEnum;
	}

	/**
	 * @return the memberNum
	 */
	public String getMemberNum() {
		return memberNum;
	}

	public void setMemberNum(String memberNum) {
		this.memberNum = memberNum;
	}

	/**
	 * @return the friendNum
	 */
	public String getFriendNum() {
		return friendNum;
	}

	public void setFriendNum(String friendNum) {
		this.friendNum = friendNum;
	}

	/**
	 * @return the val
	 */
	public BigDecimal getVal() {
		return val;
	}

	public void setVal(BigDecimal val) {
		this.val = val;
	}

	/**
	 * @return the chickenNum
	 */
	public String getChickenNum() {
		return chickenNum;
	}

	public void setChickenNum(String chickenNum) {
		this.chickenNum = chickenNum;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getEventTime() {
		return eventTime;
	}

	public void setEventTime(Date eventTime) {
		this.eventTime = eventTime;
	}

	public RangelandEventTitleDataParam getRangelandEventTitleDataParam() {
		return rangelandEventTitleDataParam;
	}

	public void setRangelandEventTitleDataParam(RangelandEventTitleDataParam rangelandEventTitleDataParam) {
		this.rangelandEventTitleDataParam = rangelandEventTitleDataParam;
	}
}
