package com.lawu.chick.service.converter;

import java.util.ArrayList;
import java.util.List;

import com.lawu.chick.repository.domain.SignInRecordDO;
import com.lawu.chick.service.bo.SignInRecordBO;

/**
 * @author zhangyong
 * @date 2018/4/27.
 */
public class SignInRecordConverter {
    public static List<SignInRecordBO> convertBOS(List<SignInRecordDO> recordDOS) {
        if (recordDOS.isEmpty()) {
            return new ArrayList<>();
        }
        List<SignInRecordBO> recordBOS = new ArrayList<>();
        for (SignInRecordDO recordDO : recordDOS) {
            SignInRecordBO recordBO = new SignInRecordBO();
            recordBO.setId(recordDO.getId());
            recordBO.setGmtCreate(recordDO.getGmtCreate());
            recordBOS.add(recordBO);
        }
        return recordBOS;
    }
}
