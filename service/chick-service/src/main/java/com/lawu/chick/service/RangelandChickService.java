package com.lawu.chick.service;

import com.lawu.chick.service.bo.ChickHouseInfoBO;
import com.lawu.chick.service.bo.ChickenBaseInfoBO;
import com.lawu.chick.service.bo.RangelandInfoBO;
import com.lawu.chick.service.query.FriendFeedQueryParam;
import com.lawu.chick.service.query.OwnerFeedQueryParam;

/**  
 * 查询牧场小鸡信息
 * @author lihj
 * @date 2018年4月26日
 */
public interface RangelandChickService {

	/**
	 * 查询牧场信息
	 * @param memberNum
	 * @return
	 */
	RangelandInfoBO getMyRangelandInfo(String memberNum);

	/**
	 * 查询鸡舍信息
	 * @param memberNum
	 * @return
	 */
	ChickHouseInfoBO getMyChickHouseInfo(String memberNum);

	/**
	 * 主人喂养
	 * @param queryParam
	 * @return
	 */
	ChickenBaseInfoBO ownerFeed(OwnerFeedQueryParam queryParam);

	/**
	 * 好友喂养
	 * @param queryParam
	 * @return
	 */
	ChickenBaseInfoBO friendFeed(FriendFeedQueryParam queryParam);
	
	

}
