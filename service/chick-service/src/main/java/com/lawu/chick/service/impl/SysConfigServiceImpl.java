package com.lawu.chick.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lawu.chick.cache.service.ChickBaseConfigCacheService;
import com.lawu.chick.cache.service.EggConfigCacheService;
import com.lawu.chick.cache.service.RangelandConfigCacheService;
import com.lawu.chick.cache.service.co.ChickBaseConfigCO;
import com.lawu.chick.cache.service.co.EggConfigCache;
import com.lawu.chick.cache.service.co.RangelandConfigBaseCO;
import com.lawu.chick.repository.domain.SysConfigDO;
import com.lawu.chick.repository.domain.SysConfigDOExample;
import com.lawu.chick.repository.mapper.SysConfigDOMapper;
import com.lawu.chick.service.SysConfigService;
import com.lawu.chick.service.bo.ChickBaseConfigBO;
import com.lawu.chick.service.bo.EggConfigBO;
import com.lawu.chick.service.bo.EggConfigBaseBO;
import com.lawu.chick.service.bo.EggExchangeRedpacketBO;
import com.lawu.chick.service.bo.HouseCleannessBO;
import com.lawu.chick.service.bo.RangelandConfigBO;
import com.lawu.chick.service.bo.RangelandConfigBaseBO;
import com.lawu.chick.service.converter.ChickBaseConfigConverter;
import com.lawu.chick.service.converter.EggConfigConverter;
import com.lawu.chick.service.param.ChickBaseConfigParam;
import com.lawu.chick.service.param.EggConfigParam;
import com.lawu.chick.service.param.RangelandConfigParam;
import com.lawu.utils.DateUtil;

/**
 * @author meishuquan
 * @date 2018/4/25.
 */
@Service
public class SysConfigServiceImpl implements SysConfigService {

    @Autowired
    private ChickBaseConfigCacheService chickBaseConfigCacheService;
    @Autowired
    private EggConfigCacheService eggConfigCacheService;
    @Autowired
    private SysConfigDOMapper sysConfigDOMapper;

    @Autowired
    private RangelandConfigCacheService rangelandConfigCacheService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveEggConfig(EggConfigParam param) {
        List<EggExchangeRedpacketBO> redpacketBOS = new ArrayList<>();
        String[] minPriceArr = param.getMinPriceGroup().split(",");
        String[] maxPriceArr = param.getMaxPriceGroup().split(",");
        String[] priceRateArr = param.getPriceRateGroup().split(",");
        for (int i = 0; i < minPriceArr.length; i++) {
            EggExchangeRedpacketBO redpacketBO = new EggExchangeRedpacketBO();
            redpacketBO.setId(i + 1);
            redpacketBO.setMinPrice(new BigDecimal(minPriceArr[i]));
            redpacketBO.setMaxPrice(new BigDecimal(maxPriceArr[i]));
            redpacketBO.setPriceRate(new BigDecimal(priceRateArr[i]));
            redpacketBOS.add(redpacketBO);
        }

        EggConfigBaseBO configBaseBO = new EggConfigBaseBO();
        configBaseBO.setExchangeRedpacketEggs(param.getExchangeRedpacketEggs());
        configBaseBO.setMaxHouseEggs(param.getMaxHouseEggs());
        configBaseBO.setMaxLayEggs(param.getMaxLayEggs());
        configBaseBO.setLayEggsTotal(param.getLayEggsTotal());
        configBaseBO.setWxSendName(param.getWxSendName());
        configBaseBO.setWxWishing(param.getWxWishing());
        configBaseBO.setWxActName(param.getWxActName());
        configBaseBO.setWxRemark(param.getWxRemark());
        configBaseBO.setMinGuaranteedValue(param.getMinGuaranteedValue());
        configBaseBO.setMaxGuaranteedValue(param.getMaxGuaranteedValue());
        configBaseBO.setRedpacketBOS(redpacketBOS);
        String eggConfig = JSON.toJSONString(configBaseBO);

        SysConfigDO configDO = new SysConfigDO();
        configDO.setEggConfig(eggConfig);
        configDO.setEggEffectiveTime(param.getEggEffectiveTime());
        configDO.setGmtModified(new Date());
        long count = sysConfigDOMapper.countByExample(null);
        if (count == 0) {
            configDO.setGmtCreate(new Date());
            sysConfigDOMapper.insertSelective(configDO);
        } else {
            SysConfigDOExample example = new SysConfigDOExample();
            example.createCriteria();
            sysConfigDOMapper.updateByExampleSelective(configDO, example);
        }
        if(param.getImmediatelyCache()==1){
        	  EggConfigCache configCache = EggConfigConverter.convertEggConfig(configBaseBO);
              eggConfigCacheService.saveCacheEggConfig(configCache);
        }
    }

