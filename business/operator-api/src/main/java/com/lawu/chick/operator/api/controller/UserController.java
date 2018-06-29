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
import com.lawu.chick.operator.api.conterver.UserConverter;
import com.lawu.chick.operator.api.dto.UserListDTO;
import com.lawu.chick.operator.api.log.LogRecord;
import com.lawu.chick.operator.service.RoleService;
import com.lawu.chick.operator.service.UserRoleService;
import com.lawu.chick.operator.service.UserService;
import com.lawu.chick.operator.service.bo.UserListBO;
import com.lawu.chick.operator.service.dto.constants.LogTitleEnum;
import com.lawu.chick.operator.service.dto.constants.OperationTypeEnum;
import com.lawu.chick.operator.service.param.UserPageParam;
import com.lawu.chick.operator.service.param.UserParam;
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
 * @date 2017/4/20.
 */
@Api(tags = "user")
@RestController
@RequestMapping(value = "user/")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserRoleService userRoleService;

    @LogRecord(type = OperationTypeEnum.ADD ,title = LogTitleEnum.USER_ADD)
    @ApiOperation(value = "新增用户", notes = "新增用户 [1004，8107]（章勇）", httpMethod = "POST")
    @RequiresPermissions("user:add")
    @RequestMapping(value = "addUser", method = RequestMethod.POST)
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    public Result addUser(@RequestParam(value = "account") @ApiParam(value = "账号", required = true) String account,
                          @RequestParam(value = "name") @ApiParam(value = "姓名") String name,
                          @RequestParam(value = "password") @ApiParam(value = "密码", required = true) String password) {

        UserListBO userListBO = userService.getUserByAccount(account);

        if (userListBO != null) {
            return successGet(ResultCode.USER_ACCOUNT_EXIST);
        }
        userService.addUser(account, name, password);
        return successCreated();
    }

    @LogRecord(type = OperationTypeEnum.ADD ,title = LogTitleEnum.USER_UPDATE, idParamName = "param.id")
    @ApiOperation(value = "修改用户信息", notes = "修改用户信息 [1003，8107]（章勇）", httpMethod = "PUT")
    @RequiresPermissions("user:edit")
    @RequestMapping(value = "editUser", method = RequestMethod.PUT)
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    public Result editUser(@ModelAttribute UserParam userParam) {
        UserListBO userListBO = userService.getUserByAccount(userParam.getAccount());

        if (userListBO != null && !userListBO.getId().equals(userParam.getId())) {
            return successGet(ResultCode.USER_ACCOUNT_EXIST);
        }
        userService.editUser(userParam);
        return successCreated();
    }

    @ApiOperation(value = "查询用户列表", notes = "查询用户列表 [1004]（章勇）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequiresPermissions("user:list")
    @PageBody
    @RequestMapping(value = "findUserList", method = RequestMethod.GET)
    public Result<Page<UserListDTO>> findUserList(@ModelAttribute UserPageParam pageParam) {
        Page<UserListDTO> userListDTOPage = new Page<>();
        Page<UserListBO> boPage = userService.findUserList(pageParam);
        if (boPage.getRecords().isEmpty()) {
            userListDTOPage.setRecords(new ArrayList<>());
            userListDTOPage.setTotalCount(0);
            return successGet(userListDTOPage);
        }
        List<UserListDTO> userListDTOS = UserConverter.coverDTOS(boPage.getRecords());
        userListDTOPage.setTotalCount(boPage.getTotalCount());
        userListDTOPage.setCurrentPage(boPage.getCurrentPage());
        userListDTOPage.setRecords(userListDTOS);
        return successGet(userListDTOPage);

    }

    @ApiOperation(value = "分配角色", notes = "分配角色 [1004，8101，1005，1000]（章勇）", httpMethod = "POST")
    @RequiresPermissions("user:addRole")
    @RequestMapping(value = "assignRoles", method = RequestMethod.POST)
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    public Result assignRoles(@RequestParam(value = "userId") Integer userId, @RequestParam(value = "roleId") String roleId) {
        if (StringUtils.isEmpty(roleId)) {
            userRoleService.delUserRole(userId);
            return successCreated();
        }
        userRoleService.assignRoles(userId, roleId);
        return successCreated();
    }

    @LogRecord(type = OperationTypeEnum.DELETE ,title = LogTitleEnum.USER_DELETE, idParamName = "id")
    @ApiOperation(value = "删除用户", notes = "删除用户 （章勇）", httpMethod = "PUT")
    @RequiresPermissions("user:del")
    @ApiResponse(code = HttpCode.SC_NO_CONTENT, message = "success")
    @RequestMapping(value = "delUser/{id}", method = RequestMethod.PUT)
    public Result delUser(@PathVariable(value = "id") Integer id) {
        userService.delUser(id);
        return successCreated();
    }

    @LogRecord(type = OperationTypeEnum.UPDATE ,title = LogTitleEnum.USER_DISABLE, idParamName = "id")
    @ApiOperation(value = "禁用用户", notes = "禁用用户 [1004,1019,1000]（章勇）", httpMethod = "PUT")
    @RequiresPermissions("user:disable")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequestMapping(value = "userDisabled/{id}", method = RequestMethod.PUT)
    public Result userDisabled(@PathVariable(value = "id") Integer id) {
        userService.userDisabled(id); 
        return successCreated();
    }

    @LogRecord(type = OperationTypeEnum.UPDATE ,title = LogTitleEnum.USER_ENABLE, idParamName = "id")
    @ApiOperation(value = "启用用户", notes = "启用用户 [1004,1019,1000]（梅述全）", httpMethod = "PUT")
    @RequiresPermissions("user:enable")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequestMapping(value = "userEnable/{id}", method = RequestMethod.PUT)
    public Result userEnable(@PathVariable(value = "id") Integer id) {
        userService.userEnable(id);
        return successCreated();
    }

    @ApiOperation(value = "查询用户详情", notes = "查询用户详情（章勇）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequiresAuthentication
    @RequestMapping(value = "findUserById/{id}", method = RequestMethod.GET)
    public Result<UserListDTO> findUserById(@PathVariable(value = "id") Integer id) {

        UserListBO userListBO = userService.finUserById(id);
        if (userListBO == null) {
            return successGet(ResultCode.RESOURCE_NOT_FOUND);
        }
        return successGet(UserConverter.coverDTO(userListBO));
    }

    @LogRecord(type = OperationTypeEnum.UPDATE ,title = LogTitleEnum.RESET_PWD, idParamName = "id")
    @ApiOperation(value = "重置密码", notes = "重置密码 [1003]（章勇）", httpMethod = "PUT")
    @RequiresPermissions("user:resetPwd")
    @RequestMapping(value = "resetPwd", method = RequestMethod.PUT)
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    public Result resetPwd(@ModelAttribute UserParam userParam) {
        userService.editUser(userParam);
        return successCreated();
    }

}
