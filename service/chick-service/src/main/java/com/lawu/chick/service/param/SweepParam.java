package com.lawu.chick.service.param;

import com.lawu.chick.service.enums.SiteTypeEnum;
import com.lawu.chick.service.enums.SweepSourceEnum;

/**
 * @Description
 * @author zhangrc
 * @date 2018年4月26日
 */
public class SweepParam {
	
	private String memberNum;
	
	private SiteTypeEnum siteType;

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
	 * @return the siteType
	 */
	public SiteTypeEnum getSiteType() {
		return siteType;
	}

	public void setSiteType(SiteTypeEnum siteType) {
		this.siteType = siteType;
	}
	
	

}
