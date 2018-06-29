package com.lawu.chick.jobs.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.lawu.chick.cache.service.ChickBaseConfigCacheService;
import com.lawu.chick.cache.service.co.ChickBaseConfigCO;
import com.lawu.chick.jobs.converter.ChickenConverter;
import com.lawu.chick.service.ChickenService;
import com.lawu.chick.service.bo.ChickenBaseInfoBO;
import com.lawu.chick.service.param.ChickenSickParam;
import com.lawu.jobsextend.AbstractWholePageJob;
import com.lawu.jobsextend.JobsExtendPageException;
import com.lawu.utils.DateUtil;

/**
 * 小鸡定时进鸡舍job
 */
public class ChickenIntoHenhouseJob extends AbstractWholePageJob<ChickenSickParam> {

    private static Logger logger = LoggerFactory.getLogger(ChickenIntoHenhouseJob.class);

    @Autowired
    private ChickenService chickenService;

    @Autowired
    private ChickBaseConfigCacheService chickBaseConfigCacheService;

    @Override
    public boolean isStatusData() {
        return true;
    }

    @Override
    public boolean continueWhenSinglePageFail() {
        return true;
    }

    @Override
    public List<ChickenSickParam> queryPage(int offset, int pageSize) {
        ChickBaseConfigCO chickBaseConfigCO = chickBaseConfigCacheService.getCacheChickBaseInfo();
        String chickEndActivitiesTime = chickBaseConfigCO.getChickEndActivitiesTime().replace(":","");
        int chickEndActivitiesTimeVal = Integer.valueOf(chickEndActivitiesTime).intValue();
        String nowTime = DateUtil.getDateFormat(new Date(),"HH:mm").replace(":","");
        int nowTimeVal = Integer.valueOf(nowTime).intValue();
        if (nowTimeVal < chickEndActivitiesTimeVal) {
            return null;
        }
        List<ChickenBaseInfoBO> chickenBaseInfoBOList = chickenService.getToBeIntoHenhouseChickList(offset,pageSize);
        return ChickenConverter.converterChickSickParamList(chickenBaseInfoBOList);
    }

    @Override
    public void executePage(List<ChickenSickParam> chickenSickParam) throws JobsExtendPageException {
        chickenService.intoHenhouse(chickenSickParam);
    }
}
