package com.lawu.chick.service.param;

import com.lawu.framework.core.page.AbstractPageParam;

import io.swagger.annotations.ApiModel;

/**
 * 
 * @Description
 * @author zhangrc
 * @date 2018年4月25日
 */
@ApiModel(description = "分页查询仓库参数", subTypes = AbstractPageParam.class)
public class InventoryPageParam extends AbstractPageParam {
	
}
