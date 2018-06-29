package com.lawu.chick.service.param;

import com.lawu.chick.service.enums.ProductStatusEnum;
import com.lawu.chick.service.enums.ProductTypeEnum;
import com.lawu.framework.core.page.AbstractPageParam;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 分页查询商品参数
 * @author jiangxinjun
 * @createDate 2018年4月25日
 * @updateDate 2018年4月25日
 */
@ApiModel(description = "分页查询商品参数", subTypes = AbstractPageParam.class)
public class ProductSearchPageParam extends AbstractPageParam {
    
    /**
     * 类型
     */
    @ApiModelProperty(value = "类型", required = true, reference = "CHICK-小鸡|CHICKEN_PROPS-小鸡道具")
    private ProductTypeEnum type;
    
    /**
     * 状态
     */
    @ApiModelProperty(value = "状态", required = true, reference = "DOWN-下架|UP-上架")
    private ProductStatusEnum status;

    public ProductTypeEnum getType() {
        return type;
    }

    public void setType(ProductTypeEnum type) {
        this.type = type;
    }

    public ProductStatusEnum getStatus() {
        return status;
    }

    public void setStatus(ProductStatusEnum status) {
        this.status = status;
    }
    
}
