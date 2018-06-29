package com.lawu.chick.wx.service;

import com.lawu.chick.wx.service.bo.LoginInfoBO;
import com.lawu.chick.wx.service.bo.WxMiniUserBO;
import com.lawu.chick.wx.service.exception.WxException;

/**
 * 微信小程序用户服务
 * @author Leach
 * @date 2018/4/25
 */
public interface WxMiniUserService {

    LoginInfoBO login(String code) throws WxException;

    WxMiniUserBO info(String sessionKey, String signature, String rawData, String encryptedData, String iv) throws WxException;
}
