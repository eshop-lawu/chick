package com.lawu.chick.api.converter;

import java.util.ArrayList;
import java.util.List;

import com.lawu.chick.api.dto.FriendListDTO;
import com.lawu.chick.service.bo.UserRelationBO;

/**
 * @author zhangyong
 * @date 2018/4/25.
 */
public class UserRelationConverter {
    public static List<FriendListDTO> covertDTOS(List<UserRelationBO> relationBOS) {
        if (relationBOS.isEmpty()) {
            return new ArrayList<>();
        }
        List<FriendListDTO> listDTOS = new ArrayList<>();
        FriendListDTO listDTO;
        for (UserRelationBO relationBO : relationBOS) {
            listDTO = new FriendListDTO();
            listDTO.setAvatarUrl(relationBO.getAvatarUrl());
            listDTO.setFriendNum(relationBO.getFriendNum());
            listDTO.setNickName(relationBO.getNickName());
            listDTO.setEggs(relationBO.getEggs());
            listDTOS.add(listDTO);
        }
        return listDTOS;
    }
}
