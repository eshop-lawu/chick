package com.lawu.chick.operator.api.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.chick.framework.web.impl.annotation.PageBody;
import com.lawu.chick.operator.api.conterver.EggDistributionRecordConverter;
import com.lawu.chick.operator.api.dto.EggDistributionRecordDTO;
import com.lawu.chick.service.EggDistributionRecordService;
import com.lawu.chick.service.bo.EggDistributionRecordBO;
import com.lawu.chick.service.query.EggDistributionRecordQueryParam;
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
@Api(tags = {"eggDistributionRecord", "鸡蛋分配记录"})
@Validated
@RestController
@RequestMapping(value = "eggDistributionRecord/")
public class EggDistributionRecordController extends BaseController {
    
    @Autowired
    private EggDistributionRecordService eggDistributionRecordService;
    
    @PageBody
    @ApiOperation(value = "查询鸡蛋分配记录", notes = "分页查询鸡蛋分配记录[]（蒋鑫俊）", httpMethod = "GET")
    @RequiresPermissions("eggDistributionRecord:page")
    @RequestMapping(value = "page", method = RequestMethod.GET)
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    public Result<Page<EggDistributionRecordDTO>> page(@ModelAttribute @Validated EggDistributionRecordQueryParam param) {
        Page<EggDistributionRecordBO> page = eggDistributionRecordService.page(param);
        Page<EggDistributionRecordDTO> model = new Page<>();
        model.setCurrentPage(page.getCurrentPage());
        model.setTotalCount(page.getTotalCount());
        model.setRecords(EggDistributionRecordConverter.convert(page.getRecords()));
        return successGet(model);
    }
    
}
