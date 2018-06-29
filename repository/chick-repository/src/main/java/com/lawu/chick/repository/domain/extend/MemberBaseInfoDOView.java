package com.lawu.chick.repository.domain.extend;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Description
 * @author zhangrc
 * @date 2018年4月27日
 */
public class MemberBaseInfoDOView {
	
	/**
	 * 用户编号
	 */
	private String memberNum;
	
	/**
	 * 微信昵称
	 */
	private String nickname;
	
	/**
	 * 微信图像
	 */
	private String avatarUrl;
	
	/**
	 * 鸡蛋数量
	 */
	private BigDecimal eggs;
	
	private Date gmtCreate;

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

	/**
	 * @return the avatarUrl
	 */
	public String getAvatarUrl() {
		return avatarUrl;
	}

	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}

	/**
	 * @return the eggs
	 */
	public BigDecimal getEggs() {
		return eggs;
	}

	public void setEggs(BigDecimal eggs) {
		this.eggs = eggs;
	}

	/**
	 * @return the gmtCreate
	 */
	public Date getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}
	
	
}
