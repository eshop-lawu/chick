package com.lawu.chick.operator.api.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawu.chick.operator.api.dto.UserDTO;
import com.lawu.chick.operator.api.dto.UserRoleDTO;
import com.lawu.chick.operator.service.UserService;
import com.lawu.chick.operator.service.bo.RoleBO;
import com.lawu.chick.operator.service.bo.UserBO;
import com.lawu.shiro.AuthService;
import com.lawu.shiro.model.ShiroRole;
import com.lawu.shiro.model.ShiroUser;


/**
 * @author Leach
 * @date 2017/4/18
 */
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserService userService;

    @Override
    public ShiroUser find(String account) {

        UserBO userBO = userService.find(account);
        if (userBO == null) {
            return null;
        }
        UserDTO userDTO = getUserRoleKey(userBO.getId());

        ShiroUser shiroUser = new ShiroUser();
        shiroUser.setAccount(userBO.getAccount());
        shiroUser.setPassword(userBO.getPassword());
        shiroUser.setRolesKey(userDTO.getRolesKey());
        List<ShiroRole> roles = new ArrayList<>();
        for (UserRoleDTO userRoleDTO : userDTO.getRoles()) {
            ShiroRole shiroRole = new ShiroRole();
            shiroRole.setRoleKey(userRoleDTO.getRoleKey());
            shiroRole.setPermissionsKey(userRoleDTO.getPermissionsKey());
            roles.add(shiroRole);
        }
        shiroUser.setRoles(roles);
        shiroUser.setRolesKey(userDTO.getRolesKey());
        return shiroUser;
    }

    @Override
    public ShiroUser find(String account, String password) {
        UserBO userBO = userService.find(account, password);
        if (userBO == null) {
            return null;
        }

        ShiroUser shiroUser = new ShiroUser();
        shiroUser.setAccount(userBO.getAccount());
        shiroUser.setPassword(userBO.getPassword());
        UserDTO userDTO = getUserRoleKey(userBO.getId());
        shiroUser.setRolesKey(userDTO.getRolesKey());
        List<ShiroRole> roles = new ArrayList<>();
        for (UserRoleDTO userRoleDTO : userDTO.getRoles()) {
            ShiroRole shiroRole = new ShiroRole();
            shiroRole.setPermissionsKey(userRoleDTO.getPermissionsKey());
            shiroRole.setRoleKey(userRoleDTO.getRoleKey());
            roles.add(shiroRole);
        }
        shiroUser.setRoles(roles);
        shiroUser.setRolesKey(userDTO.getRolesKey());
        return shiroUser;
    }


    private UserDTO getUserRoleKey(Integer userId) {
        List<RoleBO> roleBOList = userService.findUserRoleByUserId(userId);
        List<UserRoleDTO> userRoleDTOS = new ArrayList<>();
        Set<String> rolesKeys = new HashSet<>();
        UserRoleDTO userRoleDTO;
        if (!roleBOList.isEmpty()) {
            //查询role对应的permission_key
            for (RoleBO roleBO : roleBOList) {
                rolesKeys.add(roleBO.getRoleKey());
                userRoleDTO = new UserRoleDTO();
                userRoleDTO.setRoleKey(roleBO.getRoleKey());
                List<String> permissionKeyList = userService.findRolePermission(roleBO.getId());
                userRoleDTO.setPermissionsKey(permissionKeyList);
                userRoleDTOS.add(userRoleDTO);
            }
        }
        UserDTO userDTO = new UserDTO();
        userDTO.setRoles(userRoleDTOS);
        userDTO.setRolesKey(rolesKeys);

        return userDTO;
    }

}
