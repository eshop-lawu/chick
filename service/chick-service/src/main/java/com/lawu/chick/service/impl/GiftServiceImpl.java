package com.lawu.chick.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.chick.repository.domain.GiftDO;
import com.lawu.chick.repository.domain.GiftDOExample;
import com.lawu.chick.repository.mapper.GiftDOMapper;
import com.lawu.chick.service.GiftService;
import com.lawu.chick.service.bo.GiftBO;
import com.lawu.chick.service.converter.GiftConverter;
import com.lawu.chick.service.enums.GiftStatusEnum;
import com.lawu.chick.service.param.GiftParam;
import com.lawu.chick.service.query.GiftOperatorQuery;
import com.lawu.framework.core.page.Page;
import com.lawu.utils.DateUtil;

/**
 * @author meishuquan
 * @date 2018/5/16.
 */
@Service
public class GiftServiceImpl implements GiftService {

    @Autowired
    private GiftDOMapper giftDOMapper;

    @Override
    public GiftBO getGift(Long id) {
        GiftDO giftDO = giftDOMapper.selectByPrimaryKey(id);
        return GiftConverter.convertBO(giftDO);
    }

    @Override
    public List<GiftBO> listGift() {
        GiftDOExample example = new GiftDOExample();
        example.setOrderByClause("id desc");
        example.createCriteria().andStatusEqualTo(GiftStatusEnum.VALID.getVal());
        List<GiftDO> giftDOS = giftDOMapper.selectByExample(example);
        return GiftConverter.convertBOS(giftDOS);
    }

    @Override
    public Page<GiftBO> listOperatorGift(GiftOperatorQuery query) {
        GiftDOExample example = new GiftDOExample();
        example.setOrderByClause("id desc");
        GiftDOExample.Criteria criteria = example.createCriteria();
        if (query.getTypeEnum() != null) {
            criteria.andTypeEqualTo(query.getTypeEnum().getVal());
        }
        if (query.getStatusEnum() != null) {
            criteria.andStatusEqualTo(query.getStatusEnum().getVal());
        }
        if (StringUtils.isNotEmpty(query.getName())) {
            criteria.andNameLike("%" + query.getName() + "%");
        }
        if (query.getBeginTime() != null) {
            criteria.andGmtCreateGreaterThanOrEqualTo(query.getBeginTime());
        }
        if (query.getEndTime() != null) {
            criteria.andGmtCreateLessThan(DateUtil.getDayAfter(query.getEndTime()));
        }
        RowBounds rowBounds = new RowBounds(query.getOffset(), query.getPageSize());
        List<GiftDO> giftDOS = giftDOMapper.selectByExampleWithRowbounds(example, rowBounds);

        Page<GiftBO> page = new Page<>();
        page.setCurrentPage(query.getCurrentPage());
        page.setTotalCount((int) giftDOMapper.countByExample(example));
        page.setRecords(GiftConverter.convertBOS(giftDOS));
        return page;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveGift(GiftParam param) {
        GiftDO giftDO = new GiftDO();
        giftDO.setName(param.getName());
        giftDO.setImgPath(param.getImgPath());
        giftDO.setPrice(param.getPrice());
        giftDO.setInventory(param.getInventory());
        giftDO.setStatus(param.getStatusEnum().getVal());
        giftDO.setType(param.getTypeEnum().getVal());
        giftDO.setEggQuantity(param.getEggQuantity());
        giftDO.setDescription(param.getDescription());
        giftDO.setGmtModified(new Date());

        if (param.getId() != null && param.getId() > 0) {
            giftDO.setId(param.getId());
            giftDOMapper.updateByPrimaryKeySelective(giftDO);
        } else {
            giftDO.setGmtCreate(new Date());
            giftDOMapper.insertSelective(giftDO);
        }
    }

}
