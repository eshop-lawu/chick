package com.lawu.chick.api.converter;

import java.util.ArrayList;
import java.util.List;

import com.lawu.chick.api.dto.SignRecordDTO;
import com.lawu.chick.service.bo.SignInRecordBO;

/**
 * @author zhangyong
 * @date 2018/4/27.
 */
public class SignRecordConverter {
    public static List<SignRecordDTO> convertDTOS(List<SignInRecordBO> recordBOS) {
        if (recordBOS.isEmpty()) {
            return new ArrayList<>();
        }
        List<SignRecordDTO> recordDTOS = new ArrayList<>();
        for (SignInRecordBO recordBO : recordBOS) {
            SignRecordDTO recordDTO = new SignRecordDTO();
            recordDTO.setId(recordBO.getId());
            recordDTO.setGmtCreate(recordBO.getGmtCreate());
            recordDTOS.add(recordDTO);
        }
        return recordDTOS;
    }
}
