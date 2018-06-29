package com.lawu.chick.operator.service.converter;

import java.util.ArrayList;
import java.util.List;

import com.lawu.chick.operator.repository.domain.LogDO;
import com.lawu.chick.operator.service.bo.LogBO;

/**
 * @author zhangyong
 * @date 2018/4/25.
 */
public class LogConverter {
    public static LogBO convertBO(LogDO logDO) {
        if (logDO == null) {
            return null;
        }

        LogBO logBO = new LogBO();
        logBO.setId(logDO.getId());
        logBO.setAccount(logDO.getAccount());
        logBO.setOperationType(logDO.getOperationType());
        logBO.setModule(logDO.getModule());
        logBO.setBusinessId(logDO.getBusinessId());
        logBO.setChangeTitle(logDO.getChangeTitle());
        logBO.setChangeContent(logDO.getChangeContent());
        logBO.setGmtCreate(logDO.getGmtCreate());
        return logBO;
    }


    /**
     * BO转换
     *
     * @param logDOS
     * @return
     */
    public static List<LogBO> convertBO(List<LogDO> logDOS) {
        List<LogBO> logBOS = new ArrayList<>();
        if (logDOS == null || logDOS.isEmpty()) {
            return logBOS;
        }

        for (LogDO logDO : logDOS) {
            logBOS.add(convertBO(logDO));
        }
        return logBOS;
    }
}
