package com.lawu.chick.service;

import com.lawu.chick.service.bo.InventoryBO;
import com.lawu.chick.service.bo.InventoryInfoBO;
import com.lawu.chick.service.param.InventoryPageParam;
import com.lawu.chick.service.param.InventoryParam;
import com.lawu.framework.core.page.Page;

/**
 * @Description
 * @author zhangrc
 * @date 2018年4月25日
 */
public interface InventoryService {
	
	/**
	 * 仓库存货查询
	 * 
	 * @param num
	 * @param param
	 * @return
	 */
	Page<InventoryBO> page(String num , InventoryPageParam param);
	
	/**
	 * 小鸡激活
	 * @param num 用户编号
	 * @param id 仓库id
	 */
	void activateChicken(String num ,Long id);

	/**
	 * 查询自己可用饲料信息
	 * @param memberNum
	 * @param param
	 * @return
	 */
	Page<InventoryBO> getMyInventoryFeedInfo(String memberNum, InventoryPageParam param);
	
	/**
	 * 新增库存
	 * @param inventoryParam
	 */
	void addInventory(InventoryParam inventoryParam);

	/**
	 * 查询仓库中库存信息
	 * @param inventoryId
	 * @return
	 */
	InventoryInfoBO getInventoryFeedInfoById(long inventoryId);

	/**
	 * 减饲料
	 * @param inventoryParam
	 * @return
	 */
	int reduceFeedInfo(InventoryParam inventoryParam);
	
	/**
	 * 获取仓库小鸡数量
	 * @param memberNum
	 * @return
	 */
	int getInventoryChickCount(String memberNum);
	
	/**
	 * 激活仓库中所有小鸡
	 */
	void activateAllChicken();

}
