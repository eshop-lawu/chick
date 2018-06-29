package com.lawu.chick.service.param;

import java.util.Date;

import com.lawu.chick.service.enums.SiteTypeEnum;

/**
 * @Description
 * @author zhangrc
 * @date 2018年4月28日
 */
public class RangelandCleannessParam {
	
	private Date externalCleanTime;
	
	private Date houseCleanTime;
	
	private Long id;

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
	 * @return the houseCleanTime
	 */
	public Date getHouseCleanTime() {
		return houseCleanTime;
	}

	public void setHouseCleanTime(Date houseCleanTime) {
		this.houseCleanTime = houseCleanTime;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
	

}
