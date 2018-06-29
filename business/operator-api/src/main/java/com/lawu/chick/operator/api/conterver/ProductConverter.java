package com.lawu.chick.operator.api.conterver;

import java.util.ArrayList;
import java.util.List;

import com.lawu.chick.operator.api.dto.ProductDetailDTO;
import com.lawu.chick.operator.api.dto.ProductDetailPageDTO;
import com.lawu.chick.operator.api.dto.SignProductListDTO;
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
    
    public static ProductDetailPageDTO convert(ProductBO source) {
        ProductDetailPageDTO target = null;
        if (source == null) {
            return target;
        }
        target = new ProductDetailPageDTO();
        target.setId(source.getId());
        target.setImgPath(source.getImgPath());
        target.setName(source.getName());
        target.setPrice(source.getPrice());
        target.setType(source.getType());
        target.setStatus(source.getStatus());
        return target;
    }
    
    public static ProductDetailDTO convertProductDetailDTO(ProductBO source) {
        ProductDetailDTO target = null;
        if (source == null) {
            return target;
        }
        target = new ProductDetailDTO();
        target.setId(source.getId());
        target.setNum(source.getNum());
        target.setImgPath(source.getImgPath());
        target.setName(source.getName());
        target.setPrice(source.getPrice());
        target.setOriginalPrice(source.getOriginalPrice());
        target.setFullVal(source.getFullVal());
        target.setFullLimit(source.getFullLimit());
        target.setGrowthVal(source.getGrowthVal());
        target.setGrowthLimit(source.getGrowthLimit());
        target.setJoyfulVal(source.getJoyfulVal());
        target.setJoyfulLimit(source.getJoyfulLimit());
        target.setKeepCleanTime(source.getKeepCleanTime());
        target.setCleanLimit(source.getCleanLimit());
        target.setIntro(source.getIntro());
        target.setShow(source.getShow());
        target.setType(source.getType());
        target.setStatus(source.getStatus());
        return target;
    }
    
    public static List<ProductDetailPageDTO> convert(List<ProductBO> source) {
        List<ProductDetailPageDTO> target = new ArrayList<>();
        if (source == null || source.isEmpty()) {
            return target;
        }
        for (ProductBO item : source) {
            target.add(convert(item));
        }
        return target;
    }

    public static List<SignProductListDTO> convertSignDTO(List<ProductBO> records) {
        if (records.isEmpty()) {
            return new ArrayList<>();
        }
        List<SignProductListDTO> listDTOS = new ArrayList<>();
        SignProductListDTO listDTO;
        for (ProductBO productBO : records) {
            listDTO = new SignProductListDTO();
            listDTO.setNum(productBO.getNum());
            listDTO.setName(productBO.getName());
            listDTOS.add(listDTO);
        }
        return listDTOS;
    }
}
