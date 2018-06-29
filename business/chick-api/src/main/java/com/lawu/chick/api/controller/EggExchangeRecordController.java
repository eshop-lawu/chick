package com.lawu.chick.api.controller;

import com.lawu.authorization.annotation.Authorization;
import com.lawu.authorization.util.UserUtil;
import com.lawu.chick.api.authorization.impl.UserConstant;
import com.lawu.chick.api.converter.EggExchangeRecordConverter;
import com.lawu.chick.api.dto.EggExchangeRecordDTO;
import com.lawu.chick.api.dto.EggExchangeValDTO;
import com.lawu.chick.api.dto.RedpacketDTO;
import com.lawu.chick.cache.service.EggConfigCacheService;
import com.lawu.chick.cache.service.co.EggConfigCache;
import com.lawu.chick.framework.web.impl.ResultCode;
import com.lawu.chick.service.EggExchangeRecordService;
import com.lawu.chick.service.GiftService;
import com.lawu.chick.service.MemberService;
import com.lawu.chick.service.SysConfigService;
import com.lawu.chick.service.bo.*;
import com.lawu.chick.service.enums.ChickenCureTaskTypeEnum;
import com.lawu.chick.service.enums.EggExchangeRecordStatusEnum;
import com.lawu.chick.service.enums.EggExchangeRecordTypeEnum;
import com.lawu.chick.service.event.EventPublisher;
import com.lawu.chick.service.param.EggExchangeGiftParam;
import com.lawu.chick.service.param.EggExchangeParam;
import com.lawu.chick.service.query.EggExchangeRecordMemberQuery;
import com.lawu.chick.service.query.EggExchangeRecordQuery;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.HttpCode;
import com.lawu.framework.web.Result;
import com.lawu.framework.web.doc.annotation.Audit;
import com.lawu.utils.LotteryHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @author meishuquan
 * @date 2018/4/26.
 */
@Api(tags = "eggExchangeRecord")
@RestController
@RequestMapping(value = "eggExchangeRecord/")
public class EggExchangeRecordController extends BaseController {

    @Autowired
    private EggExchangeRecordService exchangeRecordService;

    @Autowired
    private EggConfigCacheService eggConfigCacheService;

    @Autowired
    private SysConfigService sysConfigService;

    @Autowired
    private GiftService giftService;

    @Autowired
    private MemberService memberService;

