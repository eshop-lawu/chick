package com.lawu.chick.operator.api.controller;

import java.util.List;

import javax.validation.constraints.Min;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.chick.framework.web.impl.ResultCode;
import com.lawu.chick.framework.web.impl.annotation.PageBody;
import com.lawu.chick.operator.api.conterver.ProductConverter;
import com.lawu.chick.operator.api.dto.ChooseProductDTO;
import com.lawu.chick.operator.api.dto.ProductDetailDTO;
import com.lawu.chick.operator.api.dto.ProductDetailPageDTO;
import com.lawu.chick.operator.api.dto.SignProductListDTO;
import com.lawu.chick.operator.api.log.LogRecord;
import com.lawu.chick.operator.service.dto.constants.LogTitleEnum;
import com.lawu.chick.operator.service.dto.constants.OperationTypeEnum;
import com.lawu.chick.service.ProductService;
import com.lawu.chick.service.bo.ProductBO;
import com.lawu.chick.service.enums.ProductStatusEnum;
import com.lawu.chick.service.param.ProductSaveParam;
import com.lawu.chick.service.param.ProductSearchPageParam;
import com.lawu.chick.service.param.ProductUpdateParam;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.HttpCode;
import com.lawu.framework.web.Result;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

/**
 * 商品控制器
 * 
 * @author jiangxinjun
 * @createDate 2018年4月25日
 * @updateDate 2018年4月25日
 */
@Api(tags = "product", description = "商品控制器")
@Validated
@RestController
@RequestMapping(value = "product/")
public class ProductController extends BaseController {
    
    @Autowired
    private ProductService productService;
    
    @PageBody
    @ApiOperation(value = "分页查询商品", notes = "分页查询商品[]（蒋鑫俊）", httpMethod = "GET")
    @RequiresPermissions("product:page")
    @RequestMapping(value = "page", method = RequestMethod.GET)
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    public Result<Page<ProductDetailPageDTO>> page(@ModelAttribute @Validated ProductSearchPageParam param) {
        Page<ProductBO> page = productService.productDetailPage(param);
        Page<ProductDetailPageDTO> model = new Page<>();
        model.setCurrentPage(page.getCurrentPage());
        model.setTotalCount(page.getTotalCount());
        model.setRecords(ProductConverter.convert(page.getRecords()));
        return successGet(model);
    }
    
    @ApiOperation(value = "商品详情", notes = "商品详情[]（蒋鑫俊）", httpMethod = "GET")
    @RequiresPermissions("product:page")
    @RequestMapping(value = "detail/{id}", method = RequestMethod.GET)
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    public Result<ProductDetailDTO> detail(@PathVariable("id") @Min(value = 1) Long id) {
        ProductBO productBO = productService.detail(id);
        if (productBO == null) {
            return successCreated(ResultCode.RESOURCE_NOT_FOUND);
        }
        return successCreated(ProductConverter.convertProductDetailDTO(productBO));
    }
    
    @SuppressWarnings("rawtypes")
    @LogRecord(type = OperationTypeEnum.ADD ,title = LogTitleEnum.PRODUCT_ADD)
    @ApiOperation(value = "新增商品", notes = "新增商品[]（蒋鑫俊）", httpMethod = "POST")
    @RequiresPermissions("product:save")
    @RequestMapping(value = "save", method = RequestMethod.POST)
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    public Result save(@ModelAttribute @Validated ProductSaveParam param) {
        productService.save(param);
        return successCreated();
    }
    
    @SuppressWarnings("rawtypes")
    @LogRecord(type = OperationTypeEnum.UPDATE ,title = LogTitleEnum.PRODUCT_UPDATE, idParamName = "id")
    @ApiOperation(value = "更新商品", notes = "更新商品[]（蒋鑫俊）", httpMethod = "PUT")
    @RequiresPermissions("product:save")
    @RequestMapping(value = "update/{id}", method = RequestMethod.PUT)
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    public Result update(@PathVariable("id") Long id, @ModelAttribute @Validated ProductUpdateParam param) {
        productService.update(id, param);
        return successCreated();
    }
    
    @SuppressWarnings("rawtypes")
    @LogRecord(type = OperationTypeEnum.UPDATE ,title = LogTitleEnum.PRODUCT_UP, idParamName = "id")
    @ApiOperation(value = "上架商品", notes = "上架商品[]（蒋鑫俊）", httpMethod = "PUT")
    @RequiresPermissions("product:save")
    @RequestMapping(value = "up/{id}", method = RequestMethod.PUT)
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    public Result up(@PathVariable("id") @Validated Long id) {
        productService.up(id);
        return successCreated();
    }
    
    @SuppressWarnings("rawtypes")
    @LogRecord(type = OperationTypeEnum.UPDATE ,title = LogTitleEnum.PRODUCT_DOWN, idParamName = "id")
    @ApiOperation(value = "下架商品", notes = "下架商品[]（蒋鑫俊）", httpMethod = "PUT")
    @RequiresPermissions("product:save")
    @RequestMapping(value = "down/{id}", method = RequestMethod.PUT)
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    public Result down(@PathVariable("id") Long id) {
        productService.down(id);
        return successCreated();
    }
    
    @ApiOperation(value = "商品列表", notes = "商品列表[]（张荣成）", httpMethod = "GET")
    @RequestMapping(value = "list", method = RequestMethod.GET)
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    public Result<ChooseProductDTO> list() {
    	ProductSearchPageParam param = new ProductSearchPageParam();
    	param.setCurrentPage(1);
    	param.setPageSize(20);
    	param.setStatus(ProductStatusEnum.UP);
    	Page<ProductBO> page = productService.productDetailPage(param);
    	List<SignProductListDTO> list = ProductConverter.convertSignDTO(page.getRecords());
    	ChooseProductDTO choose = new ChooseProductDTO();
    	choose.setList(list);
        return successGet(choose);
    }

}
