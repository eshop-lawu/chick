package com.lawu.chick.api.controller;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.authorization.annotation.Authorization;
import com.lawu.authorization.util.UserUtil;
import com.lawu.chick.api.authorization.impl.UserConstant;
import com.lawu.chick.api.converter.RangelandConvert;
import com.lawu.chick.api.dto.ChickHouseInfoDTO;
import com.lawu.chick.api.dto.ChickenBaseInfoDTO;
import com.lawu.chick.api.dto.RangelandInfoDTO;
import com.lawu.chick.api.dto.SweepAwardDTO;
import com.lawu.chick.api.enums.ChickStatusGeneralEnum;
import com.lawu.chick.cache.service.ChickBaseConfigCacheService;
import com.lawu.chick.cache.service.co.ChickBaseConfigCO;
import com.lawu.chick.framework.web.impl.ResultCode;
import com.lawu.chick.service.MemberService;
import com.lawu.chick.service.RangelandChickService;
import com.lawu.chick.service.RangelandService;
import com.lawu.chick.service.RangelandSweepService;
import com.lawu.chick.service.bo.ChickHouseInfoBO;
import com.lawu.chick.service.bo.ChickenBaseInfoBO;
import com.lawu.chick.service.bo.RangelandInfoBO;
import com.lawu.chick.service.bo.SweepAwardBO;
import com.lawu.chick.service.enums.ChickStatusEnum;
import com.lawu.chick.service.enums.PeriodTypeEnum;
import com.lawu.chick.service.enums.SiteTypeEnum;
import com.lawu.chick.service.exception.WrongOperationException;
import com.lawu.chick.service.param.FriendSweepParam;
import com.lawu.chick.service.param.SweepParam;
import com.lawu.chick.service.util.ChickLayUtil;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.HttpCode;
import com.lawu.framework.web.Result;
import com.lawu.framework.web.doc.annotation.Audit;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;

/**  
 * 牧场信息
 * @author lihj
 * @date 2018年4月26日
 */
@Api(tags = "rangelandInfo")
@RestController
@RequestMapping(value = "rangelandInfo/")
public class RangelandController extends BaseController{

	@Autowired
	private RangelandChickService rangelandChickService;
	
	@Autowired
	private RangelandSweepService rangelandSweepService;
	
	@Autowired
	private RangelandService rangelandService;

	@Autowired
	private ChickBaseConfigCacheService chickBaseConfigCacheService;
	
	@Autowired
	private MemberService memberService;
	
	@Audit(date = "2018-04-28", reviewer = "孙林青")
	@Authorization
	@ApiOperation(value = "查询自己或好友的牧场信息", notes = "查询自己或好友的牧场信息[]（李洪军）", httpMethod = "GET")
	@ApiResponse(code = HttpCode.SC_OK, message = "success")
	@RequestMapping(value = "getMyRangelandInfo", method = RequestMethod.GET)
	public Result<RangelandInfoDTO> getMyRangelandInfo(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,
			@RequestParam @ApiParam(required = false, value = "好友编号，查询自己牧场信息传0") String friendMemberNum){
		String memberNum = UserUtil.getCurrentUserNum(getRequest());
		Boolean isHavingAgency = false;
		if(StringUtils.isNoneEmpty(friendMemberNum)&&!"0".equals(friendMemberNum)){
			RangelandInfoBO bo = rangelandChickService.getMyRangelandInfo(friendMemberNum);
			if(null ==bo){
				return successGet(ResultCode.FAIL);
			}
			RangelandInfoDTO dto = RangelandConvert.convertRangelandInfoDTO(bo);
			return successGet(dto);
		} else {
			RangelandInfoBO bo = rangelandChickService.getMyRangelandInfo(memberNum);
			if(null ==bo){
				return successGet(ResultCode.FAIL);
			}
			//是否存在代办：1、存在生病或休眠的小鸡 2、鸡舍清洁度小于最低值 3、存在鸡蛋待领取
			for (ChickenBaseInfoBO chickenBaseInfoBO : bo.getChickInfo()) {
				if (PeriodTypeEnum.FAIL_ILL.getVal().equals(chickenBaseInfoBO.getPeriod()) || ChickStatusEnum.HUNGERHALO.getVal().equals(chickenBaseInfoBO.getStatus())) {
					isHavingAgency = true;
					break;
				}
			}
			if (!isHavingAgency) {
				//ChickBaseConfigCO chickBaseConfigCO = chickBaseConfigCacheService.getCacheChickBaseInfo();
				RangelandInfoBO rangelandInfoBO = rangelandService.getMyRangelandInfo(memberNum);
				if (rangelandInfoBO != null && rangelandInfoBO.getTotalEggs().compareTo(BigDecimal.ZERO) == 1) {
					isHavingAgency = true;
				}
			}
			RangelandInfoDTO dto = RangelandConvert.convertRangelandInfoDTO(bo);
			dto.setHavingAgency(isHavingAgency);
			return successGet(dto);
		}
	}

