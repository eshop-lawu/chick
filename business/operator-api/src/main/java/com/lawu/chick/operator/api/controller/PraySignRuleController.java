package com.lawu.chick.operator.api.controller;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.chick.operator.api.conterver.ProductConverter;
import com.lawu.chick.operator.api.conterver.SignRuleConverter;
import com.lawu.chick.operator.api.dto.PraySignInRuleDTO;
import com.lawu.chick.operator.api.log.LogRecord;
import com.lawu.chick.operator.service.dto.constants.LogTitleEnum;
import com.lawu.chick.operator.service.dto.constants.OperationTypeEnum;
import com.lawu.chick.service.PraySignRuleService;
import com.lawu.chick.service.ProductService;
import com.lawu.chick.service.bo.PraySignRuleBO;
import com.lawu.chick.service.bo.ProductBO;
import com.lawu.chick.service.enums.ProductStatusEnum;
import com.lawu.chick.service.enums.ProductTypeEnum;
import com.lawu.chick.service.param.PraySignRuleParam;
import com.lawu.chick.service.param.ProductSearchPageParam;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.HttpCode;
import com.lawu.framework.web.Result;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

/**
 * @author zhangyong
 * @date 2018/6/15.
 */
@Api(tags = "praySignRule")
@RestController
@RequestMapping(value = "praySignRule/")
public class PraySignRuleController extends BaseController{

    @Autowired
    private PraySignRuleService praySignRuleService;

    @Autowired
    private ProductService productService;

    @LogRecord(type = OperationTypeEnum.ADD, title = LogTitleEnum.EDIT_SIGN_RULE)
    @ApiOperation(value = "签到配置编辑", notes = "签到配置编辑（章勇）", httpMethod = "POST")
    @RequiresAuthentication
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequestMapping(value = "editSignRule", method = RequestMethod.POST)
    public Result editSignRule(@RequestBody @Validated PraySignRuleParam param) {
        praySignRuleService.editSignRule(param);
        return successCreated();
    }

    @ApiOperation(value = "签到配置详情", notes = "签到配置详情（章勇）", httpMethod = "GET")
    @RequiresPermissions("pray:detail")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequestMapping(value = "getPraySignRuleInfo", method = RequestMethod.GET)
    public Result<PraySignInRuleDTO> getPraySignRuleInfo() {
        PraySignRuleBO ruleBO = praySignRuleService.getPraySignRuleInfo();
        PraySignInRuleDTO ruleDTO = SignRuleConverter.convertPrayDTO(ruleBO);
        ProductSearchPageParam pageParam = new ProductSearchPageParam();
        pageParam.setCurrentPage(1);
        pageParam.setPageSize(30);
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
}
