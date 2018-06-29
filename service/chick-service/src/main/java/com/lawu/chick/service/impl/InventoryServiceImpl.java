package com.lawu.chick.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.chick.repository.domain.ChickenDOExample;
import com.lawu.chick.repository.domain.InventoryDO;
import com.lawu.chick.repository.domain.InventoryDOExample;
import com.lawu.chick.repository.domain.InventoryDetailDO;
import com.lawu.chick.repository.domain.ProductDO;
import com.lawu.chick.repository.domain.ProductDOExample;
import com.lawu.chick.repository.mapper.ChickenDOMapper;
import com.lawu.chick.repository.mapper.InventoryDOMapper;
import com.lawu.chick.repository.mapper.InventoryDetailDOMapper;
import com.lawu.chick.repository.mapper.ProductDOMapper;
import com.lawu.chick.repository.mapper.extend.InventoryDOMapperExtend;
import com.lawu.chick.repository.param.InventoryQuantityParam;
import com.lawu.chick.service.ChickenService;
import com.lawu.chick.service.InventoryService;
import com.lawu.chick.service.ProductService;
import com.lawu.chick.service.SysConfigService;
import com.lawu.chick.service.bo.ChickBaseConfigBO;
import com.lawu.chick.service.bo.InventoryBO;
import com.lawu.chick.service.bo.InventoryInfoBO;
import com.lawu.chick.service.bo.ProductBO;
import com.lawu.chick.service.converter.InventoryConverter;
import com.lawu.chick.service.enums.AdoptTypeEnum;
import com.lawu.chick.service.enums.InventoryDetailDirectionEnum;
import com.lawu.chick.service.enums.InventoryDetailTypeEnum;
import com.lawu.chick.service.enums.PeriodTypeEnum;
import com.lawu.chick.service.enums.ProductTypeEnum;
import com.lawu.chick.service.exception.WrongOperationException;
import com.lawu.chick.service.param.InventoryPageParam;
import com.lawu.chick.service.param.InventoryParam;
import com.lawu.framework.core.page.Page;

/**
 * @Description
 * @author zhangrc
 * @date 2018年4月25日
 */
@Service
public class InventoryServiceImpl implements InventoryService{

	@Autowired
	private InventoryDOMapper inventoryDOMapper;
	
	@Autowired
	private ProductDOMapper productDOMapper;
	
	@Autowired
	private InventoryDOMapperExtend inventoryDOMapperExtend;
	
	@Autowired
	private ChickenDOMapper chickenDOMapper;
	
	@Autowired
	private ChickenService chickenService;
	
	@Autowired
	private SysConfigService sysConfigService;
	
	@Autowired
	private ProductService productService;

	@Autowired
	private InventoryDetailDOMapper inventoryDetailDOMapper;
	
	@Override
	public Page<InventoryBO> page(String num, InventoryPageParam param) {
		Page<InventoryBO> rtn = new Page<>();
		InventoryDOExample example = new InventoryDOExample();
		example.createCriteria().andMemberNumEqualTo(num);
		long count = inventoryDOMapper.countByExample(example);
		rtn.setTotalCount((int) count);
		rtn.setCurrentPage(param.getCurrentPage());
		if (count <= 0 || count <= param.getOffset()) {
			return rtn;
		}
		RowBounds rowBounds = new RowBounds(param.getOffset(), param.getPageSize());
		List<InventoryDO> list = inventoryDOMapper.selectByExampleWithRowbounds(example, rowBounds);
		List<InventoryBO> boList = new ArrayList<>();
		for (InventoryDO inventoryDO : list) {
			ProductDOExample pexample = new ProductDOExample();
			pexample.createCriteria().andNumEqualTo(inventoryDO.getProductNum());
			List<ProductDO> proList = productDOMapper.selectByExample(pexample);
			InventoryBO bo = InventoryConverter.convert(inventoryDO,proList.get(0));
			boList.add(bo);
		}
		rtn.setRecords(boList);
		return rtn;
	}

	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void activateChicken(String num ,Long id) throws WrongOperationException{
		ChickenDOExample example = new ChickenDOExample();
		example.createCriteria().andMemberNumEqualTo(num).andPeriodNotEqualTo(PeriodTypeEnum.DIE.getVal());
		int count = (int)chickenDOMapper.countByExample(example);
		
		ChickBaseConfigBO baseInfo = sysConfigService.getCacheChickBaseInfo();
		
		if(count >= baseInfo.getChickMaxAdoptionCount()){
			throw new WrongOperationException("当前只能领养"+baseInfo.getChickMaxAdoptionCount()+"只小鸡");
		}
		
		InventoryDOExample inventoryDOExample = new InventoryDOExample();
		inventoryDOExample.createCriteria().andMemberNumEqualTo(num).andIdEqualTo(id);
		
		int inventoryCount = (int)inventoryDOMapper.countByExample(inventoryDOExample);
		if(inventoryCount == 0){
			throw new WrongOperationException("数据不存在");
		}
		//仓库数量减一
		inventoryDOMapperExtend.reduceQuantity(id);
		//新增仓库明细记录
		InventoryDO inventoryDO = inventoryDOMapper.selectByPrimaryKey(id);
		InventoryDetailDO inventoryDetailDO = new InventoryDetailDO();
		inventoryDetailDO.setInventoryId(id);
		inventoryDetailDO.setMemberNum(inventoryDO.getMemberNum());
		inventoryDetailDO.setProductNum(inventoryDO.getProductNum());
		inventoryDetailDO.setQuantity(1);
		inventoryDetailDO.setType(InventoryDetailTypeEnum.OWNER_WEAR.getVal());
		inventoryDetailDO.setDirection(InventoryDetailDirectionEnum.REDUCE.getVal());
		inventoryDetailDO.setGmtCreate(new Date());
		inventoryDetailDOMapper.insertSelective(inventoryDetailDO);
		
		//初始化小鸡信息
		chickenService.createChicken(num,AdoptTypeEnum.INVENTORY_ACTIVE,0);
	}


