package com.lawu.chick.api.converter;

import java.util.ArrayList;
import java.util.List;

import com.lawu.chick.api.dto.EggExchangeRecordDTO;
import com.lawu.chick.service.bo.EggExchangeRecordBO;
import com.lawu.chick.service.enums.EggExchangeRecordStatusEnum;
import com.lawu.chick.service.enums.EggExchangeRecordTypeEnum;

/**
 * @author meishuquan
 * @date 2018/4/26.
 */
public class EggExchangeRecordConverter {

    public static EggExchangeRecordDTO convertDTO(EggExchangeRecordBO recordBO) {
        if (recordBO == null) {
            return null;
        }

        EggExchangeRecordDTO recordDTO = new EggExchangeRecordDTO();
        recordDTO.setTypeEnum(EggExchangeRecordTypeEnum.getEnum(recordBO.getType()));
        recordDTO.setAmount(recordBO.getAmount());
        recordDTO.setStatusEnum(EggExchangeRecordStatusEnum.getEnum(recordBO.getStatus()));
        recordDTO.setExpressNum(recordBO.getExpressNum());
        recordDTO.setGmtCreate(recordBO.getGmtCreate());
        recordDTO.setGiftName(recordBO.getGiftName());
        recordDTO.setGiftImgPath(recordBO.getGiftImgPath());
        return recordDTO;
    }

    public static List<EggExchangeRecordDTO> convertDTOS(List<EggExchangeRecordBO> recordBOS) {
        List<EggExchangeRecordDTO> recordDTOS = new ArrayList<>();
        if (recordBOS == null || recordBOS.isEmpty()) {
            return recordDTOS;
        }

        for (EggExchangeRecordBO recordBO : recordBOS) {
            recordDTOS.add(convertDTO(recordBO));
        }
        return recordDTOS;
    }

}
