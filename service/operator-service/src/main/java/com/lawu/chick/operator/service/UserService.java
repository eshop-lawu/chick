package com.lawu.chick.operator.service;


import java.util.List;

import com.lawu.chick.operator.service.bo.PerssionInfoListBO;
import com.lawu.chick.operator.service.bo.RoleBO;
import com.lawu.chick.operator.service.bo.UserBO;
import com.lawu.chick.operator.service.bo.UserListBO;
import com.lawu.chick.operator.service.param.UserPageParam;
import com.lawu.chick.operator.service.param.UserParam;
import com.lawu.framework.core.page.Page;

/**
 * @author zhangyong
 * @date 2017/4/19.
 */
public interface UserService {

    UserBO find(String account, String pwd);

    List<RoleBO> findUserRoleByUserId(Integer userId);

    List<String> findRolePermission(Integer roleId);

    UserBO find(String account);

    List<PerssionInfoListBO> findRolePermissionList(Integer id);

    Integer addUser(String account, String name, String password);

    Integer editUser(UserParam userParam);

    Page<UserListBO> findUserList(UserPageParam pageParam);

    void delUser(Integer id);

    Integer userDisabled(Integer id);

    Integer userEnable(Integer id);

    UserListBO finUserById(Integer id);

    /**
     * 根据账号查询用户
     *
     * @param account
     * @return
     */
    UserListBO getUserByAccount(String account);
}
