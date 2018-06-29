package com.lawu.chick.api.dto;

import java.math.BigDecimal;

/**
 * 仓库鸡蛋数量
 * 
 * @Description
 * @author zhangrc
 * @date 2018年4月25日
 */
public class InventoryEggDTO {
	
	
	private BigDecimal eggs;

	
	public BigDecimal getEggs() {
		return eggs;
	}

	
	public void setEggs(BigDecimal eggs) {
		this.eggs = eggs;
	}
	
	

}
