package com.lawu.chick.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.lawu.chick.service.bo.TaskBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawu.chick.repository.domain.TaskRecordDO;
import com.lawu.chick.repository.domain.TaskRecordDOExample;
import com.lawu.chick.repository.mapper.TaskRecordDOMapper;
import com.lawu.chick.service.TaskRecordService;
import com.lawu.chick.service.enums.TaskRewardsTypeEnum;

/**  
 * @author lihj
 * @date 2018年6月25日
 */
@Service
public class TaskRecordServiceImpl implements TaskRecordService{

	@Autowired
	private TaskRecordDOMapper taskRecordDOMapper;

	@Override
	public boolean getAttendTaskRecord(String userNum,TaskRewardsTypeEnum type) {
		TaskRecordDOExample example =new TaskRecordDOExample();
		example.createCriteria().andUserNumEqualTo(userNum).andTypeEqualTo(type.getVal());
		long count = taskRecordDOMapper.countByExample(example);
		if(count>0){
			return true;
		}
		return false;
	}

	@Override
	public boolean getInviteTaskRecord(String userNum, TaskRewardsTypeEnum type) {
		TaskRecordDOExample example =new TaskRecordDOExample();
		example.createCriteria().andUserNumEqualTo(userNum).andTypeEqualTo(type.getVal());
		long count = taskRecordDOMapper.countByExample(example);
		if(count>0){
			return true;
		}
		return false;
	}

	@Override
	public int saveTaskRecord(String userNum, TaskRewardsTypeEnum type) {
		TaskRecordDO task =new TaskRecordDO();
		task.setType(type.getVal());
		task.setUserNum(userNum);
		task.setGmtModified(new Date());
		task.setGmtCreate(new Date());
		return taskRecordDOMapper.insert(task);
	}

	@Override
	public List<TaskBO> getTaskList(String userNum) {
		List<TaskBO> taskBOList = initTaskList();
		for (TaskBO taskBO : taskBOList) {
			if (TaskRewardsTypeEnum.BRESSING.getVal().equals(taskBO.getTaskRewardsTypeEnum().getVal())) {
				continue;
			}
			TaskRecordDOExample example =new TaskRecordDOExample();
			example.createCriteria().andUserNumEqualTo(userNum).andTypeEqualTo(taskBO.getTaskRewardsTypeEnum().getVal());
			long count = taskRecordDOMapper.countByExample(example);
			if (count > 0) {
				taskBO.setFinish(true);
			}
		}
		return taskBOList;
	}

	private List<TaskBO> initTaskList() {
		List<TaskBO> taskBOList = new ArrayList<>();
		TaskRewardsTypeEnum [] typeEnums = TaskRewardsTypeEnum.values();
		for (int i = 0 ; i < typeEnums.length ; i++) {
			TaskBO taskBO = new TaskBO();
			taskBO.setTaskRewardsTypeEnum(typeEnums[i]);
			taskBO.setFinish(false);
			taskBOList.add(taskBO);
		}
		return taskBOList;
	}
}