	@Audit(date = "2018-04-28", reviewer = "孙林青")
	@Authorization
	@ApiOperation(value = "查询自己鸡舍信息", notes = "查询自己鸡舍信息[]（李洪军）", httpMethod = "GET")
	@ApiResponse(code = HttpCode.SC_OK, message = "success")
	@RequestMapping(value = "getMyChickHouseInfo", method = RequestMethod.GET)
	public Result<ChickHouseInfoDTO> getMyChickHouseInfo(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token){
		String memberNum = UserUtil.getCurrentUserNum(getRequest());
		ChickHouseInfoBO bo = rangelandChickService.getMyChickHouseInfo(memberNum);
		ChickHouseInfoDTO dto = RangelandConvert.convertChickHouseInfoDTO(bo);
		List<ChickenBaseInfoDTO> lt = dto.getChickInfo();
		for(ChickenBaseInfoDTO base :lt){
			if(base.getStatus().getVal().equals(ChickStatusGeneralEnum.PRODUCE.getVal())){
				ChickBaseConfigCO chickBaseConfigCO = chickBaseConfigCacheService.getCacheChickBaseInfo();
	        	List<String> chickEggProdTime = chickBaseConfigCO.getChickEggProdTime();
	        	Long layMin =ChickLayUtil.getLayMin(chickEggProdTime);
	        	base.setEggLayCutdown(layMin.intValue());
			}
		}
		return successGet(dto);
	}

	@Audit(date = "2018-04-28", reviewer = "孙林青")
	@Authorization
	@ApiOperation(value = "主人打掃鸡舍和农场", notes = "主人打掃鸡舍和农场[]（张荣成）", httpMethod = "PUT")
	@ApiResponse(code = HttpCode.SC_CREATED, message = "success")
	@RequestMapping(value = "ownerSweep", method = RequestMethod.PUT)
	public Result ownerSweep(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,
			@RequestParam @ApiParam(required = true, value = "EXTERNAL 农场  HOUSE 鸡舍")  SiteTypeEnum typeEnum){
		String memberNum = UserUtil.getCurrentUserNum(getRequest());
		SweepParam param = new SweepParam();
		param.setMemberNum(memberNum);
		param.setSiteType(typeEnum);
		rangelandSweepService.ownerSweep(param);
		return successCreated();
	}

	@Audit(date = "2018-04-28", reviewer = "孙林青")
	@Authorization
	@ApiOperation(value = "好友打掃农场", notes = "好友农场[1105]（张荣成）", httpMethod = "PUT")
	@ApiResponse(code = HttpCode.SC_CREATED, message = "success")
	@RequestMapping(value = "friendSweep", method = RequestMethod.PUT)
	public Result<SweepAwardDTO> friendSweep(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,
			@RequestParam @ApiParam(required = true, value = "被打扫的用户编号")  String sweepMemberNum){
		String memberNum = UserUtil.getCurrentUserNum(getRequest());
		try {
			FriendSweepParam param = new FriendSweepParam();
			param.setFriendNum(memberNum);
			param.setSweepNum(sweepMemberNum);
			SweepAwardBO awardBO = rangelandSweepService.friendSweep(param);
			SweepAwardDTO dto = new SweepAwardDTO();
			dto.setGiveFlag(awardBO.isGiveFlag());
			dto.setGiveFoodsCount(awardBO.getGiveFoodsCount());
			dto.setGiveFoodsName(awardBO.getGiveFoodsName());
			return successCreated(dto);
		} catch (WrongOperationException e) {
			return successCreated(ResultCode.WRONG_OPERATION,e.getMessage());
		}
	}

}
