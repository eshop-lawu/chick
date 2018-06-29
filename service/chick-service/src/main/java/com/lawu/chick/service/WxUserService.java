package com.lawu.chick.service;

import com.lawu.chick.service.bo.WxUserBO;
import com.lawu.chick.service.param.WxUserParam;

/**
 * @author Leach
 * @date 2018/4/26
 */
public interface WxUserService {


    /**
     * 查找微信用户信息
     * @param openid
     * @return
     */
    WxUserBO find(String openid);


    /**
     * 根据用户编号查找微信用户信息
     * @param memberNum
     * @return
     */
    WxUserBO findByNum(String memberNum);

    /**
     * 创建微信用户信息
     * @param wxUserParam
     */
    WxUserBO createOrUpdate(WxUserParam wxUserParam);
}
