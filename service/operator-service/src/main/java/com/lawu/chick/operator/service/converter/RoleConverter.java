package com.lawu.chick.operator.service.converter;


import com.lawu.chick.operator.repository.domain.RoleDO;
import com.lawu.chick.operator.repository.domain.extend.UserRoleDOView;
import com.lawu.chick.operator.service.bo.RoleBO;

/**
 * @author zhangyong
 * @date 2017/4/19.
 */
public class RoleConverter {

    public static RoleBO coverBO(UserRoleDOView userRoleDOView) {
        if (userRoleDOView == null) {
            return null;
        }
        RoleBO roleBO = new RoleBO();
        roleBO.setRoleName(userRoleDOView.getRoleName());
        roleBO.setRoleKey(userRoleDOView.getRoleKey());
        roleBO.setId(userRoleDOView.getId());
        return roleBO;
    }

    public static RoleBO cover(RoleDO roleDO) {
        if (roleDO == null) {
            return null;
        }
        RoleBO roleBO = new RoleBO();
        roleBO.setId(roleDO.getId());
        roleBO.setRoleName(roleDO.getRoleName());
        roleBO.setRoleKey(roleDO.getRoleKey());
        roleBO.setGmtCreate(roleDO.getGmtCreate());
        return roleBO;
    }

}
