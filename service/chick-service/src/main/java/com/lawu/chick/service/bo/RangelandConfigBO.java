package com.lawu.chick.service.bo;

import java.util.Date;

/**
 * @Description
 * @author zhangrc
 * @date 2018年4月26日
 */
public class RangelandConfigBO extends RangelandConfigBaseBO{
	
	/**
	 * 牧场配置生效时间
	 */
    private Date RangelandEffectiveTime;

	/**
	 * @return the rangelandEffectiveTime
	 */
	public Date getRangelandEffectiveTime() {
		return RangelandEffectiveTime;
	}

	public void setRangelandEffectiveTime(Date rangelandEffectiveTime) {
		RangelandEffectiveTime = rangelandEffectiveTime;
	}
    
    

}
