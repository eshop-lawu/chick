package com.lawu.chick.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.chick.id.worker.generate.impl.BizIdType;
import com.lawu.chick.id.worker.generate.impl.IdWorkerHelper;
import com.lawu.chick.repository.domain.WxUserDO;
import com.lawu.chick.repository.domain.WxUserDOExample;
import com.lawu.chick.repository.mapper.WxUserDOMapper;
import com.lawu.chick.service.WxUserService;
import com.lawu.chick.service.bo.WxUserBO;
import com.lawu.chick.service.converter.WxUserConverter;
import com.lawu.chick.service.param.WxUserParam;

/**
 * @author Leach
 * @date 2018/4/26
 */
@Service
public class WxUserServiceImpl implements WxUserService {

    @Autowired
    private WxUserDOMapper wxUserDOMapper;

    @Autowired
    private IdWorkerHelper idWorkerHelper;

    /**
     * 根据openid查找微信用户信息
     * @param openid
     * @return
     */
    private WxUserDO select(String openid) {

        WxUserDOExample example = new WxUserDOExample();
        example.createCriteria().andOpenidEqualTo(openid);
        List<WxUserDO> wxUserDOs = wxUserDOMapper.selectByExample(example);
        return wxUserDOs.isEmpty() ? null : wxUserDOs.get(0);
    }

    @Override
    public WxUserBO find(String openid) {
        WxUserDO wxUserDO = select(openid);
        return WxUserConverter.convert(wxUserDO);

    }

    @Override
    public WxUserBO findByNum(String memberNum) {

        WxUserDOExample example = new WxUserDOExample();
        example.createCriteria().andMemberNumEqualTo(memberNum);
        List<WxUserDO> wxUserDOs = wxUserDOMapper.selectByExample(example);
        return wxUserDOs.isEmpty() ? null : WxUserConverter.convert(wxUserDOs.get(0));
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public WxUserBO createOrUpdate(WxUserParam wxUserParam) {
        WxUserDO wxUserDO = select(wxUserParam.getOpenid());
        if (wxUserDO == null) {
            wxUserDO = new WxUserDO();
            wxUserDO.setOpenid(wxUserParam.getOpenid());
            wxUserDO.setMemberNum(idWorkerHelper.generate(BizIdType.MEMBER));
            wxUserDO.setGmtCreate(new Date());
        }
        wxUserDO.setProvince(wxUserParam.getProvince());
        wxUserDO.setNickname(wxUserParam.getNickname());
        wxUserDO.setLanguage(wxUserParam.getLanguage());
        wxUserDO.setAvatarurl(wxUserParam.getAvatarUrl());
        wxUserDO.setCity(wxUserParam.getCity());
        wxUserDO.setCountry(wxUserParam.getCountry());
        wxUserDO.setGender(wxUserParam.getGender());
        wxUserDO.setGmtModified(new Date());
        wxUserDO.setUnionid(wxUserParam.getUnionId());
        wxUserDO.setSubscribe(wxUserParam.getSubscribe());
        wxUserDO.setSubscribeTime(wxUserParam.getSubscribeTime());
        
        if (wxUserDO.getId() == null || wxUserDO.getId() <= 0) {
            wxUserDOMapper.insert(wxUserDO);
        } else {
            wxUserDOMapper.updateByPrimaryKeySelective(wxUserDO);
        }
        
        WxUserBO wxUser = WxUserConverter.convert(wxUserDO);
        return wxUser;
    }
}
