/*package com.lawu.chick.jobs.impl;

import com.lawu.chick.jobs.converter.ChickenConverter;
import com.lawu.chick.service.ChickenService;
import com.lawu.chick.service.bo.ChickenBaseInfoBO;
import com.lawu.chick.service.param.ChickenSickParam;
import com.lawu.jobsextend.AbstractPageJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

*//**
 * 小鸡定时生病job
 *//*
@Deprecated
public class ChickenSickJob extends AbstractPageJob<ChickenSickParam> {

    private static Logger logger = LoggerFactory.getLogger(ChickenSickJob.class);

    @Autowired
    private ChickenService chickenService;

    @Override
    public boolean isStatusData() {
        return true;
    }

    @Override
    public boolean continueWhenSinglePageFail() {
        return true;
    }

    @Override
    public void executeSingle(ChickenSickParam chickenSickParam) {
        chickenService.doSick(chickenSickParam);
    }

    @Override
    public List<ChickenSickParam> queryPage(int offset, int pageSize) {
        List<ChickenBaseInfoBO> chickenBaseInfoBOList = chickenService.getToBeSickChickList(offset, pageSize);
        return ChickenConverter.converterChickSickParamList(chickenBaseInfoBOList);
    }
}
*/