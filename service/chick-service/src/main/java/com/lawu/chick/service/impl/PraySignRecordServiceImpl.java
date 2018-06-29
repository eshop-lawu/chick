package com.lawu.chick.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.lawu.chick.cache.service.PraySignService;
import com.lawu.chick.cache.service.co.PraySignRuleCO;
import com.lawu.chick.repository.domain.PraySignRecordDO;
import com.lawu.chick.repository.domain.PraySignRecordDOExample;
import com.lawu.chick.repository.domain.PraySignRewardsDO;
import com.lawu.chick.repository.domain.PraySignRewardsDOExample;
import com.lawu.chick.repository.domain.ProductDO;
import com.lawu.chick.repository.domain.ProductDOExample;
import com.lawu.chick.repository.mapper.PraySignRecordDOMapper;
import com.lawu.chick.repository.mapper.PraySignRewardsDOMapper;
import com.lawu.chick.repository.mapper.ProductDOMapper;
import com.lawu.chick.service.ChickenService;
import com.lawu.chick.service.InventoryService;
import com.lawu.chick.service.PraySignRecordService;
import com.lawu.chick.service.PraySignRuleService;
import com.lawu.chick.service.bo.AwardBO;
import com.lawu.chick.service.bo.PraySignAwardBO;
import com.lawu.chick.service.enums.InventoryDetailDirectionEnum;
import com.lawu.chick.service.enums.InventoryDetailTypeEnum;
import com.lawu.chick.service.exception.WrongOperationException;
import com.lawu.chick.service.param.InventoryParam;
import com.lawu.chick.service.param.PraySignRuleExtraParam;
import com.lawu.chick.service.util.RandomUtil;

/**
 * @Description
 * @author zhangrc
 * @date 2018年6月15日
 */
@Service
public class PraySignRecordServiceImpl implements PraySignRecordService{

	@Autowired
	private PraySignRecordDOMapper praySignRecordDOMapper;
	
	@Autowired
	private PraySignRewardsDOMapper praySignRewardsDOMapper;
	
	@Autowired
	private ProductDOMapper productDOMapper;
	
	@Autowired
	private InventoryService inventoryService;

	@Autowired
	private PraySignRuleService praySignRuleService;

	@Autowired
	private PraySignService praySignService;

