package com.lawu.chick.api.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.lawu.authorization.manager.TokenManager;
import com.lawu.chick.api.authorization.impl.UserConstant;
import com.lawu.chick.api.converter.WxUserConverter;
import com.lawu.chick.cache.service.ChickBaseConfigCacheService;
import com.lawu.chick.cache.service.WxUserInfoCacheService;
import com.lawu.chick.cache.service.co.ChickBaseConfigCO;
import com.lawu.chick.framework.web.impl.ResultCode;
import com.lawu.chick.framework.web.impl.dto.TokenDTO;
import com.lawu.chick.service.MemberUserService;
import com.lawu.chick.service.WxUserService;
import com.lawu.chick.service.bo.WxUserBO;
import com.lawu.chick.service.param.WxUserParam;
import com.lawu.chick.wx.service.WxMiniUserService;
import com.lawu.chick.wx.service.WxMpAuthService;
import com.lawu.chick.wx.service.bo.LoginInfoBO;
import com.lawu.chick.wx.service.bo.WxMiniUserBO;
import com.lawu.chick.wx.service.bo.WxMpUserBO;
import com.lawu.chick.wx.service.exception.WxException;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.HttpCode;
import com.lawu.framework.web.Result;
import com.lawu.framework.web.doc.annotation.Audit;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;

/**
 * @author Leach
 * @date 2018/4/26
 */
@Api(tags = "common")
@RestController
@RequestMapping(value = "/")
public class CommonController extends BaseController {

    @Autowired
    private WxMiniUserService wxMiniUserService;

    @Autowired
    private TokenManager tokenManager;

    @Autowired
    private WxUserService wxUserService;

    @Autowired
    private MemberUserService memberUserService;
    
    @Autowired
    private ChickBaseConfigCacheService chickBaseConfigCacheService;
    
    @Autowired
    private WxUserInfoCacheService wxUserInfoCacheService;

    @Autowired
    private WxMpAuthService wxMpAuthService;

    @Audit(date = "2018-04-27", reviewer = "孙林青")
    @ApiOperation(value = "登录测试", notes = "根据用户编号登录，成功返回token。[2005|2006]（孙林青）", httpMethod = "POST")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequestMapping(value = "loginTest/{num}", method = RequestMethod.POST)
    public Result<TokenDTO> loginTest(@PathVariable @ApiParam(required = true, value = "用户编号") String num) {
        WxUserBO wxUserBO = wxUserService.findByNum(num);

        if (wxUserBO != null) {

            TokenDTO tokenDTO = createLoginToken(wxUserBO, 0);

            return successCreated(tokenDTO);
        }

        return successCreated(ResultCode.MEMBER_WRONG_PWD);
    }

    @Deprecated
    @Audit(date = "2018-04-26", reviewer = "孙林青")
    @ApiOperation(value = "登录", notes = "根据code登录，成功返回token。[2007|2008]（孙林青）", httpMethod = "POST")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequestMapping(value = "login/{code}", method = RequestMethod.POST)
    public Result<TokenDTO> login(@PathVariable @ApiParam(required = true, value = "微信临时登录凭证") String code) {

        LoginInfoBO loginInfoBO;
        try {
            loginInfoBO = wxMiniUserService.login(code);
        } catch (WxException e) {
            return successCreated(ResultCode.WX_LOGIN_FAIL, e.getMessage());
        }

        String openid = loginInfoBO.getOpenid();

        WxUserBO wxUserBO = wxUserService.find(openid);

        TokenDTO tokenDTO = new TokenDTO();
        tokenDTO.setOpenid(openid);
        tokenDTO.setSessionKey(loginInfoBO.getSessionKey());

        if (wxUserBO != null) {

            String token = tokenManager.createToken(UserConstant.MEMBER_TOKEN_TYPE, wxUserBO.getMemberNum(), wxUserBO.getId(), openid);
            tokenDTO.setToken(token);
            tokenDTO.setUserNum(wxUserBO.getMemberNum());
            return successCreated(tokenDTO);
        }
        
        // 如果没有查询到用户, 把SessionKey缓存, 以便注册时使用
        wxUserInfoCacheService.setSessionKey(openid, loginInfoBO.getSessionKey());
        
        return successCreated(ResultCode.WX_NOT_AUTH, tokenDTO);
    }

