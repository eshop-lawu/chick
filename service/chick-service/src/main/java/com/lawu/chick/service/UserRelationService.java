package com.lawu.chick.service;

import java.util.List;

import com.lawu.chick.service.bo.UserRelationBO;
import com.lawu.chick.repository.param.FriendParam;

/**
 * @author zhangyong
 * @date 2018/4/25.
 */
public interface UserRelationService {

    void addFriend(String memberNum, String friendNum);

    List<UserRelationBO> getFriendList(FriendParam param);

    int getFriendListCount(FriendParam param);

    Boolean isFriend(String memberNum, String friendNum);
}
