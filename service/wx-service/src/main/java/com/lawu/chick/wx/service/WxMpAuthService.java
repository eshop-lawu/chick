package com.lawu.chick.wx.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import com.lawu.chick.wx.service.bo.WxMpUserBO;
import com.lawu.chick.wx.service.exception.WxException;

/**
 * 微信公众号授权服务
 * @author Leach
 * @date 2018/6/4
 */
public interface WxMpAuthService {

    /**
     * 获取重定向授权地址
     * @param redirectURL
     * @return
     * @throws UnsupportedEncodingException
     */
    String getAuthRedirectUrl(String redirectURL) throws UnsupportedEncodingException;

    /**
     * 获取微信公众号用户信息
     * @param code
     * @return
     * @throws IOException
     * @throws WxException
     */
    WxMpUserBO getWxUser(String code) throws IOException, WxException;


    /**
     * 关注微信公众号后获取用户信息
     * @param openid
     * @return
     * @throws IOException
     * @throws WxException
     */
    WxMpUserBO getWxUserAfterSubscribe(String openid) throws IOException, WxException;
}
