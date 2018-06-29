package com.lawu.chick.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.chick.cache.service.EggConfigCacheService;
import com.lawu.chick.cache.service.co.EggConfigCache;
import com.lawu.chick.id.worker.generate.impl.BizIdType;
import com.lawu.chick.id.worker.generate.impl.IdWorkerHelper;
import com.lawu.chick.repository.domain.EggExchangeRecordDO;
import com.lawu.chick.repository.domain.EggExchangeRecordDOExample;
import com.lawu.chick.repository.domain.GiftDO;
import com.lawu.chick.repository.domain.WxUserDO;
import com.lawu.chick.repository.domain.WxUserDOExample;
import com.lawu.chick.repository.mapper.EggExchangeRecordDOMapper;
import com.lawu.chick.repository.mapper.GiftDOMapper;
import com.lawu.chick.repository.mapper.WxUserDOMapper;
import com.lawu.chick.repository.mapper.extend.GiftDOExtendMapper;
import com.lawu.chick.repository.mapper.extend.MemberDOExtendMapper;
import com.lawu.chick.repository.param.MemberEggOperatorParam;
import com.lawu.chick.service.EggExchangeRecordService;
import com.lawu.chick.service.MemberService;
import com.lawu.chick.service.RangelandEventRecordService;
import com.lawu.chick.service.bo.EggExchangeRecordBO;
import com.lawu.chick.service.bo.EggExchangeValBO;
import com.lawu.chick.service.bo.InventoryEggBO;
import com.lawu.chick.service.constants.EventTitleConstant;
import com.lawu.chick.service.converter.EggExchangeRecordConverter;
import com.lawu.chick.service.enums.ChickenCureTaskTypeEnum;
import com.lawu.chick.service.enums.EggExchangeRecordStatusEnum;
import com.lawu.chick.service.enums.EggExchangeRecordTypeEnum;
import com.lawu.chick.service.enums.EventRecordAttrTypeEnum;
import com.lawu.chick.service.enums.EventRecordDirectionEnum;
import com.lawu.chick.service.enums.EventRecordFactorEnum;
import com.lawu.chick.service.enums.EventRecordSourceEnum;
import com.lawu.chick.service.event.EventPublisher;
import com.lawu.chick.service.param.EggExchangeParam;
import com.lawu.chick.service.param.RangelandEventRecordParam;
import com.lawu.chick.service.query.EggExchangeRecordMemberQuery;
import com.lawu.chick.service.query.EggExchangeRecordOperatorQuery;
import com.lawu.framework.core.page.Page;
import com.lawu.utils.DateUtil;

/**
 * @author meishuquan
 * @date 2018/4/26.
 */
@Service
public class EggExchangeRecordServiceImpl implements EggExchangeRecordService {

    @Autowired
    private EggExchangeRecordDOMapper exchangeRecordDOMapper;

    @Autowired
    private MemberService memberService;

    @Autowired
    private EggConfigCacheService eggConfigCacheService;

    @Autowired
    private IdWorkerHelper idWorkerHelper;

    @Autowired
    private MemberDOExtendMapper memberDOExtendMapper;

    @Autowired
    private RangelandEventRecordService rangelandEventRecordService;

    @Autowired
    private GiftDOExtendMapper giftDOExtendMapper;

    @Autowired
    private EventPublisher eventPublisher;

    @Autowired
    private GiftDOMapper giftDOMapper;

    @Autowired
    private WxUserDOMapper wxUserDOMapper;

    @Override
    public EggExchangeValBO getEggExchangeVal(String memberNum) {
        EggExchangeValBO exchangeValBO = new EggExchangeValBO();
        InventoryEggBO eggBO = memberService.getEggs(memberNum);
        EggConfigCache configCache = eggConfigCacheService.getCacheEggConfig();
        if (configCache == null) {
            return null;
        }
        if (eggBO.getEggs().compareTo(configCache.getExchangeRedpacketEggs()) == -1) {
            exchangeValBO.setEggExchangeVal(0);
            return exchangeValBO;
        }
        int exchangeVal = eggBO.getEggs().divide(configCache.getExchangeRedpacketEggs(), 3, BigDecimal.ROUND_DOWN).intValue();
        exchangeValBO.setEggExchangeVal(exchangeVal);
        return exchangeValBO;
    }

