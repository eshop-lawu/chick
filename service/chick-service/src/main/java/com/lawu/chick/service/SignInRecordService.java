package com.lawu.chick.service;

import com.lawu.chick.service.bo.SignRecordListBO;
import com.lawu.chick.service.param.SignExtraParam;

/**
 * @author zhangyong
 * @date 2018/4/27.
 */
public interface SignInRecordService {
    /**
     * 根据用户编号判断是否签到
     * @param userNum
     * @return
     */
    Boolean getRecordByMemberNum(String userNum);

    /**
     * 签到
     * @param userNum
     * @return
     */
    Boolean sign(String userNum);

    /**
     * 领取额外奖励
     * @param userNum
     * @param signDay
     * @return
     */
    Boolean getSignAward(String userNum, Integer signDay);

    /**
     * 获取签到记录列表
     * @param userNum
     * @return
     */
    SignRecordListBO getSignRecord(String userNum);

    /**
     * 判断额外奖励是否领取
     * @param param
     * @return
     */
    Boolean isGetSignExtraAward(SignExtraParam param);
}
