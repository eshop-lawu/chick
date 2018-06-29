package com.lawu.chick.operator.api.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.chick.framework.web.impl.annotation.PageBody;
import com.lawu.chick.operator.api.conterver.MemberConverter;
import com.lawu.chick.operator.api.dto.MemberBaseInfoDTO;
import com.lawu.chick.service.MemberService;
import com.lawu.chick.service.bo.MemberBaseInfoBO;
import com.lawu.chick.service.param.MemberPageParam;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.HttpCode;
import com.lawu.framework.web.Result;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

/**
 * 用户管理
 * 
 * @author zhangrc
 * @createDate 2018年4月27日
 * @updateDate 2018年4月27日
 */
@Api(tags = "member", description = "用户管理")
@Validated
@RestController
@RequestMapping(value = "member/")
public class MemberController extends BaseController {
    
    @Autowired
    private MemberService memberService;
    
    @PageBody
    @ApiOperation(value = "分页查询用户", notes = "分页查询用户[]（张荣成）", httpMethod = "GET")
    @RequiresPermissions("member:page")
    @RequestMapping(value = "page", method = RequestMethod.GET)
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    public Result<Page<MemberBaseInfoDTO>> page(@ModelAttribute @Validated MemberPageParam param) {
        Page<MemberBaseInfoBO> page = memberService.page(param);
        Page<MemberBaseInfoDTO> model = new Page<>();
        model.setCurrentPage(page.getCurrentPage());
        model.setTotalCount(page.getTotalCount());
        model.setRecords(MemberConverter.convertList(page.getRecords()));
        return successGet(model);
    }
    
}
