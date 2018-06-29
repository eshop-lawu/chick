package com.lawu.chick.service.converter;

import java.util.ArrayList;
import java.util.List;

import com.lawu.chick.repository.domain.RangelandEventRecordDO;
import com.lawu.chick.service.bo.RangelandEventRecordBO;

/**
 * @author meishuquan
 * @date 2018/5/10.
 */
public class RangelandEventRecordConverter {

    public static RangelandEventRecordBO convertBO(RangelandEventRecordDO eventRecordDO) {
        if (eventRecordDO == null) {
            return null;
        }

        RangelandEventRecordBO eventRecordBO = new RangelandEventRecordBO();
        eventRecordBO.setId(eventRecordDO.getId());
        eventRecordBO.setMemberNum(eventRecordDO.getMemberNum());
        eventRecordBO.setChickenNum(eventRecordDO.getChickenNum());
        eventRecordBO.setTitle(eventRecordDO.getTitle());
        eventRecordBO.setAttrType(eventRecordDO.getAttrType());
        eventRecordBO.setEventFactor(eventRecordDO.getEventFactor());
        eventRecordBO.setDirection(eventRecordDO.getDirection());
        eventRecordBO.setVal(eventRecordDO.getVal());
        eventRecordBO.setSource(eventRecordDO.getSource());
        eventRecordBO.setFriendNum(eventRecordDO.getFriendNum());
        eventRecordBO.setEventTime(eventRecordDO.getEventTime());
        eventRecordBO.setGmtCreate(eventRecordDO.getGmtCreate());
        return eventRecordBO;
    }

    public static List<RangelandEventRecordBO> convertBOS(List<RangelandEventRecordDO> eventRecordDOS) {
        List<RangelandEventRecordBO> eventRecordBOS = new ArrayList<>();
        if (eventRecordDOS == null || eventRecordDOS.isEmpty()) {
            return eventRecordBOS;
        }

        for (RangelandEventRecordDO eventRecordDO : eventRecordDOS) {
            eventRecordBOS.add(convertBO(eventRecordDO));
        }
        return eventRecordBOS;
    }

}
