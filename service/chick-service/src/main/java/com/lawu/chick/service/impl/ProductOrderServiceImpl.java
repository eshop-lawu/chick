package com.lawu.chick.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.binarywang.wxpay.exception.WxPayException;
import com.lawu.chick.id.worker.generate.impl.BizIdType;
import com.lawu.chick.id.worker.generate.impl.IdWorkerHelper;
import com.lawu.chick.repository.domain.ChickenDOExample;
import com.lawu.chick.repository.domain.ProductDO;
import com.lawu.chick.repository.domain.ProductDOExample;
import com.lawu.chick.repository.domain.ProductOrderDO;
import com.lawu.chick.repository.domain.ProductOrderDOExample;
import com.lawu.chick.repository.domain.WxUserDO;
import com.lawu.chick.repository.domain.WxUserDOExample;
import com.lawu.chick.repository.mapper.ChickenDOMapper;
import com.lawu.chick.repository.mapper.ProductDOMapper;
import com.lawu.chick.repository.mapper.ProductOrderDOMapper;
import com.lawu.chick.repository.mapper.WxUserDOMapper;
import com.lawu.chick.service.ChickenCureTaskService;
import com.lawu.chick.service.ChickenService;
import com.lawu.chick.service.InventoryService;
import com.lawu.chick.service.ProductOrderService;
import com.lawu.chick.service.SysConfigService;
import com.lawu.chick.service.bo.ChickBaseConfigBO;
import com.lawu.chick.service.bo.PaymentInformationBO;
import com.lawu.chick.service.bo.ProductOrderListBO;
import com.lawu.chick.service.converter.ProductOrderConverter;
import com.lawu.chick.service.enums.AdoptTypeEnum;
import com.lawu.chick.service.enums.ChickenCureTaskTypeEnum;
import com.lawu.chick.service.enums.InventoryDetailDirectionEnum;
import com.lawu.chick.service.enums.InventoryDetailTypeEnum;
import com.lawu.chick.service.enums.ProductOrderStatusEnum;
import com.lawu.chick.service.enums.ProductStatusEnum;
import com.lawu.chick.service.enums.ProductTypeEnum;
import com.lawu.chick.service.event.EventPublisher;
import com.lawu.chick.service.exception.DataNotExistException;
import com.lawu.chick.service.exception.WrongOperationException;
import com.lawu.chick.service.param.CommonPageParam;
import com.lawu.chick.service.param.InventoryParam;
import com.lawu.chick.service.param.ProductOrderBuyParam;
import com.lawu.chick.service.param.ProductOrderCallbackParam;
import com.lawu.chick.wx.service.PayService;
import com.lawu.chick.wx.service.bo.OrderPayBO;
import com.lawu.chick.wx.service.param.OrderParam;
import com.lawu.framework.core.page.Page;
import com.lawu.utils.DateUtil;

/**
 * 商品订单服务接口实现类
 * @author jiangxinjun
 * @createDate 2018年4月27日
 * @updateDate 2018年4月27日
 */
@Service
public class ProductOrderServiceImpl implements ProductOrderService {
    
    @Autowired
    private ProductDOMapper productDOMapper;
    
    @Autowired
    private ProductOrderDOMapper productOrderDOMapper;
    
    @Autowired
    private WxUserDOMapper wxUserDOMapper;
    
    @Autowired
    private InventoryService inventoryService;
    
    @Autowired
    private IdWorkerHelper idWorkerHelper;
    
    @Autowired
    private PayService payService;
    
    @Autowired
    private ChickenCureTaskService chickenCureTaskService;

    @Autowired
    private EventPublisher eventPublisher;
    
    @Autowired
    private ChickenService chickenService;
    
    @Autowired
    private SysConfigService sysConfigService;
    
