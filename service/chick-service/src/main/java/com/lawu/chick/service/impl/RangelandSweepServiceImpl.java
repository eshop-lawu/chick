package com.lawu.chick.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.chick.cache.service.ChickJobCacheService;
import com.lawu.chick.repository.domain.ChickenDO;
import com.lawu.chick.repository.domain.ChickenDOExample;
import com.lawu.chick.repository.domain.RangelandDO;
import com.lawu.chick.repository.domain.RangelandDOExample;
import com.lawu.chick.repository.mapper.ChickenDOMapper;
import com.lawu.chick.repository.mapper.RangelandDOMapper;
import com.lawu.chick.service.InventoryService;
import com.lawu.chick.service.PeriodBehaviorCtrlService;
import com.lawu.chick.service.ProductService;
import com.lawu.chick.service.RangelandEventRecordService;
import com.lawu.chick.service.RangelandService;
import com.lawu.chick.service.RangelandSweepService;
import com.lawu.chick.service.SysConfigService;
import com.lawu.chick.service.UserRelationService;
import com.lawu.chick.service.WxUserService;
import com.lawu.chick.service.bo.RangelandConfigBaseBO;
import com.lawu.chick.service.bo.SweepAwardBO;
import com.lawu.chick.service.bo.WxUserBO;
import com.lawu.chick.service.constants.EventTitleConstant;
import com.lawu.chick.service.enums.EventRecordAttrTypeEnum;
import com.lawu.chick.service.enums.EventRecordDirectionEnum;
import com.lawu.chick.service.enums.EventRecordFactorEnum;
import com.lawu.chick.service.enums.EventRecordSourceEnum;
import com.lawu.chick.service.enums.SiteTypeEnum;
import com.lawu.chick.service.exception.WrongOperationException;
import com.lawu.chick.service.param.FriendSweepParam;
import com.lawu.chick.service.param.RangelandEventRecordParam;
import com.lawu.chick.service.param.SweepParam;

/**
 * @Description
 * @author zhangrc
 * @date 2018年4月26日
 */
@Service
public class RangelandSweepServiceImpl implements RangelandSweepService {

	@Autowired
	private RangelandEventRecordService rangelandEventRecordService;
	
	@Autowired
	private RangelandService rangelandService;
	
	@Autowired
	private SysConfigService sysConfigService;
	
	@Autowired
	private InventoryService inventoryService;
	
	@Autowired
	private PeriodBehaviorCtrlService periodBehaviorCtrlService;
	
	@Autowired
	private UserRelationService userRelationService;
	
	@Autowired
	private WxUserService wxUserService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
    private RangelandDOMapper rangelandDOMapper;
	
	@Autowired
    private ChickenDOMapper chickenDOMapper;
	
