package com.lawu.chick.operator.api.controller;

import java.math.BigDecimal;

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
import com.lawu.chick.id.worker.generate.impl.BizIdType;
import com.lawu.chick.id.worker.generate.impl.IdWorkerHelper;
import com.lawu.chick.operator.api.conterver.EggExchangeRecordConverter;
import com.lawu.chick.operator.api.dto.EggExchangeRecordDTO;
import com.lawu.chick.operator.api.log.LogRecord;
import com.lawu.chick.operator.service.dto.constants.LogTitleEnum;
import com.lawu.chick.operator.service.dto.constants.OperationTypeEnum;
import com.lawu.chick.service.EggExchangeRecordService;
import com.lawu.chick.service.RedpacketSendRecordService;
import com.lawu.chick.service.bo.EggExchangeRecordBO;
import com.lawu.chick.service.enums.RedpacketSendRecordStatusEnum;
import com.lawu.chick.service.param.RedpacketSendRecordParam;
import com.lawu.chick.service.query.EggExchangeRecordOperatorQuery;
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
 * @date 2018/4/26.
 */
@Api(tags = "eggExchangeRecord")
@RestController
@RequestMapping(value = "eggExchangeRecord/")
public class EggExchangeRecordController extends BaseController {

    @Autowired
    private EggExchangeRecordService exchangeRecordService;

    @Autowired
    private IdWorkerHelper idWorkerHelper;

    @Autowired
    private RedpacketSendRecordService redpacketSendRecordService;

    @PageBody
    @ApiOperation(value = "鸡蛋兑换记录", notes = "鸡蛋兑换记录（梅述全）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequiresPermissions("exchangeRecord:list")
    @RequestMapping(value = "listOperatorEggExchangeRecord", method = RequestMethod.GET)
    public Result<Page<EggExchangeRecordDTO>> listOperatorEggExchangeRecord(@ModelAttribute @ApiParam EggExchangeRecordOperatorQuery query) {
        Page<EggExchangeRecordBO> recordBOPage = exchangeRecordService.listOperatorEggExchangeRecord(query);
        Page<EggExchangeRecordDTO> page = new Page<>();
        page.setCurrentPage(recordBOPage.getCurrentPage());
        page.setTotalCount(recordBOPage.getTotalCount());
        page.setRecords(EggExchangeRecordConverter.convertDTOS(recordBOPage.getRecords()));
        return successGet(page);
    }

    @LogRecord(type = OperationTypeEnum.UPDATE, title = LogTitleEnum.EGG_EXCHANGE_GOLE_SEND, idParamName = "id")
    @ApiOperation(value = "发放礼品", notes = "发放礼品（梅述全）", httpMethod = "PUT")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequiresPermissions("exchangeGift:send")
    @RequestMapping(value = "sendExchangeGift/{id}", method = RequestMethod.PUT)
    public Result sendExchangeGift(@PathVariable @ApiParam(required = true, value = "id") Long id,
                                   @RequestParam @ApiParam(required = true, value = "物流单号") String expressNum) {
        exchangeRecordService.sendExchangeGift(id, expressNum);
        return successCreated();
    }

    @LogRecord(type = OperationTypeEnum.UPDATE, title = LogTitleEnum.EGG_EXCHANGE_REDPACKET_SEND, idParamName = "id")
    @ApiOperation(value = "发放红包", notes = "发放红包 [1001]（梅述全）", httpMethod = "PUT")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequiresPermissions("exchangeRedpacket:send")
    @RequestMapping(value = "sendExchangeRedpacket/{id}", method = RequestMethod.PUT)
    public Result sendExchangeRedpacket(@PathVariable @ApiParam(required = true, value = "id") Long id) {
        EggExchangeRecordBO recordBO = exchangeRecordService.getEggExchangeRecord(id);
        String mchBillno = idWorkerHelper.generate(BizIdType.REDPACKET);
        RedpacketSendRecordParam sendRecordParam = new RedpacketSendRecordParam();
        sendRecordParam.setEggExchangeRecordId(recordBO.getId());
        sendRecordParam.setUserNum(recordBO.getMemberNum());
        sendRecordParam.setMchBillno(mchBillno);
        sendRecordParam.setTotalAmount(recordBO.getAmount().multiply(BigDecimal.valueOf(1000)).intValue());
        sendRecordParam.setStatusEnum(RedpacketSendRecordStatusEnum.SENDING);
        Long redpacketSendRecordId = redpacketSendRecordService.saveRedpacketSendRecord(sendRecordParam);
        if (redpacketSendRecordId == null || redpacketSendRecordId <= 0) {
            return successCreated(ResultCode.FAIL);
        }
        redpacketSendRecordService.sendRedpacket(redpacketSendRecordId);
        return successCreated();
    }

}
