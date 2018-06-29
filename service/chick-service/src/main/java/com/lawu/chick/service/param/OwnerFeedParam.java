package com.lawu.chick.service.param;

import io.swagger.annotations.ApiModelProperty;

/**  
 * 主人喂养参数
 * @author lihj
 * @date 2018年4月27日
 */
public class OwnerFeedParam {

	@ApiModelProperty(value = "小鸡编号")
	private String num;
	
	@ApiModelProperty(value = "仓库库存货品表id")
	private long inventoryId;

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
	
	
	
}