    @Override
    public EggConfigBO getEggConfig() {
        List<SysConfigDO> configDOS = sysConfigDOMapper.selectByExampleWithBLOBs(null);
        if (configDOS.isEmpty() || StringUtils.isEmpty(configDOS.get(0).getEggConfig())) {
            return null;
        }

        EggConfigBO configBO = new EggConfigBO();
        EggConfigBaseBO configBaseBO = JSONObject.parseObject(configDOS.get(0).getEggConfig(), EggConfigBaseBO.class);
        configBO.setExchangeRedpacketEggs(configBaseBO.getExchangeRedpacketEggs());
        configBO.setMaxHouseEggs(configBaseBO.getMaxHouseEggs());
        configBO.setMaxLayEggs(configBaseBO.getMaxLayEggs());
        configBO.setLayEggsTotal(configBaseBO.getLayEggsTotal());
        configBO.setEggEffectiveTime(configDOS.get(0).getEggEffectiveTime());
        configBO.setWxSendName(configBaseBO.getWxSendName());
        configBO.setWxWishing(configBaseBO.getWxWishing());
        configBO.setWxActName(configBaseBO.getWxActName());
        configBO.setWxRemark(configBaseBO.getWxRemark());
        configBO.setMinGuaranteedValue(configBaseBO.getMinGuaranteedValue());
        configBO.setMaxGuaranteedValue(configBaseBO.getMaxGuaranteedValue());
        configBO.setRedpacketBOS(configBaseBO.getRedpacketBOS());
        return configBO;
    }

    @Override
	public void addChickBaseConfigInfo(ChickBaseConfigParam param) {
    	List<String> lst = new ArrayList<>();
    	List<String> lt = param.getChickEggProdTime();
        for(String str :lt){
        	str=str.concat(":00");
        	lst.add(str);
        }
        param.setChickEggProdTime(lst);
    	SysConfigDOExample example =new SysConfigDOExample();
		example.createCriteria();
		List<SysConfigDO> list = sysConfigDOMapper.selectByExampleWithBLOBs(example);
		long count = sysConfigDOMapper.countByExample(example);
		if(count ==0){
			SysConfigDO sys = new SysConfigDO();
			sys.setChickenConfig(JSON.toJSONString(param));
			sys.setChickenEffectiveTime(DateUtil.getDateTimeFormat(param.getSettingStartTime()));
			sys.setGmtModified(new Date());
			sys.setGmtCreate(new Date());
			sysConfigDOMapper.insertSelective(sys);
		}else{
			SysConfigDO sys = list.get(0);
			sys.setChickenConfig(JSON.toJSONString(param));
			sys.setChickenEffectiveTime(DateUtil.getDateTimeFormat(param.getSettingStartTime()));
			sys.setGmtModified(new Date());
			sysConfigDOMapper.updateByPrimaryKeySelective(sys);
		}
		if(param.getImmediatelyCache()==1){
			ChickBaseConfigCO co =ChickBaseConfigConverter.convertChickBaseConfigCOParam(param);
			chickBaseConfigCacheService.saveCacheChickBaseInfo(co);
		}
	}

	@Override
	public ChickBaseConfigBO getChickBaseConfigInfo() {
		SysConfigDOExample example =new SysConfigDOExample();
		example.createCriteria();
		List<SysConfigDO> list = sysConfigDOMapper.selectByExampleWithBLOBs(example);
		return ChickBaseConfigConverter.convertSysConfigDO(list);
	}

	@Override
	public void executeJob() {
		SysConfigDOExample example =new SysConfigDOExample();
		example.createCriteria();
		int deviationVal=2000*60;
		List<SysConfigDO> list = sysConfigDOMapper.selectByExampleWithBLOBs(example);
		if(list.size()>0){
			SysConfigDO config =list.get(0);
			if(null!=config.getChickenEffectiveTime() && config.getChickenEffectiveTime().getTime()<= System.currentTimeMillis() && System.currentTimeMillis()<= config.getChickenEffectiveTime().getTime()+deviationVal){
				ChickBaseConfigBO bo = JSON.parseObject(config.getChickenConfig(),ChickBaseConfigBO.class);
				ChickBaseConfigCO co =ChickBaseConfigConverter.convertChickBaseConfigCO(bo);
				chickBaseConfigCacheService.saveCacheChickBaseInfo(co);
			}
			if(null!=config.getEggEffectiveTime() && config.getEggEffectiveTime().getTime()<= System.currentTimeMillis() && System.currentTimeMillis() <=config.getEggEffectiveTime().getTime()+deviationVal){
                EggConfigBaseBO configBaseBO = JSONObject.parseObject(config.getEggConfig(), EggConfigBaseBO.class);
                EggConfigCache configCache = EggConfigConverter.convertEggConfig(configBaseBO);
                eggConfigCacheService.saveCacheEggConfig(configCache);
            }
			if(null!=config.getRangelandEffectiveTime() && config.getRangelandEffectiveTime().getTime()<= System.currentTimeMillis() && System.currentTimeMillis()<=config.getRangelandEffectiveTime().getTime()+deviationVal ){
                RangelandConfigBaseBO configBaseBO = JSONObject.parseObject(config.getRangelandConfig(), RangelandConfigBaseBO.class);
                RangelandConfigBaseCO co = ChickBaseConfigConverter.convertCacheChickBaseConfigCO(configBaseBO);
                rangelandConfigCacheService.saveCacheRangelandConfig(co);
            }
		}
	}

