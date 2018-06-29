package com.lawu.chick.service.converter;

import java.util.ArrayList;
import java.util.List;

import com.lawu.chick.repository.domain.WxUserDO;
import com.lawu.chick.repository.domain.extend.MemberBaseInfoDOView;
import com.lawu.chick.service.bo.MemberBaseInfoBO;
import com.lawu.chick.service.bo.WxUserBO;

/**
 * @author Leach
 * @date 2018/4/26
 */
public class WxUserConverter {

    public static WxUserBO convert(WxUserDO wxUserDO) {
        if (wxUserDO == null) {
            return null;
        }
        WxUserBO wxUserBO = new WxUserBO();
        wxUserBO.setId(wxUserDO.getId());
        wxUserBO.setMemberNum(wxUserDO.getMemberNum());
        wxUserBO.setOpenid(wxUserDO.getOpenid());
        wxUserBO.setNickname(wxUserDO.getNickname());
        wxUserBO.setAvatarUrl(wxUserDO.getAvatarurl());
        return wxUserBO;
    }
    
    
    public static List<MemberBaseInfoBO> convertList(List<MemberBaseInfoDOView> listView) {
    	List<MemberBaseInfoBO> list = new ArrayList<>();
        if (listView.isEmpty()) {
            return list;
        }
        
        for (MemberBaseInfoDOView memberBaseInfoDOView : listView) {
        	MemberBaseInfoBO wxUserBO = new MemberBaseInfoBO();
            wxUserBO.setMemberNum(memberBaseInfoDOView.getMemberNum());
            wxUserBO.setEggs(memberBaseInfoDOView.getEggs());
            wxUserBO.setNickname(memberBaseInfoDOView.getNickname());
            wxUserBO.setAvatarUrl(memberBaseInfoDOView.getAvatarUrl());
            wxUserBO.setGmtCreate(memberBaseInfoDOView.getGmtCreate());
            list.add(wxUserBO);
		}
       
        return list;
    }
}