    @Audit(date = "2018-04-28", reviewer = "孙林青")
    @ApiOperation(value = "查询可兑换的红包数量", notes = "查询可兑换的红包数量 [2016]（梅述全）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @Authorization
    @RequestMapping(value = "getEggExchangeVal", method = RequestMethod.GET)
    public Result<EggExchangeValDTO> getEggExchangeVal(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token) {
        String memberNum = UserUtil.getCurrentUserNum(getRequest());
        EggExchangeValBO exchangeValBO = exchangeRecordService.getEggExchangeVal(memberNum);
        if (exchangeValBO == null) {
            return successGet(ResultCode.EGG_EXCHANGE_RULE_NOT_SET);
        }
        EggExchangeValDTO exchangeValDTO = new EggExchangeValDTO();
        exchangeValDTO.setEggExchangeVal(exchangeValBO.getEggExchangeVal());
        return successGet(exchangeValDTO);
    }

    @Audit(date = "2018-04-28", reviewer = "孙林青")
    @ApiOperation(value = "鸡蛋兑换记录", notes = "鸡蛋兑换记录（梅述全）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @Authorization
    @RequestMapping(value = "listMemberEggExchangeRecord", method = RequestMethod.GET)
    public Result<Page<EggExchangeRecordDTO>> listMemberEggExchangeRecord(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,
                                                                          @ModelAttribute @ApiParam EggExchangeRecordQuery query) {
        String memberNum = UserUtil.getCurrentUserNum(getRequest());
        EggExchangeRecordMemberQuery memberQuery = new EggExchangeRecordMemberQuery();
        memberQuery.setMemberNum(memberNum);
        memberQuery.setCurrentPage(query.getCurrentPage());
        memberQuery.setPageSize(query.getPageSize());
        Page<EggExchangeRecordBO> recordBOPage = exchangeRecordService.listMemberEggExchangeRecord(memberQuery);
        Page<EggExchangeRecordDTO> page = new Page<>();
        page.setCurrentPage(recordBOPage.getCurrentPage());
        page.setTotalCount(recordBOPage.getTotalCount());
        page.setRecords(EggExchangeRecordConverter.convertDTOS(recordBOPage.getRecords()));
        return successGet(page);
    }

    @Audit(date = "2018-04-28", reviewer = "孙林青")
    @ApiOperation(value = "兑换礼品", notes = "兑换礼品 [1104|2006|2021]（梅述全）", httpMethod = "POST")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @Authorization
    @RequestMapping(value = "eggExchangeGift", method = RequestMethod.POST)
    public Result eggExchangeGift(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,
                                  @ModelAttribute @ApiParam EggExchangeGiftParam giftParam) {
        String memberNum = UserUtil.getCurrentUserNum(getRequest());
        InventoryEggBO eggBO = memberService.getEggs(memberNum);
        GiftBO giftBO = giftService.getGift(giftParam.getGiftId());
        if (eggBO == null || giftBO == null) {
            return successCreated(ResultCode.RESOURCE_NOT_FOUND);
        }
        if (giftBO.getInventory() == 0) {
            return successCreated(ResultCode.GIFT_INVENTORY_SHORTAGE);
        }
        if (eggBO.getEggs().compareTo(giftBO.getEggQuantity()) == -1) {
            return successCreated(ResultCode.EGG_INVENTORY_SHORTAGE);
        }

        EggExchangeParam param = new EggExchangeParam();
        param.setMemberNum(memberNum);
        param.setTypeEnum(EggExchangeRecordTypeEnum.GIFT);
        param.setEggQuantity(giftBO.getEggQuantity());
        param.setAmount(giftBO.getPrice());
        param.setStatusEnum(EggExchangeRecordStatusEnum.PENDING);
        param.setName(giftParam.getName());
        param.setMobile(giftParam.getMobile());
        param.setAddress(giftParam.getAddress());
        param.setGiftId(giftParam.getGiftId());
        param.setGiftName(giftBO.getName());
        param.setGiftImgPath(giftBO.getImgPath());
        try {
            exchangeRecordService.saveEggExchangeRecord(param);
        } catch (Exception e) {
            return successCreated(ResultCode.GIFT_INVENTORY_SHORTAGE);
        }
        return successCreated();
    }

    @Audit(date = "2018-04-28", reviewer = "孙林青")
    @ApiOperation(value = "兑换红包", notes = "兑换红包 [2006|2016|2017]（梅述全）", httpMethod = "POST")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @Authorization
    @RequestMapping(value = "eggExchangeRedpacket", method = RequestMethod.POST)
    public Result<RedpacketDTO> eggExchangeRedpacket(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token) {
        String memberNum = UserUtil.getCurrentUserNum(getRequest());
        EggExchangeValBO exchangeValBO = exchangeRecordService.getEggExchangeVal(memberNum);
        EggConfigCache configCache = eggConfigCacheService.getCacheEggConfig();
        if (exchangeValBO == null || configCache == null) {
            return successCreated(ResultCode.EGG_EXCHANGE_RULE_NOT_SET);
        }
        if (exchangeValBO.getEggExchangeVal() <= 0) {
            return successCreated(ResultCode.EGG_INVENTORY_SHORTAGE);
        }

        EggConfigBO configBO = sysConfigService.getEggConfig();
        if (configBO == null || configBO.getRedpacketBOS() == null || configBO.getRedpacketBOS().isEmpty()) {
            return successCreated(ResultCode.EGG_EXCHANGE_RULE_NOT_SET);
        }
        Map<Object, Double> probs = new HashMap<>();
        for (EggExchangeRedpacketBO redpacketBO : configBO.getRedpacketBOS()) {
            probs.put(redpacketBO.getId(), redpacketBO.getPriceRate().doubleValue());
        }
        Object redpacketPriceId = LotteryHelper.draw(probs, 2);
        if (redpacketPriceId == null) {
            return successCreated(ResultCode.REDPACKET_AMOUNT_GET_FAIL);
        }
        int randomAmount = 0;
        for (EggExchangeRedpacketBO redpacketBO : configBO.getRedpacketBOS()) {
            if (redpacketPriceId.toString().equals(redpacketBO.getId().toString())) {
                int minPrice = redpacketBO.getMinPrice().multiply(BigDecimal.valueOf(100)).intValue();
                int maxPrice = redpacketBO.getMaxPrice().multiply(BigDecimal.valueOf(100)).intValue();
                randomAmount = minPrice + (int) (Math.random() * ((maxPrice - minPrice) + 1));
                break;
            }
        }
        if (randomAmount == 0) {
            return successCreated(ResultCode.REDPACKET_AMOUNT_GET_FAIL);
        }

        BigDecimal amount = BigDecimal.valueOf(randomAmount).divide(BigDecimal.valueOf(100), 2, BigDecimal.ROUND_DOWN);
        EggExchangeParam param = new EggExchangeParam();
        param.setMemberNum(memberNum);
        param.setTypeEnum(EggExchangeRecordTypeEnum.REDPACKET);
        param.setEggQuantity(configCache.getExchangeRedpacketEggs());
        param.setAmount(amount);
        param.setStatusEnum(EggExchangeRecordStatusEnum.PENDING);
        try {
            exchangeRecordService.saveEggExchangeRecord(param);
        } catch (Exception e) {
            return successCreated(ResultCode.EGG_INVENTORY_SHORTAGE);
        }
        RedpacketDTO redpacketDTO = new RedpacketDTO();
        redpacketDTO.setAmount(amount);
        return successCreated(redpacketDTO);
    }

}
