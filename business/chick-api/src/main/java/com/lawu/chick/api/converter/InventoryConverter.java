package com.lawu.chick.api.converter;

import java.util.ArrayList;
import java.util.List;

import com.lawu.chick.api.dto.InventoryDTO;
import com.lawu.chick.service.bo.InventoryBO;

/**
 * @Description
 * @author zhangrc
 * @date 2018年4月25日
 */
public class InventoryConverter {

	/**
	 * @param records
	 * @return
	 */
	public static List<InventoryDTO> convertListDTO(List<InventoryBO> records) {
		List<InventoryDTO> list = new ArrayList<>();
		if(records.isEmpty()){
			return list;
		}
		for (InventoryBO inventoryBO : records) {
			InventoryDTO resouse = new InventoryDTO();
			resouse.setId(inventoryBO.getId());
			resouse.setName(inventoryBO.getName());
			resouse.setIntro(inventoryBO.getIntro());
			resouse.setProductImg(inventoryBO.getProductImg());
			resouse.setQuantity(inventoryBO.getQuantity());
			resouse.setFullVal(inventoryBO.getFullVal());
			resouse.setIsFullFirst(inventoryBO.getIsFullFirst());
			resouse.setGrowthVal(inventoryBO.getGrowthVal());
			resouse.setIsGrowthFirst(inventoryBO.getIsGrowthFirst());
			resouse.setJoyfulVal(inventoryBO.getJoyfulVal());
			resouse.setIsJoyfulFirst(inventoryBO.getIsJoyfulFirst());
			resouse.setType(inventoryBO.getType());
			list.add(resouse);
		}
		return list;
	}

}
