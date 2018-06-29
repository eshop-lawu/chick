package com.lawu.chick.service.param;

import com.lawu.framework.core.page.AbstractPageParam;

/**
 * @Description
 * @author zhangrc
 * @date 2018年4月27日
 */
public class MemberPageParam extends AbstractPageParam{
	
	private String memberNum;
	
	private String nickname;

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
	 * @return the nickname
	 */
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	

}
