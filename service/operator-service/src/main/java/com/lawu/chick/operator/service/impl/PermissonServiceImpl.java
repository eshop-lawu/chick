package com.lawu.chick.operator.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.chick.operator.repository.domain.PermissionDO;
import com.lawu.chick.operator.repository.domain.PermissionDOExample;
import com.lawu.chick.operator.repository.domain.RolePermissionDO;
import com.lawu.chick.operator.repository.domain.RolePermissionDOExample;
import com.lawu.chick.operator.repository.mapper.PermissionDOMapper;
import com.lawu.chick.operator.repository.mapper.RolePermissionDOMapper;
import com.lawu.chick.operator.service.PermissonService;
import com.lawu.chick.operator.service.bo.PermissionBO;
import com.lawu.chick.operator.service.converter.PermissionConverter;
import com.lawu.chick.operator.service.param.PermissionParam;
import com.lawu.chick.operator.service.param.PerssionParam;
import com.lawu.framework.core.page.Page;

/**
 * @author zhangyong
 * @date 2017/4/20.
 */
@Service
public class PermissonServiceImpl implements PermissonService {
    @Autowired
    private PermissionDOMapper permissionDOMapper;

    @Autowired
    private RolePermissionDOMapper rolePermissionDOMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer addPerssion(PerssionParam perssionParam) {
        PermissionDO permissionDO = new PermissionDO();
        permissionDO.setParentId(perssionParam.getParentId());
        permissionDO.setPermissionKey(perssionParam.getPermissionKey());
        permissionDO.setPermissionName(perssionParam.getPermissionName());
        permissionDO.setPermissionUrl(perssionParam.getPermissionUrl());
        permissionDO.setSortId(999);
        permissionDO.setGmtCreate(new Date());
        permissionDO.setGmtModified(new Date());
        Integer row = permissionDOMapper.insert(permissionDO);
        return row;
    }

    @Override
    public Page<PermissionBO> findPerminnionList(PermissionParam param) {
        PermissionDOExample example = new PermissionDOExample();
        if(StringUtils.isNotEmpty(param.getPermissionName())){
            example.createCriteria().andPermissionNameLike("%"+param.getPermissionName()+"%");
        }
        example.setOrderByClause("id desc");
        RowBounds bounds = new RowBounds(param.getOffset(), param.getPageSize());
        int total = (int) permissionDOMapper.countByExample(example);

        List<PermissionDO> list = permissionDOMapper.selectByExampleWithRowbounds(example, bounds);
        if (list.isEmpty()) {
            return new Page<>();
        }
        List<PermissionBO> permissionBOS = new ArrayList<>();
        for (PermissionDO p : list) {
            PermissionBO permissionBO = PermissionConverter.cover(p);
            permissionBOS.add(permissionBO);
        }
        Page<PermissionBO> page = new Page<>();
        page.setCurrentPage(param.getCurrentPage());
        page.setTotalCount(total);
        page.setRecords(permissionBOS);
        return page;
    }

    @Override
    public List<PermissionBO> findAllPermissionList() {
        PermissionDOExample example = new PermissionDOExample();
        example.setOrderByClause("id asc");
        List<PermissionDO> permissionDOS = permissionDOMapper.selectByExample(example);
        if (permissionDOS.isEmpty()) {
            return new ArrayList<>();
        }
        List<PermissionBO> permissionBOS = new ArrayList<>();
        for (PermissionDO p : permissionDOS) {
            PermissionBO permissionBO = PermissionConverter.cover(p);
            permissionBOS.add(permissionBO);
        }
        return permissionBOS;
    }

    @Override
    public List<PermissionBO> findPermissionListByRoleId(Integer roleId) {
        RolePermissionDOExample example = new RolePermissionDOExample();
        example.createCriteria().andRoleIdEqualTo(roleId);
        example.setOrderByClause("id asc");
        List<RolePermissionDO> permissionDOS = rolePermissionDOMapper.selectByExample(example);
        if (permissionDOS.isEmpty()) {
            return new ArrayList<>();
        }

        return PermissionConverter.coverRolePermissionDTOS(permissionDOS);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delPermission(String permissionIds) {
        String[] idString = permissionIds.split(",");
        List<Integer> ids = new ArrayList<>();
        for (int i = 0; i < idString.length; i++) {
            ids.add(Integer.valueOf(idString[i]));
        }
        //删除关联信息
        RolePermissionDOExample example = new RolePermissionDOExample();
        example.createCriteria().andPermissionIdIn(ids);
        rolePermissionDOMapper.deleteByExample(example);

        PermissionDOExample example1 = new PermissionDOExample();
        example1.createCriteria().andIdIn(ids);
        permissionDOMapper.deleteByExample(example1);
    }

    @Override
    public PermissionBO findPermissionInfoById(Integer id) {
        PermissionDO permissionDO= permissionDOMapper.selectByPrimaryKey(id);
        PermissionBO permissionBO = PermissionConverter.cover(permissionDO);
        return permissionBO;
    }

    @Override
    public void editPermission(Integer id, PerssionParam perssionParam) {
        PermissionDO permissionDO = new PermissionDO();
        permissionDO.setParentId(perssionParam.getParentId());
        permissionDO.setPermissionKey(perssionParam.getPermissionKey());
        permissionDO.setPermissionName(perssionParam.getPermissionName());
        permissionDO.setPermissionUrl(perssionParam.getPermissionUrl());
        permissionDO.setGmtModified(new Date());
        permissionDO.setGmtCreate(new Date());
        permissionDO.setId(id);
        permissionDOMapper.updateByPrimaryKeySelective(permissionDO);
    }
}
