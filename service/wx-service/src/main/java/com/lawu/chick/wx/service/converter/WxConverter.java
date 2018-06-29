package com.lawu.chick.wx.service.converter;

import com.lawu.chick.wx.service.bo.LoginInfoBO;
import com.lawu.chick.wx.service.bo.WxMiniUserBO;
import com.lawu.chick.wx.service.bo.WxMpUserBO;
import com.lawu.weixinapi.dto.WeixinUserDTO;

import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;

/**
 * @author Leach
 * @date 2018/4/25
 */
public class WxConverter {

    /**
     * 小程序登录信息
     * @param session
     * @return
     */
    public static LoginInfoBO convertLoginInfo(WxMaJscode2SessionResult session) {

        LoginInfoBO loginInfoBO = new LoginInfoBO();
        loginInfoBO.setOpenid(session.getOpenid());
        loginInfoBO.setUnionid(session.getUnionid());
        loginInfoBO.setSessionKey(session.getSessionKey());
        return loginInfoBO;
    }

    /**
     * 小程序用户信息
     * @param wxMaUserInfo
     * @return
     */
    public static WxMiniUserBO convertWxUser(WxMaUserInfo wxMaUserInfo) {
        WxMiniUserBO wxMiniUserBO = new WxMiniUserBO();
        wxMiniUserBO.setOpenid(wxMaUserInfo.getOpenId());
        wxMiniUserBO.setAvatarUrl(wxMaUserInfo.getAvatarUrl());
        wxMiniUserBO.setNickname(wxMaUserInfo.getNickName());
        wxMiniUserBO.setGender(Integer.parseInt(wxMaUserInfo.getGender()));
        wxMiniUserBO.setCity(wxMaUserInfo.getCity());
        wxMiniUserBO.setProvince(wxMaUserInfo.getProvince());
        wxMiniUserBO.setCountry(wxMaUserInfo.getCountry());
        wxMiniUserBO.setLanguage(wxMaUserInfo.getLanguage());
        wxMiniUserBO.setUnionId(wxMaUserInfo.getUnionId());
        return wxMiniUserBO;
    }

    /**
     * 微信公众号用户信息
     * @param weixinUserDTO
     * @return
     */
    public static WxMpUserBO convertWxUser(WeixinUserDTO weixinUserDTO) {
        WxMpUserBO wxMpUserBO = new WxMpUserBO();
        if (weixinUserDTO != null) {
            wxMpUserBO.setOpenid(weixinUserDTO.getOpenid());
            wxMpUserBO.setCity(weixinUserDTO.getCity());
            wxMpUserBO.setCountry(weixinUserDTO.getCountry());
            wxMpUserBO.setGroupid(weixinUserDTO.getGroupid());
            wxMpUserBO.setHeadimgurl(weixinUserDTO.getHeadimgurl());
            wxMpUserBO.setLanguage(weixinUserDTO.getLanguage());
            wxMpUserBO.setNickname(weixinUserDTO.getNickname());
            wxMpUserBO.setProvince(weixinUserDTO.getProvince());
            wxMpUserBO.setRemark(weixinUserDTO.getRemark());
            wxMpUserBO.setSex(weixinUserDTO.getSex());
            wxMpUserBO.setSubscribe(weixinUserDTO.getSubscribe());
            wxMpUserBO.setSubscribeTime(weixinUserDTO.getSubscribeTime());
            wxMpUserBO.setTagidList(weixinUserDTO.getTagidList());
            wxMpUserBO.setUnionid(weixinUserDTO.getUnionid());
        }
        return wxMpUserBO;
    }
}
