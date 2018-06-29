package com.lawu.chick.service;

import com.lawu.chick.service.bo.RangelandEventRecordBO;
import com.lawu.chick.service.param.RangelandEventRecordParam;
import com.lawu.chick.service.query.RangelandEventRecordQuery;
import com.lawu.framework.core.page.Page;

/**
 * 农场事件记录
 * 
 * @Description
 * @author zhangrc
 * @date 2018年4月26日
 */
public interface RangelandEventRecordService {
	
	/**
	 * 保存农场事件记录
	 * @param param
	 */
	void saveRangelandEventRecord(RangelandEventRecordParam param);
	
	/**
	 * 获取好友打扫次数
	 * @param friendnum
	 * @param memberNum
	 * @return
	 */
	int getCleannessCount(String friendnum, String memberNum);

	/**
	 * 查询主人当天喂养小鸡次数
	 * @param memberNum
	 * @param num
	 * @return
	 */
	int getOwnerFeedTimes(String memberNum, String num);

	/**
	 * 查询当天小鸡成长值累计
	 * @param memberNum
	 * @param num
	 * @return
	 */
	int getChickDayGrowthVal(String memberNum, String num);

	/**
	 * 查询事件记录
	 *
	 * @param query
	 * @return
	 * @author meishuquan
	 */
	Page<RangelandEventRecordBO> listRangelandEventRecord(RangelandEventRecordQuery query);
	
}
