package com.lawu.chick.api.converter;

import java.util.ArrayList;
import java.util.List;

import com.lawu.chick.api.dto.PaymentInformationDTO;
import com.lawu.chick.api.dto.ProductOrderPageDTO;
import com.lawu.chick.service.bo.PaymentInformationBO;
import com.lawu.chick.service.bo.ProductOrderListBO;

/**
 * ProductOrder转换器
 * 
 * @author jiangxinjun
 * @createDate 2018年4月28日
 * @updateDate 2018年4月28日
 */
public class ProductOrderConverter {
    
    private ProductOrderConverter() {
    }
    
    public static PaymentInformationDTO convert(PaymentInformationBO source) {
        PaymentInformationDTO target = null;
        if (source == null) {
            return target;
        }
        target = new PaymentInformationDTO();
        target.setAppid(source.getAppid());
        target.setDataPackage(source.getDataPackage());
        target.setNonceStr(source.getNonceStr());
        target.setPaySign(source.getPaySign());
        target.setSignType(source.getSignType());
        target.setTimeStamp(source.getTimeStamp());
        return target;
    }

    public static List<ProductOrderPageDTO> convertDTOS(List<ProductOrderListBO> records) {
        if (records.isEmpty()) {
            return new ArrayList<>();
        }
        List<ProductOrderPageDTO> pageDTOS = new ArrayList<>();
        ProductOrderPageDTO pageDTO;
        for (ProductOrderListBO listBO : records) {
            pageDTO = new ProductOrderPageDTO();
            pageDTO.setGmtCreate(listBO.getGmtCreate());
            pageDTO.setTotalPrice(listBO.getTotalPrice());
            pageDTO.setProductImgPath(listBO.getProductImgPath() == null ? "" : listBO.getProductImgPath());
            pageDTO.setProductName(listBO.getProductName() == null ? "" : listBO.getProductName());
            pageDTO.setQuantity(listBO.getQuantity());
            pageDTOS.add(pageDTO);
        }
        return pageDTOS;
    }
}
