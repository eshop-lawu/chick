package com.lawu.chick.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.chick.cache.service.EggConfigCacheService;
import com.lawu.chick.cache.service.co.EggConfigCache;
import com.lawu.chick.repository.param.ChickUpdateInfoParam;
import com.lawu.chick.service.ChickenService;
import com.lawu.chick.service.InventoryService;
import com.lawu.chick.service.MemberService;
import com.lawu.chick.service.PeriodBehaviorCtrlService;
import com.lawu.chick.service.RangelandChickService;
import com.lawu.chick.service.RangelandEventRecordService;
import com.lawu.chick.service.RangelandService;
import com.lawu.chick.service.SysConfigService;
import com.lawu.chick.service.bo.ChickBaseConfigBO;
import com.lawu.chick.service.bo.ChickHouseInfoBO;
import com.lawu.chick.service.bo.ChickenBaseInfoBO;
import com.lawu.chick.service.bo.InventoryEggBO;
import com.lawu.chick.service.bo.InventoryInfoBO;
import com.lawu.chick.service.bo.RangelandInfoBO;
import com.lawu.chick.service.constants.EventTitleConstant;
import com.lawu.chick.service.enums.ChickStatusEnum;
import com.lawu.chick.service.enums.ChickenAttrEnum;
import com.lawu.chick.service.enums.EventRecordAttrTypeEnum;
import com.lawu.chick.service.enums.EventRecordDirectionEnum;
import com.lawu.chick.service.enums.EventRecordFactorEnum;
import com.lawu.chick.service.enums.EventRecordSourceEnum;
import com.lawu.chick.service.enums.InventoryDetailDirectionEnum;
import com.lawu.chick.service.enums.InventoryDetailTypeEnum;
import com.lawu.chick.service.enums.PeriodTypeEnum;
import com.lawu.chick.service.param.InventoryParam;
import com.lawu.chick.service.param.RangelandEventRecordParam;
import com.lawu.chick.service.query.FriendFeedQueryParam;
import com.lawu.chick.service.query.OwnerFeedQueryParam;
import com.lawu.utils.DateUtil;

/**  
 * 牧场小鸡service
 * @author lihj
 * @date 2018年4月26日
 */
@Service
public class RangelandChickServiceImpl implements RangelandChickService{
	
	@Autowired
	private RangelandService rangelandService;
	@Autowired
	private ChickenService chickenService;
	@Autowired
	private SysConfigService sysConfigService;
	@Autowired
	private InventoryService inventoryService;
	@Autowired
	private PeriodBehaviorCtrlService periodBehaviorCtrlService;
	@Autowired
	private RangelandEventRecordService rangelandEventRecordService;

	@Autowired
	private EggConfigCacheService eggConfigCacheService;
	
	@Autowired
	private MemberService memberService;
	
	@Override
	public RangelandInfoBO getMyRangelandInfo(String memberNum) {
		RangelandInfoBO bo = rangelandService.getMyRangelandInfo(memberNum);
		if(null ==bo){
			return bo;
		}
		List<ChickenBaseInfoBO> list = chickenService.getChickenListByMemberNum(memberNum);
		InventoryEggBO eggBO = memberService.getEggs(memberNum);
		bo.setChickInfo(list);
		bo.setTotalEggs(eggBO.getEggs());
		return bo;
	}

	@Override
	public ChickHouseInfoBO getMyChickHouseInfo(String memberNum) {
		RangelandInfoBO bo = rangelandService.getMyRangelandInfo(memberNum);
		List<ChickenBaseInfoBO> list = chickenService.getChickenListByMemberNum(memberNum);
		EggConfigCache configCache = eggConfigCacheService.getCacheEggConfig();
		ChickHouseInfoBO house =new ChickHouseInfoBO();
		house.setChickInfo(list);
		house.setExternalCleanness(bo.getExternalCleanness());
		house.setHouseCleanness(bo.getHouseCleanness());
		house.setLayEggs(bo.getTotalEggs());
		house.setType(bo.getType());
		house.setMaxHouseEggs(configCache.getMaxHouseEggs());
		return house;
	}

