package com.lawu.chick.jobs.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.lawu.chick.cache.service.ChickBaseConfigCacheService;
import com.lawu.chick.cache.service.co.ChickBaseConfigCO;
import com.lawu.chick.repository.domain.extend.ChickDOView;
import com.lawu.chick.repository.param.ChickenDayHenhouseQueryParam;
import com.lawu.chick.service.ChickenService;
import com.lawu.jobsextend.AbstractWholePageJob;
import com.lawu.jobsextend.JobsExtendPageException;
import com.lawu.utils.DateUtil;

/**
 * 白天未处牧区每15分钟-2愉悦值
 * 未在牧场中的鸡加入缓存
 */
public class DayHenhouseMinusJoyfullJob extends AbstractWholePageJob<ChickDOView> {

    private static Logger logger = LoggerFactory.getLogger(DayHenhouseMinusJoyfullJob.class);

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
    public List<ChickDOView> queryPage(int offset, int pageSize) {
        ChickBaseConfigCO chickBaseConfigCO = chickBaseConfigCacheService.getCacheChickBaseInfo();
        String chickStartActivitiesTime = chickBaseConfigCO.getChickStartActivitiesTime().replace(":","");
        String chickEndActivitiesTime = chickBaseConfigCO.getChickEndActivitiesTime().replace(":","");
        String nowTime = DateUtil.getDateFormat(new Date(),"HH:mm").replace(":","");
        int chickStartActivitiesTimeVal = Integer.valueOf(chickStartActivitiesTime).intValue();
        int chickEndActivitiesTimeVal = Integer.valueOf(chickEndActivitiesTime).intValue();
        int nowTimeVal = Integer.valueOf(nowTime).intValue();
        if (nowTimeVal < chickStartActivitiesTimeVal || nowTimeVal > chickEndActivitiesTimeVal) {
            return null;
        }

        ChickenDayHenhouseQueryParam param = new ChickenDayHenhouseQueryParam();
        param.setChickInRangelandAddJoyfulValMinute(chickBaseConfigCO.getChickInRangelandAddJoyfulValMinute());
        param.setOffset(offset);
        param.setPageSize(pageSize);
        List<ChickDOView> chickDOViewList = chickenService.getDayHenhouseMinusJoyfullChickens(param);
        for (ChickDOView chickDOView : chickDOViewList) {
            chickDOView.setVal(chickBaseConfigCO.getChickInhouseReduFullyVal());
            chickDOView.setTimedMins(chickBaseConfigCO.getChickInRangelandAddJoyfulValMinute());
        }
        return chickDOViewList;
    }

    @Override
    public void executePage(List<ChickDOView> chickDOViewList) throws JobsExtendPageException {
        chickenService.addDayHenhouseJoyfullCache(chickDOViewList);
    }
}