	@Override
	public ChickBaseConfigBO getCacheChickBaseInfo() {
		ChickBaseConfigCO co = chickBaseConfigCacheService.getCacheChickBaseInfo();
		return ChickBaseConfigConverter.convertCacheChickBaseConfigBO(co);
	}


	@Override
	public void saveRangelandConfig(RangelandConfigParam param) {
        List<HouseCleannessBO> houseCleannessBOS = new ArrayList<>();
        String[] houseCleannessArr = param.getHouseCleannessGroup().split(",");
        String[] attenuationJoyfulValArr = param.getAttenuationJoyfulValGroup().split(",");
        String[] attenuationTimesArr = param.getAttenuationTimesGroup().split(",");
        for (int i = 0; i < houseCleannessArr.length; i++) {
            HouseCleannessBO houseCleannessBO = new HouseCleannessBO();
            houseCleannessBO.setHouseCleanness(Integer.valueOf(houseCleannessArr[i]));
            houseCleannessBO.setAttenuationJoyfulVal(Integer.valueOf(attenuationJoyfulValArr[i]));
            houseCleannessBO.setAttenuationTimes(Integer.valueOf(attenuationTimesArr[i]));
            houseCleannessBOS.add(houseCleannessBO);
        }

		RangelandConfigBaseBO configBaseBO = new RangelandConfigBaseBO();
		configBaseBO.setChickDeclineExternalVal(param.getChickDeclineExternalVal());
		configBaseBO.setChickDeclineExternalValMinute(param.getChickDeclineExternalValMinute());
		configBaseBO.setChickDeclineHouseVal(param.getChickDeclineHouseVal());
		configBaseBO.setChickDeclineHouseValMinute(param.getChickDeclineHouseValMinute());
		configBaseBO.setFriendSweepExternalVal(param.getFriendSweepExternalVal());
		configBaseBO.setRangelandMaxCleannessVal(param.getRangelandMaxCleannessVal());
        configBaseBO.setHouseCleannessBOS(houseCleannessBOS);
		String rangelandConfig = JSON.toJSONString(configBaseBO);

		SysConfigDO configDO = new SysConfigDO();
		configDO.setRangelandConfig(rangelandConfig);
		configDO.setRangelandEffectiveTime(param.getRangelandEffectiveTime());
		configDO.setGmtModified(new Date());
		long count = sysConfigDOMapper.countByExample(null);
		if (count == 0) {
			configDO.setGmtCreate(new Date());
			sysConfigDOMapper.insertSelective(configDO);
		} else {
			SysConfigDOExample example = new SysConfigDOExample();
			example.createCriteria();
			sysConfigDOMapper.updateByExampleSelective(configDO, example);
		}
		if(param.getImmediatelyCache()==1){
			RangelandConfigBaseCO co = ChickBaseConfigConverter.convertCacheChickBaseConfigCO(configBaseBO);
			 rangelandConfigCacheService.saveCacheRangelandConfig(co);
		}

	}


	@Override
    public RangelandConfigBO getRangelandConfig() {
        List<SysConfigDO> configDOS = sysConfigDOMapper.selectByExampleWithBLOBs(null);
        if (configDOS.isEmpty() || StringUtils.isEmpty(configDOS.get(0).getRangelandConfig())) {
            return null;
        }
        RangelandConfigBO configBO = new RangelandConfigBO();
        RangelandConfigBaseBO configBaseBO = JSONObject.parseObject(configDOS.get(0).getRangelandConfig(), RangelandConfigBaseBO.class);
        configBO.setChickDeclineExternalVal(configBaseBO.getChickDeclineExternalVal());
        configBO.setChickDeclineExternalValMinute(configBaseBO.getChickDeclineExternalValMinute());
        configBO.setChickDeclineHouseVal(configBaseBO.getChickDeclineHouseVal());
        configBO.setChickDeclineHouseValMinute(configBaseBO.getChickDeclineHouseValMinute());
        configBO.setRangelandEffectiveTime(configDOS.get(0).getRangelandEffectiveTime());
        configBO.setFriendSweepExternalVal(configBaseBO.getFriendSweepExternalVal());
        configBO.setRangelandMaxCleannessVal(configBaseBO.getRangelandMaxCleannessVal());
        configBO.setHouseCleannessBOS(configBaseBO.getHouseCleannessBOS());
        return configBO;
    }


	@Override
	public RangelandConfigBaseBO getRangelandCacheConfig() {
		RangelandConfigBaseCO co = rangelandConfigCacheService.getCacheRangelandConfig();
		RangelandConfigBaseBO bo = ChickBaseConfigConverter.convertCacheChickBaseConfigBO(co);
		return bo;
	}




}
