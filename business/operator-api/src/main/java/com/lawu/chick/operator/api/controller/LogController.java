package com.lawu.chick.operator.api.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.chick.framework.web.impl.annotation.PageBody;
import com.lawu.chick.operator.api.conterver.LogConverter;
import com.lawu.chick.operator.api.dto.LogDTO;
import com.lawu.chick.operator.service.LogService;
import com.lawu.chick.operator.service.bo.LogBO;
import com.lawu.chick.operator.service.dto.param.ListLogParam;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.HttpCode;
import com.lawu.framework.web.Result;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;

/**
 * @author meishuquan
 * @date 2017/5/3.
 */
@Api(tags = "log")
@RestController
@RequestMapping(value = "log/")
public class LogController extends BaseController {

    @Autowired
    private LogService logService;

    @ApiOperation(value = "日志列表", notes = "查询日志列表。（梅述全）", httpMethod = "POST")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequiresPermissions("log:list")
    @PageBody
    @RequestMapping(value = "listLog", method = RequestMethod.POST)
    public Result<Page<LogDTO>> listAd(@RequestBody @ApiParam ListLogParam listLogParam) {
    	Page<LogBO>  pageBO = logService.listLog(listLogParam);
    	Page<LogDTO> pageDTO = new Page<>();
    	List<LogDTO> listDTO = LogConverter.convertDTO(pageBO.getRecords());
    	pageDTO.setCurrentPage(pageBO.getCurrentPage());
    	pageDTO.setRecords(listDTO);
    	pageDTO.setTotalCount(pageBO.getTotalCount());
        return successCreated(pageDTO);
    }

    @ApiOperation(value = "日志详情", notes = "查询日志详情。[1002]（梅述全）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequestMapping(value = "getLog/{id}", method = RequestMethod.GET)
    public Result<LogDTO> getLog(@PathVariable @ApiParam(value = "ID") Long id) {
    	LogBO logBO = logService.getLogById(id);
    	LogDTO dto = LogConverter.convertDTO(logBO);
        return successGet(dto);
    }

}
