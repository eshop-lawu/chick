package com.lawu.chick.operator.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.chick.framework.web.impl.annotation.PageBody;
import com.lawu.chick.operator.api.conterver.RoleConverter;
import com.lawu.chick.operator.api.dto.RoleDTO;
import com.lawu.chick.operator.api.log.LogRecord;
import com.lawu.chick.operator.service.RoleService;
import com.lawu.chick.operator.service.UserRoleService;
import com.lawu.chick.operator.service.bo.RoleBO;
import com.lawu.chick.operator.service.bo.UserRoleBO;
import com.lawu.chick.operator.service.dto.constants.LogTitleEnum;
import com.lawu.chick.operator.service.dto.constants.OperationTypeEnum;
import com.lawu.chick.operator.service.param.RoleInfoParam;
import com.lawu.chick.operator.service.param.RoleParam;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.HttpCode;
import com.lawu.framework.web.Result;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;

/**
 * @author zhangyong
 * @date 2017/4/21.
 */
@Api(tags = "role")
@RestController
@RequestMapping(value = "role/")
public class RoleController extends BaseController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserRoleService userRoleService;

    @ApiOperation(value = "查询角色信息记录", notes = "查询角色信息记录 [1004，1000]（章勇）", httpMethod = "GET")
    @RequiresPermissions("role:list")
    @PageBody
    @RequestMapping(value = "findRoleList", method = RequestMethod.GET)
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    public Result<Page<RoleDTO>> findRoleList(@ModelAttribute RoleParam param) {
        Page<RoleDTO> page = new Page<>();
        Page<RoleBO> boPage = roleService.findroleList(param);
        if (boPage.getRecords().isEmpty()) {
            page.setRecords(new ArrayList<>());
            return successGet(page);
        }
        List<RoleDTO> list = new ArrayList<>();
        for (RoleBO roleBO : boPage.getRecords()) {
            RoleDTO roleDTO = RoleConverter.coverDTO(roleBO);
            list.add(roleDTO);
        }
        page.setRecords(list);
        page.setTotalCount(boPage.getTotalCount());
        page.setCurrentPage(boPage.getCurrentPage());
        return successGet(page);
    }

    @ApiOperation(value = "查询所有角色信息记录", notes = "查询所有角色信息记录 [1000]（章勇）", httpMethod = "GET")
    @PageBody
    @RequestMapping(value = "findRoleListAll", method = RequestMethod.GET)
    @RequiresAuthentication
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    public Result<List<RoleDTO>> findRoleListAll() {
        List<RoleBO> list = roleService.findroleListAll();
        if (list == null) {
            return successGet(new ArrayList<>());
        }
        List<RoleDTO> roleDTOS = new ArrayList<>();
        for (RoleBO roleBO : list) {
            RoleDTO roleDTO = RoleConverter.coverDTO(roleBO);
            roleDTOS.add(roleDTO);
        }
        return successGet(roleDTOS);
    }


    @LogRecord(type = OperationTypeEnum.ADD ,title = LogTitleEnum.ROLE_ADD)
    @ApiOperation(value = "添加角色信息", notes = "添加角色信息 [1004,1000]（章勇）", httpMethod = "POST")
    @RequiresPermissions("role:add")
    @RequestMapping(value = "addRole", method = RequestMethod.POST)
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    public Result addRole(@ModelAttribute RoleInfoParam param) {
        roleService.addRole(param);
        return successCreated();
    }

    @LogRecord(type = OperationTypeEnum.UPDATE ,title = LogTitleEnum.ROLE_UPDATE, idParamName = "id")
    @ApiOperation(value = "编辑角色信息", notes = "编辑角色信息 [1004,1000]（章勇）", httpMethod = "PUT")
    @RequiresPermissions("role:edit")
    @RequestMapping(value = "editRole/{id}", method = RequestMethod.PUT)
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    public Result updateRole(@PathVariable(value = "id") @ApiParam(value = "角色ID", required = true) Integer id, @ModelAttribute RoleInfoParam param) {

        roleService.updateRole(id, param);
        return successCreated();
    }

    @LogRecord(type = OperationTypeEnum.UPDATE ,title = LogTitleEnum.ROLE_DELETE, idParamName = "id")
    @ApiOperation(value = "删除角色信息", notes = "删除角色信息 [1004，8102,1000]（章勇）", httpMethod = "PUT")
    @RequiresPermissions("role:del")
    @ApiResponse(code = HttpCode.SC_NO_CONTENT, message = "success")
    @RequestMapping(value = "delRole/{id}", method = RequestMethod.PUT)
    public Result delRole(@PathVariable(value = "id") Integer id) {

        roleService.delRole(id);
        return successCreated();
    }


    /**
     * 权限关联
     *
     * @param roleId
     * @param permissionIds
     * @return
     */
    @ApiOperation(value = "权限关联", notes = "权限关联 [1004，2103,1000]（章勇）", httpMethod = "POST")
    @RequiresPermissions("role:addPermission")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequestMapping(value = "addRolePermission", method = RequestMethod.POST)
    public Result addRolePermission(@RequestParam(value = "roleId") Integer roleId,
                                    @RequestParam(value = "permissionIds") String permissionIds) {
        userRoleService.addRolePermission(roleId, permissionIds);
        return successCreated();
    }

    @ApiOperation(value = "根据userId查询关联的角色ID", notes = "根据userId查询关联的角色ID [1000]（章勇）", httpMethod = "GET")
    @PageBody
    @RequestMapping(value = "findRoleByUserId/{userId}", method = RequestMethod.GET)
    @RequiresAuthentication
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    public Result<List<RoleDTO>> findRoleByUserId(@PathVariable(value = "userId") Integer userId) {
        List<UserRoleBO> userRoleBOS = userRoleService.findRoleByUserId(userId);
        if (userRoleBOS.isEmpty()) {
            return successGet(new ArrayList<>());
        }
        List<RoleDTO> list = new ArrayList<>();
        for (UserRoleBO userRoleBO : userRoleBOS) {
            RoleDTO roleDTO = new RoleDTO();
            roleDTO.setId(userRoleBO.getRoleId());
            list.add(roleDTO);
        }
        return successGet(list);
    }


    @ApiOperation(value = "根据id查询角色", notes = "根据id查询角色 [1000]（章勇）", httpMethod = "GET")
    @RequestMapping(value = "findRoleById/{id}", method = RequestMethod.GET)
    @RequiresAuthentication
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    public Result<RoleDTO> findRoleById(@PathVariable(value = "id") Integer id) {
        RoleBO roleBO = roleService.findRoleById(id);
        return successGet(RoleConverter.coverDTO(roleBO));

    }

}
