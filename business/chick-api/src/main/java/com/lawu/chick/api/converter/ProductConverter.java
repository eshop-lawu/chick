package com.lawu.chick.api.converter;

import java.util.ArrayList;
import java.util.List;

import com.lawu.chick.api.dto.ProductPageDTO;
import com.lawu.chick.service.bo.ProductBO;

/**
 * Product转换器
 * 
 * @author jiangxinjun
 * @createDate 2018年4月25日
 * @updateDate 2018年4月25日
 */
public class ProductConverter {
    
    private ProductConverter() {
    }
    
    public static ProductPageDTO convert(ProductBO source) {
        ProductPageDTO target = null;
        if (source == null) {
            return target;
        }
        target = new ProductPageDTO();
        target.setNum(source.getNum());
        target.setImgPath(source.getImgPath());
        target.setIntro(source.getIntro());
        target.setName(source.getName());
        target.setPrice(source.getPrice());
        target.setOriginalPrice(source.getOriginalPrice());
        target.setFullVal(source.getFullVal());
        target.setGrowthVal(source.getGrowthVal());
        target.setJoyfulVal(source.getJoyfulVal());
        
        target.setType(source.getType());
        return target;
    }
    
    public static List<ProductPageDTO> convert(List<ProductBO> source) {
        List<ProductPageDTO> target = new ArrayList<>();
        if (source == null || source.isEmpty()) {
            return target;
        }
        for (ProductBO item : source) {
            target.add(convert(item));
        }
        return target;
    }
}
