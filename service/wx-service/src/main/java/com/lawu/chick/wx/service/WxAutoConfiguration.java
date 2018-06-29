package com.lawu.chick.wx.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.redis.core.StringRedisTemplate;

import com.github.binarywang.wxpay.config.WxPayConfig;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.service.impl.WxPayServiceImpl;
import com.lawu.chick.wx.service.config.WxMiniProperties;
import com.lawu.chick.wx.service.config.WxMpProperties;
import com.lawu.chick.wx.service.config.WxPayProperties;
import com.lawu.chick.wx.service.storage.WxMiniInRedisConfigStorage;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.api.impl.WxMaServiceImpl;
import cn.binarywang.wx.miniapp.config.WxMaConfig;
import cn.binarywang.wx.miniapp.config.WxMaInMemoryConfig;

/**
 * @author Leach
 * @date 2018/4/25
 */
@ConditionalOnProperty(name = { "lawu.chick.wechat.enabled" }, havingValue = "true", matchIfMissing = false)
@EnableAspectJAutoProxy(proxyTargetClass = true)
@Configuration
@EnableConfigurationProperties({WxMiniProperties.class, WxPayProperties.class, WxMpProperties.class})
public class WxAutoConfiguration {

    @Autowired
    private WxMiniProperties wxMiniProperties;

    @Autowired
    private WxPayProperties wxPayProperties;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Bean
    @ConditionalOnMissingBean
    public WxMaConfig maConfig() {
        WxMaInMemoryConfig config = new WxMiniInRedisConfigStorage(stringRedisTemplate);
        config.setAppid(wxMiniProperties.getAppid());
        config.setSecret(wxMiniProperties.getSecret());
        config.setToken(wxMiniProperties.getToken());
        config.setAesKey(wxMiniProperties.getAesKey());
        config.setMsgDataFormat(wxMiniProperties.getMsgDataFormat());

        return config;
    }

    @Bean
    @ConditionalOnMissingBean
    public WxMaService wxMaService(WxMaConfig maConfig) {
        WxMaService service = new WxMaServiceImpl();
        service.setWxMaConfig(maConfig);
        return service;
    }

    @Bean
    @ConditionalOnMissingBean
    public WxPayConfig payConfig() {
        WxPayConfig payConfig = new WxPayConfig();
        payConfig.setAppId(wxPayProperties.getAppId());
        payConfig.setMchId(wxPayProperties.getMchId());
        payConfig.setMchKey(wxPayProperties.getMchKey());
        payConfig.setKeyPath(wxPayProperties.getKeyPath());

        return payConfig;
    }

    @Bean
    @ConditionalOnMissingBean
    public WxPayService wxPayService(WxPayConfig payConfig) {
        WxPayService wxPayService = new WxPayServiceImpl();
        wxPayService.setConfig(payConfig);
        return wxPayService;
    }

}
