package com.lawu.chick.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.authorization.annotation.Authorization;
import com.lawu.authorization.util.UserUtil;
import com.lawu.chick.api.authorization.impl.UserConstant;
import com.lawu.chick.api.converter.RangelandEventRecordConverter;
import com.lawu.chick.api.dto.EggRecordDTO;
import com.lawu.chick.api.dto.InventoryEggDTO;
import com.lawu.chick.service.MemberService;
import com.lawu.chick.service.RangelandEventRecordService;
import com.lawu.chick.service.bo.InventoryEggBO;
import com.lawu.chick.service.bo.RangelandEventRecordBO;
import com.lawu.chick.service.enums.EventRecordAttrTypeEnum;
import com.lawu.chick.service.enums.EventRecordFactorEnum;
import com.lawu.chick.service.query.EggRecordQuery;
import com.lawu.chick.service.query.RangelandEventRecordQuery;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.HttpCode;
import com.lawu.framework.web.Result;
import com.lawu.framework.web.doc.annotation.Audit;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;

/**
 * @Description
 * @author zhangrc
 * @date 2018年4月25日
 */
@Api(tags = "egg", value = "鸡蛋接口")
@RestController
@RequestMapping(value = "egg/")
public class EggController extends BaseController{
	
	@Autowired
	private MemberService  memberService;

	@Autowired
	private RangelandEventRecordService rangelandEventRecordService;

	@Audit(date = "2018-04-28", reviewer = "孙林青")
	@Authorization
	@ApiOperation(value = "获取鸡蛋数量", notes = "获取鸡蛋数量（张荣成）", httpMethod = "GET")
	@ApiResponse(code = HttpCode.SC_OK, message = "success")
	@RequestMapping(value = "getEggs", method = RequestMethod.GET)
	public Result<InventoryEggDTO> getEggs(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token) {
		String memberNum = UserUtil.getCurrentUserNum(getRequest());
		InventoryEggBO eggBO = memberService.getEggs(memberNum);
		InventoryEggDTO dto = new InventoryEggDTO();
		dto.setEggs(eggBO.getEggs());
		return successGet(dto);
	}

	@Audit(date = "2018-05-10", reviewer = "孙林青")
	@ApiOperation(value = "查询鸡蛋明细", notes = "查询鸡蛋明细（梅述全）", httpMethod = "GET")
	@ApiResponse(code = HttpCode.SC_OK, message = "success")
	@Authorization
	@RequestMapping(value = "listEggRecord", method = RequestMethod.GET)
	public Result<Page<EggRecordDTO>> listEggRecord(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,
													@ModelAttribute @ApiParam EggRecordQuery query) {
		String memberNum = UserUtil.getCurrentUserNum(getRequest());
		RangelandEventRecordQuery eventRecordQuery = new RangelandEventRecordQuery();
		eventRecordQuery.setMemberNum(memberNum);
		eventRecordQuery.setAttrTypeEnum(EventRecordAttrTypeEnum.EGG);
		eventRecordQuery.setFactorEnum(EventRecordFactorEnum.EGG_OPERATE);
		eventRecordQuery.setCurrentPage(query.getCurrentPage());
		eventRecordQuery.setPageSize(query.getPageSize());
		Page<RangelandEventRecordBO> recordBOPage = rangelandEventRecordService.listRangelandEventRecord(eventRecordQuery);

		Page<EggRecordDTO> page = new Page<>();
		page.setCurrentPage(recordBOPage.getCurrentPage());
		page.setTotalCount(recordBOPage.getTotalCount());
		page.setRecords(RangelandEventRecordConverter.convertEggRecordDTOS(recordBOPage.getRecords()));
		return successGet(page);
	}

}
