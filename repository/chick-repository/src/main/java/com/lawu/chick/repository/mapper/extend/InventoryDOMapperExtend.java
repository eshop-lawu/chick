package com.lawu.chick.repository.mapper.extend;

import com.lawu.chick.repository.param.InventoryQuantityParam;

/**
 * @Description
 * @author zhangrc
 * @date 2018年4月25日
 */
public interface InventoryDOMapperExtend {
	
	/**
	 * 仓库数量减一
	 * 
	 * @param id
	 */
	int reduceQuantity(Long id);
	
	
	/**
	 * 仓库数量加一
	 * 
	 * @param id
	 */
	void addQuantity(InventoryQuantityParam param);

}
