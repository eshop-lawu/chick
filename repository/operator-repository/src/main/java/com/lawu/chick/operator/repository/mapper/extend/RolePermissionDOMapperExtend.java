package com.lawu.chick.operator.repository.mapper.extend;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lawu.chick.operator.repository.domain.extend.RolePermissionDOView;


/**
 * @author zhangyong
 * @date 2017/4/19.
 */
public interface RolePermissionDOMapperExtend {

    List<RolePermissionDOView>  findRolePermission(Integer roleId);

    List<RolePermissionDOView>  findRolePermissionList(@Param("roleids") List<Integer> roleids);
}
