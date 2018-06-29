package com.lawu.chick.api.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * @Description
 * @author zhangrc
 * @date 2018年4月25日
 */
public class TokenDTO {
	
	@ApiModelProperty(value = "用户编号", required = true)
	private String userNum;

	@ApiModelProperty(value = "用户token，登录后每次请求必须带上该标志", required = true)
	private String token;

	/**
	 * @return the userNum
	 */
	public String getUserNum() {
		return userNum;
	}

	public void setUserNum(String userNum) {
		this.userNum = userNum;
	}

	/**
	 * @return the token
	 */
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	

}
