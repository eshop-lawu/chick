package com.lawu.chick.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.chick.id.worker.generate.impl.BizIdType;
import com.lawu.chick.id.worker.generate.impl.IdWorkerHelper;
import com.lawu.chick.repository.domain.ProductDO;
import com.lawu.chick.repository.domain.ProductDOExample;
import com.lawu.chick.repository.mapper.ProductDOMapper;
import com.lawu.chick.service.ProductService;
import com.lawu.chick.service.bo.ProductBO;
import com.lawu.chick.service.converter.ProductConverter;
import com.lawu.chick.service.enums.ProductStatusEnum;
import com.lawu.chick.service.param.ProductPageParam;
import com.lawu.chick.service.param.ProductSaveParam;
import com.lawu.chick.service.param.ProductSearchPageParam;
import com.lawu.chick.service.param.ProductUpdateParam;
import com.lawu.framework.core.page.Page;

/**
 * 商品服务接口实现类
 * @author jiangxinjun
 * @createDate 2018年4月25日
 * @updateDate 2018年4月25日
 */
@Service
public class ProductServiceImpl implements ProductService {
    
    @Autowired
    private ProductDOMapper productDOMapper;
    
    @Autowired
    private IdWorkerHelper idWorkerHelper;
    
    @Override
    public Page<ProductBO> productPage(ProductPageParam param) {
        Page<ProductBO> rtn = new Page<>();
        ProductDOExample example = new ProductDOExample();
        example.createCriteria().andStatusEqualTo(ProductStatusEnum.UP.getValue())
            .andIsShowEqualTo(true);
        long count = productDOMapper.countByExample(example);
        rtn.setTotalCount((int) count);
        rtn.setCurrentPage(param.getCurrentPage());
        if (count <= 0 || count <= param.getOffset()) {
            return rtn;
        }
        RowBounds rowBounds = new RowBounds(param.getOffset(), param.getPageSize());
        List<ProductDO> list = productDOMapper.selectByExampleWithRowbounds(example, rowBounds);
        rtn.setRecords(ProductConverter.convert(list));
        return rtn;
    }

    @Override
    public Page<ProductBO> productDetailPage(ProductSearchPageParam param) {
        Page<ProductBO> rtn = new Page<>();
        ProductDOExample example = new ProductDOExample();
        ProductDOExample.Criteria criteria = example.createCriteria();
        if (param.getStatus() != null) {
            criteria.andStatusEqualTo(param.getStatus().getValue());
        }
        if (param.getType() != null) {
            criteria.andTypeEqualTo(param.getType().getValue());
        }
        long count = productDOMapper.countByExample(example);
        rtn.setTotalCount((int) count);
        rtn.setCurrentPage(param.getCurrentPage());
        if (count <= 0 || count <= param.getOffset()) {
            return rtn;
        }
        RowBounds rowBounds = new RowBounds(param.getOffset(), param.getPageSize());
        List<ProductDO> list = productDOMapper.selectByExampleWithRowbounds(example, rowBounds);
        rtn.setRecords(ProductConverter.convert(list));
        return rtn;
    }
    
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void save(ProductSaveParam param) {
        ProductDO record = new ProductDO();
        record.setImgPath(param.getImgPath());
        record.setIntro(param.getIntro());
        record.setFullVal(param.getFullVal());
        record.setFullLimit(param.getFullLimit());
        record.setGrowthVal(param.getGrowthVal());
        record.setGrowthLimit(param.getGrowthLimit());
        record.setJoyfulVal(param.getJoyfulVal());
        record.setJoyfulLimit(param.getJoyfulLimit());
        record.setKeepCleanTime(param.getKeepCleanTime());
        record.setCleanLimit(param.getCleanLimit());
        record.setName(param.getName());
        record.setNum(idWorkerHelper.generate(BizIdType.PRODUCT));
        record.setPrice(param.getPrice());
        record.setStatus(ProductStatusEnum.DOWN.getValue());
        record.setIsShow(param.getShow());
        record.setType(param.getType().getValue());
        record.setOriginalPrice(param.getOriginalPrice());
        record.setGmtModified(new Date());
        record.setGmtCreate(new Date());
        productDOMapper.insertSelective(record);
    }
    
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(Long id, ProductUpdateParam param) {
        ProductDO record = new ProductDO();
        record.setId(id);
        record.setImgPath(param.getImgPath());
        record.setIntro(param.getIntro());
        record.setJoyfulVal(param.getJoyfulVal());
        record.setJoyfulLimit(param.getJoyfulLimit());
        record.setFullVal(param.getFullVal());
        record.setFullLimit(param.getFullLimit());
        record.setGrowthVal(param.getGrowthVal());
        record.setGrowthLimit(param.getGrowthLimit());
        record.setKeepCleanTime(param.getKeepCleanTime());
        record.setCleanLimit(param.getCleanLimit());
        record.setName(param.getName());
        record.setPrice(param.getPrice());
        if (param.getType() != null) {
            record.setType(param.getType().getValue());
        }
        record.setOriginalPrice(param.getOriginalPrice());
        record.setGmtModified(new Date());
        productDOMapper.updateByPrimaryKeySelective(record);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void up(Long id) {
        ProductDO record = new ProductDO();
        record.setId(id);
        record.setStatus(ProductStatusEnum.UP.getValue());
        record.setGmtModified(new Date());
        productDOMapper.updateByPrimaryKeySelective(record);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void down(Long id) {
        ProductDO record = new ProductDO();
        record.setId(id);
        record.setStatus(ProductStatusEnum.DOWN.getValue());
        record.setGmtModified(new Date());
        productDOMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public ProductBO detail(Long id) {
        ProductDO productDO = productDOMapper.selectByPrimaryKey(id);
        return ProductConverter.convert(productDO);
    }

    @Override
    public ProductBO getProductByNum(String num) {
        ProductDOExample example = new ProductDOExample();
        example.createCriteria().andNumEqualTo(num);
        List<ProductDO> productDOS = productDOMapper.selectByExample(example);
        if (productDOS.isEmpty()) {
            return null;
        }
        return ProductConverter.convert(productDOS.get(0));
    }

}
