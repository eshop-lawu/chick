package com.lawu.chick.service;

import java.math.BigDecimal;

import com.lawu.chick.service.bo.EggDistributionRecordBO;
import com.lawu.chick.service.query.EggDistributionRecordQueryParam;
import com.lawu.framework.core.page.Page;

/**
 * 鸡蛋分配记录接口
 * 
 * @author jiangxinjun
 * @createDate 2018年5月7日
 * @updateDate 2018年5月7日
 */
public interface EggDistributionRecordService {

    /**
     * 保存分配记录
     * 
     * @param chicks
     *            小鸡的总数量
     * @param layEggsTotal
     *            金蛋分配的总数量
     * @return
     * @author jiangxinjun
     * @createDate 2018年5月7日
     * @updateDate 2018年5月7日
     */
    Long save(Long chicks, BigDecimal layEggsTotal);

    /**
     * 分配完成
     * 
     * @param recordId
     *            记录id
     * @author jiangxinjun
     * @createDate 2018年5月7日
     * @updateDate 2018年5月7日
     */
    void distributionCompleted(Long recordId);
    
    /**
     * 更新状态为发放完成
     * 
     * @author jiangxinjun
     * @createDate 2018年5月8日
     * @updateDate 2018年5月8日
     */
    void releaseCompleted(Long recordId);
    
    /**
     * 
     * @return
     * @author jiangxinjun
     * @createDate 2018年5月8日
     * @updateDate 2018年5月8日
     */
    Page<EggDistributionRecordBO> page(EggDistributionRecordQueryParam param);
}
