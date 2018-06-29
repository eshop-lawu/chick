package com.lawu.chick.operator.service;

import java.util.List;

import com.lawu.chick.operator.service.bo.UserRoleBO;


/**
 * @author zhangyong
 * @date 2017/4/20.
 */
public interface UserRoleService {
    UserRoleBO findUserRoleInfo(Integer userId, Integer roleId);

    void assignRoles(Integer userId, String roleId);

    List<UserRoleBO> findUserRoleByRoleId(Integer id);

    void addRolePermission(Integer roleId, String permissionIds);

    List<UserRoleBO> findRoleByUserId(Integer userId);

    void delUserRole(Integer userId);
}
