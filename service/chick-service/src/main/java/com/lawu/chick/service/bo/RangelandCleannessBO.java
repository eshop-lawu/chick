package com.lawu.chick.service.bo;

import java.util.Date;

/**
 * @Description
 * @author zhangrc
 * @date 2018年4月26日
 */
public class RangelandCleannessBO {
	
	private Long id;
	
	private Date externalCleanTime;
	
	private String memberNum;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the externalCleanTime
	 */
	public Date getExternalCleanTime() {
		return externalCleanTime;
	}

	public void setExternalCleanTime(Date externalCleanTime) {
		this.externalCleanTime = externalCleanTime;
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


	
}
