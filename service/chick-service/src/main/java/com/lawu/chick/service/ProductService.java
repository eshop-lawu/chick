package com.lawu.chick.service;

import com.lawu.chick.service.bo.ProductBO;
import com.lawu.chick.service.param.ProductPageParam;
import com.lawu.chick.service.param.ProductSaveParam;
import com.lawu.chick.service.param.ProductSearchPageParam;
import com.lawu.chick.service.param.ProductUpdateParam;
import com.lawu.framework.core.page.Page;

/**
 * 商品服务接口
 * 
 * @author jiangxinjun
 * @createDate 2018年4月25日
 * @updateDate 2018年4月25日
 */
public interface ProductService {
    
    /**
     * 分页查询商品列表
     * @param param
     * @return
     * @author jiangxinjun
     * @createDate 2018年4月25日
     * @updateDate 2018年4月25日
     */
    Page<ProductBO> productPage(ProductPageParam param);
    
    /**
     * 分页查询商品列表
     * @param param
     * @return
     * @author jiangxinjun
     * @createDate 2018年4月25日
     * @updateDate 2018年4月25日
     */
    Page<ProductBO> productDetailPage(ProductSearchPageParam param);
    
    /**
     * 新增商品
     * @param param
     * @author jiangxinjun
     * @createDate 2018年4月25日
     * @updateDate 2018年4月25日
     */
    void save(ProductSaveParam param);
    
    /**
     * 更新商品
     * @param id
     * @param param
     * @author jiangxinjun
     * @createDate 2018年4月25日
     * @updateDate 2018年4月25日
     */
    void update(Long id, ProductUpdateParam param);
    
    /**
     * 上架商品
     * @param id
     * @author jiangxinjun
     * @createDate 2018年4月25日
     * @updateDate 2018年4月25日
     */
    void up(Long id);
    
    /**
     * 下架商品
     * @param id
     * @author jiangxinjun
     * @createDate 2018年4月25日
     * @updateDate 2018年4月25日
     */
    void down(Long id);
    
    /**
     * 商品详情
     * @param id
     * @author jiangxinjun
     * @createDate 2018年4月25日
     * @updateDate 2018年4月25日
     */
    ProductBO detail(Long id);

    /**
     * 商品详情
     * @param num
     * @createDate 2018年4月25日
     * @updateDate 2018年4月25日
     */
    ProductBO getProductByNum(String num);

}
