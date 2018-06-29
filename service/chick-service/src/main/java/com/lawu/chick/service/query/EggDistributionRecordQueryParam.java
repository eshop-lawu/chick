package com.lawu.chick.service.query;

import com.lawu.chick.service.enums.DistributionStatusEnum;
import com.lawu.framework.core.page.AbstractPageParam;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 鸡蛋分配记录查询参数
 * @author jiangxinjun
 * @createDate 2018年5月8日
 * @updateDate 2018年5月8日
 */
@ApiModel(description = "鸡蛋分配记录查询参数", parent = AbstractPageParam.class)
public class EggDistributionRecordQueryParam extends AbstractPageParam {
    
    /**
     * 分配状态
     */
    @ApiModelProperty(value = "分配状态")
    private DistributionStatusEnum status;

    public DistributionStatusEnum getStatus() {
        return status;
    }

    public void setStatus(DistributionStatusEnum status) {
        this.status = status;
    }
    
}
