package com.lawu.chick.service.converter;

import java.util.ArrayList;
import java.util.List;

import com.lawu.chick.repository.domain.extend.FriendDOView;
import com.lawu.chick.service.bo.UserRelationBO;

/**
 * @author zhangyong
 * @date 2018/4/25.
 */
public class UserRelationConverter {
    public static List<UserRelationBO> convertBOS(List<FriendDOView> friendDOList) {
        if (friendDOList.isEmpty()) {
            return new ArrayList<>();
        }
        List<UserRelationBO> relationBOS = new ArrayList<>();
        UserRelationBO relationBO;
        for (FriendDOView doView : friendDOList) {
            relationBO = new UserRelationBO();
            relationBO.setAvatarUrl(doView.getAvatarUrl());
            relationBO.setFriendNum(doView.getFriendNum());
            relationBO.setGender(doView.getGender());
            relationBO.setNickName(doView.getNickName());
            relationBO.setEggs(doView.getEggs());
            relationBOS.add(relationBO);
        }
        return relationBOS;
    }
}
