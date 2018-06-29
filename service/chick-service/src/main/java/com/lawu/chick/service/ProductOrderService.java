package com.lawu.chick.service;

import com.lawu.chick.service.bo.PaymentInformationBO;
import com.lawu.chick.service.bo.ProductOrderListBO;
import com.lawu.chick.service.exception.DataNotExistException;
import com.lawu.chick.service.exception.WrongOperationException;
import com.lawu.chick.service.param.CommonPageParam;
import com.lawu.chick.service.param.ProductOrderBuyParam;
import com.lawu.chick.service.param.ProductOrderCallbackParam;
import com.lawu.framework.core.page.Page;

/**
 * 商品订单服务接口
 * 
 * @author jiangxinjun
 * @createDate 2018年4月25日
 * @updateDate 2018年4月25日
 */
public interface ProductOrderService {
    
    /**
     * 购买接口
     * 
     * @param param
     * @author jiangxinjun
     * @createDate 2018年4月27日
     * @updateDate 2018年4月27日
     */
    PaymentInformationBO buy(ProductOrderBuyParam param) throws DataNotExistException, WrongOperationException;
    
    /**
     * 支付回调
     * 
     * @author jiangxinjun
     * @createDate 2018年4月27日
     * @updateDate 2018年4月27日
     */
    void payCallback(ProductOrderCallbackParam param);

    Page<ProductOrderListBO> getProductOrderList(String memberNum, CommonPageParam param);
}
