package com.lawu.chick.service.impl;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.lawu.chick.service.ProductService;
import com.lawu.chick.service.bo.ProductBO;
import com.lawu.framework.core.page.Page;

/**
 * 商品服务接口单元测试类
 * @author jiangxinjun
 * @createDate 2018年4月25日
 * @updateDate 2018年4月25日
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceImplTest {
    
    @Autowired
    private ProductService productService;
    
//    @Override
//    public Page<ProductBO> productPage() {
//        productService.productPage(param);
//    }

}