	/**
	 *  //查询当前小鸡饱腹值
	 *	//查询当天主人喂养次数
	 *	//查询当天好友喂养次数
	 *	//查询当天小鸡成长值累计
	 *	//查询小鸡当前成长值
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public ChickenBaseInfoBO ownerFeed(OwnerFeedQueryParam queryParam) {
		int addGrowth=0;
		int addjoyfull =0;
		int addfullval=0;
		int addcleanval=0;
		InventoryInfoBO invent = inventoryService.getInventoryFeedInfoById(queryParam.getInventoryId());
		//增加愉悦度
		if(invent.getJoyfulVal()>0){
			int joyfull = periodBehaviorCtrlService.useProduct(queryParam.getNum(), ChickenAttrEnum.JOYFULL, invent.getProductNum());
			if(invent.getJoyfulLimit()!=0){//有次数限制
				if(joyfull<=invent.getJoyfulLimit()){
					addjoyfull=invent.getJoyfulVal();
				}
			}else{//没有次数限制
				addjoyfull=invent.getJoyfulVal();
			}
			if(addjoyfull>0){
				initOwnerEvent(queryParam,EventRecordAttrTypeEnum.JOYFULL,addjoyfull,EventTitleConstant.TITLE_OWNER_FEED);
			}
		}
		//增加饱腹值
		if(invent.getFullVal()>0){
			int fullval = periodBehaviorCtrlService.useProduct(queryParam.getNum(), ChickenAttrEnum.FULL, invent.getProductNum());
			if(invent.getFullLimit()!=0){
				if(fullval<=invent.getFullLimit()){
					addfullval=invent.getFullVal();
				}
			}else{
				addfullval=invent.getFullVal();
			}
			if(addfullval>0){
				initOwnerEvent(queryParam,EventRecordAttrTypeEnum.FULL,addfullval,EventTitleConstant.TITLE_OWNER_FEED);
			}
		}
		//增加成长值
		if(invent.getGrowthVal()>0){
			int growval = periodBehaviorCtrlService.useProduct(queryParam.getNum(), ChickenAttrEnum.GROWTH, invent.getProductNum());
			if(invent.getGrowthLimit()!=0){
				if(growval<=invent.getGrowthLimit()){
					addGrowth=invent.getGrowthVal();
				}
			}else{
				addGrowth=invent.getGrowthVal();
			}
			if(addGrowth>0){
				initOwnerEvent(queryParam,EventRecordAttrTypeEnum.GROWTH,addGrowth,EventTitleConstant.TITLE_OWNER_FEED);
			}
		}
		//清洁度
		if(invent.getKeepCleanTime()>0){
			int cleanval = periodBehaviorCtrlService.useProduct(queryParam.getNum(), ChickenAttrEnum.CLEANLINESS, invent.getProductNum());
			if(invent.getCleanLimit()!=0){
				if(cleanval<=invent.getCleanLimit()){
					addcleanval=invent.getKeepCleanTime();
				}
			}else{
				addcleanval=invent.getKeepCleanTime();
			}
			if(addcleanval>0){
				String title =EventTitleConstant.TITLE_USE_PROP;
				title=title.replace("{1}", String.valueOf(addcleanval));
				initOwnerEvent(queryParam,EventRecordAttrTypeEnum.CLEANLINESS,addcleanval,title);
			}
		}
		ChickBaseConfigBO cacheConfig = sysConfigService.getCacheChickBaseInfo();
		ChickUpdateInfoParam feedUpdate = initChickUpdateParam(queryParam.getNum(),queryParam.getMemberNum(), addjoyfull, addfullval, addGrowth,addcleanval, cacheConfig);
		chickenService.feedChickUpdateChickInfo(feedUpdate);
		//减库存
		InventoryParam inventoryParam = new InventoryParam();
		inventoryParam.setInventoryId(queryParam.getInventoryId());
		inventoryParam.setQuantity(1);
		inventoryParam.setTypeEnum(InventoryDetailTypeEnum.OWNER_WEAR);
		inventoryParam.setDirectionEnum(InventoryDetailDirectionEnum.REDUCE);
		inventoryService.reduceFeedInfo(inventoryParam);
		return chickenService.getChickenByChickenNum(queryParam.getMemberNum(), queryParam.getNum());
	}

	@Override
	public ChickenBaseInfoBO friendFeed(FriendFeedQueryParam queryParam) {
		int addGrowth=0;
		int addjoyfull =0;
		int addfullval=0;
		int addcleanval=0;
		InventoryInfoBO invent = inventoryService.getInventoryFeedInfoById(queryParam.getInventoryId());
		if(invent.getJoyfulVal()>0){
			int joyfull = periodBehaviorCtrlService.useProduct(queryParam.getNum(), ChickenAttrEnum.JOYFULL, invent.getProductNum());
			if(invent.getJoyfulLimit() != 0){
				if(joyfull<=invent.getJoyfulLimit()){
					addjoyfull=invent.getJoyfulVal();
				}
			}else{
				addjoyfull=invent.getJoyfulVal();
			}
			if(addjoyfull>0){
				initFriendEvent(queryParam,EventRecordAttrTypeEnum.JOYFULL,addjoyfull,EventTitleConstant.TITLE_FRIEND_FEED);
			}
		}
		if(invent.getFullVal()>0){
			int fullval = periodBehaviorCtrlService.useProduct(queryParam.getNum(), ChickenAttrEnum.FULL, invent.getProductNum());
			if(invent.getFullLimit() !=0){
				if(fullval<=invent.getFullLimit()){
					addfullval=invent.getFullVal();
				}
			}else{
				addfullval=invent.getFullVal();
			}
			if(addfullval>0){
				initFriendEvent(queryParam,EventRecordAttrTypeEnum.FULL,addfullval,EventTitleConstant.TITLE_FRIEND_FEED);
			}
		}
		if(invent.getGrowthVal()>0){
			int growval = periodBehaviorCtrlService.useProduct(queryParam.getNum(), ChickenAttrEnum.GROWTH, invent.getProductNum());
			if(invent.getGrowthLimit()!=0){
				if(growval<=invent.getGrowthLimit()){
					addGrowth=invent.getGrowthVal();
				}
			}else{
				addGrowth=invent.getGrowthVal();
			}
			if(addGrowth>0){
				initFriendEvent(queryParam,EventRecordAttrTypeEnum.GROWTH,addGrowth,EventTitleConstant.TITLE_FRIEND_FEED);
			}
			
		}
		if(invent.getKeepCleanTime()>0){
			int cleanval = periodBehaviorCtrlService.useProduct(queryParam.getNum(), ChickenAttrEnum.CLEANLINESS, invent.getProductNum());
			if(invent.getCleanLimit()!=0){
				if(cleanval<=invent.getCleanLimit()){
					addcleanval=invent.getKeepCleanTime();
				}
			}else{
				addcleanval=invent.getKeepCleanTime();
			}
			if(addcleanval>0){
				String title =EventTitleConstant.TITLE_FRIEND_USE_PROP;
				title=title.replace("{1}", String.valueOf(addcleanval));
				initFriendEvent(queryParam,EventRecordAttrTypeEnum.GROWTH,addcleanval,title);
			}
		}
		
		ChickBaseConfigBO cacheConfig = sysConfigService.getCacheChickBaseInfo();
		ChickUpdateInfoParam feedUpdate = initChickUpdateParam(queryParam.getNum(),queryParam.getFriendNum(), addjoyfull, addfullval, addGrowth, addcleanval,cacheConfig);
		chickenService.feedChickUpdateChickInfo(feedUpdate);
		//减库存
		InventoryParam inventoryParam = new InventoryParam();
		inventoryParam.setInventoryId(queryParam.getInventoryId());
		inventoryParam.setMemberNum(queryParam.getMemberNum());
		inventoryParam.setFriendNum(queryParam.getFriendNum());
		inventoryParam.setQuantity(1);
		inventoryParam.setTypeEnum(InventoryDetailTypeEnum.HELP_FRIEND_WEAR);
		inventoryParam.setDirectionEnum(InventoryDetailDirectionEnum.REDUCE);
		inventoryService.reduceFeedInfo(inventoryParam);
		ChickenBaseInfoBO chickenBaseInfoBO = chickenService.getChickenByChickenNum(queryParam.getFriendNum(), queryParam.getNum());
		chickenBaseInfoBO.setGiveFlag(false);
		chickenBaseInfoBO.setGiveFoodsCount(1);
		chickenBaseInfoBO.setGiveFoodsName("");
		return chickenBaseInfoBO;
	}

	private ChickUpdateInfoParam initChickUpdateParam(String num,String memberNum, int joyfull, int fullval, int growval,
			int addcleanval, ChickBaseConfigBO cacheConfig) {
		ChickUpdateInfoParam feedUpdate =new ChickUpdateInfoParam();
		feedUpdate.setNum(num);
		feedUpdate.setMemberNum(memberNum);
		feedUpdate.setJoyfulVal(joyfull);
		feedUpdate.setFullVal(fullval);
		feedUpdate.setFullMaxVal(cacheConfig.getChickMaxFullVal());
		feedUpdate.setGrowthVal(growval);
		feedUpdate.setHalfGrowVal(cacheConfig.getChickSemiMatureVal());
		feedUpdate.setGrowthMaxVal(cacheConfig.getChickMaxGrowthVal());
		feedUpdate.setHalfGrowth(PeriodTypeEnum.HALF_GROWTH.getVal().intValue());
		feedUpdate.setMature(PeriodTypeEnum.MATURE.getVal().intValue());
		feedUpdate.setSdate(DateUtil.getDateFormat(new Date(),"yyyy-MM-dd").concat(" ").concat(cacheConfig.getChickStartActivitiesTime()).concat(":00"));
		feedUpdate.setEdate(DateUtil.getDateFormat(new Date(),"yyyy-MM-dd").concat(" ").concat(cacheConfig.getChickEndActivitiesTime()).concat(":00"));
		feedUpdate.setStatusActive(ChickStatusEnum.ACTIVITY.getVal().intValue());
		feedUpdate.setStatusSleep(ChickStatusEnum.SLEEP.getVal().intValue());
		feedUpdate.setKeepCleanTime(addcleanval);
		return feedUpdate;
	}

	private void initFriendEvent(FriendFeedQueryParam queryParam,EventRecordAttrTypeEnum typeEnum,int val,String title) {
		//记录事件
		RangelandEventRecordParam event = new RangelandEventRecordParam();
		event.setAttrTypeEnum(typeEnum);
		event.setDirectionEnum(EventRecordDirectionEnum.ADD);
		event.setFactorEnum(EventRecordFactorEnum.FEED);
		event.setMemberNum(queryParam.getMemberNum());
		event.setFriendNum(queryParam.getFriendNum());
		event.setChickenNum(queryParam.getNum());
		event.setSourceEnum(EventRecordSourceEnum.FRIENDS);
		event.setTitle(EventTitleConstant.TITLE_FRIEND_FEED);
		event.setVal(BigDecimal.valueOf(val));
		rangelandEventRecordService.saveRangelandEventRecord(event);
	}

	private void initOwnerEvent(OwnerFeedQueryParam queryParam,EventRecordAttrTypeEnum typeEnum,int val,String title) {
		//记录事件
		RangelandEventRecordParam event = new RangelandEventRecordParam();
		event.setAttrTypeEnum(typeEnum);
		event.setDirectionEnum(EventRecordDirectionEnum.ADD);
		event.setFactorEnum(EventRecordFactorEnum.FEED);
		event.setMemberNum(queryParam.getMemberNum());
		event.setChickenNum(queryParam.getNum());
		event.setSourceEnum(EventRecordSourceEnum.OWNER);
		event.setTitle(title);
		event.setVal(BigDecimal.valueOf(val));
		rangelandEventRecordService.saveRangelandEventRecord(event);
	}

}
