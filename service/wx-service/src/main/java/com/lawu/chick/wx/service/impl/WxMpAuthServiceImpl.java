package com.lawu.chick.wx.service.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lawu.chick.wx.service.WxMpAuthService;
import com.lawu.chick.wx.service.bo.WxMpUserBO;
import com.lawu.chick.wx.service.config.WxMpProperties;
import com.lawu.chick.wx.service.converter.WxConverter;
import com.lawu.chick.wx.service.exception.WxException;
import com.lawu.chick.wx.service.utils.JsonUtil;
import com.lawu.weixinapi.dto.WeixinUserDTO;

/**
 * @author Leach
 * @date 2018/6/4
 */
@Service
public class WxMpAuthServiceImpl implements WxMpAuthService {

    private Logger logger = LoggerFactory.getLogger(WxMpAuthServiceImpl.class);

    private HttpClient httpclient = HttpClients.createDefault();

    @Autowired
    private WxMpProperties wxMpProperties;

    @Override
    public String getAuthRedirectUrl(String redirectURL) throws UnsupportedEncodingException {
        String authRedirectUrl = String.format(wxMpProperties.getRedirectUrl(),
                wxMpProperties.getAppKey(),
                URLEncoder.encode(redirectURL, "utf-8"),
                "snsapi_userinfo", 1);
        return authRedirectUrl;
    }

    @Override
    public WxMpUserBO getWxUser(String code) throws IOException, WxException {

        String useInfoUrl = String.format(wxMpProperties.getGetUserInfoUrl(), wxMpProperties.getAppKey(), code);
        return getWxMpUser(useInfoUrl);
    }

    @Override
    public WxMpUserBO getWxUserAfterSubscribe(String openid) throws IOException, WxException {

        String useInfoUrl = String.format(wxMpProperties.getGetSubscribeUserInfoUrl(), wxMpProperties.getAppKey(), openid);
        return getWxMpUser(useInfoUrl);
    }

    /**
     * 根据制定链接获取微信用户信息
     * @param useInfoUrl
     * @return
     * @throws IOException
     * @throws WxException
     */
    private WxMpUserBO getWxMpUser(String useInfoUrl) throws IOException, WxException {
        HttpGet httpGet = new HttpGet(useInfoUrl);
        HttpResponse response = this.httpclient.execute(httpGet);
        String resJson = EntityUtils.toString(response.getEntity(), "utf-8");

        logger.debug("Wx auth result: {}", resJson);

        JSONObject json = JSON.parseObject(resJson);
        if (StringUtils.isEmpty(resJson) || json.getInteger("ret") != 1000) {
            throw new WxException("Fail to get weixin user");
        }
        WeixinUserDTO model = JsonUtil.read(json.get("model").toString(), WeixinUserDTO.class);
        return WxConverter.convertWxUser(model);
    }
}