	@Override
	public Page<InventoryBO> getMyInventoryFeedInfo(String memberNum, InventoryPageParam param) {
		Page<InventoryBO> rtn = new Page<>();
		InventoryDOExample example = new InventoryDOExample();
		example.createCriteria().andMemberNumEqualTo(memberNum).andTypeEqualTo(ProductTypeEnum.CHICKEN_PROPS.getValue()).andQuantityGreaterThan(0);
		long count = inventoryDOMapper.countByExample(example);
		rtn.setTotalCount((int) count);
		rtn.setCurrentPage(param.getCurrentPage());
		if (count <= 0 || count <= param.getOffset()) {
			return rtn;
		}
		RowBounds rowBounds = new RowBounds(param.getOffset(), param.getPageSize());
		example.setOrderByClause(" quantity desc ");
		List<InventoryDO> list = inventoryDOMapper.selectByExampleWithRowbounds(example, rowBounds);
		List<InventoryBO> boList = new ArrayList<>();
		for (InventoryDO inventoryDO : list) {
			ProductDOExample pexample = new ProductDOExample();
			pexample.createCriteria().andNumEqualTo(inventoryDO.getProductNum());
			List<ProductDO> proList = productDOMapper.selectByExample(pexample);
			InventoryBO bo = InventoryConverter.convertInventoryBO(inventoryDO,proList.get(0));
			boList.add(bo);
		}
		rtn.setRecords(boList);
		return rtn;
	}


	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void addInventory(InventoryParam inventoryParam) {
		Long inventoryId = 0L;
		InventoryDOExample example = new InventoryDOExample();
		example.createCriteria().andMemberNumEqualTo(inventoryParam.getMemberNum()).andProductNumEqualTo(inventoryParam.getProductNum());
		List<InventoryDO> list = inventoryDOMapper.selectByExample(example);
		if (!list.isEmpty()) { 
			//仓库数量
			InventoryQuantityParam param = new InventoryQuantityParam();
			param.setCount(inventoryParam.getQuantity());
			param.setId(list.get(0).getId());
			inventoryDOMapperExtend.addQuantity(param);
			inventoryId = list.get(0).getId();
		} else { 
			//新增记录
			ProductBO product = productService.getProductByNum(inventoryParam.getProductNum());
			InventoryDO record = new InventoryDO();
			record.setGmtCreate(new Date());
			record.setGmtModified(new Date());
			record.setGrowthVal(product.getGrowthVal());
			record.setGrowthLimit(product.getGrowthLimit());
			record.setJoyfulVal(product.getJoyfulVal());
			record.setJoyfulLimit(product.getJoyfulLimit());
			record.setFullVal(product.getFullVal());
			record.setFullLimit(product.getFullLimit());
			record.setKeepCleanTime(product.getKeepCleanTime());
			record.setCleanLimit(product.getCleanLimit());
			record.setMemberNum(inventoryParam.getMemberNum());
			record.setName(product.getName());
			record.setDayUsageLimit(product.getDayUsageLimit());
			record.setProductNum(inventoryParam.getProductNum());
			record.setQuantity(inventoryParam.getQuantity());
			record.setType(product.getType().getValue());
			inventoryDOMapper.insertSelective(record);
			inventoryId = record.getId();
		}

		//新增仓库明细记录
		InventoryDetailDO inventoryDetailDO = new InventoryDetailDO();
		inventoryDetailDO.setInventoryId(inventoryId);
		inventoryDetailDO.setMemberNum(inventoryParam.getMemberNum());
		inventoryDetailDO.setFriendNum(inventoryParam.getFriendNum());
		inventoryDetailDO.setProductNum(inventoryParam.getProductNum());
		inventoryDetailDO.setQuantity(inventoryParam.getQuantity());
		inventoryDetailDO.setType(inventoryParam.getTypeEnum().getVal());
		inventoryDetailDO.setDirection(inventoryParam.getDirectionEnum().getVal());
		inventoryDetailDO.setGmtCreate(new Date());
		inventoryDetailDOMapper.insertSelective(inventoryDetailDO);
	}


