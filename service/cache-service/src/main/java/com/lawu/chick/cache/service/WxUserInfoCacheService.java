package com.lawu.chick.cache.service;

/**
 * 
 * @author jiangxinjun
 * @createDate 2018年5月18日
 * @updateDate 2018年5月18日
 */
public interface WxUserInfoCacheService {
    
    /**
     * 缓存SessionKey
     * @param openid
     * @param sessionKey
     * @author jiangxinjun
     * @createDate 2018年5月18日
     * @updateDate 2018年5月18日
     */
    void setSessionKey(String openid, String sessionKey);
    
    /**
     * 根据微信openid获取缓存SessionKey
     * @param openid
     * @param sessionKey
     * @author jiangxinjun
     * @createDate 2018年5月18日
     * @updateDate 2018年5月18日
     */
    String getSessionKey(String openid);
    
}
