package com.lawu.chick.service.param;

import com.lawu.framework.core.page.AbstractPageParam;

import io.swagger.annotations.ApiModel;

/**
 * 分页查询商品参数
 * @author jiangxinjun
 * @createDate 2018年4月25日
 * @updateDate 2018年4月25日
 */
@ApiModel(description = "分页查询商品参数", subTypes = AbstractPageParam.class)
public class ProductPageParam extends AbstractPageParam {
}
