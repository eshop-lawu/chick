package com.lawu.chick.jobs.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.lawu.chick.repository.param.ChickenReduceCleanessParam;
import com.lawu.chick.service.ChickenService;
import com.lawu.chick.service.RangelandEventRecordService;
import com.lawu.chick.service.SysConfigService;
import com.lawu.chick.service.bo.ChickenCleanessBO;
import com.lawu.chick.service.bo.RangelandConfigBaseBO;
import com.lawu.chick.service.constants.EventTitleConstant;
import com.lawu.chick.service.enums.EventRecordAttrTypeEnum;
import com.lawu.chick.service.enums.EventRecordDirectionEnum;
import com.lawu.chick.service.enums.EventRecordFactorEnum;
import com.lawu.chick.service.enums.EventRecordSourceEnum;
import com.lawu.chick.service.param.RangelandEventRecordParam;
import com.lawu.jobsextend.AbstractPageJob;

/**
 * @author zhangyong
 * @date 2018/6/4.
 */
public class ChickenInHouseReduceCleanessJob extends AbstractPageJob<ChickenCleanessBO> {

    @Autowired
    private ChickenService chickenService;

    @Autowired
    private SysConfigService sysConfigService;

    @Autowired
    private RangelandEventRecordService rangelandEventRecordService;

    @Override
    public boolean isStatusData() {
        return true;
    }

    @Override
    public boolean continueWhenSinglePageFail() {
        return true;
    }

    @Override
    public void executeSingle(ChickenCleanessBO cleanBO) {
        chickenService.reduceCleaness(cleanBO);
        RangelandEventRecordParam event = new RangelandEventRecordParam();
        event.setAttrTypeEnum(EventRecordAttrTypeEnum.CLEANLINESS);
        event.setChickenNum(cleanBO.getNum());
        event.setDirectionEnum(EventRecordDirectionEnum.REDUCE);
        event.setFactorEnum(EventRecordFactorEnum.GROWTH);
        event.setMemberNum(cleanBO.getMemberNum());
        event.setSourceEnum(EventRecordSourceEnum.NONE);
        event.setTitle(EventTitleConstant.TITLE_HOUSE_REDUCE_CLEAN);
        event.setVal(BigDecimal.valueOf(cleanBO.getCleannessVal()));
        rangelandEventRecordService.saveRangelandEventRecord(event);
    }

    @Override
    public List<ChickenCleanessBO> queryPage(int offset, int pageSize) {
        RangelandConfigBaseBO baseBO = sysConfigService.getRangelandCacheConfig();
        if (baseBO == null || baseBO.getChickDeclineHouseValMinute() <= 0) {
            return new ArrayList<>();
        }
        ChickenReduceCleanessParam param = new ChickenReduceCleanessParam();
        param.setOffset(offset);
        param.setPageSize(pageSize);
        param.setInhouseDuration(baseBO.getChickDeclineHouseValMinute());
        param.setCleannessVal(baseBO.getChickDeclineHouseVal());
        return chickenService.getChickenInHouseReduceCleanessList(param);
    }
}
