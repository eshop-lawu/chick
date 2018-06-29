package com.lawu.chick.operator.api.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.chick.framework.web.impl.ResultCode;
import com.lawu.chick.framework.web.impl.annotation.PageBody;
import com.lawu.chick.operator.api.conterver.ProductConverter;
import com.lawu.chick.operator.api.conterver.TaskRewardsConfigConverter;
import com.lawu.chick.operator.api.dto.TaskRewardsConfigDTO;
import com.lawu.chick.operator.api.log.LogRecord;
import com.lawu.chick.operator.service.dto.constants.LogTitleEnum;
import com.lawu.chick.operator.service.dto.constants.OperationTypeEnum;
import com.lawu.chick.service.ProductService;
import com.lawu.chick.service.TaskRewardsConfigService;
import com.lawu.chick.service.bo.ProductBO;
import com.lawu.chick.service.bo.TaskRewardsConfigBO;
import com.lawu.chick.service.enums.ProductStatusEnum;
import com.lawu.chick.service.param.ProductSearchPageParam;
import com.lawu.chick.service.param.TaskRewardsConfigParam;
import com.lawu.chick.service.query.TaskRewardsConfigQuery;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.HttpCode;
import com.lawu.framework.web.Result;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

/**
 * @author meishuquan
 * @date 2018/6/15.
 */
@Api(tags = "taskRewardsConfig")
@RestController
@RequestMapping(value = "taskRewardsConfig/")
public class TaskRewardsConfigController extends BaseController {

    @Autowired
    private TaskRewardsConfigService taskRewardsConfigService;

    @Autowired
    private ProductService productService;

    @LogRecord(type = OperationTypeEnum.ADD, title = LogTitleEnum.TASK_REWARDS_CONFIG)
    @ApiOperation(value = "保存任务奖励配置", notes = "保存任务奖励配置（梅述全）", httpMethod = "POST")
    @RequiresPermissions("taskRewardsConfig:add")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequestMapping(value = "saveTaskRewardsConfig", method = RequestMethod.POST)
    public Result saveTaskRewardsConfig(@ModelAttribute TaskRewardsConfigParam param) {
        int result = taskRewardsConfigService.saveTaskRewardsConfig(param);
        if (result > 0) {
            return successCreated(ResultCode.FAIL);
        }
        return successCreated();
    }

    @PageBody
    @ApiOperation(value = "任务奖励配置列表", notes = "任务奖励配置列表（梅述全）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequiresPermissions("taskRewardsConfig:list")
    @RequestMapping(value = "listTaskRewardsConfig", method = RequestMethod.GET)
    public Result<Page<TaskRewardsConfigDTO>> listTaskRewardsConfig(@ModelAttribute TaskRewardsConfigQuery query) {
        Page<TaskRewardsConfigBO> configBOPage = taskRewardsConfigService.listTaskRewardsConfig(query);
        Page<TaskRewardsConfigDTO> page = new Page<>();
        page.setCurrentPage(configBOPage.getCurrentPage());
        page.setTotalCount(configBOPage.getTotalCount());
        page.setRecords(TaskRewardsConfigConverter.convertDTOS(configBOPage.getRecords()));
        return successGet(page);
    }

    @ApiOperation(value = "任务奖励配置详情", notes = "任务奖励配置详情（梅述全）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequestMapping(value = "getTaskRewardsConfig/{id}", method = RequestMethod.GET)
    public Result<TaskRewardsConfigDTO> getTaskRewardsConfig(@PathVariable("id") Long id) {
        TaskRewardsConfigBO taskRewardsConfigBO = taskRewardsConfigService.getTaskRewardsConfig(id);
        TaskRewardsConfigDTO configDTO = TaskRewardsConfigConverter.convertDTO(taskRewardsConfigBO);

        ProductSearchPageParam pageParam = new ProductSearchPageParam();
        pageParam.setCurrentPage(1);
        pageParam.setPageSize(10000);
        pageParam.setStatus(ProductStatusEnum.UP);
        Page<ProductBO> productBOPage = productService.productDetailPage(pageParam);
        configDTO.setProductListDTOS(ProductConverter.convertSignDTO(productBOPage.getRecords()));
        return successGet(configDTO);
    }

}
