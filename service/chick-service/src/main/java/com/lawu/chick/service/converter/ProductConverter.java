package com.lawu.chick.service.converter;

import java.util.ArrayList;
import java.util.List;

import com.lawu.chick.repository.domain.ProductDO;
import com.lawu.chick.service.bo.ProductBO;
import com.lawu.chick.service.enums.ProductStatusEnum;
import com.lawu.chick.service.enums.ProductTypeEnum;

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
    
    public static ProductBO convert(ProductDO source) {
        ProductBO target = null;
        if (source == null) {
            return target;
        }
        target = new ProductBO();
        target.setId(source.getId());
        target.setImgPath(source.getImgPath());
        target.setIntro(source.getIntro());
        target.setJoyfulVal(source.getJoyfulVal());
        target.setJoyfulLimit(source.getJoyfulLimit());
        target.setFullVal(source.getFullVal());
        target.setFullLimit(source.getFullLimit());
        target.setGrowthVal(source.getGrowthVal());
        target.setGrowthLimit(source.getGrowthLimit());
        target.setKeepCleanTime(source.getKeepCleanTime());
        target.setCleanLimit(source.getCleanLimit());
        target.setName(source.getName());
        target.setNum(source.getNum());
        target.setPrice(source.getPrice());
        target.setDayUsageLimit(source.getDayUsageLimit());
        target.setOriginalPrice(source.getOriginalPrice());
        target.setShow(source.getIsShow());
        target.setStatus(ProductStatusEnum.getEnum(source.getStatus()));
        target.setType(ProductTypeEnum.getEnum(source.getType()));
        return target;
    }
    
    public static List<ProductBO> convert(List<ProductDO> source) {
        List<ProductBO> target = null;
        if (source == null || source.isEmpty()) {
            return target;
        }
        target = new ArrayList<>();
        for (ProductDO item : source) {
            target.add(convert(item));
        }
        return target;
    }
}
