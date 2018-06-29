package com.lawu.chick.operator.service;


import java.util.List;

import com.lawu.chick.operator.service.bo.PermissionBO;
import com.lawu.chick.operator.service.param.PermissionParam;
import com.lawu.chick.operator.service.param.PerssionParam;
import com.lawu.framework.core.page.Page;


/**
 * @author zhangyong
 * @date 2017/4/20.
 */
public interface PermissonService {
     Integer addPerssion(PerssionParam perssionParam);

     Page<PermissionBO> findPerminnionList(PermissionParam param);

    List<PermissionBO> findAllPermissionList();

    List<PermissionBO> findPermissionListByRoleId(Integer roleId);

    void delPermission(String permissionIds);

    PermissionBO findPermissionInfoById(Integer id);

    void editPermission(Integer id, PerssionParam perssionParam);
}