	@Autowired
	private ChickenService chickenService;

	
	@Override
	public PraySignAwardBO praySignRecordInfo(String memberNum) {
		
		PraySignAwardBO awardBO = new PraySignAwardBO();

		PraySignRecordDOExample recordExample = new PraySignRecordDOExample();
		recordExample.createCriteria().andMemberNumEqualTo(memberNum).andSignTimeEqualTo(new Date());
		List<PraySignRecordDO> records = praySignRecordDOMapper.selectByExample(recordExample);
		if (records.isEmpty()) {
			awardBO.setIsSign(false);
			return awardBO;
		}
		PraySignRecordDO praySignRecordDO = records.get(0);
		awardBO.setIsSign(true);
		awardBO.setSignId(praySignRecordDO.getId());
		if (praySignRecordDO.getTakeTime() == null) {
			awardBO.setIsDraw(false);
		} else {
			awardBO.setIsDraw(true);
		}
		awardBO.setAwards(getAwardList(praySignRecordDO.getId(),memberNum));
		return awardBO;
	}

	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void drawReward(String memberNum) throws WrongOperationException{
		PraySignRecordDOExample recordExample = new PraySignRecordDOExample();
		recordExample.createCriteria().andMemberNumEqualTo(memberNum).andSignTimeEqualTo(new Date());
		List<PraySignRecordDO> records = praySignRecordDOMapper.selectByExample(recordExample);
		if (records.isEmpty()) {
			throw new WrongOperationException("当前没有可领取的奖励");
		}
		PraySignRecordDO praySignRecordDO = records.get(0);
		if (praySignRecordDO.getTakeTime() != null) {
			throw new WrongOperationException("已领取过奖励");
		}
		
		PraySignRewardsDOExample rewardsDOExample = new PraySignRewardsDOExample();
		rewardsDOExample.createCriteria().andSignIdEqualTo(praySignRecordDO.getId()).andMemberNumEqualTo(memberNum);
		List<PraySignRewardsDO> rewards = praySignRewardsDOMapper.selectByExample(rewardsDOExample);
		
		for (PraySignRewardsDO praySignRewardsDO : rewards) {
			//仓库新增记录
			InventoryParam inventoryParam = new InventoryParam();
			inventoryParam.setMemberNum(memberNum);
			inventoryParam.setProductNum(praySignRewardsDO.getProductNum());
			inventoryParam.setQuantity(praySignRewardsDO.getProductCount());
			inventoryParam.setTypeEnum(InventoryDetailTypeEnum.SIGN_AWARD); 
			inventoryParam.setDirectionEnum(InventoryDetailDirectionEnum.ADD);
			inventoryService.addInventory(inventoryParam);
		}
		
		PraySignRecordDO record = new PraySignRecordDO();
		record.setId(praySignRecordDO.getId());	
		record.setTakeTime(new Date());
		praySignRecordDOMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public List<AwardBO> addPraySign(String memberNum) {
		PraySignRuleCO ruleCO = praySignService.getSignRuleCache();
		if(ruleCO == null){
			return new ArrayList<>();
		}
		PraySignRecordDO record = new PraySignRecordDO();
		record.setRuleId(ruleCO.getId());
		record.setGmtCreate(new Date());
		record.setSignTime(new Date());
		record.setMemberNum(memberNum);
		praySignRecordDOMapper.insertSelective(record);
		if (!StringUtils.isEmpty(ruleCO.getProductNum())) {
			PraySignRewardsDO rewardsDO = new PraySignRewardsDO();
			if (ruleCO.getBasisChick()) {
				Integer count = chickenService.getChickCount(memberNum);
				rewardsDO.setProductCount(ruleCO.getProductCount() * count);
			} else {
				rewardsDO.setProductCount(ruleCO.getProductCount());
			}
			rewardsDO.setProductNum(ruleCO.getProductNum());
			rewardsDO.setIsExtra(false);
			rewardsDO.setSignId(record.getId());
			rewardsDO.setMemberNum(memberNum);
			rewardsDO.setGmtCreate(new Date());
			praySignRewardsDOMapper.insertSelective(rewardsDO);
		}
		//新增奖励记录--加入缓存记录连续签到天数
		Integer day = praySignService.addPraySignCache(memberNum, ruleCO.getDay());
		if (ruleCO.getDay().equals(day) && StringUtils.isNotEmpty(ruleCO.getExtra())) {
			//连续签到奖励
			List<Double> rates = new ArrayList<>();
			List<PraySignRuleExtraParam> extras = new ArrayList<>();
			if (StringUtils.isNotEmpty(ruleCO.getExtra())) {
				extras = JSONArray.parseArray(ruleCO.getExtra(), PraySignRuleExtraParam.class);
			}
			for (PraySignRuleExtraParam param : extras) {
				rates.add(param.getRate() * 0.01);
			}
			PraySignRewardsDO extraReward = new PraySignRewardsDO();
			int rewardRandom = RandomUtil.getRandomRate(rates);
			extraReward.setProductCount(extras.get(rewardRandom).getProductCount());
			extraReward.setProductNum(extras.get(rewardRandom).getProductNum());
			extraReward.setIsExtra(true);
			extraReward.setSignId(record.getId());
			extraReward.setMemberNum(memberNum);
			extraReward.setGmtCreate(new Date());
			praySignRewardsDOMapper.insertSelective(extraReward);
		}

		return getAwardList(record.getId(),memberNum);
	}

	@Override
	public Boolean getRecordByMemberNum(String memberNum) {
		PraySignRecordDOExample example = new PraySignRecordDOExample();
		example.createCriteria().andMemberNumEqualTo(memberNum).andSignTimeEqualTo(new Date());
		List<PraySignRecordDO> recordDOS = praySignRecordDOMapper.selectByExample(example);
		if (recordDOS.isEmpty()) {
			return false;
		}
		return true;
	}


	private List<AwardBO> getAwardList(Long signRecordId, String num) {
		PraySignRewardsDOExample rewardsDOExample = new PraySignRewardsDOExample();
		rewardsDOExample.createCriteria().andSignIdEqualTo(signRecordId).andMemberNumEqualTo(num);
		List<PraySignRewardsDO> rewards = praySignRewardsDOMapper.selectByExample(rewardsDOExample);
		List<AwardBO> awards = new ArrayList<>();
		for (PraySignRewardsDO praySignRewardsDO : rewards) {
			ProductDOExample productDOExample = new ProductDOExample();
			productDOExample.createCriteria().andNumEqualTo(praySignRewardsDO.getProductNum());
			List<ProductDO> list = productDOMapper.selectByExample(productDOExample);
			ProductDO productDO = list.get(0);
			AwardBO award = new AwardBO();
			award.setName(productDO.getName());
			award.setIsExtra(praySignRewardsDO.getIsExtra());
			award.setImgPath(productDO.getImgPath());
			award.setAwardCount(praySignRewardsDO.getProductCount());
			awards.add(award);
		}
		return awards;

	}


}
