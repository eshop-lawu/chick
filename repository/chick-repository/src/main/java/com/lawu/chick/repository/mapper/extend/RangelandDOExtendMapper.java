package com.lawu.chick.repository.mapper.extend;

import com.lawu.chick.repository.param.CleannessParam;

/**
 * @Description
 * @author zhangrc
 * @date 2018年4月25日
 */
public interface RangelandDOExtendMapper {

    /**
     * 降低清洁度
     * 
     * @param param
     */
    void reduceCleanness(CleannessParam param);
    
    /**
     * 增加清洁度
     * @param param
     */
    void addCleanness(CleannessParam param);

	/**
	 * 小鸡在牧场时间累计
	 * 
	 * @param memberNum
	 */
	void chickInRangelandTime(String memberNum);
    
}
