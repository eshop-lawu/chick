package com.lawu.chick.operator.api.controller;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.chick.framework.web.impl.annotation.PageBody;
import com.lawu.chick.operator.api.conterver.ProductConverter;
import com.lawu.chick.operator.api.conterver.SignRuleConverter;
import com.lawu.chick.operator.api.dto.SignInRuleDTO;
import com.lawu.chick.operator.api.dto.SignRulePageDTO;
import com.lawu.chick.operator.api.log.LogRecord;
import com.lawu.chick.operator.service.dto.constants.LogTitleEnum;
import com.lawu.chick.operator.service.dto.constants.OperationTypeEnum;
import com.lawu.chick.service.ProductService;
import com.lawu.chick.service.SignInRuleService;
import com.lawu.chick.service.bo.ProductBO;
import com.lawu.chick.service.bo.SignInRuleBO;
import com.lawu.chick.service.enums.ProductStatusEnum;
import com.lawu.chick.service.enums.ProductTypeEnum;
import com.lawu.chick.service.enums.StatusEnum;
import com.lawu.chick.service.param.CommonPageParam;
import com.lawu.chick.service.param.ProductSearchPageParam;
import com.lawu.chick.service.param.SignInRuleParam;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.HttpCode;
import com.lawu.framework.web.Result;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

/**
 * @author zhangyong
 * @date 2018/4/26.
 */
@Api(tags = "signInRule")
@RestController
@RequestMapping(value = "signInRule/")
public class SignInRuleController extends BaseController {

    @Autowired
    private SignInRuleService signInRuleService;

    @Autowired
    private ProductService productService;

    @LogRecord(type = OperationTypeEnum.ADD, title = LogTitleEnum.EDIT_SIGN_RULE)
    @ApiOperation(value = "签到配置编辑", notes = "签到配置编辑（章勇）", httpMethod = "POST")
    @RequiresPermissions("sign:edit")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequestMapping(value = "editSignInRule", method = RequestMethod.POST)
    public Result editSignInRule(@RequestBody @Validated SignInRuleParam param) {
        signInRuleService.editSignInRule(param);
        return successCreated();
    }

    @ApiOperation(value = "签到配置详情", notes = "签到配置详情（章勇）", httpMethod = "GET")
    @RequiresAuthentication
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequestMapping(value = "getSignRuleInfo/{id}", method = RequestMethod.GET)
    public Result<SignInRuleDTO> getSignRuleInfo(@PathVariable("id") Long id) {
        SignInRuleDTO ruleDTO = SignRuleConverter.convertDTO(signInRuleService.getSignRuleInfo(id));
        ProductSearchPageParam pageParam  = new ProductSearchPageParam();
        pageParam.setCurrentPage(1);
        pageParam.setPageSize(20);
        pageParam.setStatus(ProductStatusEnum.UP);
        pageParam.setType(ProductTypeEnum.CHICKEN_PROPS);
        Page<ProductBO> pageProduct = productService.productDetailPage(pageParam);
        ruleDTO.setProducts(ProductConverter.convertSignDTO(pageProduct.getRecords()));
        if (StringUtils.isNotEmpty(ruleDTO.getProductNum())) {
            ProductBO productBO = productService.getProductByNum(ruleDTO.getProductNum());
            ruleDTO.setProductName(productBO == null ? "" : productBO.getName());
        }
        return successGet(ruleDTO);
    }

    @LogRecord(type = OperationTypeEnum.ADD, title = LogTitleEnum.EDIT_SIGN_RULE)
    @ApiOperation(value = "禁用/启用", notes = "禁用/启用（章勇）", httpMethod = "PUT")
    @RequiresAuthentication
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequestMapping(value = "editSignRuleStatus/{id}", method = RequestMethod.PUT)
    public Result editSignRuleStatus(@PathVariable("id") Long id, @RequestParam("status") StatusEnum status) {
        signInRuleService.editSignRuleStatus(id, status);
        return successCreated();
    }


    @ApiOperation(value = "查询签到记录列表", notes = "查询签到记录列表（章勇）", httpMethod = "GET")
    @RequiresPermissions("sign:list")
    @PageBody
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequestMapping(value = "getSignRuleList", method = RequestMethod.GET)
    public Result<Page<SignRulePageDTO>> getSignRuleList(@ModelAttribute CommonPageParam param) {
        Page<SignInRuleBO> ruleBOPage = signInRuleService.getSignRuleList(param);
        for(SignInRuleBO ruleBO :ruleBOPage.getRecords()){
            ProductBO productBO = productService.getProductByNum(ruleBO.getProductNum());
            ruleBO.setProductName(productBO == null ? "" : productBO.getName());
        }
        Page<SignRulePageDTO> page = new Page<>();
        page.setCurrentPage(ruleBOPage.getCurrentPage());
        page.setTotalCount(ruleBOPage.getTotalCount());
        page.setRecords(SignRuleConverter.convertDTOS(ruleBOPage.getRecords()));
        return successGet(page);
    }

}
