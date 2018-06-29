package com.lawu.chick.operator.service.converter;

import java.util.ArrayList;
import java.util.List;

import com.lawu.chick.operator.repository.domain.PermissionDO;
import com.lawu.chick.operator.repository.domain.RolePermissionDO;
import com.lawu.chick.operator.repository.domain.extend.RolePermissionDOView;
import com.lawu.chick.operator.service.bo.PermissionBO;


/**
 * @author zhangyong
 * @date 2017/4/19.
 */
public class PermissionConverter {
    public static PermissionBO coverBO(RolePermissionDOView rolePermissionDOView) {
        if (rolePermissionDOView == null) {
            return null;
        }
        PermissionBO permissionBO = new PermissionBO();
        permissionBO.setPermissionName(rolePermissionDOView.getPermissionName());
        permissionBO.setPermissionKey(rolePermissionDOView.getPermissionKey());
        permissionBO.setId(rolePermissionDOView.getId());
        return permissionBO;
    }

    public static PermissionBO cover(PermissionDO p) {
        if (p == null) {
            return null;
        }
        PermissionBO permissionBO = new PermissionBO();
        permissionBO.setId(p.getId());
        permissionBO.setGmtCreate(p.getGmtCreate());
        permissionBO.setPermissionName(p.getPermissionName());
        permissionBO.setPermissionKey(p.getPermissionKey());
        permissionBO.setPermissionUrl(p.getPermissionUrl());
        permissionBO.setParentId(p.getParentId());
        return permissionBO;
    }



    public static List<PermissionBO> coverRolePermissionDTOS(List<RolePermissionDO> permissionDOS) {
        if (permissionDOS.isEmpty()) {
            return null;
        }
        List<PermissionBO> list = new ArrayList<>();
        for (RolePermissionDO rolePermissionDO : permissionDOS) {
            PermissionBO permissionBO = new PermissionBO();
            permissionBO.setId(rolePermissionDO.getPermissionId());
            list.add(permissionBO);
        }

        return list;
    }

}
