package com.lawu.chick.operator.api.conterver;

import com.lawu.chick.operator.api.dto.RoleDTO;
import com.lawu.chick.operator.service.bo.RoleBO;

/**
 * @author zhangyong
 * @date 2018/4/25.
 */
public class RoleConverter {
    public static RoleDTO coverDTO(RoleBO roleBO) {
        if (roleBO == null) {
            return null;
        }
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setId(roleBO.getId());
        roleDTO.setRoleName(roleBO.getRoleName());
        roleDTO.setRoleKey(roleBO.getRoleKey());
        roleDTO.setGmtCreate(roleBO.getGmtCreate());
        return roleDTO;
    }
}
