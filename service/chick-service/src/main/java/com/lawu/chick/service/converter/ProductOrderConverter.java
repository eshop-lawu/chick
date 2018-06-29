package com.lawu.chick.service.converter;

import java.util.ArrayList;
import java.util.List;

import com.lawu.chick.repository.domain.ProductOrderDO;
import com.lawu.chick.service.bo.ProductOrderListBO;

/**
 * @author zhangyong
 * @date 2018/5/10.
 */
public class ProductOrderConverter {
    public static List<ProductOrderListBO> coverBOS(List<ProductOrderDO> orderDOS) {
        if (orderDOS.isEmpty()) {
            return new ArrayList<>();
        }
        List<ProductOrderListBO> listBOS = new ArrayList<>();
        ProductOrderListBO listBO;
        for (ProductOrderDO orderDO : orderDOS) {
            listBO = new ProductOrderListBO();
            listBO.setProductImgPath(orderDO.getProductImgPath());
            listBO.setProductName(orderDO.getProductName());
            listBO.setQuantity(orderDO.getQuantity());
            listBO.setTotalPrice(orderDO.getTotalPrice());
            listBO.setGmtCreate(orderDO.getGmtCreate());
            listBOS.add(listBO);
        }
        return listBOS;
    }
}
