package com.lawu.chick.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.authorization.annotation.Authorization;
import com.lawu.chick.api.authorization.impl.UserConstant;
import com.lawu.chick.api.converter.GiftConverter;
import com.lawu.chick.api.dto.GiftDTO;
import com.lawu.chick.api.dto.GiftListDTO;
import com.lawu.chick.service.GiftService;
import com.lawu.chick.service.bo.GiftBO;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.HttpCode;
import com.lawu.framework.web.Result;
import com.lawu.framework.web.doc.annotation.Audit;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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

    @Audit(date = "2018-05-17", reviewer = "孙林青")
    @ApiOperation(value = "礼品列表", notes = "礼品列表 （梅述全）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @Authorization
    @RequestMapping(value = "listGift", method = RequestMethod.GET)
    public Result<GiftListDTO> listGift(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token) {
        List<GiftBO> giftBOS = giftService.listGift();
        List<GiftDTO> giftDTOS = GiftConverter.convertDTOS(giftBOS);
        GiftListDTO giftListDTO = new GiftListDTO();
        giftListDTO.setGiftDTOS(giftDTOS);
        return successGet(giftListDTO);
    }

}