    @Override
    public Page<EggExchangeRecordBO> listMemberEggExchangeRecord(EggExchangeRecordMemberQuery query) {
        EggExchangeRecordDOExample example = new EggExchangeRecordDOExample();
        example.setOrderByClause("id desc");
        example.createCriteria().andMemberNumEqualTo(query.getMemberNum());
        RowBounds rowBounds = new RowBounds(query.getOffset(), query.getPageSize());
        List<EggExchangeRecordDO> recordDOS = exchangeRecordDOMapper.selectByExampleWithRowbounds(example, rowBounds);

        Page<EggExchangeRecordBO> page = new Page<>();
        page.setCurrentPage(query.getCurrentPage());
        page.setTotalCount((int) exchangeRecordDOMapper.countByExample(example));
        page.setRecords(EggExchangeRecordConverter.convertBOS(recordDOS));
        return page;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveEggExchangeRecord(EggExchangeParam param) throws Exception {
        MemberEggOperatorParam memberEggOperatorParam = new MemberEggOperatorParam();
        memberEggOperatorParam.setEggs(param.getEggQuantity());
        memberEggOperatorParam.setMemberNum(param.getMemberNum());
        memberEggOperatorParam.setIncrease(false);
        memberDOExtendMapper.eggOperator(memberEggOperatorParam);
        if (param.getTypeEnum() == EggExchangeRecordTypeEnum.GIFT) {
            giftDOExtendMapper.updateGiftInventory(param.getGiftId());
        }

        EggExchangeRecordDO recordDO = new EggExchangeRecordDO();
        recordDO.setNum(idWorkerHelper.generate(BizIdType.EXCHANGE));
        recordDO.setMemberNum(param.getMemberNum());
        recordDO.setType(param.getTypeEnum().getVal());
        recordDO.setEggQuantity(param.getEggQuantity());
        recordDO.setAmount(param.getAmount());
        recordDO.setGiftId(param.getGiftId());
        recordDO.setGiftName(param.getGiftName());
        recordDO.setGiftImgPath(param.getGiftImgPath());
        recordDO.setStatus(param.getStatusEnum().getVal());
        recordDO.setName(param.getName());
        recordDO.setMobile(param.getMobile());
        recordDO.setAddress(param.getAddress());
        recordDO.setGmtModified(new Date());
        recordDO.setGmtCreate(new Date());
        exchangeRecordDOMapper.insertSelective(recordDO);

        //记录事件
        RangelandEventRecordParam event = new RangelandEventRecordParam();
        event.setAttrTypeEnum(EventRecordAttrTypeEnum.EGG);
        event.setDirectionEnum(EventRecordDirectionEnum.REDUCE);
        event.setFactorEnum(EventRecordFactorEnum.EGG_OPERATE);
        event.setMemberNum(param.getMemberNum());
        event.setSourceEnum(EventRecordSourceEnum.OWNER);
        event.setTitle(EventTitleConstant.TITLE_EGG_EXCHANGE);
        event.setVal(param.getEggQuantity());
        rangelandEventRecordService.saveRangelandEventRecord(event);

    }

    @Override
    public Page<EggExchangeRecordBO> listOperatorEggExchangeRecord(EggExchangeRecordOperatorQuery query) {
        EggExchangeRecordDOExample example = new EggExchangeRecordDOExample();
        example.setOrderByClause("id desc");
        EggExchangeRecordDOExample.Criteria criteria = example.createCriteria();
        if (query.getTypeEnum() != null) {
            criteria.andTypeEqualTo(query.getTypeEnum().getVal());
        }
        if (query.getStatusEnum() != null) {
            criteria.andStatusEqualTo(query.getStatusEnum().getVal());
        }
        if (StringUtils.isNotEmpty(query.getGiftName())) {
            criteria.andGiftNameLike("%" + query.getGiftName() + "%");
        }
        if (StringUtils.isNotEmpty(query.getName())) {
            criteria.andNameLike("%" + query.getName() + "%");
        }
        if (StringUtils.isNotEmpty(query.getMobile())) {
            criteria.andMobileEqualTo(query.getMobile());
        }
        if (StringUtils.isNotEmpty(query.getExpressNum())) {
            criteria.andExpressNumEqualTo(query.getExpressNum());
        }
        if (query.getBeginTime() != null) {
            criteria.andGmtCreateGreaterThanOrEqualTo(query.getBeginTime());
        }
        if (query.getEndTime() != null) {
            criteria.andGmtCreateLessThan(DateUtil.getDayAfter(query.getEndTime()));
        }
        RowBounds rowBounds = new RowBounds(query.getOffset(), query.getPageSize());
        List<EggExchangeRecordDO> recordDOS = exchangeRecordDOMapper.selectByExampleWithRowbounds(example, rowBounds);
        List<EggExchangeRecordBO> recordBOS = EggExchangeRecordConverter.convertBOS(recordDOS);
        if (!recordBOS.isEmpty()) {
            for (EggExchangeRecordBO recordBO : recordBOS) {
                if (EggExchangeRecordTypeEnum.getEnum(recordBO.getType()) == EggExchangeRecordTypeEnum.GIFT && recordBO.getGiftId() > 0) {
                    GiftDO giftDO = giftDOMapper.selectByPrimaryKey(recordBO.getGiftId());
                    recordBO.setGiftType(giftDO.getType());
                }
                WxUserDOExample wxUserDOExample = new WxUserDOExample();
                wxUserDOExample.createCriteria().andMemberNumEqualTo(recordBO.getMemberNum());
                List<WxUserDO> userDOS = wxUserDOMapper.selectByExample(wxUserDOExample);
                if (!userDOS.isEmpty()) {
                    recordBO.setWxNickName(userDOS.get(0).getNickname());
                }
            }
        }

        Page<EggExchangeRecordBO> page = new Page<>();
        page.setCurrentPage(query.getCurrentPage());
        page.setTotalCount((int) exchangeRecordDOMapper.countByExample(example));
        page.setRecords(recordBOS);
        return page;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void sendExchangeGift(Long id, String expressNum) {
        EggExchangeRecordDO recordDO = new EggExchangeRecordDO();
        recordDO.setId(id);
        recordDO.setStatus(EggExchangeRecordStatusEnum.SENT.getVal());
        recordDO.setExpressNum(expressNum);
        recordDO.setGmtSend(new Date());
        recordDO.setGmtModified(new Date());
        exchangeRecordDOMapper.updateByPrimaryKeySelective(recordDO);
    }

    @Override
    public List<EggExchangeRecordBO> listEggExchangeRedpacket(int offset, int pageSize) {
        EggExchangeRecordDOExample example = new EggExchangeRecordDOExample();
        example.createCriteria().andTypeEqualTo(EggExchangeRecordTypeEnum.REDPACKET.getVal()).andStatusEqualTo(EggExchangeRecordStatusEnum.PENDING.getVal());
        RowBounds rowBounds = new RowBounds(offset, pageSize);
        List<EggExchangeRecordDO> recordDOS = exchangeRecordDOMapper.selectByExampleWithRowbounds(example, rowBounds);
        return EggExchangeRecordConverter.convertBOS(recordDOS);
    }

    @Override
    public EggExchangeRecordBO getEggExchangeRecord(Long id) {
        EggExchangeRecordDO recordDO = exchangeRecordDOMapper.selectByPrimaryKey(id);
        return EggExchangeRecordConverter.convertBO(recordDO);
    }

}
