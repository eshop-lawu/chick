package com.lawu.chick.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.chick.service.MemberService;
import com.lawu.chick.service.MemberUserService;
import com.lawu.chick.service.WxUserService;
import com.lawu.chick.service.bo.WxUserBO;
import com.lawu.chick.service.param.WxUserParam;

/**
 * @author Leach
 * @date 2018/4/26
 */
@Service
public class MemberUserServiceImpl implements MemberUserService {

    @Autowired
    private WxUserService wxUserService;

    @Autowired
    private MemberService memberService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public WxUserBO register(WxUserParam wxUserParam) {
    	WxUserBO wxUser = wxUserService.createOrUpdate(wxUserParam);
        memberService.createMember(wxUser.getMemberNum());
        return wxUser;
    }
}
