package com.lawu.chick.api.controller;

import com.lawu.authorization.annotation.Authorization;
import com.lawu.authorization.util.UserUtil;
import com.lawu.chick.api.authorization.impl.UserConstant;
import com.lawu.chick.api.dto.RewewardConfigForwardDTO;
import com.lawu.chick.api.dto.RewewardConfigForwardRtnDTO;
import com.lawu.chick.api.dto.TaskDTO;
import com.lawu.chick.api.dto.TaskRtnDTO;
import com.lawu.chick.framework.web.impl.ResultCode;
import com.lawu.chick.service.TaskRecordService;
import com.lawu.chick.service.TaskRewardsConfigService;
import com.lawu.chick.service.bo.RewewardConfigForwardBO;
import com.lawu.chick.service.bo.TaskBO;
import com.lawu.chick.service.enums.TaskRewardsTypeEnum;
import com.lawu.chick.service.exception.WxAuthException;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.HttpCode;
import com.lawu.framework.web.Result;
import com.lawu.framework.web.doc.annotation.Audit;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Api(tags = "taskRewards")
@RestController
@RequestMapping(value = "taskRewards/")
public class TaskRewardsController extends BaseController {

    @Autowired
    private TaskRewardsConfigService taskRewardsConfigService;
    
    @Autowired
    private TaskRecordService taskRecordService;

    @Audit(date = "2018-06-25", reviewer = "孙林青")
    @ApiOperation(value = "查询邀请和关注公众号奖励信息", notes = "查询邀请和关注公众号奖励信息（杨清华）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @Authorization
    @RequestMapping(value = "getRewardsConfig", method = RequestMethod.GET)
    public Result<RewewardConfigForwardRtnDTO> getRewardsConfig(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,
                                                                @RequestParam @ApiParam(required = true, value = "任务类型") TaskRewardsTypeEnum typeEnum) {
        List<RewewardConfigForwardBO> rewewardConfigForwardBOs = taskRewardsConfigService.getRewardsConfig(typeEnum);
        if (rewewardConfigForwardBOs == null || rewewardConfigForwardBOs.isEmpty()) {
            return successCreated(ResultCode.RESOURCE_NOT_FOUND);
        }
        RewewardConfigForwardRtnDTO rewewardConfigForwardRtnDTO = new RewewardConfigForwardRtnDTO();
        List<RewewardConfigForwardDTO> rewewardConfigForwardDTOS = new ArrayList<>();
        for (RewewardConfigForwardBO rewewardConfigForwardBO : rewewardConfigForwardBOs) {
            RewewardConfigForwardDTO rewewardConfigForwardDTO = new RewewardConfigForwardDTO();
            rewewardConfigForwardDTO.setProductCount(rewewardConfigForwardBO.getProductCount());
            rewewardConfigForwardDTO.setProductImgPath(rewewardConfigForwardBO.getProductImgPath());
            rewewardConfigForwardDTO.setProductName(rewewardConfigForwardBO.getProductName());
            rewewardConfigForwardDTO.setProductNum(rewewardConfigForwardBO.getProductNum());
            rewewardConfigForwardDTOS.add(rewewardConfigForwardDTO);
        }
        rewewardConfigForwardRtnDTO.setRewewardConfigForwardDTOS(rewewardConfigForwardDTOS);
        return successCreated(rewewardConfigForwardRtnDTO);
    }

    @Audit(date = "2018-06-25", reviewer = "孙林青")
    @ApiOperation(value = "完成邀请和关注公众号后处理奖励", notes = "完成邀请和关注公众号后处理奖励（杨清华）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @Authorization
    @RequestMapping(value = "doHandleAfterTask", method = RequestMethod.GET)
    public Result doHandleAfterTask(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,
                               @RequestParam @ApiParam(required = true, value = "任务类型") TaskRewardsTypeEnum typeEnum) {
        //如果奖励配置禁用或无奖励直接返回成功
        if (!taskRewardsConfigService.isExistRewardsConfig(typeEnum)) {
            return successCreated();
        }
        String openid = UserUtil.getCurrentAccount(getRequest());
        String userNum = UserUtil.getCurrentUserNum(getRequest());
        boolean attend =false;
        boolean invite =false;
        
        try {
        	if(typeEnum.equals(TaskRewardsTypeEnum.ATTENTION)){
        		attend = taskRecordService.getAttendTaskRecord(userNum, typeEnum);
        	}else{
        		invite = taskRecordService.getInviteTaskRecord(userNum, typeEnum);
        	}
            
        	if(typeEnum.equals(TaskRewardsTypeEnum.ATTENTION) && !attend){
        		taskRewardsConfigService.doHandleAfterTask(userNum,openid,typeEnum,true);
        	}else if(typeEnum.equals(TaskRewardsTypeEnum.INVITE) && !invite){
        		taskRewardsConfigService.doHandleAfterTask(userNum,openid,typeEnum,true);
        	}
            return successCreated();
        } catch (WxAuthException ex) {
            return successCreated(ResultCode.FAIL,ex.getMessage());
        }
    }

    @Audit(date = "2018-06-26", reviewer = "孙林青")
    @ApiOperation(value = "任务列表", notes = "任务列表（杨清华）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @Authorization
    @RequestMapping(value = "getTaskList", method = RequestMethod.GET)
    public Result<TaskRtnDTO> getTaskList(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token) {
        List<TaskBO> taskBOList = taskRecordService.getTaskList(UserUtil.getCurrentUserNum(getRequest()));
        List<TaskDTO> taskDTOList = new ArrayList<>();
        for (TaskBO taskBO : taskBOList){
            TaskDTO taskDTO = new TaskDTO();
            taskDTO.setTaskRewardsTypeEnum(taskBO.getTaskRewardsTypeEnum());
            taskDTO.setFinish(taskBO.getFinish());
            taskDTOList.add(taskDTO);
        }
        TaskRtnDTO taskRtnDTO = new TaskRtnDTO();
        taskRtnDTO.setTaskDTOList(taskDTOList);
        return successCreated(taskRtnDTO);
    }

}
