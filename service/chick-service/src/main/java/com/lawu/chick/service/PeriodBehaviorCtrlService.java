package com.lawu.chick.service;

import com.lawu.chick.service.enums.ChickenAttrEnum;

/**
 *
 * 周期行为控制服务接口
 *
 * @author Leach
 * @date 2018/4/27
 */
public interface PeriodBehaviorCtrlService {

    /**
     * 每天好友喂养商品使用，按照具体配置
     * @param chickenNum
     * @param attr
     * @param productNum
     * @return
     */
    int useProduct(String chickenNum, ChickenAttrEnum attr, String productNum);

    /**
     * 周期内主人喂养
     * @param chickenNum
     * @param attr
     * @return
     */
    int feedByMaster(String chickenNum, ChickenAttrEnum attr);

    /**
     * 周期内好友喂养
     * @param chickenNum
     * @param attr
     * @return
     */
    int feedByFriend(String chickenNum, ChickenAttrEnum attr);

    /**
     * 周期内成长值限制
     * @param chickenNum
     * @return
     */
    int increaseGrowth(String chickenNum, int value);

    /**
     * 周期内每个人帮忙打扫
     * @param memberNum 接收帮助的用户
     * @param helpMemberNum 帮助别人的用户
     * @return
     */
    int cleanByFriend(String memberNum, String helpMemberNum);

    /**
     * 周期内帮助别人
     * @param currentMemberNum
     * @return
     */
    int help(String currentMemberNum);
}
