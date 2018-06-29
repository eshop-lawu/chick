package com.lawu.chick.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.authorization.annotation.Authorization;
import com.lawu.chick.api.authorization.impl.UserConstant;
import com.lawu.chick.api.converter.ProductConverter;
import com.lawu.chick.api.dto.ProductPageDTO;
import com.lawu.chick.service.ProductService;
import com.lawu.chick.service.bo.ProductBO;
import com.lawu.chick.service.param.ProductPageParam;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.HttpCode;
import com.lawu.framework.web.Result;
import com.lawu.framework.web.doc.annotation.Audit;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

/**
 * 商品控制器
 * @author jiangxinjun
 * @createDate 2018年4月25日
 * @updateDate 2018年4月25日
 */
@Api(tags = "product", description = "商品控制器")
@RestController
@RequestMapping(value = "product/")
public class ProductController extends BaseController {
	
	@Autowired
	private ProductService productServcie;

	@Audit(date = "2018-04-28", reviewer = "孙林青")
	@Authorization
	@ApiOperation(value = "分页查询商品信息", notes = "分页查询商品信息[]（蒋鑫俊）", httpMethod = "GET")
	@ApiResponse(code = HttpCode.SC_OK, message = "success")
	@RequestMapping(value = "productPage", method = RequestMethod.GET)
    public Result<Page<ProductPageDTO>> productPage(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,
            @ModelAttribute @Validated ProductPageParam param) {
	    Page<ProductBO> page = productServcie.productPage(param);
	    Page<ProductPageDTO> model = new Page<>();
	    model.setCurrentPage(page.getCurrentPage());
	    model.setTotalCount(page.getTotalCount());
	    model.setRecords(ProductConverter.convert(page.getRecords()));
		return successCreated(model);
    }

}
