package com.lawu.chick.service.converter;

import com.lawu.chick.cache.service.co.EggConfigCache;
import com.lawu.chick.service.bo.EggConfigBaseBO;

/**
 * @author meishuquan
 * @date 2018/4/25.
 */
public class EggConfigConverter {

    public static EggConfigCache convertEggConfig(EggConfigBaseBO configBaseBO) {
        if (configBaseBO == null) {
            return null;
        }

        EggConfigCache eggConfig = new EggConfigCache();
        eggConfig.setExchangeRedpacketEggs(configBaseBO.getExchangeRedpacketEggs());
        eggConfig.setMaxHouseEggs(configBaseBO.getMaxHouseEggs());
        eggConfig.setMaxLayEggs(configBaseBO.getMaxLayEggs());
        eggConfig.setLayEggsTotal(configBaseBO.getLayEggsTotal());
        eggConfig.setMinGuaranteedValue(configBaseBO.getMinGuaranteedValue());
        eggConfig.setMaxGuaranteedValue(configBaseBO.getMaxGuaranteedValue());
        return eggConfig;
    }

    public static EggConfigBaseBO convertEggConfigBaseBO(EggConfigCache eggConfig) {
        if (eggConfig == null) {
            return null;
        }

        EggConfigBaseBO configBaseBO = new EggConfigBaseBO();
        configBaseBO.setExchangeRedpacketEggs(eggConfig.getExchangeRedpacketEggs());
        configBaseBO.setMaxHouseEggs(eggConfig.getMaxHouseEggs());
        configBaseBO.setMaxLayEggs(eggConfig.getMaxLayEggs());
        configBaseBO.setLayEggsTotal(eggConfig.getLayEggsTotal());
        return configBaseBO;
    }

}
