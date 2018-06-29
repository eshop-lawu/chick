package com.lawu.chick.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.chick.repository.domain.MemberDO;
import com.lawu.chick.repository.domain.MemberDOExample;
import com.lawu.chick.repository.domain.extend.MemberBaseInfoDOView;
import com.lawu.chick.repository.mapper.MemberDOMapper;
import com.lawu.chick.repository.mapper.extend.MemberDOExtendMapper;
import com.lawu.chick.repository.param.MemberParam;
import com.lawu.chick.service.ChickenService;
import com.lawu.chick.service.MemberService;
import com.lawu.chick.service.bo.InventoryEggBO;
import com.lawu.chick.service.bo.MemberBaseInfoBO;
import com.lawu.chick.service.converter.WxUserConverter;
import com.lawu.chick.service.enums.AdoptTypeEnum;
import com.lawu.chick.service.exception.WrongOperationException;
import com.lawu.chick.service.param.MemberPageParam;
import com.lawu.framework.core.page.Page;

/**
 * @Description
 * @author zhangrc
 * @date 2018年4月25日
 */
@Service
public class MemberServiceImpl implements MemberService{
	
	@Autowired
	private MemberDOMapper memberDOMapper;
	
	@Autowired
	private ChickenService chickenService;
	
	@Autowired
	private MemberDOExtendMapper memberDOExtendMapper;
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void createMember(String memberNum) throws WrongOperationException {
		
		MemberDOExample example = new MemberDOExample();
		example.createCriteria().andNumEqualTo(memberNum);
		int count = (int) memberDOMapper.countByExample(example);

		if (count > 0) {
			return;
		}
		
		//插入用户信息
		MemberDO record = new MemberDO();
		record.setNum(memberNum);
		record.setEggs(new BigDecimal("0"));
		record.setGmtCreate(new Date());
		record.setGmtModified(new Date());
		memberDOMapper.insertSelective(record);
		
		//领养小鸡
		chickenService.createChicken(record.getNum(),AdoptTypeEnum.INIT,0);
		
	}

	
	@Override
	public InventoryEggBO getEggs(String memberNum) {
		MemberDOExample example = new MemberDOExample();
		example.createCriteria().andNumEqualTo(memberNum);
		List<MemberDO> list = memberDOMapper.selectByExample(example);
		InventoryEggBO eggBO = new InventoryEggBO();
		eggBO.setEggs(list.get(0).getEggs());
		return eggBO;
	}


	@Override
	public Page<MemberBaseInfoBO> page(MemberPageParam param) {
		Page<MemberBaseInfoBO> rtn = new Page<>();
		
		long count = memberDOMapper.countByExample(null);
		rtn.setTotalCount((int) count);
		rtn.setCurrentPage(param.getCurrentPage());
		if (count <= 0 || count <= param.getOffset()) {
			return rtn;
		}
		MemberParam query = new MemberParam();
		query.setMemberNum(param.getMemberNum());
		query.setNickname(param.getNickname());
		
		RowBounds rowBounds = new RowBounds(param.getOffset(), param.getPageSize());
		List<MemberBaseInfoDOView> list = memberDOExtendMapper.selectByExampleWithRowbounds(query,rowBounds);
		rtn.setRecords(WxUserConverter.convertList(list));
		return rtn;
	}


	@Override
	public Long getMemberId(String memberNum) {
		MemberDOExample example = new MemberDOExample();
		example.createCriteria().andNumEqualTo(memberNum);
		List<MemberDO> list = memberDOMapper.selectByExample(example);
		if(list.size()==0){
			return null;
		}
		return list.get(0).getId();
	}

}
