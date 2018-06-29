/*
package com.lawu.chick.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.lawu.authorization.annotation.Authorization;
import com.lawu.authorization.util.UserUtil;
import com.lawu.chick.api.authorization.impl.UserConstant;
import com.lawu.chick.api.converter.SignRecordConverter;
import com.lawu.chick.api.dto.SignRecordListDTO;
import com.lawu.chick.api.dto.SignRuleExtraDTO;
import com.lawu.chick.api.dto.SignRuleExtraListDTO;
import com.lawu.chick.framework.web.impl.ResultCode;
import com.lawu.chick.service.ProductService;
import com.lawu.chick.service.SignInRecordService;
import com.lawu.chick.service.bo.ProductBO;
import com.lawu.chick.service.bo.SignRecordListBO;
import com.lawu.chick.service.param.SignExtraParam;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.HttpCode;
import com.lawu.framework.web.Result;
import com.lawu.framework.web.doc.annotation.Audit;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;

*/
/**
 * @author zhangyong
 * @date 2018/4/27.
 *//*

@Api(tags = "signInRule")
@RestController
@RequestMapping(value = "signInRule/")
public class SignInRuleController extends BaseController{

    @Autowired
    private SignInRecordService signInRecordService;

    @Autowired
    private ProductService productService;

    @Audit(date = "2018-04-28", reviewer = "孙林青")
    @ApiOperation(value = "签到", notes = "签到（章勇）[2009,2010]", httpMethod = "POST")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @Authorization
    @RequestMapping(value = "sign", method = RequestMethod.POST)
    public Result sign(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token){
        String userNum = UserUtil.getCurrentUserNum(getRequest());

        Boolean signFlag = signInRecordService.getRecordByMemberNum(userNum);
        if(signFlag){
            return successCreated(ResultCode.HAS_SIGN_RECORD);
        }

        Boolean sign = signInRecordService.sign(userNum);
        if(!sign){
            return successCreated(ResultCode.SIGN_RECORD_ERROR);
        }
        return successCreated();
    }

    @Audit(date = "2018-04-28", reviewer = "孙林青")
    @ApiOperation(value = "获取签到奖励", notes = "获取签到奖励（章勇）[2011]", httpMethod = "POST")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @Authorization
    @RequestMapping(value = "getSignAward", method = RequestMethod.POST)
    public Result getSignAward(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,
                               @RequestParam("signDay") @ApiParam(value = "签到天数") Integer signDay){
        String userNum = UserUtil.getCurrentUserNum(getRequest());
        Boolean flag = signInRecordService.getSignAward(userNum,signDay);
        if(!flag){
            return successCreated(ResultCode.GET_SIGN_ERROR);
        }
        return successCreated();
    }

    @Audit(date = "2018-04-28", reviewer = "孙林青")
    @ApiOperation(value = "签到列表", notes = "获取签到奖励（章勇）[1104]", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @Authorization
    @RequestMapping(value = "getSignRecordList", method = RequestMethod.GET)
    public Result<SignRecordListDTO> getSignRecordList(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token) {
        String userNum = UserUtil.getCurrentUserNum(getRequest());
        SignRecordListBO listBO = signInRecordService.getSignRecord(userNum);
        if (listBO == null) {
            return successGet(ResultCode.RESOURCE_NOT_FOUND);
        }
        SignExtraParam param = new SignExtraParam();
        param.setGmtEnd(listBO.getGmtEnd());
        param.setGmtStart(listBO.getGmtStart());
        param.setUserNum(userNum);
        List<SignRuleExtraDTO> extraDTOS = JSONArray.parseArray(listBO.getExtras(), SignRuleExtraDTO.class);
        List<SignRuleExtraListDTO> listDTOS = new ArrayList<>();
        for (SignRuleExtraDTO extraDTO : extraDTOS) {
            SignRuleExtraListDTO extraListDTO = new SignRuleExtraListDTO();
            extraListDTO.setProductName(extraDTO.getProductName());
            extraListDTO.setSignDay(extraDTO.getSignDay());
            ProductBO productBO = productService.getProductByNum(extraDTO.getProductNum());
            param.setSignDay(extraDTO.getSignDay());
            extraListDTO.setImgPath(productBO.getImgPath());
            extraListDTO.setIsDraw(signInRecordService.isGetSignExtraAward(param));
            extraListDTO.setCount(extraDTO.getProductCount());
            listDTOS.add(extraListDTO);
        }
        ProductBO productBO = productService.getProductByNum(listBO.getProductNum());
        SignRecordListDTO listDTO = new SignRecordListDTO();
        listDTO.setProductName(productBO.getName());
        listDTO.setCount(listBO.getCount());
        listDTO.setImgPath(productBO.getImgPath());
        listDTO.setGmtStart(listBO.getGmtStart());
        listDTO.setGmtEnd(listBO.getGmtEnd());
        listDTO.setExtras(listDTOS);
        listDTO.setRecordDTOS(SignRecordConverter.convertDTOS(listBO.getRecordBOS()));
        return successGet(listDTO);
    }
}
*/
