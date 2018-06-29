package com.lawu.chick.service;

import java.util.List;

import com.lawu.chick.service.bo.EggExchangeRecordBO;
import com.lawu.chick.service.bo.EggExchangeValBO;
import com.lawu.chick.service.param.EggExchangeParam;
import com.lawu.chick.service.query.EggExchangeRecordMemberQuery;
import com.lawu.chick.service.query.EggExchangeRecordOperatorQuery;
import com.lawu.framework.core.page.Page;

/**
 * @author meishuquan
 * @date 2018/4/26.
 */
public interface EggExchangeRecordService {

    /**
     * 查询可兑换的红包数量
     *
     * @param memberNum
     * @return
     * @author meishuquan
     */
    EggExchangeValBO getEggExchangeVal(String memberNum);

    /**
     * 用户鸡蛋兑换记录
     *
     * @param query
     * @return
     * @author meishuquan
     */
    Page<EggExchangeRecordBO> listMemberEggExchangeRecord(EggExchangeRecordMemberQuery query);

    /**
     * 保存鸡蛋兑换记录
     *
     * @param param
     * @author meishuquan
     */
    void saveEggExchangeRecord(EggExchangeParam param) throws Exception;

    /**
     * 运营平台查询鸡蛋兑换记录
     *
     * @param query
     * @return
     * @author meishuquan
     */
    Page<EggExchangeRecordBO> listOperatorEggExchangeRecord(EggExchangeRecordOperatorQuery query);

    /**
     * 运营平台发放兑换的礼品
     *
     * @param id
     * @param expressNum
     * @author meishuquan
     */
    void sendExchangeGift(Long id, String expressNum);

    /**
     * 查询未发放的兑换红包记录
     *
     * @param offset
     * @param pageSize
     * @return
     * @author meishuquan
     */
    List<EggExchangeRecordBO> listEggExchangeRedpacket(int offset, int pageSize);

    /**
     * 根据id查询鸡蛋兑换记录
     *
     * @param id
     * @return
     * @author meishuquan
     */
    EggExchangeRecordBO getEggExchangeRecord(Long id);

}
