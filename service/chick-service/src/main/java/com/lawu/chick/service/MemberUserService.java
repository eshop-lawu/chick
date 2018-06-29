package com.lawu.chick.service;

import com.lawu.chick.service.bo.WxUserBO;
import com.lawu.chick.service.param.WxUserParam;

/**
 * @author Leach
 * @date 2018/4/26
 */
public interface MemberUserService {

    /**
     * 注册用户信息
     * @param wxUserParam
     * @return
     */
    WxUserBO register(WxUserParam wxUserParam);
    
}
