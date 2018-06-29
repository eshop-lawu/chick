package com.lawu.chick.operator.api.conterver;

import java.util.ArrayList;
import java.util.List;

import com.lawu.chick.operator.api.dto.EggExchangeRecordDTO;
import com.lawu.chick.service.bo.EggExchangeRecordBO;
import com.lawu.chick.service.enums.EggExchangeRecordStatusEnum;
import com.lawu.chick.service.enums.EggExchangeRecordTypeEnum;
import com.lawu.chick.service.enums.GiftTypeEnum;

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
        recordDTO.setId(recordBO.getId());
        recordDTO.setNum(recordBO.getNum());
        recordDTO.setTypeEnum(EggExchangeRecordTypeEnum.getEnum(recordBO.getType()));
        recordDTO.setEggQuantity(recordBO.getEggQuantity());
        recordDTO.setAmount(recordBO.getAmount());
        recordDTO.setStatusEnum(EggExchangeRecordStatusEnum.getEnum(recordBO.getStatus()));
        recordDTO.setName(recordBO.getName());
        recordDTO.setMobile(recordBO.getMobile());
        recordDTO.setAddress(recordBO.getAddress());
        recordDTO.setExpressNum(recordBO.getExpressNum());
        recordDTO.setRemark(recordBO.getRemark());
        recordDTO.setGmtSend(recordBO.getGmtSend());
        recordDTO.setGmtCreate(recordBO.getGmtCreate());
        recordDTO.setTypeDes(EggExchangeRecordTypeEnum.getEnum(recordBO.getType()).getName());
        recordDTO.setStatusDes(EggExchangeRecordStatusEnum.getEnum(recordBO.getStatus()).getName());
        recordDTO.setGiftTypeEnum(GiftTypeEnum.getEnum(recordBO.getGiftType()));
        if (recordDTO.getGiftTypeEnum() != null) {
            recordDTO.setGiftTypeDes(GiftTypeEnum.getEnum(recordBO.getGiftType()).getName());
        }
        recordDTO.setGiftName(recordBO.getGiftName());
        recordDTO.setGiftImgPath(recordBO.getGiftImgPath());
        recordDTO.setMemberNum(recordBO.getMemberNum());
        recordDTO.setWxNickName(recordBO.getWxNickName());
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