    @Transactional(rollbackFor = Exception.class)
    @Override
    public PaymentInformationBO buy(ProductOrderBuyParam param) {
    	
        ProductDOExample productDOExample = new ProductDOExample();
        productDOExample.createCriteria().andNumEqualTo(param.getProductNum());
        List<ProductDO> list = productDOMapper.selectByExample(productDOExample);
        if (list.isEmpty()) {
            throw new DataNotExistException("商品不存在");
        }
        ProductDO productDO = list.get(0);
        if (ProductStatusEnum.DOWN == ProductStatusEnum.getEnum(productDO.getStatus())) {
            throw new WrongOperationException("商品已经下架");
        }
		if (ProductTypeEnum.getEnum(productDO.getType()) == ProductTypeEnum.CHICK) {
			//获取用户激活的小鸡数量
	    	int chickenCount = chickenService.getChickenCount(param.getMemberNum());
	    	
	    	//获取仓库小鸡数量
	    	int inventoryChickenCount = inventoryService.getInventoryChickCount(param.getMemberNum());
	    	int chickTotalCount = chickenCount+inventoryChickenCount;
	    	
	    	//获取小鸡可领养总数
	    	ChickBaseConfigBO config = sysConfigService.getCacheChickBaseInfo();
	    	int chickMaxAdoptionCount = config.getChickMaxAdoptionCount();
			if (chickTotalCount + param.getQuantity() > chickMaxAdoptionCount) {
				throw new WrongOperationException("只可领养" + chickMaxAdoptionCount + "只小鸡");
			}
		}
        
        ProductOrderDO record = new ProductOrderDO();
        record.setMemberNum(param.getMemberNum());
        record.setProductId(productDO.getId());
        record.setProductNum(productDO.getNum());
        record.setQuantity(param.getQuantity());
        record.setPrice(productDO.getPrice());
        record.setProductName(productDO.getName());
        record.setProductImgPath(productDO.getImgPath());
        record.setTotalPrice(record.getPrice().multiply(new BigDecimal(record.getQuantity())));
        record.setStatus(ProductOrderStatusEnum.UNPAID.getValue());
        record.setOrderNum(idWorkerHelper.generate(BizIdType.ORDER));
        record.setGmtCreate(new Date());
        record.setGmtModified(new Date());
        productOrderDOMapper.insert(record);
        
        WxUserDOExample wxUserDOExample = new WxUserDOExample();
        wxUserDOExample.createCriteria().andMemberNumEqualTo(param.getMemberNum());
        List<WxUserDO> wxUserDOList = wxUserDOMapper.selectByExample(wxUserDOExample);
        if (wxUserDOList.isEmpty()) {
            throw new WrongOperationException("微信未绑定");
        }
        WxUserDO wxUserDO = wxUserDOList.get(0);
        OrderParam orderParam = new OrderParam();
        orderParam.setOpenid(wxUserDO.getOpenid());
        orderParam.setOrderNum(record.getOrderNum());
        orderParam.setOrderTime(DateUtil.getDateFormat(record.getGmtCreate(), DateUtil.DATETIME_INT_FORMAT));
        orderParam.setSpbillCreateIp(param.getSpbillCreateIp());
        Integer totalFee = record.getTotalPrice().multiply(new BigDecimal(100)).setScale(0).intValue();
        orderParam.setTotalFee(totalFee);
        try {
            OrderPayBO orderPayBO = payService.unifiedOrder(orderParam);
            PaymentInformationBO rtn = new PaymentInformationBO();
            rtn.setAppid(orderPayBO.getAppid());
            rtn.setDataPackage(orderPayBO.getDataPackage());
            rtn.setNonceStr(orderPayBO.getNonceStr());
            rtn.setPaySign(orderPayBO.getPaySign());
            rtn.setSignType(orderPayBO.getSignType());
            rtn.setTimeStamp(orderPayBO.getTimeStamp());
            return rtn;
        } catch (WxPayException e) {
            throw new WrongOperationException("微信支付请求失败");
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void payCallback(ProductOrderCallbackParam param) {
        ProductOrderDOExample productOrderDOExample = new ProductOrderDOExample();
        productOrderDOExample.createCriteria().andOrderNumEqualTo(param.getOrderNum());
        // 更新订单的状态
        ProductOrderDO record = new ProductOrderDO();
        if (param.getIsSuccessful()) {
            record.setStatus(ProductOrderStatusEnum.TRANSACTION_SUCCESS.getValue());
            List<ProductOrderDO> list = productOrderDOMapper.selectByExample(productOrderDOExample);
            if (list.isEmpty()) {
                throw new DataNotExistException("商品订单不存在");
            }
            ProductOrderDO productOrderDO = list.get(0);
            /*
             * 重复回调判断
             * 判断订单的状态, 是否已经执行过回调方法
             */
            if (ProductOrderStatusEnum.UNPAID != ProductOrderStatusEnum.getEnum(productOrderDO.getStatus())) {
                // 订单已经回调完成
                return;
            }
            
            // 购买小鸡后激活
            chickenService.createChicken(productOrderDO.getMemberNum(), AdoptTypeEnum.BUY,productOrderDO.getQuantity());

            //完成治愈任务
        } else {
            record.setStatus(ProductOrderStatusEnum.TRANSACTION_FAILED.getValue());
        }
        record.setPayTransactionId(param.getTransactionId());
        record.setPayRemark(param.getRemark());
        record.setGmtModified(new Date());
        productOrderDOMapper.updateByExampleSelective(record, productOrderDOExample);
    }

    @Override
    public Page<ProductOrderListBO> getProductOrderList(String memberNum, CommonPageParam param) {
        ProductOrderDOExample example = new ProductOrderDOExample();
        example.createCriteria().andMemberNumEqualTo(memberNum).andStatusEqualTo(ProductOrderStatusEnum.TRANSACTION_SUCCESS.getValue());
        example.setOrderByClause("id desc");
        RowBounds rowBounds = new RowBounds(param.getOffset(), param.getPageSize());
        List<ProductOrderDO> orderDOS = productOrderDOMapper.selectByExampleWithRowbounds(example, rowBounds);
        Page<ProductOrderListBO> page = new Page<>();
        page.setCurrentPage(param.getCurrentPage());
        page.setTotalCount((int) productOrderDOMapper.countByExample(example));
        page.setRecords(ProductOrderConverter.coverBOS(orderDOS));
        return page;
    }

}
