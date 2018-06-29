package com.lawu.chick.service.query;

import com.lawu.chick.service.enums.EventRecordAttrTypeEnum;
import com.lawu.chick.service.enums.EventRecordFactorEnum;
import com.lawu.framework.core.page.AbstractPageParam;

/**
 * @author meishuquan
 * @date 2018/5/10.
 */
public class RangelandEventRecordQuery extends AbstractPageParam {

    private String memberNum;

    private EventRecordAttrTypeEnum attrTypeEnum;

    private EventRecordFactorEnum factorEnum;

    public String getMemberNum() {
        return memberNum;
    }

    public void setMemberNum(String memberNum) {
        this.memberNum = memberNum;
    }

    public EventRecordAttrTypeEnum getAttrTypeEnum() {
        return attrTypeEnum;
    }

    public void setAttrTypeEnum(EventRecordAttrTypeEnum attrTypeEnum) {
        this.attrTypeEnum = attrTypeEnum;
    }

    public EventRecordFactorEnum getFactorEnum() {
        return factorEnum;
    }

    public void setFactorEnum(EventRecordFactorEnum factorEnum) {
        this.factorEnum = factorEnum;
    }
}
