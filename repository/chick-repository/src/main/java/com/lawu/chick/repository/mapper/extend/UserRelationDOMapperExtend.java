package com.lawu.chick.repository.mapper.extend;

import java.util.List;

import com.lawu.chick.repository.domain.extend.FriendDOView;
import com.lawu.chick.repository.param.FriendParam;

/**
 * @author zhangyong
 * @date 2018/4/25.
 */
public interface UserRelationDOMapperExtend {
    List<FriendDOView> getFriendList(FriendParam param);

    int getFriendListCount(FriendParam param);
}
