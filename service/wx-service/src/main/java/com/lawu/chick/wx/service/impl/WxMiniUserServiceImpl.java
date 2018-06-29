package com.lawu.chick.wx.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawu.chick.wx.service.WxMiniUserService;
import com.lawu.chick.wx.service.bo.LoginInfoBO;
import com.lawu.chick.wx.service.bo.WxMiniUserBO;
import com.lawu.chick.wx.service.converter.WxConverter;
import com.lawu.chick.wx.service.exception.WxException;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import me.chanjar.weixin.common.exception.WxErrorException;

/**
 * @author Leach
 * @date 2018/4/25
 */
@Service
public class WxMiniUserServiceImpl implements WxMiniUserService {

    private Logger logger = LoggerFactory.getLogger(WxMiniUserServiceImpl.class);

    @Autowired
    private WxMaService wxService;

    @Override
    public LoginInfoBO login(String code) throws WxException {
        try {
            WxMaJscode2SessionResult session = wxService.getUserService().getSessionInfo(code);
            logger.info(session.toString());
            return WxConverter.convertLoginInfo(session);
        } catch (WxErrorException e) {
            this.logger.error("login failed", e);
            throw new WxException("login failed", e);
        }
    }

    @Override
    public WxMiniUserBO info(String sessionKey, String signature, String rawData, String encryptedData, String iv) throws WxException {
        // 用户信息校验
        if (!wxService.getUserService().checkUserInfo(sessionKey, rawData, signature)) {
            throw new WxException("user check failed");
        }

        // 解密用户信息
        WxMaUserInfo userInfo = wxService.getUserService().getUserInfo(sessionKey, encryptedData, iv);

        return WxConverter.convertWxUser(userInfo);
    }
}
