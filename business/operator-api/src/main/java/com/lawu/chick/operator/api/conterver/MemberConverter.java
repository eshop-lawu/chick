package com.lawu.chick.operator.api.conterver;

import java.util.ArrayList;
import java.util.List;

import com.lawu.chick.operator.api.dto.MemberBaseInfoDTO;
import com.lawu.chick.service.bo.MemberBaseInfoBO;

/**
 * @author zhangrc
 * @date 2018/4/27
 */
public class MemberConverter {

    
    public static List<MemberBaseInfoDTO> convertList(List<MemberBaseInfoBO> listBO) {
    	List<MemberBaseInfoDTO> list = new ArrayList<>();
        if (listBO.isEmpty()) {
            return list;
        }
        
        for (MemberBaseInfoBO memberBaseInfoBO : listBO) {
        	MemberBaseInfoDTO wxUserDTO = new MemberBaseInfoDTO();
        	wxUserDTO.setMemberNum(memberBaseInfoBO.getMemberNum());
        	wxUserDTO.setEggs(memberBaseInfoBO.getEggs());
        	wxUserDTO.setNickname(memberBaseInfoBO.getNickname());
        	wxUserDTO.setAvatarUrl(memberBaseInfoBO.getAvatarUrl());
        	wxUserDTO.setGmtCreate(memberBaseInfoBO.getGmtCreate());
            list.add(wxUserDTO);
		}
       
        return list;
    }
}