	@Override
	public InventoryInfoBO getInventoryFeedInfoById(long inventoryId) {
		InventoryDO invert = inventoryDOMapper.selectByPrimaryKey(inventoryId);
		if(null ==invert){
			return null;
		}
		InventoryInfoBO bo = InventoryConverter.convertInventoryInfoBO(invert);
		return bo;
	}


	@Override
	@Transactional(rollbackFor = Exception.class)
	public int reduceFeedInfo(InventoryParam inventoryParam) {
		int result = inventoryDOMapperExtend.reduceQuantity(inventoryParam.getInventoryId());
		InventoryDO inventoryDO = inventoryDOMapper.selectByPrimaryKey(inventoryParam.getInventoryId());
		//新增仓库明细记录
		InventoryDetailDO inventoryDetailDO = new InventoryDetailDO();
		inventoryDetailDO.setInventoryId(inventoryParam.getInventoryId());
		inventoryDetailDO.setMemberNum(inventoryDO.getMemberNum());
		inventoryDetailDO.setFriendNum(inventoryParam.getFriendNum());
		inventoryDetailDO.setProductNum(inventoryDO.getProductNum());
		inventoryDetailDO.setQuantity(inventoryParam.getQuantity());
		inventoryDetailDO.setType(inventoryParam.getTypeEnum().getVal());
		inventoryDetailDO.setDirection(inventoryParam.getDirectionEnum().getVal());
		inventoryDetailDO.setGmtCreate(new Date());
		inventoryDetailDOMapper.insertSelective(inventoryDetailDO);
		return result;
	}


	
	@Override
	public int getInventoryChickCount(String memberNum) {
		InventoryDOExample example = new InventoryDOExample();
		example.createCriteria().andMemberNumEqualTo(memberNum).andTypeEqualTo(ProductTypeEnum.CHICK.getValue());
		List<InventoryDO> list = inventoryDOMapper.selectByExample(example);
		if(list.isEmpty()){
			return 0;
		} 
		return list.get(0).getQuantity();
	}
	
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void activateAllChicken() {
		InventoryDOExample example = new InventoryDOExample();
		example.createCriteria().andTypeEqualTo(ProductTypeEnum.CHICK.getValue()).andQuantityGreaterThan(0);
		List<InventoryDO> list = inventoryDOMapper.selectByExample(example);
		if(list.isEmpty()){
			return;
		}
		
		for (InventoryDO inventoryDO : list) {
			for (int i = 0; i < inventoryDO.getQuantity(); i++) {
				//仓库数量减一
				inventoryDOMapperExtend.reduceQuantity(inventoryDO.getId());
				//新增仓库明细记录
				InventoryDetailDO inventoryDetailDO = new InventoryDetailDO();
				inventoryDetailDO.setInventoryId(inventoryDO.getId());
				inventoryDetailDO.setMemberNum(inventoryDO.getMemberNum());
				inventoryDetailDO.setProductNum(inventoryDO.getProductNum());
				inventoryDetailDO.setQuantity(1);
				inventoryDetailDO.setType(InventoryDetailTypeEnum.OWNER_WEAR.getVal());
				inventoryDetailDO.setDirection(InventoryDetailDirectionEnum.REDUCE.getVal());
				inventoryDetailDO.setGmtCreate(new Date());
				inventoryDetailDOMapper.insertSelective(inventoryDetailDO);
				
				//初始化小鸡信息
				chickenService.createChicken(inventoryDO.getMemberNum(),AdoptTypeEnum.INVENTORY_ACTIVE,0);
			}
			
		}
		
		
	}


}
