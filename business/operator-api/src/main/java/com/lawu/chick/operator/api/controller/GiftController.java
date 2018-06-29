package com.lawu.chick.operator.api.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.authorization.annotation.Authorization;
import com.lawu.chick.framework.web.impl.annotation.PageBody;
import com.lawu.chick.operator.api.conterver.GiftConverter;
import com.lawu.chick.operator.api.dto.GiftDTO;
import com.lawu.chick.operator.api.log.LogRecord;
import com.lawu.chick.operator.service.dto.constants.LogTitleEnum;
import com.lawu.chick.operator.service.dto.constants.OperationTypeEnum;
import com.lawu.chick.service.GiftService;
import com.lawu.chick.service.bo.GiftBO;
import com.lawu.chick.service.param.GiftParam;
import com.lawu.chick.service.query.GiftOperatorQuery;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.HttpCode;
import com.lawu.framework.web.Result;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;

/**
 * @author meishuquan
 * @date 2018/5/16.
 */
@Api(tags = "gift")
@RestController
@RequestMapping(value = "gift/")
public class GiftController extends BaseController {

    @Autowired
    private GiftService giftService;

    @PageBody
    @ApiOperation(value = "礼品列表", notes = "礼品列表 （梅述全）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @Authorization
    @RequiresPermissions("gift:list")
    @RequestMapping(value = "listOperatorGift", method = RequestMethod.GET)
    public Result<Page<GiftDTO>> listOperatorGift(@ModelAttribute @ApiParam GiftOperatorQuery query) {
        Page<GiftBO> giftBOPage = giftService.listOperatorGift(query);
        Page<GiftDTO> page = new Page<>();
        page.setCurrentPage(giftBOPage.getCurrentPage());
        page.setTotalCount(giftBOPage.getTotalCount());
        page.setRecords(GiftConverter.convertDTOS(giftBOPage.getRecords()));
        return successGet(page);
    }

    @LogRecord(type = OperationTypeEnum.ADD, title = LogTitleEnum.GIFT_ADD)
    @ApiOperation(value = "保存礼品信息", notes = "保存礼品信息（梅述全）", httpMethod = "POST")
    @RequiresPermissions("gift:add")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequestMapping(value = "saveGift", method = RequestMethod.POST)
    public Result saveGift(@ModelAttribute @ApiParam GiftParam param) {
        giftService.saveGift(param);
        return successCreated();
    }

    @ApiOperation(value = "查询礼品信息", notes = "查询礼品信息（梅述全）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequestMapping(value = "getGift/{id}", method = RequestMethod.GET)
    public Result<GiftDTO> getGift(@PathVariable @ApiParam(required = true, value = "id") Long id) {
        GiftBO giftBO = giftService.getGift(id);
        GiftDTO giftDTO = GiftConverter.convertDTO(giftBO);
        return successGet(giftDTO);
    }

}
