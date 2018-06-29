package com.lawu.chick.service;

import com.lawu.chick.service.bo.InventoryEggBO;
import com.lawu.chick.service.bo.MemberBaseInfoBO;
import com.lawu.chick.service.param.MemberPageParam;
import com.lawu.framework.core.page.Page;

/**
 * 用户信息接口
 * 
 * @Description
 * @author zhangrc
 * @date 2018年4月25日
 */
public interface MemberService {
	
	/**
	 * 初始化账户信息
	 * 
	 * @param memberNum
	 */
	void createMember(String memberNum);
	
	/**
	 * 获取鸡蛋数量
	 * @param memberNum
	 * @return
	 */
	InventoryEggBO getEggs(String memberNum);
	
	/**
	 * 用户管理
	 * @param param
	 * @return
	 */
	Page<MemberBaseInfoBO> page(MemberPageParam param);
	
	/**
	 * 获取用户id
	 * @param memberNum
	 * @return
	 */
	Long getMemberId(String memberNum);

}
