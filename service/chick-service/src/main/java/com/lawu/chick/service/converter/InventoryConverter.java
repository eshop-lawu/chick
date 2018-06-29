package com.lawu.chick.service.converter;

import com.lawu.chick.repository.domain.InventoryDO;
import com.lawu.chick.repository.domain.ProductDO;
import com.lawu.chick.service.bo.InventoryBO;
import com.lawu.chick.service.bo.InventoryInfoBO;
import com.lawu.chick.service.enums.ProductTypeEnum;

/**
 * @Description
 * @author zhangrc
 * @date 2018年4月25日
 */
public class InventoryConverter {


	/**
	 * @param inventoryDO
	 * @param productDO
	 */
	public static InventoryBO convert(InventoryDO inventoryDO, ProductDO productDO) {
		InventoryBO resouse = new InventoryBO();
		if(inventoryDO == null ){
			return resouse;
		}
		resouse.setId(inventoryDO.getId());
		resouse.setName(inventoryDO.getName());
		resouse.setIntro(productDO.getIntro());
		resouse.setProductImg(productDO.getImgPath());
		resouse.setQuantity(inventoryDO.getQuantity());
		resouse.setType(ProductTypeEnum.getEnum(inventoryDO.getType()));
		return resouse;
		
	}

	public static InventoryBO convertInventoryBO(InventoryDO inv, ProductDO productDO) {
		InventoryBO resouse = new InventoryBO();
		if(inv == null ){
			return resouse;
		}
		resouse.setId(inv.getId());
		resouse.setName(inv.getName());
		resouse.setIntro(productDO.getIntro());
		resouse.setProductImg(productDO.getImgPath());
		resouse.setQuantity(inv.getQuantity());
		resouse.setFullVal(inv.getFullVal());
		resouse.setIsFullFirst(inv.getIsFullFirst());
		resouse.setGrowthVal(inv.getGrowthVal());
		resouse.setIsGrowthFirst(inv.getIsGrowthFirst());
		resouse.setJoyfulVal(inv.getJoyfulVal());
		resouse.setIsJoyfulFirst(inv.getIsJoyfulFirst());
		return resouse;
	}

	public static InventoryInfoBO convertInventoryInfoBO(InventoryDO invert) {
		InventoryInfoBO info = new InventoryInfoBO();
		info.setId(invert.getId());
		info.setProductNum(invert.getProductNum());
		info.setMemberNum(invert.getMemberNum());
		info.setName(invert.getName());
		info.setQuantity(invert.getQuantity());
		info.setType(invert.getType());
		info.setJoyfulVal(invert.getJoyfulVal());
		info.setJoyfulLimit(invert.getJoyfulLimit());
		info.setGrowthVal(invert.getGrowthVal());
		info.setGrowthLimit(invert.getGrowthLimit());
		info.setFullVal(invert.getFullVal());
		info.setFullLimit(invert.getFullLimit());
		info.setKeepCleanTime(invert.getKeepCleanTime());
		info.setCleanLimit(invert.getCleanLimit());
		return info;
	}

}
