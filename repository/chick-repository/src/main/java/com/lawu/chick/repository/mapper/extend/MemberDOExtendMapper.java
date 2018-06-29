package com.lawu.chick.repository.mapper.extend;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.lawu.chick.repository.domain.extend.MemberBaseInfoDOView;
import com.lawu.chick.repository.param.MemberEggOperatorParam;
import com.lawu.chick.repository.param.MemberParam;

/**
 * @author meishuquan
 * @date 2018/4/26.
 */
public interface MemberDOExtendMapper {
    
    /**
     * 积分数量操作
     * 
     * @param param
     * @author jiangxinjun
     * @createDate 2018年4月27日
     * @updateDate 2018年4月27日
     */
    void eggOperator(MemberEggOperatorParam param);

	/**
	 * @param rowBounds
	 * @return
	 */
	List<MemberBaseInfoDOView> selectByExampleWithRowbounds(MemberParam param ,RowBounds rowBounds);

}
