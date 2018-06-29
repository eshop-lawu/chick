package com.lawu.chick.service;

import com.lawu.chick.service.bo.TaskBO;
import com.lawu.chick.service.enums.TaskRewardsTypeEnum;

import java.util.List;

/**  
 * @author lihj
 * @date 2018年6月25日
 */
public interface TaskRecordService {

	/**
	 * 判断是否关注
	 * @param userNum
	 * @param type
	 * @return
	 */
	boolean getAttendTaskRecord(String userNum,TaskRewardsTypeEnum type);
	
	/**
	 * 判断是否邀请
	 * @param userNum
	 * @param type
	 * @return
	 */
	boolean getInviteTaskRecord(String userNum,TaskRewardsTypeEnum type);
	
	/**
	 * 初始化任务记录
	 * @param userNum
	 * @param type
	 * @return
	 */
	int saveTaskRecord(String userNum,TaskRewardsTypeEnum type);

	/**
	 * 任务列表
	 * @param userNum
	 * @return
	 */
    List<TaskBO> getTaskList(String userNum);
}
