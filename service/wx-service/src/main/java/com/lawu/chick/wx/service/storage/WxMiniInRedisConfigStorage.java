package com.lawu.chick.wx.service.storage;

import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.StringRedisTemplate;

import cn.binarywang.wx.miniapp.config.WxMaInMemoryConfig;

/**
 * @author Leach
 * @date 2018/1/19
 */
public class WxMiniInRedisConfigStorage extends WxMaInMemoryConfig {

    private final static String ACCESS_TOKEN_KEY = "chick_wechat_access_token_";

    private final static String JSAPI_TICKET_KEY = "chick_wechat_jsapi_ticket_";

    private final static String CARDAPI_TICKET_KEY = "chick_wechat_cardapi_ticket_";

    private StringRedisTemplate stringRedisTemplate;

    private String accessTokenKey;

    private String jsapiTicketKey;

    private String cardapiTicketKey;

    public WxMiniInRedisConfigStorage(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    public void setAppid(String appid) {
        super.setAppid(appid);
        this.accessTokenKey = ACCESS_TOKEN_KEY.concat(appid);
        this.jsapiTicketKey = JSAPI_TICKET_KEY.concat(appid);
        this.cardapiTicketKey = CARDAPI_TICKET_KEY.concat(appid);
    }

    @Override
    public String getAccessToken() {
        return stringRedisTemplate.opsForValue().get(accessTokenKey);
    }

    @Override
    public boolean isAccessTokenExpired() {
        return stringRedisTemplate.getExpire(accessTokenKey, TimeUnit.SECONDS) < 2;
    }

    @Override
    public synchronized void updateAccessToken(String accessToken, int expiresInSeconds) {
        stringRedisTemplate.boundValueOps(accessTokenKey).set(accessToken, expiresInSeconds - 200, TimeUnit.SECONDS);
    }

    @Override
    public void expireAccessToken() {
        stringRedisTemplate.expire(accessTokenKey, 0, TimeUnit.SECONDS);
    }
}