    @Deprecated
    @Audit(date = "2018-04-26", reviewer = "孙林青")
    @ApiOperation(value = "用户注册", notes = "根据登录返回信息进行用户注册。[2007]（孙林青）", httpMethod = "POST")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequestMapping(value = "register", method = RequestMethod.POST)
    public Result<TokenDTO> register(@RequestParam(required = false) @ApiParam(value = "会话密钥", hidden = true) String sessionKey,
                            @RequestParam(required = false) @ApiParam(value = "openid") String openid,
                           @RequestParam @ApiParam(required = true, value = "使用 sha1( rawData + sessionkey ) 得到字符串，用于校验用户信息") String signature,
                           @RequestParam @ApiParam(required = true, value = "不包括敏感信息的原始数据字符串，用于计算签名") String rawData,
                           @RequestParam @ApiParam(required = true, value = "包括敏感数据在内的完整用户信息的加密数据") String encryptedData,
                           @RequestParam @ApiParam(required = true, value = "加密算法的初始向量") String iv) {
        WxMiniUserBO wxMiniUserBO = null;
        // 如果sessionKey为空, 从缓存中查询
        if (StringUtils.isNotBlank(openid)) {
            sessionKey = wxUserInfoCacheService.getSessionKey(openid);
        }
        try {
            wxMiniUserBO = wxMiniUserService.info(sessionKey, signature, rawData, encryptedData, iv);
        } catch (WxException e) {
            return successCreated(ResultCode.WX_LOGIN_FAIL, e.getMessage());
        }

        WxUserParam wxParam = new WxUserParam();
        wxParam.setOpenid(wxMiniUserBO.getOpenid());
        wxParam.setNickname(wxMiniUserBO.getNickname());
        wxParam.setAvatarUrl(wxMiniUserBO.getAvatarUrl());
        wxParam.setCity(wxMiniUserBO.getCity());
        wxParam.setCountry(wxMiniUserBO.getCountry());
        wxParam.setGender(wxMiniUserBO.getGender());
        wxParam.setLanguage(wxMiniUserBO.getLanguage());
        wxParam.setProvince(wxMiniUserBO.getProvince());
        wxParam.setUnionId(wxMiniUserBO.getUnionId());
        WxUserBO wxUserBO = memberUserService.register(wxParam);

        TokenDTO tokenDTO = new TokenDTO();
        tokenDTO.setOpenid(wxUserBO.getOpenid());
        String token = tokenManager.createToken(UserConstant.MEMBER_TOKEN_TYPE, wxUserBO.getMemberNum(), wxUserBO.getId(), wxUserBO.getOpenid());
        tokenDTO.setToken(token);
        tokenDTO.setUserNum(wxUserBO.getMemberNum());
        
        ChickBaseConfigCO chickBaseConfig = chickBaseConfigCacheService.getCacheChickBaseInfo();
        tokenDTO.setChickAdoptionCount(chickBaseConfig.getChickAdoptionCount());
        return successCreated(tokenDTO);
    }


    @Audit(date = "2018-06-04", reviewer = "孙林青")
    @ApiOperation(value = "H5授权重定向", notes = "调整到微信服务器，授权完之后跳转回来(孙林青)", httpMethod = "POST")
    @RequestMapping(value = "redirect")
    public ModelAndView redirect(@RequestParam @ApiParam(name = "redirectURL", required = true, value = "授权之后回跳的地址") String redirectURL) throws UnsupportedEncodingException {
        String authUrl = wxMpAuthService.getAuthRedirectUrl(redirectURL);
        return createMav("redirect:" + authUrl, null);
    }

    @Audit(date = "2018-06-04", reviewer = "孙林青")
    @ApiOperation(value = "H5用户登录", notes = "登录并获取用户信息 (孙林青)", httpMethod = "GET")
    @RequestMapping(value = "userLogin", method = RequestMethod.GET)
    public Result<TokenDTO> userLogin(@RequestParam @ApiParam(name = "微信临时登录凭证", required = true, value = "code") String code) throws IOException {
        WxMpUserBO wxUser;
        try {
            wxUser = wxMpAuthService.getWxUser(code);
        } catch (WxException e) {
            return successCreated(ResultCode.WX_LOGIN_FAIL);
        }

        if (wxUser == null) {
            return successCreated(ResultCode.WX_LOGIN_FAIL);
        }

        String openid = wxUser.getOpenid();
        WxUserBO wxUserBO = wxUserService.find(openid);

        // 新用户初始化赠送小鸡数量信息
        int chickenCount = 0;

        // 新用户注册
        if (wxUserBO == null) {
            wxUserBO = memberUserService.register(WxUserConverter.convert(wxUser));

            chickenCount = chickBaseConfigCacheService.getCacheChickBaseInfo().getChickAdoptionCount();

        }
        TokenDTO tokenDTO = createLoginToken(wxUserBO, chickenCount);

        return successCreated(tokenDTO);
    }

    /**
     * 创建token信息
     * @param wxUserBO
     * @param chickenCount
     * @return
     */
    private TokenDTO createLoginToken(WxUserBO wxUserBO, int chickenCount) {
        TokenDTO tokenDTO = new TokenDTO();
        tokenDTO.setOpenid(wxUserBO.getOpenid());
        tokenDTO.setNickname(wxUserBO.getNickname());
        tokenDTO.setAvatarUrl(wxUserBO.getAvatarUrl());
        tokenDTO.setUserNum(wxUserBO.getMemberNum());
        tokenDTO.setChickAdoptionCount(chickenCount);
        tokenDTO.setNewReg(chickenCount > 0);
        String token = tokenManager.createToken(UserConstant.MEMBER_TOKEN_TYPE, wxUserBO.getMemberNum(), wxUserBO.getId(), wxUserBO.getOpenid());
        tokenDTO.setToken(token);

        return tokenDTO;
    }
}
