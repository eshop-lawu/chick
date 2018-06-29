package com.lawu.chick.operator.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.chick.framework.web.impl.ResultCode;
import com.lawu.chick.framework.web.impl.annotation.PageBody;
import com.lawu.chick.operator.api.conterver.PermissionConverter;
import com.lawu.chick.operator.api.dto.PermissionDTO;
import com.lawu.chick.operator.api.dto.PermissionListDTO;
import com.lawu.chick.operator.api.log.LogRecord;
import com.lawu.chick.operator.service.PermissonService;
import com.lawu.chick.operator.service.UserService;
import com.lawu.chick.operator.service.bo.PermissionBO;
import com.lawu.chick.operator.service.bo.PerssionInfoListBO;
import com.lawu.chick.operator.service.bo.UserBO;
import com.lawu.chick.operator.service.dto.constants.LogTitleEnum;
import com.lawu.chick.operator.service.dto.constants.OperationTypeEnum;
import com.lawu.chick.operator.service.param.PermissionParam;
import com.lawu.chick.operator.service.param.PerssionParam;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.HttpCode;
import com.lawu.framework.web.Result;
import com.lawu.shiro.util.UserUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;


/**
 * @author zhangyong
 * @date 2017/4/20.
 */
@Api(tags = "permission")
@RestController
@RequestMapping(value = "permission/")
public class PermissionController extends BaseController {
    @Autowired
    private PermissonService permissonService;

    @Autowired
    private UserService userService;


    @ApiOperation(value = "查询权限信息", notes = "查询权限信息 [8100]（章勇）", httpMethod = "GET")
    @RequestMapping(value = "getPermission", method = RequestMethod.GET)
    @RequiresAuthentication
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    public Result<List<PermissionDTO>> getPermission() {

        String account = UserUtil.getCurrentUserAccount();
        if (StringUtils.isEmpty(account)) {
            return successGet(ResultCode.USER_NOT_LOGIN);
        }
        UserBO userBO = userService.find(account);
        List<PerssionInfoListBO> permissionInfoListBOS = userService.findRolePermissionList(userBO.getId());
        return successGet( PermissionConverter.coverDTOS(permissionInfoListBOS));
    }

    @LogRecord(type = OperationTypeEnum.ADD ,title = LogTitleEnum.PERMSION_ADD)
    @ApiOperation(value = "新增权限", notes = "新增权限 [1004，1005]（章勇）", httpMethod = "POST")
    @RequiresPermissions("permission:add")
    @RequestMapping(value = "addPermission", method = RequestMethod.POST)
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    public Result addPermission(@ModelAttribute PerssionParam perssionParam) {
         permissonService.addPerssion(perssionParam);
        return successCreated();
    }

    @ApiOperation(value = "查询权限记录列表", notes = "查询权限记录列表 [1004，1000]（章勇）", httpMethod = "GET")
     @RequiresPermissions("permission:list")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @PageBody
    @RequestMapping(value = "findPermissionList", method = RequestMethod.GET)
    public Result<Page<PermissionListDTO>> findPermissionList(@ModelAttribute PermissionParam param) {
        Page<PermissionListDTO> pages = new Page<>();
        Page<PermissionBO> boPage = permissonService.findPerminnionList(param);
        if (boPage == null || boPage.getRecords().isEmpty()) {
            pages.setRecords(new ArrayList<>());
            return successGet(pages);
        }
        List<PermissionListDTO> listDTOList = new ArrayList<>();
        for (PermissionBO permissionBO : boPage.getRecords()) {
            PermissionListDTO permissionListDTO = PermissionConverter.coverDTO(permissionBO);
            listDTOList.add(permissionListDTO);
        }
        pages.setTotalCount(boPage.getTotalCount());
        pages.setCurrentPage(boPage.getCurrentPage());
        pages.setRecords(listDTOList);
        return successGet(pages);
    }

    @ApiOperation(value = "查询权限记录列表", notes = "查询权限记录列表 [1004，1000]（章勇）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequiresAuthentication
    @RequestMapping(value = "findAllPermissionList", method = RequestMethod.GET)
    public Result<List<PermissionListDTO>> findAllPerminnionList() {
        List<PermissionBO> permissionBOS = permissonService.findAllPermissionList();
        if (permissionBOS.isEmpty()) {
            return successGet(new ArrayList<>());
        }
        List<PermissionListDTO> listDTOList = new ArrayList<>();
        for (PermissionBO permissionBO : permissionBOS) {
            PermissionListDTO permissionListDTO = PermissionConverter.coverDTO(permissionBO);
            listDTOList.add(permissionListDTO);
        }
        return successGet(listDTOList);
    }


    @ApiOperation(value = "根据roleId查询权限记录ID", notes = "根据roleId查询权限记录ID [1000]（章勇）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequiresAuthentication
    @RequestMapping(value = "findPermissionListByRoleId/{roleId}", method = RequestMethod.GET)
    public Result<List<PermissionListDTO>> findPermissionListByRoleId(@PathVariable(value = "roleId") Integer roleId) {
        List<PermissionBO> permissionBOS = permissonService.findPermissionListByRoleId(roleId);
        if (permissionBOS.isEmpty()) {
            return successGet(new ArrayList<>());
        }
        List<PermissionListDTO> listDTOList = new ArrayList<>();
        for (PermissionBO permissionBO : permissionBOS) {
            PermissionListDTO permissionListDTO = new PermissionListDTO();
            permissionListDTO.setId(permissionBO.getId());
            listDTOList.add(permissionListDTO);
        }
        return successGet(listDTOList);
    }

    @LogRecord(type =OperationTypeEnum.DELETE ,title = LogTitleEnum.PERMSION_DELETE)
    @ApiOperation(value = "删除权限记录", notes = "删除权限记录 [1004，1000]（章勇）", httpMethod = "POST")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequiresPermissions("permission:del")
    @RequestMapping(value = "delPermission", method = RequestMethod.POST)
    public Result delPermission(@RequestParam(value = "permissionIds") String permissionIds) {
        permissonService.delPermission(permissionIds);
        return successCreated();
    }

    @ApiOperation(value = "根据ID查询权限信息", notes = "根据ID查询权限信息 [1004，1000]（章勇）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequiresAuthentication
    @RequestMapping(value = "findPermissionInfoById/{id}", method = RequestMethod.GET)
    public Result<PermissionListDTO> findPermissionInfoById(@PathVariable(value = "id") Integer id) {
        PermissionBO permissionBO = permissonService.findPermissionInfoById(id);
        if (permissionBO == null) {
            return successGet();
        }
        return successGet( PermissionConverter.coverDTO(permissionBO));
    }

    @LogRecord(type =OperationTypeEnum.UPDATE ,title = LogTitleEnum.PERMSION_UPDATE,idParamName ="id")
    @ApiOperation(value = "编辑权限信息", notes = "编辑权限信息 [1004，1000]（章勇）", httpMethod = "PUT")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequiresPermissions("permission:edit")
    @RequestMapping(value = "editPermission/{id}", method = RequestMethod.PUT)
    public Result editPermission(@PathVariable(value = "id") Integer id, @ModelAttribute PerssionParam perssionParam) {
        permissonService.editPermission(id, perssionParam);
        return successCreated();
    }

}
