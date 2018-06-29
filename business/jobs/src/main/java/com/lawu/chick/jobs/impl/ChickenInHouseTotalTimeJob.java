package com.lawu.chick.jobs.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.lawu.chick.repository.param.CommonListPageParam;
import com.lawu.chick.service.ChickenService;
import com.lawu.chick.service.bo.ChickenCleanessBO;
import com.lawu.jobsextend.AbstractPageJob;

/**
 * @author zhangyong
 * @date 2018/6/4.
 */
public class ChickenInHouseTotalTimeJob extends AbstractPageJob<ChickenCleanessBO> {

    @Autowired
    private ChickenService chickenService;

    @Override
    public void executeSingle(ChickenCleanessBO cleanBO) {
        chickenService.updateChickInHouseTime(cleanBO.getId());
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
    public List<ChickenCleanessBO> queryPage(int offset, int pageSize) {
        CommonListPageParam param = new CommonListPageParam();
        param.setPageSize(pageSize);
        param.setOffset(offset);
        return chickenService.getChickenInHouseList(param);
    }
}
