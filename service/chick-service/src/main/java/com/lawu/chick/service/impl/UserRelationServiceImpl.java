package com.lawu.chick.service.impl;

import java.util.Date;
import java.util.List;

import com.lawu.chick.service.enums.ChickenCureTaskTypeEnum;
import com.lawu.chick.service.event.EventPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.chick.repository.domain.UserRelationDO;
import com.lawu.chick.repository.domain.UserRelationDOExample;
import com.lawu.chick.repository.domain.extend.FriendDOView;
import com.lawu.chick.repository.mapper.UserRelationDOMapper;
import com.lawu.chick.repository.mapper.extend.UserRelationDOMapperExtend;
import com.lawu.chick.repository.param.FriendParam;
import com.lawu.chick.service.UserRelationService;
import com.lawu.chick.service.bo.UserRelationBO;
import com.lawu.chick.service.converter.UserRelationConverter;

/**
 * @author zhangyong
 * @date 2018/4/25.
 */
@Service
public class UserRelationServiceImpl implements UserRelationService {

    @Autowired
    private UserRelationDOMapper userRelationDOMapper;

    @Autowired
    private UserRelationDOMapperExtend userRelationDOMapperExtend;

    @Autowired
    private EventPublisher eventPublisher;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addFriend(String memberNum, String friendNum) {
        UserRelationDO relationDO = new UserRelationDO();
        relationDO.setFriendNum(friendNum);
        relationDO.setMemberNum(memberNum);
        relationDO.setGmtCreate(new Date());
        userRelationDOMapper.insertSelective(relationDO);

        UserRelationDO friendDO = new UserRelationDO();
        friendDO.setFriendNum(memberNum);
        friendDO.setMemberNum(friendNum);
        friendDO.setGmtCreate(new Date());
        userRelationDOMapper.insertSelective(friendDO);

    }

    @Override
    public List<UserRelationBO> getFriendList(FriendParam param) {
        List<FriendDOView> friendDOList = userRelationDOMapperExtend.getFriendList(param);
        return UserRelationConverter.convertBOS(friendDOList);
    }

    @Override
    public int getFriendListCount(FriendParam param) {
        return userRelationDOMapperExtend.getFriendListCount(param);
    }

    @Override
    public Boolean isFriend(String memberNum, String friendNum) {
        UserRelationDOExample example = new UserRelationDOExample();
        example.createCriteria().andMemberNumEqualTo(memberNum).andFriendNumEqualTo(friendNum);
        List<UserRelationDO> relationDOS = userRelationDOMapper.selectByExample(example);
        if (relationDOS.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }


}
