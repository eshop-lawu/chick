package com.lawu.chick.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.authorization.annotation.Authorization;
import com.lawu.authorization.util.UserUtil;
import com.lawu.chick.api.authorization.impl.UserConstant;
import com.lawu.chick.api.converter.PraySignRecordConverter;
import com.lawu.chick.api.dto.PrayAwardDTO;
import com.lawu.chick.api.dto.PraySignAwardDTO;
import com.lawu.chick.cache.service.PraySignService;
import com.lawu.chick.framework.web.impl.ResultCode;
import com.lawu.chick.service.PraySignRecordService;
import com.lawu.chick.service.bo.AwardBO;
import com.lawu.chick.service.bo.PraySignAwardBO;
import com.lawu.chick.service.exception.WrongOperationException;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.HttpCode;
import com.lawu.framework.web.Result;
import com.lawu.framework.web.doc.annotation.Audit;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

/**
 * 签到记录
 * 
 * @Description
 * @author zhangrc
 * @date 2018年6月15日
 */
@Api(tags = "praySignRecord")
@RestController
@RequestMapping(value = "praySignRecord/")
public class PraySignRecordController extends BaseController{
	
	@Autowired
	private PraySignRecordService praySignRecordService;

	@Autowired
	private PraySignService praySignService;

	@Audit(date = "2018-06-25", reviewer = "孙林青")
	@Authorization
	@ApiOperation(value = "签到奖励信息", notes = "签到奖励信息[]（张荣成）", httpMethod = "GET")
	@ApiResponse(code = HttpCode.SC_OK, message = "success")
	@RequestMapping(value = "praySignRecordInfo", method = RequestMethod.GET)
    public Result<PraySignAwardDTO> praySignRecordInfo(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token) {
		String memberNum = UserUtil.getCurrentUserNum(getRequest());
	    PraySignAwardBO praySignAwardBO = praySignRecordService.praySignRecordInfo(memberNum);
	    PraySignAwardDTO praySignAwardDTO = PraySignRecordConverter.convertDTO(praySignAwardBO);
		praySignAwardDTO.setSignDay(praySignService.getSignDayByNum(memberNum));
		return successGet(praySignAwardDTO);
    }


	@Audit(date = "2018-06-25", reviewer = "孙林青")
	@Authorization
	@ApiOperation(value = "领取奖励", notes = "签到奖励信息[1105]（张荣成）", httpMethod = "POST")
	@ApiResponse(code = HttpCode.SC_OK, message = "success")
	@RequestMapping(value = "drawReward", method = RequestMethod.POST)
	public Result drawReward(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token) {
		try {
			String memberNum = UserUtil.getCurrentUserNum(getRequest());
			praySignRecordService.drawReward(memberNum);
			return successCreated();
		} catch (WrongOperationException e) {
			return successCreated(ResultCode.WRONG_OPERATION,e.getMessage());
		}
	}

	@Audit(date = "2018-06-25", reviewer = "孙林青")
	@Authorization
	@ApiOperation(value = "祈福签到", notes = "祈福签到[2009，2010]（章勇）", httpMethod = "POST")
	@ApiResponse(code = HttpCode.SC_OK, message = "success")
	@RequestMapping(value = "addPraySign", method = RequestMethod.POST)
	public Result<PrayAwardDTO> addPraySign(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token) {
		String memberNum = UserUtil.getCurrentUserNum(getRequest());
		Boolean signFlag = praySignRecordService.getRecordByMemberNum(memberNum);
		if (signFlag) {
			return successCreated(ResultCode.HAS_SIGN_RECORD);
		}
		List<AwardBO> awardBOS = praySignRecordService.addPraySign(memberNum);
		if (awardBOS.isEmpty()) {
			return successCreated(ResultCode.SIGN_RECORD_ERROR);
		}
		return successCreated(PraySignRecordConverter.convertPrayAwardDTO(awardBOS));
	}

}
