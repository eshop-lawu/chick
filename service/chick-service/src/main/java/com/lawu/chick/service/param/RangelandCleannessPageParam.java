package com.lawu.chick.service.param;

import com.lawu.chick.service.enums.SiteTypeEnum;
import com.lawu.framework.core.page.AbstractPageParam;

/**
 * @Description
 * @author zhangrc
 * @date 2018年4月26日
 */
public class RangelandCleannessPageParam extends AbstractPageParam{
	
	private int offset;
	
	private SiteTypeEnum typeEnum;

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	/**
	 * @return the typeEnum
	 */
	public SiteTypeEnum getTypeEnum() {
		return typeEnum;
	}

	public void setTypeEnum(SiteTypeEnum typeEnum) {
		this.typeEnum = typeEnum;
	}
	
	

}
