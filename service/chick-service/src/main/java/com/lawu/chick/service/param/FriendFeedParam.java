package com.lawu.chick.service.param;

import io.swagger.annotations.ApiModelProperty;

/**  
 * 好友喂养参数
 * @author lihj
 * @date 2018年4月27日
 */
public class FriendFeedParam {

	@ApiModelProperty(value = "小鸡编号")
	private String num;
	
	@ApiModelProperty(value = "好友编号")
	private String friendNum;
	
	@ApiModelProperty(value = "仓库库存货品表id")
	private Long inventoryId;

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getFriendNum() {
		return friendNum;
	}

	public void setFriendNum(String friendNum) {
		this.friendNum = friendNum;
	}

	public Long getInventoryId() {
		return inventoryId;
	}

	public void setInventoryId(Long inventoryId) {
		this.inventoryId = inventoryId;
	}
	
	
}
