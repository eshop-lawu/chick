package com.lawu.chick.operator.api.conterver;

import java.util.ArrayList;
import java.util.List;

import com.lawu.chick.operator.api.dto.PermissionDTO;
import com.lawu.chick.operator.api.dto.PermissionListDTO;
import com.lawu.chick.operator.service.bo.PermissionBO;
import com.lawu.chick.operator.service.bo.PerssionInfoListBO;

/**
 * @author zhangyong
 * @date 2018/4/25.
 */
public class PermissionConverter {
    public static List<PermissionDTO> coverDTOS(List<PerssionInfoListBO> perssionInfoListBOS) {
        if (perssionInfoListBOS == null) {
            return null;
        }
        List<PermissionDTO> list = new ArrayList<>();

        for (PerssionInfoListBO perssionInfoListBO : perssionInfoListBOS) {
            PermissionDTO permissionDTO = new PermissionDTO();
            permissionDTO.setPermissionUrl(perssionInfoListBO.getPermissionUrl());
            permissionDTO.setId(perssionInfoListBO.getId());
            permissionDTO.setPermissionName(perssionInfoListBO.getPermissionName());
            permissionDTO.setPermissionKey(perssionInfoListBO.getPermissionKey());
            permissionDTO.setParentId(perssionInfoListBO.getParentId());
            list.add(permissionDTO);
        }
        return list;
    }

    public static PermissionListDTO coverDTO(PermissionBO permissionBO) {
        if (permissionBO == null) {
            return null;
        }
        PermissionListDTO permissionListDTO = new PermissionListDTO();
        permissionListDTO.setId(permissionBO.getId());
        permissionListDTO.setGmtCreate(permissionBO.getGmtCreate());
        permissionListDTO.setPermissionName(permissionBO.getPermissionName());
        permissionListDTO.setPermissionKey(permissionBO.getPermissionKey());
        permissionListDTO.setPermissionUrl(permissionBO.getPermissionUrl());
        permissionListDTO.setParentId(permissionBO.getParentId());
        return permissionListDTO;
    }

}
