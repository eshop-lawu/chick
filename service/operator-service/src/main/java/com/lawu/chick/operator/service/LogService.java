package com.lawu.chick.operator.service;

import com.lawu.chick.operator.service.bo.LogBO;
import com.lawu.chick.operator.service.dto.param.ListLogParam;
import com.lawu.chick.operator.service.dto.param.LogParam;
import com.lawu.framework.core.page.Page;

/**
 * @author meishuquan
 * @date 2017/5/3.
 */
public interface LogService {

    /**
     * 保存日志
     *
     * @param logParam
     */
    void saveLog(LogParam logParam);

    /**
     * 根据ID查询日志
     *
     * @param id
     * @return
     */
    LogBO getLogById(Long id);

    /**
     * 查询日志列表
     *
     * @param listLogParam
     * @return
     */
    Page<LogBO> listLog(ListLogParam listLogParam);
}
