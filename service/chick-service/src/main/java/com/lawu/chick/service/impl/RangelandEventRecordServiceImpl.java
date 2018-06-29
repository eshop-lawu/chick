package com.lawu.chick.service.impl;

import java.util.Date;
import java.util.List;

import com.lawu.chick.service.constants.EventTitleConstant;
import com.lawu.chick.service.enums.EventMiniTypeEnum;
import com.lawu.chick.service.param.RangelandEventTitleDataParam;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.chick.repository.domain.RangelandEventRecordDO;
import com.lawu.chick.repository.domain.RangelandEventRecordDOExample;
import com.lawu.chick.repository.mapper.RangelandEventRecordDOMapper;
import com.lawu.chick.service.RangelandEventRecordService;
import com.lawu.chick.service.bo.RangelandEventRecordBO;
import com.lawu.chick.service.converter.RangelandEventRecordConverter;
import com.lawu.chick.service.enums.EventRecordAttrTypeEnum;
import com.lawu.chick.service.enums.EventRecordDirectionEnum;
import com.lawu.chick.service.enums.EventRecordSourceEnum;
import com.lawu.chick.service.param.RangelandEventRecordParam;
import com.lawu.chick.service.query.RangelandEventRecordQuery;
import com.lawu.framework.core.page.Page;
import com.lawu.utils.DateUtil;

/**
 * @author zhangrc
 * @Description
 * @date 2018年4月26日
 */
@Service
public class RangelandEventRecordServiceImpl implements RangelandEventRecordService {

    @Autowired
    private RangelandEventRecordDOMapper rangelandEventRecordDOMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveRangelandEventRecord(RangelandEventRecordParam param) {
        RangelandEventRecordDO record = new RangelandEventRecordDO();
        record.setAttrType(param.getAttrTypeEnum().getVal());
        record.setChickenNum(param.getChickenNum());
        record.setDirection(param.getDirectionEnum().getVal());
        record.setEventFactor(param.getFactorEnum().getVal());
        record.setEventTime(new Date());
        if (null != param.getEventTime()) {
            record.setEventTime(param.getEventTime());
        }
        record.setFriendNum(param.getFriendNum());
        record.setGmtCreate(new Date());
        record.setMemberNum(param.getMemberNum());
        record.setSource(param.getSourceEnum().getVal());
        if (param.getRangelandEventTitleDataParam() == null) {
            record.setTitle(param.getTitle());
        } else {
            record.setTitle(fillEventTitle(param.getRangelandEventTitleDataParam()));
        }
        record.setVal(param.getVal());
        rangelandEventRecordDOMapper.insertSelective(record);
    }

    private String fillEventTitle(RangelandEventTitleDataParam rangelandEventTitleDataParam) {
        String title = "";
        switch (rangelandEventTitleDataParam.getEventMiniTypeEnum()) {
            case RANGELAND_CLEAN_ADD_JOYFUL:
                title = EventTitleConstant.TITLE_JOYFUL_ADD;
                break;
            case HOUSE_CLEAN_MINUS_JOYFUL:
                title = EventTitleConstant.TITLE_JOYFUL_MINUS_HOUSECLEAN;
                break;
            case DAY_HOUSE_CLEAN_MINUS_JOYFUL:
                title = EventTitleConstant.TITLE_JOYFUL_MINUS_DAYHOUSE;
                break;
            default:
                break;
        }
        if (StringUtils.isNotBlank(title)) {
            title = title.replace("{0}",rangelandEventTitleDataParam.getRangelandClean()== null ? "" : rangelandEventTitleDataParam.getRangelandClean());
            title = title.replace("{1}", rangelandEventTitleDataParam.getTimedMins() == null ? "" : rangelandEventTitleDataParam.getTimedMins());
            title = title.replace("{2}",rangelandEventTitleDataParam.getHouseClean()== null ? "" : rangelandEventTitleDataParam.getHouseClean());
        }
        return title;
    }


