package com.lawu.chick.api.converter;

import com.lawu.chick.service.param.WxUserParam;
import com.lawu.chick.wx.service.bo.WxMpUserBO;

/**
 * @author Leach
 * @date 2018/6/4
 */
public class WxUserConverter {

    public static WxUserParam convert(WxMpUserBO wxUser) {

        WxUserParam wxParam = new WxUserParam();
        wxParam.setOpenid(wxUser.getOpenid());
        wxParam.setNickname(wxUser.getNickname());
        wxParam.setAvatarUrl(wxUser.getHeadimgurl());
        wxParam.setCity(wxUser.getCity());
        wxParam.setCountry(wxUser.getCountry());
        wxParam.setGender(wxUser.getSex());
        wxParam.setLanguage(wxUser.getLanguage());
        wxParam.setProvince(wxUser.getProvince());
        wxParam.setUnionId(wxUser.getUnionid());
        wxParam.setSubscribe(wxUser.getSubscribe());
        wxParam.setSubscribeTime(wxUser.getSubscribeTime());
        return wxParam;
    }
}
