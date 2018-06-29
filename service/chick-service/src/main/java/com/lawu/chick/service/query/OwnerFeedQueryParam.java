package com.lawu.chick.service.query;

import io.swagger.annotations.ApiModelProperty;

/**  
 * 主人喂养
 * @author lihj
 * @date 2018年4月27日
 */
public class OwnerFeedQueryParam {
	@ApiModelProperty(value = "小鸡编号")
	private String num;
	
	@ApiModelProperty(value = "仓库库存货品表id")
	private long inventoryId;
	
	@ApiModelProperty(value = "用户编号")
	private String memberNum;

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public long getInventoryId() {
		return inventoryId;
	}

	public void setInventoryId(long inventoryId) {
		this.inventoryId = inventoryId;
	}

	public String getMemberNum() {
		return memberNum;
	}

	public void setMemberNum(String memberNum) {
		this.memberNum = memberNum;
	}
	
	
}
