package com.lawu.chick.operator.repository.mapper.extend;

import java.util.List;

import com.lawu.chick.operator.repository.domain.extend.UserRoleDOView;


/**
 * @author zhangyong
 * @date 2017/4/19.
 */
public interface UserRoleDOMapperExtend {
    /**
     * @author zhangyong
     * @date 2017/4/19.
     */
     List<UserRoleDOView> findUserRoleByUserId(Integer userId);
}
