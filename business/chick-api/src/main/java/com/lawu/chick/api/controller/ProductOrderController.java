package com.lawu.chick.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.authorization.annotation.Authorization;
import com.lawu.authorization.util.UserUtil;
import com.lawu.chick.api.authorization.impl.UserConstant;
import com.lawu.chick.api.converter.ProductOrderConverter;
import com.lawu.chick.api.dto.PaymentInformationDTO;
import com.lawu.chick.api.dto.ProductOrderPageDTO;
import com.lawu.chick.api.param.ProductOrderBuyExternalParam;
import com.lawu.chick.framework.web.impl.ResultCode;
import com.lawu.chick.service.ProductOrderService;
import com.lawu.chick.service.bo.PaymentInformationBO;
import com.lawu.chick.service.bo.ProductOrderListBO;
import com.lawu.chick.service.exception.DataNotExistException;
import com.lawu.chick.service.exception.WrongOperationException;
import com.lawu.chick.service.param.CommonPageParam;
import com.lawu.chick.service.param.ProductOrderBuyParam;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.HttpCode;
import com.lawu.framework.web.Result;
import com.lawu.framework.web.doc.annotation.Audit;
import com.lawu.utils.IpUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

/**
 * 商品订单控制器
 * 
 * @author jiangxinjun
 * @createDate 2018年4月27日
 * @updateDate 2018年4月27日
 */
@Api(tags = "productOrder", description = "商品订单控制器")
@RestController
@RequestMapping(value = "productOrder/")
public class ProductOrderController extends BaseController {

    @Autowired
    private ProductOrderService productOrderService;

    @Audit(date = "2018-04-28", reviewer = "孙林青")
    @Authorization
    @ApiOperation(value = "购买商品", notes = "购买商品[]（蒋鑫俊）", httpMethod = "POST")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequestMapping(value = "buy", method = RequestMethod.POST)
    public Result<PaymentInformationDTO> buy(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,
            @ModelAttribute @Validated ProductOrderBuyExternalParam param) {
        String memberNum = UserUtil.getCurrentUserNum(getRequest());
        ProductOrderBuyParam productOrderBuyParam = new ProductOrderBuyParam();
        productOrderBuyParam.setMemberNum(memberNum);
        productOrderBuyParam.setProductNum(param.getProductNum());
        productOrderBuyParam.setQuantity(param.getQuantity());
        productOrderBuyParam.setSpbillCreateIp(IpUtil.getIpAddress(getRequest()));
        try {
            PaymentInformationBO paymentInformationBO = productOrderService.buy(productOrderBuyParam);
            return successCreated(ProductOrderConverter.convert(paymentInformationBO));
        } catch (DataNotExistException e) {
            return successCreated(ResultCode.RESOURCE_NOT_FOUND, e.getMessage());
        } catch (WrongOperationException e) {
            return successCreated(ResultCode.WRONG_OPERATION, e.getMessage());
        }
    }

    @Audit(date = "2018-05-10", reviewer = "孙林青")
    @Authorization
    @ApiOperation(value = "消费记录列表", notes = "消费记录列表[]（章勇）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequestMapping(value = "getProductOrderList", method = RequestMethod.GET)
    public Result<Page<ProductOrderPageDTO>> getProductOrderList(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,
                                                                 @ModelAttribute CommonPageParam param){
        String memberNum = UserUtil.getCurrentUserNum(getRequest());
        Page<ProductOrderListBO> listBOPage = productOrderService.getProductOrderList(memberNum,param);
        Page<ProductOrderPageDTO> page = new Page<>();
        page.setCurrentPage(listBOPage.getCurrentPage());
        page.setTotalCount(listBOPage.getTotalCount());
        page.setRecords(ProductOrderConverter.convertDTOS(listBOPage.getRecords()));
        return successGet(page);
    }

}
