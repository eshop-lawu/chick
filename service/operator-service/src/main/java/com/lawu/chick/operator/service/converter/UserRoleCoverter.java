package com.lawu.chick.operator.service.converter;


import com.lawu.chick.operator.repository.domain.UserRoleDO;
import com.lawu.chick.operator.service.bo.UserRoleBO;

/**
 * @author zhangyong
 * @date 2017/4/20.
 */
public class UserRoleCoverter {

    public static UserRoleBO cover(UserRoleDO userRoleDO) {
        if (userRoleDO == null){
            return  null;
        }
        UserRoleBO userRoleBO = new UserRoleBO();
        userRoleBO.setId(userRoleDO.getId());
        userRoleBO.setRoleId(userRoleDO.getRoleId());
        userRoleBO.setUserId(userRoleDO.getUserId());
        return userRoleBO;
    }
}
