package com.lawu.chick.operator.api.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.chick.operator.api.conterver.SysConfigConverter;
import com.lawu.chick.operator.api.dto.EggConfigDTO;
import com.lawu.chick.operator.api.log.LogRecord;
import com.lawu.chick.operator.service.dto.constants.LogTitleEnum;
import com.lawu.chick.operator.service.dto.constants.OperationTypeEnum;
import com.lawu.chick.service.SysConfigService;
import com.lawu.chick.service.bo.EggConfigBO;
import com.lawu.chick.service.param.EggConfigParam;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.HttpCode;
import com.lawu.framework.web.Result;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

/**
 * @author meishuquan
 * @date 2018/4/25.
 */
@Api(tags = "sysConfig")
@RestController
@RequestMapping(value = "sysConfig/")
public class SysConfigController extends BaseController {

    @Autowired
    private SysConfigService sysConfigService;

    @LogRecord(type = OperationTypeEnum.ADD ,title = LogTitleEnum.EGG_CONFIG)
    @ApiOperation(value = "保存鸡蛋配置", notes = "保存鸡蛋配置（梅述全）", httpMethod = "POST")
    @RequiresPermissions("eggConfig:add")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequestMapping(value = "saveEggConfig", method = RequestMethod.POST)
    public Result saveEggConfig(@ModelAttribute EggConfigParam eggConfigParam) {
        sysConfigService.saveEggConfig(eggConfigParam);
        return successCreated();
    }

    @ApiOperation(value = "查询鸡蛋配置", notes = "查询鸡蛋配置（梅述全）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequestMapping(value = "getEggConfig", method = RequestMethod.GET)
    public Result<EggConfigDTO> getEggConfig() {
        EggConfigBO configBO = sysConfigService.getEggConfig();
        EggConfigDTO configDTO = SysConfigConverter.convertEggConfigDTO(configBO);
        return successGet(configDTO);
    }

}
