package com.lawu.chick.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.authorization.annotation.Authorization;
import com.lawu.authorization.util.UserUtil;
import com.lawu.chick.api.authorization.impl.UserConstant;
import com.lawu.chick.api.converter.InventoryConverter;
import com.lawu.chick.api.dto.InventoryDTO;
import com.lawu.chick.framework.web.impl.ResultCode;
import com.lawu.chick.service.InventoryService;
import com.lawu.chick.service.bo.InventoryBO;
import com.lawu.chick.service.exception.WrongOperationException;
import com.lawu.chick.service.param.InventoryPageParam;
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
 * 仓库接口
 * 
 * @author zhangrc
 * @createDate 2018年4月25日
 * @updateDate 2018年4月25日
 */
@Api(tags = "inventory", description = "仓库")
@RestController
@RequestMapping(value = "inventory/")
public class InventoryController extends BaseController {
	
	@Autowired
	private InventoryService inventoryService;

	@Audit(date = "2018-04-28", reviewer = "孙林青")
	@Authorization
	@ApiOperation(value = "分页查询仓库信息", notes = "分页查询仓库信息[]（张荣成）", httpMethod = "GET")
	@ApiResponse(code = HttpCode.SC_OK, message = "success")
	@RequestMapping(value = "page", method = RequestMethod.GET)
    public Result<Page<InventoryDTO>> page(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,
            @Validated InventoryPageParam param) {
		String memberNum = UserUtil.getCurrentUserNum(getRequest());
	    Page<InventoryBO> page = inventoryService.page(memberNum, param);
	    Page<InventoryDTO> model = new Page<>();
	    model.setCurrentPage(page.getCurrentPage());
	    model.setTotalCount(page.getTotalCount());
	    model.setRecords(InventoryConverter.convertListDTO(page.getRecords()));
		return successGet(model);
    }

	@Audit(date = "2018-04-28", reviewer = "孙林青")
	@Authorization
	@ApiOperation(value = "激活小鸡", notes = "激活小鸡[2004]（张荣成）", httpMethod = "POST")
	@ApiResponse(code = HttpCode.SC_CREATED, message = "success")
	@RequestMapping(value = "activateChicken/{id}", method = RequestMethod.POST)
    public Result activateChicken(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,
            @PathVariable@ApiParam(required = true, value = "仓库id") Long id) {
		String memberNum = UserUtil.getCurrentUserNum(getRequest());
	    try {
	    	inventoryService.activateChicken(memberNum, id);
			return successCreated();
		} catch (WrongOperationException e) {
			return successCreated(ResultCode.ADOPT_CHICK_NUMBER_OVER,e.getMessage());
		}
    }

	@Audit(date = "2018-04-28", reviewer = "孙林青")
	@Authorization
	@ApiOperation(value = "分页查询可用饲料信息", notes = "分页查询可用试料信息[]（李洪军）", httpMethod = "GET")
	@ApiResponse(code = HttpCode.SC_OK, message = "success")
	@RequestMapping(value = "getMyInventoryFeedInfo", method = RequestMethod.GET)
	 public Result<Page<InventoryDTO>> getMyInventoryFeedInfo(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token, @Validated InventoryPageParam param){
		String memberNum = UserUtil.getCurrentUserNum(getRequest());
		Page<InventoryBO> page  = inventoryService.getMyInventoryFeedInfo(memberNum,param);
		Page<InventoryDTO> model = new Page<>();
		model.setCurrentPage(page.getCurrentPage());
		model.setTotalCount(page.getTotalCount());
		model.setRecords(InventoryConverter.convertListDTO(page.getRecords()));
		return successGet(model);
	}
	
	
}
