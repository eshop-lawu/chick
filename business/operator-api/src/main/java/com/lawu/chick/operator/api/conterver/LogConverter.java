package com.lawu.chick.operator.api.conterver;

import java.util.ArrayList;
import java.util.List;

import com.lawu.chick.operator.api.dto.LogDTO;
import com.lawu.chick.operator.repository.domain.LogDO;
import com.lawu.chick.operator.service.bo.LogBO;
import com.lawu.chick.operator.service.dto.constants.ModuleEnum;
import com.lawu.chick.operator.service.dto.constants.OperationTypeEnum;

/**
 * @author meishuquan
 * @date 2017/5/3.
 */
public class LogConverter {

    /**
     * BO转换
     *
     * @param logDO
     * @return
     */
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
     * DTO转换
     *
     * @param logBO
     * @return
     */
    public static LogDTO convertDTO(LogBO logBO) {
        if (logBO == null) {
            return null;
        }

        LogDTO logDTO = new LogDTO();
        logDTO.setId(logBO.getId());
        logDTO.setAccount(logBO.getAccount());
        logDTO.setTypeEnum(OperationTypeEnum.getEnum(logBO.getOperationType()));
        logDTO.setModuleEnum(ModuleEnum.getEnum(logBO.getModule()));
        logDTO.setBusinessId(logBO.getBusinessId());
        logDTO.setChangeTitle(logBO.getChangeTitle());
        logDTO.setChangeContent(logBO.getChangeContent());
        logDTO.setGmtCreate(logBO.getGmtCreate());
        return logDTO;
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

    /**
     * DTO转换
     *
     * @param logBOS
     * @return
     */
    public static List<LogDTO> convertDTO(List<LogBO> logBOS) {
        List<LogDTO> logDTOS = new ArrayList<>();
        if (logBOS == null || logBOS.isEmpty()) {
            return logDTOS;
        }

        for (LogBO logBO : logBOS) {
            logDTOS.add(convertDTO(logBO));
        }
        return logDTOS;
    }
}
