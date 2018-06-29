package com.lawu.chick.api.controller;

import java.util.List;

import com.lawu.chick.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.authorization.annotation.Authorization;
import com.lawu.authorization.util.UserUtil;
import com.lawu.chick.api.authorization.impl.UserConstant;
import com.lawu.chick.api.converter.UserRelationConverter;
import com.lawu.chick.api.dto.FriendListDTO;
import com.lawu.chick.framework.web.impl.ResultCode;
import com.lawu.chick.repository.param.FriendParam;
import com.lawu.chick.service.UserRelationService;
import com.lawu.chick.service.bo.UserRelationBO;
import com.lawu.chick.service.enums.ChickenCureTaskTypeEnum;
import com.lawu.chick.service.event.EventPublisher;
import com.lawu.chick.service.param.CommonPageParam;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.HttpCode;
import com.lawu.framework.web.Result;
import com.lawu.framework.web.doc.annotation.Audit;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

/**
 * @author zhangyong
 * @date 2018/4/25.
 */
@Api(tags = "userRelation")
@RestController
@RequestMapping(value = "userRelation/")
public class UserRelationController extends BaseController {

    @Autowired
    private UserRelationService userRelationService;

    @Autowired
    private MemberService memberService;

    @Audit(date = "2018-04-28", reviewer = "孙林青")
    @Authorization
    @ApiOperation(value = "添加好友", notes = "添加好友[2004，1000]（章勇）", httpMethod = "POST")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequestMapping(value = "addFriend", method = RequestMethod.POST)
    public Result addFriend(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token, @RequestParam("friendNum") String friendNum) {
        Long id = memberService.getMemberId(friendNum);
        if (id == null || id <= 0) {
            return successCreated(ResultCode.RESOURCE_NOT_FOUND);
        }
        String memberNum = UserUtil.getCurrentUserNum(getRequest());
        Boolean flag = userRelationService.isFriend(memberNum, friendNum);
        if (flag) {
            return successCreated(ResultCode.IS_FRIEND);
        }
        userRelationService.addFriend(memberNum, friendNum);
        return successCreated();
    }

    @Audit(date = "2018-04-28", reviewer = "孙林青")
    @Authorization
    @ApiOperation(value = "好友列表", notes = "好友列表[]（章勇）", httpMethod = "POST")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequestMapping(value = "getFriendList", method = RequestMethod.POST)
    public Result<Page<FriendListDTO>> getFriendList(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,
                                                     @ModelAttribute CommonPageParam param) {
        String memberNum = UserUtil.getCurrentUserNum(getRequest());
        FriendParam friendParam = new FriendParam();
        friendParam.setMemberNum(memberNum);
        friendParam.setCurrentPage(param.getCurrentPage());
        friendParam.setPageSize(param.getPageSize());
        List<UserRelationBO> relationBOS = userRelationService.getFriendList(friendParam);
        Page<FriendListDTO> page = new Page<>();
        page.setCurrentPage(param.getCurrentPage());
        page.setTotalCount(userRelationService.getFriendListCount(friendParam));
        page.setRecords(UserRelationConverter.covertDTOS(relationBOS));
        return successGet(page);
    }

}
