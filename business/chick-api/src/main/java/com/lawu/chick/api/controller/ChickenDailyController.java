package com.lawu.chick.api.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.authorization.annotation.Authorization;
import com.lawu.authorization.util.UserUtil;
import com.lawu.chick.api.authorization.impl.UserConstant;
import com.lawu.chick.api.converter.ChickenConverter;
import com.lawu.chick.api.dto.ChickenBaseInfoDTO;
import com.lawu.chick.api.dto.ChickenBaseInfoRtnDTO;
import com.lawu.chick.api.enums.ChickStatusGeneralEnum;
import com.lawu.chick.cache.service.ChickBaseConfigCacheService;
import com.lawu.chick.cache.service.co.ChickBaseConfigCO;
import com.lawu.chick.framework.web.impl.ResultCode;
import com.lawu.chick.service.ChickenCureTaskService;
import com.lawu.chick.service.ChickenService;
import com.lawu.chick.service.bo.ChickenBaseInfoBO;
import com.lawu.chick.service.exception.RestockingIsEmptyException;
import com.lawu.chick.service.util.ChickLayUtil;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.HttpCode;
import com.lawu.framework.web.Result;
import com.lawu.framework.web.doc.annotation.Audit;
import com.lawu.utils.DateUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;

@Api(tags = "chickenDaily")
@RestController
@RequestMapping(value = "chickenDaily/")
public class ChickenDailyController extends BaseController {

    @Autowired
    protected ChickenService chickenService;

    @Autowired
    private ChickenCureTaskService chickenCureTaskService;

    @Autowired
    private ChickBaseConfigCacheService chickBaseConfigCacheService;

    @Audit(date = "2018-04-28", reviewer = "孙林青")
    @Authorization
    @ApiOperation(value = "查询小鸡列表", notes = "查询小鸡列表，默认查询当前登录用户，好友用户编号不为空时查询好友的小鸡列表（杨清华）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequestMapping(value = "getChickenListByMemberNum", method = RequestMethod.GET)
    public Result<ChickenBaseInfoRtnDTO> getChickenListByMemberNum(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,
                                                                   @RequestParam @ApiParam(required = false, value = "好友用户编号（查自己的传0）") String memberNum) {
        if ("0".equals(memberNum)) {
            memberNum = UserUtil.getCurrentUserNum(getRequest());
        }
        List<ChickenBaseInfoBO> chickenBaseInfoBOList = chickenService.getChickenListByMemberNum(memberNum);
        List<ChickenBaseInfoDTO> chickenBaseInfoDTOList = ChickenConverter.converterChickenBaseInfoDTOList(chickenBaseInfoBOList);
        ChickenBaseInfoRtnDTO rtnDTO = new ChickenBaseInfoRtnDTO();
        rtnDTO.setChickenBaseInfoDTOList(chickenBaseInfoDTOList);

        //存在生产中小鸡时，返回产蛋倒计时（分钟）
        for (ChickenBaseInfoDTO chickenBaseInfoDTO : chickenBaseInfoDTOList) {
            if (chickenBaseInfoDTO.getStatus().getVal().equals(ChickStatusGeneralEnum.PRODUCE.getVal())) {
                //获取系统配置的产蛋时间，排序
                ChickBaseConfigCO chickBaseConfigCO = chickBaseConfigCacheService.getCacheChickBaseInfo();
                List<String> chickEggProdTime = chickBaseConfigCO.getChickEggProdTime();
                Long diffMin = ChickLayUtil.getLayMin(chickEggProdTime);
                rtnDTO.setEggLayCutdown(diffMin.intValue());
                break;
            }
        }

        return successCreated(rtnDTO);
    }

    @Audit(date = "2018-04-28", reviewer = "孙林青")
    @Authorization
    @ApiOperation(value = "放养小鸡", notes = "放养小鸡（杨清华）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequestMapping(value = "restocking", method = RequestMethod.GET)
    public Result restocking(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token) {
        try {
            chickenService.restocking(UserUtil.getCurrentUserNum(getRequest()));
        } catch (RestockingIsEmptyException ex) {
            return successCreated(ResultCode.RESTOCKING_IS_EMPTY);
        } catch (Exception ex) {
            return successCreated(ResultCode.FAIL);
        }
        return successCreated(ResultCode.SUCCESS);
    }


    @Audit(date = "2018-05-10", reviewer = "孙林青")
    @Authorization
    @ApiOperation(value = "根据小鸡编号查询小鸡信息", notes = "根据小鸡编号查询小鸡信息（杨清华）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequestMapping(value = "getChickenByChickenNum", method = RequestMethod.GET)
    public Result<ChickenBaseInfoDTO> getChickenByChickenNum(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,
                                                             @RequestParam @ApiParam(required = true, value = "好友编号(查自己的传0)") String friendNum,
                                                             @RequestParam @ApiParam(required = true, value = "小鸡编号") String chickenNum) {
        String memberNum = UserUtil.getCurrentUserNum(getRequest());
        if (!"0".equals(friendNum.trim())) {
            memberNum = friendNum;
        }
        ChickenBaseInfoBO chickenBaseInfoBO = chickenService.getChickenByChickenNum(memberNum,chickenNum);
        ChickenBaseInfoDTO chickenBaseInfoDTO = ChickenConverter.converterChickenBaseInfoDTO(chickenBaseInfoBO);
        if(chickenBaseInfoDTO.getStatus().getVal().equals(ChickStatusGeneralEnum.PRODUCE.getVal())){
        	ChickBaseConfigCO chickBaseConfigCO = chickBaseConfigCacheService.getCacheChickBaseInfo();
        	List<String> chickEggProdTime = chickBaseConfigCO.getChickEggProdTime();
        	Long layMin =ChickLayUtil.getLayMin(chickEggProdTime);
        	chickenBaseInfoDTO.setEggLayCutdown(layMin.intValue());
        }
        return successCreated(chickenBaseInfoDTO);
    }

}
