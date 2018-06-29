package com.lawu.chick.service.converter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.lawu.chick.cache.service.co.ChickenCacheOperateCO;
import com.lawu.chick.repository.domain.ChickenDO;
import com.lawu.chick.repository.domain.extend.ChickDOView;
import com.lawu.chick.service.bo.ChickenBaseInfoBO;
import com.lawu.chick.service.bo.ChickenGrowthBO;
import com.lawu.chick.service.enums.ChickStatusEnum;
import com.lawu.chick.service.enums.PeriodTypeEnum;
import com.lawu.chick.service.bo.ChickenManagePageBO;
import com.lawu.chick.service.enums.ChickStatusEnum;
import com.lawu.chick.service.enums.PeriodTypeEnum;

public class ChickenConverter {

    public static List<ChickenBaseInfoBO> converterChickenBaseInfoBOList(List<ChickenDO> chickenDOList) {
        if (chickenDOList == null) {
            return null;
        }
        List<ChickenBaseInfoBO> chickenBaseInfoBOList = new ArrayList<>();
        for (ChickenDO chickenDO : chickenDOList) {
            chickenBaseInfoBOList.add(convertChickenBaseInfoBO(chickenDO));
        }
        return chickenBaseInfoBOList;
    }

    public static List<ChickenGrowthBO> convertChickenGrowthBO(List<ChickenDO> list) {
        List<ChickenGrowthBO> lt = new ArrayList<ChickenGrowthBO>();
        for (ChickenDO chick : list) {
            ChickenGrowthBO grow = new ChickenGrowthBO();
            grow.setId(chick.getId());
            grow.setMemberNum(chick.getMemberNum());
            grow.setNum(chick.getNum());
            lt.add(grow);
        }
        return lt;
    }

    public static List<ChickenCacheOperateCO> converterChickenCacheCOList(List<ChickDOView> chickDOViewList) {
        List<ChickenCacheOperateCO> chickenCacheOperateCOList = new ArrayList<>();
        for (ChickDOView chickDOView : chickDOViewList) {
            chickenCacheOperateCOList.add(converterChickenCacheCO(chickDOView));
        }
        return chickenCacheOperateCOList;
    }

    private static ChickenCacheOperateCO converterChickenCacheCO(ChickDOView chickDOView) {
        ChickenCacheOperateCO chickenCacheOperateCO = new ChickenCacheOperateCO();
        chickenCacheOperateCO.setMemberNum(chickDOView.getMemberNum());
        chickenCacheOperateCO.setChickenStatus(chickDOView.getChickenStatus());
        chickenCacheOperateCO.setChickNum(chickDOView.getChickNum());
        chickenCacheOperateCO.setExternalCleanness(chickDOView.getExternalCleanness());
        chickenCacheOperateCO.setHouseCleanness(chickDOView.getHouseCleanness());
        chickenCacheOperateCO.setOutside(chickDOView.isOutside());
        chickenCacheOperateCO.setVal(chickDOView.getVal());
        chickenCacheOperateCO.setDate(new Date());
        chickenCacheOperateCO.setTimedMins(chickDOView.getTimedMins());
        return chickenCacheOperateCO;
    }

    public static ChickenBaseInfoBO convertChickenBaseInfoBO(ChickenDO chickenDO) {
        if (chickenDO == null) {
            return null;
        }
        ChickenBaseInfoBO chickenBaseInfoBO = new ChickenBaseInfoBO();
        chickenBaseInfoBO.setId(chickenDO.getId());
        chickenBaseInfoBO.setName(chickenDO.getName());
        chickenBaseInfoBO.setNum(chickenDO.getNum());
        chickenBaseInfoBO.setMemberNum(chickenDO.getMemberNum());
        chickenBaseInfoBO.setPeriod(chickenDO.getPeriod());
        chickenBaseInfoBO.setStatus(chickenDO.getStatus());
        chickenBaseInfoBO.setOutside(chickenDO.getIsOutside());
        chickenBaseInfoBO.setPregnant(chickenDO.getIsPregnant());
        chickenBaseInfoBO.setJoyfulVal(chickenDO.getJoyfulVal());
        chickenBaseInfoBO.setGrowthVal(chickenDO.getGrowthVal());
        chickenBaseInfoBO.setFullVal(chickenDO.getFullVal());
        chickenBaseInfoBO.setLayEggs(chickenDO.getLayEggs());
        chickenBaseInfoBO.setLifeStartTime(chickenDO.getLifeStartTime());
        chickenBaseInfoBO.setCleannessVal(chickenDO.getCleannessVal());
        chickenBaseInfoBO.setHouseEggs(chickenDO.getHouseEggs());
        //未放养、生病|治疗、休眠|睡眠、生产的小鸡在鸡窝
        if (!chickenDO.getIsOutside() ||
                PeriodTypeEnum.FAIL_ILL.getVal().equals(chickenDO.getPeriod()) || PeriodTypeEnum.CURE.getVal().equals(chickenDO.getPeriod()) ||
                ChickStatusEnum.HUNGERHALO.getVal().equals(chickenDO.getStatus()) || ChickStatusEnum.SLEEP.getVal().equals(chickenDO.getStatus()) ||
                chickenDO.getIsPregnant()) {
            chickenBaseInfoBO.setInHouse(true);
        } else {
            chickenBaseInfoBO.setInHouse(false);
        }
        return chickenBaseInfoBO;
    }

    public static ChickenManagePageBO convertManage(ChickenDO chickenDO) {
        if (chickenDO == null) {
            return null;
        }
        ChickenManagePageBO chickenManageBO = new ChickenManagePageBO();
        chickenManageBO.setId(chickenDO.getId());
        chickenManageBO.setName(chickenDO.getName());
        chickenManageBO.setNum(chickenDO.getNum());
        chickenManageBO.setGmtCreate(chickenDO.getGmtCreate());
        chickenManageBO.setJoyfulVal(chickenDO.getJoyfulVal());
        chickenManageBO.setGrowthVal(chickenDO.getGrowthVal());
        chickenManageBO.setFullVal(chickenDO.getFullVal());
        chickenManageBO.setLayEggs(chickenDO.getLayEggs());
        chickenManageBO.setLifeStartTime(chickenDO.getLifeStartTime());
        chickenManageBO.setIsOutside(chickenDO.getIsOutside());
        chickenManageBO.setLifeStartTime(chickenDO.getLifeStartTime());
        chickenManageBO.setPeriodType(PeriodTypeEnum.getEnum(chickenDO.getPeriod()));
        chickenManageBO.setStatusEnum(ChickStatusEnum.getEnum(chickenDO.getStatus()));
        return chickenManageBO;
    }
}
