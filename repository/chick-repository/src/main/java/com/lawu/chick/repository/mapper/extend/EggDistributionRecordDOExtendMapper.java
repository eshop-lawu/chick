package com.lawu.chick.repository.mapper.extend;

import com.lawu.chick.repository.param.UpdateAssignedQuantityParam;
import com.lawu.chick.repository.param.UpdateIssueQuantityParam;

/**
 * 金蛋分配记录扩展Mapper
 * @author jiangxinjun
 * @createDate 2018年5月7日
 * @updateDate 2018年5月7日
 */
public interface EggDistributionRecordDOExtendMapper {
    
    /**
     * 更新分配的小鸡数量以及分配的金蛋数量
     * @param param
     * @author jiangxinjun
     * @createDate 2018年5月7日
     * @updateDate 2018年5月7日
     */
    void updateAssignedQuantity(UpdateAssignedQuantityParam param);
    
    /**
     * 更新分配的小鸡数量以及分配的金蛋数量
     * @param param
     * @author jiangxinjun
     * @createDate 2018年5月7日
     * @updateDate 2018年5月7日
     */
    void updateIssueQuantity(UpdateIssueQuantityParam param);
}
