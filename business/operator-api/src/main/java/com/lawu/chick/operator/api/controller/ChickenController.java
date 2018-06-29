package com.lawu.chick.operator.api.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.chick.framework.web.impl.annotation.PageBody;
import com.lawu.chick.operator.api.conterver.ChickenConverter;
import com.lawu.chick.operator.api.dto.ChickenManagePageDTO;
import com.lawu.chick.service.ChickenService;
import com.lawu.chick.service.InventoryService;
import com.lawu.chick.service.bo.ChickenManagePageBO;
import com.lawu.chick.service.param.ChickenPageParam;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.HttpCode;
import com.lawu.framework.web.Result;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

/**
 * 用户管理
 * 
 * @author zhangrc
 * @createDate 2018年4月27日
 * @updateDate 2018年4月27日
 */
@Api(tags = "chick", description = "小鸡管理")
@Validated
@RestController
@RequestMapping(value = "chick/")
public class ChickenController extends BaseController {
    
    @Autowired
    private ChickenService chickenService;
    
    @Autowired
    private InventoryService inventoryService;
    
    @PageBody
    @ApiOperation(value = "分页查询小鸡", notes = "分页查询小鸡[]（张荣成）", httpMethod = "GET")
    @RequiresPermissions("chick:page")
    @RequestMapping(value = "page", method = RequestMethod.GET)
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    public Result<Page<ChickenManagePageDTO>> page(@ModelAttribute @Validated ChickenPageParam param) {
        Page<ChickenManagePageBO> page = chickenService.managPage(param);
        Page<ChickenManagePageDTO> model = new Page<>();
        model.setCurrentPage(page.getCurrentPage());
        model.setTotalCount(page.getTotalCount());
        model.setRecords(ChickenConverter.converterList(page.getRecords()));
        return successGet(model);
    }
    
     
    @ApiOperation(value = "激活所有小鸡", notes = "激活所有小鸡[]（张荣成）", httpMethod = "PUT")
    @RequestMapping(value = "activateAllChicken", method = RequestMethod.PUT)
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    public Result activateAllChicken() {
    	inventoryService.activateAllChicken();
        return successCreated();
    }
    
}
