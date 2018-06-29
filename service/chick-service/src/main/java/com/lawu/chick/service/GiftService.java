package com.lawu.chick.service;

import java.util.List;

import com.lawu.chick.service.bo.GiftBO;
import com.lawu.chick.service.param.GiftParam;
import com.lawu.chick.service.query.GiftOperatorQuery;
import com.lawu.framework.core.page.Page;

/**
 * @author meishuquan
 * @date 2018/5/16.
 */
public interface GiftService {

    /**
     * 根据id查询礼品
     *
     * @param id
     * @return
     * @author meishuquan
     */
    GiftBO getGift(Long id);

    /**
     * 礼品列表
     *
     * @return
     * @author meishuquan
     */
    List<GiftBO> listGift();

    /**
     * 运营平台礼品列表
     *
     * @param query
     * @return
     * @author meishuquan
     */
    Page<GiftBO> listOperatorGift(GiftOperatorQuery query);

    /**
     * 保存礼品信息
     *
     * @param param
     * @author meishuquan
     */
    void saveGift(GiftParam param);

}
