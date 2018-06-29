package com.lawu.chick.operator.api.controller;
/**  
 * 小鸡配置
 * @author lihj
 * @date 2018年4月25日
 */

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.chick.operator.api.conterver.ChickBaseConfigConvert;
import com.lawu.chick.operator.api.dto.ChickBaseConfigDTO;
import com.lawu.chick.operator.api.dto.RangelandConfigDTO;
import com.lawu.chick.operator.api.log.LogRecord;
import com.lawu.chick.operator.service.dto.constants.LogTitleEnum;
import com.lawu.chick.operator.service.dto.constants.OperationTypeEnum;
import com.lawu.chick.service.SysConfigService;
import com.lawu.chick.service.bo.ChickBaseConfigBO;
import com.lawu.chick.service.bo.RangelandConfigBO;
import com.lawu.chick.service.param.ChickBaseConfigParam;
import com.lawu.chick.service.param.RangelandConfigParam;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.HttpCode;
import com.lawu.framework.web.Result;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;

@Api(tags="chickBaseConfig")
@RestController
@RequestMapping(value = "chickBaseConfig/")
public class ChickBaseConfigController extends BaseController{

	@Autowired
	private SysConfigService sysConfigService;
	
	
	@ApiOperation(value="初始化小鸡配置",notes="初始化小鸡配置",httpMethod="POST")
	@LogRecord(type = OperationTypeEnum.UPDATE ,title = LogTitleEnum.CHICK_CONFIG)
	@RequestMapping(value = "addChickBaseConfigInfo", method = RequestMethod.POST)
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
	public Result addChickBaseConfigInfo(@RequestBody @ApiParam ChickBaseConfigParam param){
		sysConfigService.addChickBaseConfigInfo(param);
		return successCreated();
	} 
	
	@ApiOperation(value="查询小鸡配置",notes="查询小鸡配置",httpMethod="GET")
	@RequestMapping(value = "getChickBaseConfigInfo", method = RequestMethod.GET)
	@ApiResponse(code = HttpCode.SC_CREATED, message = "success")
	public Result<ChickBaseConfigDTO> getChickBaseConfigInfo(){
		ChickBaseConfigBO bo = sysConfigService.getChickBaseConfigInfo();
		ChickBaseConfigDTO dto = ChickBaseConfigConvert.convertChickBaseConfigDTO(bo);
		return successCreated(dto);
	} 
	
	
	@LogRecord(type = OperationTypeEnum.UPDATE ,title = LogTitleEnum.CHICK_CONFIG)
	@ApiOperation(value="初始化牧场配置",notes="初始化牧场配置",httpMethod="POST")
	@RequestMapping(value = "saveRangelandConfig", method = RequestMethod.POST)
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
	public Result saveRangelandConfig(@RequestBody @ApiParam RangelandConfigParam param){
		sysConfigService.saveRangelandConfig(param);
		return successCreated();
	} 
	
	@ApiOperation(value="查询牧场配置",notes="查询牧场配置",httpMethod="GET")
	@RequiresPermissions("rangeland:get")
	@RequestMapping(value = "getRangelandConfig", method = RequestMethod.GET)
	@ApiResponse(code = HttpCode.SC_CREATED, message = "success")
	public Result<RangelandConfigDTO> getRangelandConfig(){
		RangelandConfigBO bo = sysConfigService.getRangelandConfig();
		RangelandConfigDTO dto = ChickBaseConfigConvert.convertRangelandConfigDTO(bo);
		return successCreated(dto);
	} 
	
}
