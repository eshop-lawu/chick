/*package com.lawu.chick.jobs.impl;

import com.lawu.chick.cache.service.ChickBaseConfigCacheService;
import com.lawu.chick.cache.service.co.ChickBaseConfigCO;
import com.lawu.chick.repository.domain.extend.ChickDOView;
import com.lawu.chick.repository.param.ChickenMinusJoyfullQueryParam;
import com.lawu.chick.service.ChickenService;
import com.lawu.jobsextend.AbstractWholePageJob;
import com.lawu.jobsextend.JobsExtendPageException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

*//**
 * 小鸡位于清洁度<60的鸡舍每10分钟-1愉悦值
 *//*
public class HenhouseCleanJoyfullJob extends AbstractWholePageJob<ChickDOView> {

    private static Logger logger = LoggerFactory.getLogger(HenhouseCleanJoyfullJob.class);

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
        ChickenMinusJoyfullQueryParam param = new ChickenMinusJoyfullQueryParam();
        param.setChickRangelandCleanVal(chickBaseConfigCO.getChickRangelandCleanVal());
        param.setChickDeclineJoyfulValMinute(chickBaseConfigCO.getChickInhouseReduFullMinute());
        param.setOffset(offset);
        param.setPageSize(pageSize);
        List<ChickDOView> chickDOViewList = chickenService.getHenhouseCleanMinusJoyfullChickens(param);
        for (ChickDOView chickDOView : chickDOViewList) {
            chickDOView.setVal(chickBaseConfigCO.getChickDeclineJoyfulVal());
            chickDOView.setTimedMins(chickBaseConfigCO.getChickInhouseReduFullMinute());
        }
        return chickDOViewList;
    }

    @Override
    public void executePage(List<ChickDOView> chickDOViewList) throws JobsExtendPageException {
        chickenService.addHenhouseCleanJoyfullCache(chickDOViewList);
    }
}
*/