    @Override
    public int getCleannessCount(String friendnum, String memberNum) {
        String begin = DateUtil.getDate() + " 00:00:00";
        String end = DateUtil.getDate() + " 23:59:59";
        RangelandEventRecordDOExample example = new RangelandEventRecordDOExample();
        example.createCriteria().andAttrTypeEqualTo(EventRecordAttrTypeEnum.CLEANLINESS.getVal()).andFriendNumEqualTo(friendnum)
                .andMemberNumEqualTo(memberNum).andSourceEqualTo(EventRecordSourceEnum.FRIENDS.getVal()).andEventTimeGreaterThanOrEqualTo(DateUtil.stringToDate(begin))
                .andEventTimeLessThanOrEqualTo(DateUtil.stringToDate(end));
        int count = (int) rangelandEventRecordDOMapper.countByExample(example);
        return count;
    }


    @Override
    public int getOwnerFeedTimes(String memberNum, String num) {
        String begin = DateUtil.getDate() + " 00:00:00";
        String end = DateUtil.getDate() + " 23:59:59";
        RangelandEventRecordDOExample example = new RangelandEventRecordDOExample();
        example.createCriteria().andSourceEqualTo(EventRecordSourceEnum.OWNER.getVal()).andChickenNumEqualTo(num).andMemberNumEqualTo(memberNum)
                .andAttrTypeEqualTo(EventRecordAttrTypeEnum.FULL.getVal()).andEventTimeGreaterThanOrEqualTo(DateUtil.stringToDate(begin))
                .andEventTimeLessThanOrEqualTo(DateUtil.stringToDate(end));
        int count = (int) rangelandEventRecordDOMapper.countByExample(example);
        return count;
    }


    @Override
    public int getChickDayGrowthVal(String memberNum, String num) {
        String begin = DateUtil.getDate() + " 00:00:00";
        String end = DateUtil.getDate() + " 23:59:59";
        RangelandEventRecordDOExample example = new RangelandEventRecordDOExample();
        example.createCriteria().andChickenNumEqualTo(num).andMemberNumEqualTo(memberNum).andAttrTypeEqualTo(EventRecordAttrTypeEnum.GROWTH.getVal())
                .andDirectionEqualTo(EventRecordDirectionEnum.ADD.getVal())
                .andEventTimeGreaterThanOrEqualTo(DateUtil.stringToDate(begin))
                .andEventTimeLessThanOrEqualTo(DateUtil.stringToDate(end));
        List<RangelandEventRecordDO> list = rangelandEventRecordDOMapper.selectByExample(example);
        if (list.size() > 0) {
            int totalGrow = 0;
            for (RangelandEventRecordDO event : list) {
                totalGrow += event.getVal().intValue();
            }
            return totalGrow;
        }
        return 0;
    }

    @Override
    public Page<RangelandEventRecordBO> listRangelandEventRecord(RangelandEventRecordQuery query) {
        RangelandEventRecordDOExample example = new RangelandEventRecordDOExample();
        example.setOrderByClause("id desc");
        example.createCriteria().andMemberNumEqualTo(query.getMemberNum()).andAttrTypeEqualTo(query.getAttrTypeEnum().getVal()).andEventFactorEqualTo(query.getFactorEnum().getVal());
        RowBounds rowBounds = new RowBounds(query.getOffset(), query.getPageSize());
        List<RangelandEventRecordDO> eventRecordDOS = rangelandEventRecordDOMapper.selectByExampleWithRowbounds(example, rowBounds);

        Page<RangelandEventRecordBO> page = new Page<>();
        page.setCurrentPage(query.getCurrentPage());
        page.setTotalCount((int) rangelandEventRecordDOMapper.countByExample(example));
        page.setRecords(RangelandEventRecordConverter.convertBOS(eventRecordDOS));
        return page;
    }

}
