package com.lawu.chick.jobs.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.lawu.chick.cache.service.ChickBaseConfigCacheService;
import com.lawu.chick.cache.service.ChickJobCacheService;
import com.lawu.chick.cache.service.co.ChickBaseConfigCO;
import com.lawu.chick.service.ChickenService;
import com.lawu.chick.service.bo.ChickenBaseInfoBO;
import com.lawu.jobsextend.AbstractPageJob;

/**
 * @author meishuquan
 * @date 2018/6/4.
 */
public class ChickenAttenuationJoyfulValJob extends AbstractPageJob<ChickenBaseInfoBO> {

    @Autowired
    private ChickBaseConfigCacheService chickBaseConfigCacheService;

    @Autowired
    private ChickJobCacheService chickJobCacheService;

    @Autowired
    private ChickenService chickenService;

    @Override
    public void executeSingle(ChickenBaseInfoBO chickenBaseInfoBO) {
        chickenService.attenuationJoyfulVal(chickenBaseInfoBO);
    }

    @Override
    public boolean isStatusData() {
        return false;
    }

    @Override
    public boolean continueWhenSinglePageFail() {
        return true;
    }

    @Override
    public List<ChickenBaseInfoBO> queryPage(int offset, int pageSize) {
        ChickBaseConfigCO chickBaseConfigCO = chickBaseConfigCacheService.getCacheChickBaseInfo();
        if (chickBaseConfigCO == null) {
            return null;
        }
        long lastMilliseconds = chickJobCacheService.getAttenuationJoyfulValMilliseconds();
        long currMilliseconds = System.currentTimeMillis();
        long intervalMin = (currMilliseconds - lastMilliseconds) / 1000 / 60;
        if (intervalMin < chickBaseConfigCO.getChickInhouseReduFullMinute()) {
            return null;
        }

        List<ChickenBaseInfoBO> list = chickenService.listAttenuationJoyfulVal(offset, pageSize);
        if (list == null || list.isEmpty()) {
            chickJobCacheService.setAttenuationJoyfulValMilliseconds();
        }
        return list;
    }

}
