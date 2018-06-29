package com.lawu.chick.operator.service;


import java.util.List;

import com.lawu.chick.operator.service.bo.RoleBO;
import com.lawu.chick.operator.service.param.RoleInfoParam;
import com.lawu.chick.operator.service.param.RoleParam;
import com.lawu.framework.core.page.Page;


/**
 * @author zhangyong
 * @date 2017/4/21.
 */
public interface RoleService {
    Page<RoleBO> findroleList(RoleParam param);

    Integer addRole(RoleInfoParam param);

    Integer updateRole(Integer id, RoleInfoParam param);

    void delRole(Integer id);

    List<RoleBO> findroleListAll();


    RoleBO findRoleById(Integer id);
}