	@Autowired
    private ChickJobCacheService chickJobCacheService;
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void ownerSweep(SweepParam param) {
		//获取牧场配置
		RangelandConfigBaseBO config = sysConfigService.getRangelandCacheConfig();
		
		//修改牧场清洁度
		if(param.getSiteType() == SiteTypeEnum.EXTERNAL){
			int cleanness = rangelandService.getCleanness(param.getSiteType(), param.getMemberNum());
			if(cleanness == config.getRangelandMaxCleannessVal()){
				return ;
			}
			RangelandDOExample example = new RangelandDOExample();
			example.createCriteria().andMemberNumEqualTo(param.getMemberNum());
			RangelandDO record = new RangelandDO();
			record.setExternalCleanness(config.getRangelandMaxCleannessVal());
			record.setGmtModified(new Date());
			record.setOutsideDuration(0);
			record.setOutsideTime(new Date());
			rangelandDOMapper.updateByExampleSelective(record, example);
			//插入事件记录
			RangelandEventRecordParam event = new RangelandEventRecordParam();
			event.setAttrTypeEnum(EventRecordAttrTypeEnum.CLEANLINESS);
			event.setDirectionEnum(EventRecordDirectionEnum.ADD);
			event.setFactorEnum(EventRecordFactorEnum.CLEAN);
			event.setMemberNum(param.getMemberNum());
			event.setSourceEnum(EventRecordSourceEnum.OWNER);
			event.setTitle(EventTitleConstant.TITLE_RANGELAND_EXTERNAL_CLEANNES_OWNES);
			event.setVal(BigDecimal.valueOf(config.getRangelandMaxCleannessVal()));
			rangelandEventRecordService.saveRangelandEventRecord(event);
		}else{
			ChickenDOExample chickenDOExample = new ChickenDOExample();
			chickenDOExample.createCriteria().andMemberNumEqualTo(param.getMemberNum()).andCleannessValLessThan(config.getRangelandMaxCleannessVal());
			List<ChickenDO> chicks = chickenDOMapper.selectByExample(chickenDOExample);
			for (ChickenDO chickenDO : chicks) {
				int cleannessVal = chickenDO.getCleannessVal();
				ChickenDO record = new ChickenDO();
				record.setId(chickenDO.getId());
				record.setCleannessVal(config.getRangelandMaxCleannessVal());
				record.setHouseCleanTime(new Date());
				record.setInhouseTime(new Date());
				record.setInhouseDuration(0);
				record.setGmtModified(new Date());
				chickenDOMapper.updateByPrimaryKeySelective(record);
				
				//打扫鸡窝次数更新
				chickJobCacheService.removeAttenuationJoyfulValTimes(chickenDO.getNum());
				
				//插入事件记录
				RangelandEventRecordParam event = new RangelandEventRecordParam();
				event.setChickenNum(chickenDO.getNum());
				event.setAttrTypeEnum(EventRecordAttrTypeEnum.CLEANLINESS);
				event.setDirectionEnum(EventRecordDirectionEnum.ADD);
				event.setFactorEnum(EventRecordFactorEnum.CLEAN);
				event.setMemberNum(param.getMemberNum());
				event.setSourceEnum(EventRecordSourceEnum.OWNER);
			    event.setTitle(EventTitleConstant.TITLE_RANGELAND_HOUSE_CLEANNES_OWNES);
				event.setVal(BigDecimal.valueOf(config.getRangelandMaxCleannessVal()-cleannessVal));
				rangelandEventRecordService.saveRangelandEventRecord(event);
			}
		}

	}


	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public SweepAwardBO friendSweep(FriendSweepParam param) throws WrongOperationException {
		//是否好友判断
		Boolean flag = userRelationService.isFriend(param.getFriendNum(), param.getSweepNum());
		if(!flag){
			throw new WrongOperationException("不是对方好友");
		}
		//获取牧场配置
		RangelandConfigBaseBO config = sysConfigService.getRangelandCacheConfig();
		
		int cleanness = rangelandService.getCleanness(SiteTypeEnum.EXTERNAL, param.getSweepNum());
		if(cleanness == config.getRangelandMaxCleannessVal()){
			throw new WrongOperationException("当前用户无需打扫");
		}
		
		//获取好友对单个用户打扫次数
	/*	int sweepCount = periodBehaviorCtrlService.cleanByFriend(param.getFriendNum(), param.getSweepNum());
		
		if (sweepCount > config.getSweepCount()) {
			throw new WrongOperationException("对单个用户当前只能打扫"+config.getSweepCount()+"次");
		}*/
		
		//修改被打扫用户牧场清洁度
		rangelandService.friendSweepCleanness(param.getSweepNum());
		
		//获取好友信息
		WxUserBO wxUserBO = wxUserService.findByNum(param.getFriendNum());
		
		//添加事件记录
		RangelandEventRecordParam event = new RangelandEventRecordParam();
		event.setAttrTypeEnum(EventRecordAttrTypeEnum.CLEANLINESS);
		event.setDirectionEnum(EventRecordDirectionEnum.ADD);
		event.setFactorEnum(EventRecordFactorEnum.CLEAN);
		event.setMemberNum(param.getSweepNum());
		event.setSourceEnum(EventRecordSourceEnum.FRIENDS);
		event.setTitle(wxUserBO.getNickname().concat(EventTitleConstant.TITLE_RANGELAND_HOUSE_CLEANNES_FRIEND));
		event.setVal(BigDecimal.valueOf(config.getFriendSweepExternalVal()));
		event.setFriendNum(param.getFriendNum());
		rangelandEventRecordService.saveRangelandEventRecord(event);
		
		/*int awardCount = periodBehaviorCtrlService.help(param.getFriendNum());
		SweepAwardBO award = new SweepAwardBO();
		if (awardCount > config.getForageCount()) {
			award.setGiveFlag(false);
			return award;
		}
		
		//仓库新增记录
		InventoryParam inventoryParam = new InventoryParam();
		inventoryParam.setMemberNum(param.getFriendNum());
		inventoryParam.setFriendNum(param.getSweepNum());
		inventoryParam.setProductNum(config.getProductNum());
		inventoryParam.setQuantity(1);
		inventoryParam.setTypeEnum(InventoryDetailTypeEnum.HELP_FRIEND_AWARD); 
		inventoryParam.setDirectionEnum(InventoryDetailDirectionEnum.ADD);
		inventoryService.addInventory(inventoryParam);
		
		ProductBO product = productService.getProductByNum(config.getProductNum());*/
		SweepAwardBO award = new SweepAwardBO();
		award.setGiveFlag(false);
		award.setGiveFoodsCount(1);
		award.setGiveFoodsName("");
		return award;

	}
	

}
