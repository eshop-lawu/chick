package com.lawu.chick.service.impl;

import com.lawu.chick.cache.service.ChickBaseConfigCacheService;
import com.lawu.chick.cache.service.co.ChickBaseConfigCO;
import com.lawu.chick.repository.domain.ChickenCureTaskDO;
import com.lawu.chick.repository.domain.ChickenCureTaskDOExample;
import com.lawu.chick.repository.domain.ChickenDO;
import com.lawu.chick.repository.domain.ChickenDOExample;
import com.lawu.chick.repository.mapper.ChickenCureTaskDOMapper;
import com.lawu.chick.repository.mapper.ChickenDOMapper;
import com.lawu.chick.repository.mapper.extend.ChickenCureTaskDOExtendMapper;
import com.lawu.chick.repository.param.ChickenCureTaskAddParam;
import com.lawu.chick.service.ChickenCureTaskService;
import com.lawu.chick.service.bo.CureTaskBO;
import com.lawu.chick.service.bo.CureTaskRtnBO;
import com.lawu.chick.service.enums.ChickenCureTaskTypeEnum;
import com.lawu.chick.service.enums.PeriodTypeEnum;
import com.lawu.chick.service.param.ChickenCureTaskCalcParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class ChickenCureTaskServiceImpl implements ChickenCureTaskService {

    @Autowired
    private ChickenDOMapper chickenDOMapper;

    @Autowired
    private ChickenCureTaskDOMapper chickenCureTaskDOMapper;

    @Autowired
    private ChickenCureTaskDOExtendMapper chickenCureTaskDOExtendMapper;

    @Autowired
    private ChickBaseConfigCacheService chickBaseConfigCacheService;

    /**
     * 处理小鸡治愈任务进度
     *
     * @param chickenCureTaskCalcParam
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void dealCureTaskProcess(ChickenCureTaskCalcParam chickenCureTaskCalcParam) {
        if (chickenCureTaskCalcParam.getChickenCureTaskTypeEnum().getVal().equals(ChickenCureTaskTypeEnum.FEEDCLEAN.getVal()) &&
                (chickenCureTaskCalcParam.getFriendMemberId() == null || chickenCureTaskCalcParam.getFriendMemberId() == 0L)) {
            return;
        }
        //判断该用户是否存在治疗中的小鸡
        ChickenDOExample exampleQuery = new ChickenDOExample();
        exampleQuery.createCriteria().andMemberNumEqualTo(chickenCureTaskCalcParam.getMemberNum()).andPeriodEqualTo(PeriodTypeEnum.CURE.getVal());
        List<ChickenDO> chickenDOList = chickenDOMapper.selectByExample(exampleQuery);
        if (chickenDOList == null || chickenDOList.size() == 0) {
            return;
        }
        //初始化或累计任务进度
        ChickenCureTaskDOExample chickenCureTaskDOExample = new ChickenCureTaskDOExample();
        chickenCureTaskDOExample.createCriteria().andMemberNumEqualTo(chickenCureTaskCalcParam.getMemberNum()).andIsFinishedEqualTo(false).andTaskTypeEqualTo(chickenCureTaskCalcParam.getChickenCureTaskTypeEnum().getVal());
        List<ChickenCureTaskDO> chickenCureTaskDOList1 = chickenCureTaskDOMapper.selectByExample(chickenCureTaskDOExample);
        if (chickenCureTaskDOList1 == null || chickenCureTaskDOList1.size() == 0) {
            ChickenCureTaskDO chickenCureTaskDO = new ChickenCureTaskDO();
            chickenCureTaskDO.setChickenNum(chickenDOList.get(0).getNum());
            chickenCureTaskDO.setMemberNum(chickenCureTaskCalcParam.getMemberNum());
            chickenCureTaskDO.setTaskType(chickenCureTaskCalcParam.getChickenCureTaskTypeEnum().getVal());
            if (chickenCureTaskCalcParam.getChickenCureTaskTypeEnum().getVal().equals(ChickenCureTaskTypeEnum.FEEDCLEAN.getVal())) {
                chickenCureTaskDO.setRelateMemberIds(chickenCureTaskCalcParam.getFriendMemberId().toString());
            }
            chickenCureTaskDO.setIsFinished(false);
            chickenCureTaskDO.setProgress(1);
            chickenCureTaskDO.setGmtCreate(new Date());
            chickenCureTaskDOMapper.insert(chickenCureTaskDO);
        } else {
            ChickenCureTaskAddParam chickenCureTaskAddParam = new ChickenCureTaskAddParam();
            if (chickenCureTaskCalcParam.getChickenCureTaskTypeEnum().getVal().equals(ChickenCureTaskTypeEnum.FEEDCLEAN.getVal())) {
                String relateMemberIds = chickenCureTaskDOList1.get(0).getRelateMemberIds();
                List<String> relateMemberIdsArray = Arrays.asList(relateMemberIds.split(","));
                if (relateMemberIdsArray.contains(chickenCureTaskCalcParam.getFriendMemberId().toString())) {
                    return;//已帮助过该好友喂食和打扫，则不需要计数
                }
                chickenCureTaskAddParam.setFriendMemberId(chickenCureTaskCalcParam.getFriendMemberId().toString());
            }
            chickenCureTaskAddParam.setChickenCureTaskType(chickenCureTaskCalcParam.getChickenCureTaskTypeEnum().getVal());
            chickenCureTaskAddParam.setMemberNum(chickenCureTaskCalcParam.getMemberNum());
            chickenCureTaskDOExtendMapper.addCureTaskProcess(chickenCureTaskAddParam);
        }
        //满足要求小鸡治疗成功
        ChickBaseConfigCO chickBaseConfigCO = chickBaseConfigCacheService.getCacheChickBaseInfo();
        ChickenCureTaskDOExample checkQueryChickenCureTaskDOExample = new ChickenCureTaskDOExample();
        checkQueryChickenCureTaskDOExample.createCriteria().andMemberNumEqualTo(chickenCureTaskCalcParam.getMemberNum()).andIsFinishedEqualTo(false);
        List<ChickenCureTaskDO> chickenCureTaskDOList = chickenCureTaskDOMapper.selectByExample(checkQueryChickenCureTaskDOExample);
        int finishFlag = 0;
        for (ChickenCureTaskDO chickenCureTaskDO : chickenCureTaskDOList) {
            if (ChickenCureTaskTypeEnum.INVISIT.getVal().equals(chickenCureTaskDO.getTaskType()) && chickenCureTaskDO.getProgress().intValue() >= chickBaseConfigCO.getFinishTaskInviteCount()) {
                finishFlag++;
            }
            if (ChickenCureTaskTypeEnum.SALES.getVal().equals(chickenCureTaskDO.getTaskType()) && chickenCureTaskDO.getProgress().intValue() >= chickBaseConfigCO.getFinishTaskSalesCount()) {
                finishFlag++;
            }
            if (ChickenCureTaskTypeEnum.FEEDCLEAN.getVal().equals(chickenCureTaskDO.getTaskType()) && chickenCureTaskDO.getProgress().intValue() >= chickBaseConfigCO.getFinishTaskHelpCount()) {
                finishFlag++;
            }
            if (ChickenCureTaskTypeEnum.TRADE.getVal().equals(chickenCureTaskDO.getTaskType()) && chickenCureTaskDO.getProgress().intValue() >= chickBaseConfigCO.getFinishTaskTradeCount()) {
                finishFlag++;
            }
        }
        if (finishFlag >= chickBaseConfigCO.getFinishTaskLessTCount()) {
            //修改小鸡为成熟期，重置生命周期计时起点，如果处于放养阶段还需更新状态为放养？
            ChickenDO chickenDO = new ChickenDO();
            chickenDO.setId(chickenDOList.get(0).getId());
            chickenDO.setPeriod(PeriodTypeEnum.MATURE.getVal());
            chickenDO.setLifeStartTime(new Date());
            chickenDO.setGmtModified(new Date());
            chickenDOMapper.updateByPrimaryKeySelective(chickenDO);

            ChickenCureTaskDOExample updateChickenCureTaskDOExample = new ChickenCureTaskDOExample();
            updateChickenCureTaskDOExample.createCriteria().andMemberNumEqualTo(chickenCureTaskCalcParam.getMemberNum()).andIsFinishedEqualTo(false).andChickenNumEqualTo(chickenDOList.get(0).getNum());
            ChickenCureTaskDO chickenCureTaskDO = new ChickenCureTaskDO();
            chickenCureTaskDO.setIsFinished(true);
            chickenCureTaskDO.setGmtModify(new Date());
            chickenCureTaskDOMapper.updateByExampleSelective(chickenCureTaskDO, updateChickenCureTaskDOExample);
        }
    }

    /**
     * 查询小鸡治愈任务列表
     *
     * @param currentUserNum
     * @return
     */
    @Override
    public CureTaskRtnBO getTreatTaskList(String currentUserNum, String chickenNum) {
        ChickenCureTaskDOExample chickenCureTaskDOExample = new ChickenCureTaskDOExample();
        chickenCureTaskDOExample.createCriteria().andMemberNumEqualTo(currentUserNum).andChickenNumEqualTo(chickenNum).andIsFinishedEqualTo(false);
        List<ChickenCureTaskDO> chickenCureTaskDOList = chickenCureTaskDOMapper.selectByExample(chickenCureTaskDOExample);
        List<CureTaskBO> cureTaskBOList = initCureTask();
        ChickBaseConfigCO chickBaseConfigCO = chickBaseConfigCacheService.getCacheChickBaseInfo();//取缓存配置
        int finishFlag = 0;
        for (ChickenCureTaskDO chickenCureTaskDO : chickenCureTaskDOList) {
            if (ChickenCureTaskTypeEnum.INVISIT.getVal().equals(chickenCureTaskDO.getTaskType()) && chickenCureTaskDO.getProgress().intValue() >= chickBaseConfigCO.getFinishTaskInviteCount()) {
                updateInitCureTask(cureTaskBOList,chickenCureTaskDO.getTaskType());
                finishFlag++;
            }
            if (ChickenCureTaskTypeEnum.SALES.getVal().equals(chickenCureTaskDO.getTaskType()) && chickenCureTaskDO.getProgress().intValue() >= chickBaseConfigCO.getFinishTaskSalesCount()) {
                updateInitCureTask(cureTaskBOList,chickenCureTaskDO.getTaskType());
                finishFlag++;
            }
            if (ChickenCureTaskTypeEnum.FEEDCLEAN.getVal().equals(chickenCureTaskDO.getTaskType()) && chickenCureTaskDO.getProgress().intValue() >= chickBaseConfigCO.getFinishTaskHelpCount()) {
                updateInitCureTask(cureTaskBOList,chickenCureTaskDO.getTaskType());
                finishFlag++;
            }
            if (ChickenCureTaskTypeEnum.TRADE.getVal().equals(chickenCureTaskDO.getTaskType()) && chickenCureTaskDO.getProgress().intValue() >= chickBaseConfigCO.getFinishTaskTradeCount()) {
                updateInitCureTask(cureTaskBOList,chickenCureTaskDO.getTaskType());
                finishFlag++;
            }
        }
        for (CureTaskBO cureTaskBO : cureTaskBOList) {
            if (ChickenCureTaskTypeEnum.INVISIT.getVal().equals(cureTaskBO.getChickenCureTaskTypeEnum().getVal())) {
                cureTaskBO.setNumber(chickBaseConfigCO.getFinishTaskInviteCount());
            } else if (ChickenCureTaskTypeEnum.SALES.getVal().equals(cureTaskBO.getChickenCureTaskTypeEnum().getVal())) {
                cureTaskBO.setNumber(chickBaseConfigCO.getFinishTaskSalesCount());
            } else if (ChickenCureTaskTypeEnum.FEEDCLEAN.getVal().equals(cureTaskBO.getChickenCureTaskTypeEnum().getVal())) {
                cureTaskBO.setNumber(chickBaseConfigCO.getFinishTaskHelpCount());
            } else if (ChickenCureTaskTypeEnum.TRADE.getVal().equals(cureTaskBO.getChickenCureTaskTypeEnum().getVal())) {
                cureTaskBO.setNumber(chickBaseConfigCO.getFinishTaskTradeCount());
            }
        }
        CureTaskRtnBO cureTaskRtnBO = new CureTaskRtnBO();
        cureTaskRtnBO.setCureTaskBOList(cureTaskBOList);
        cureTaskRtnBO.setFinishTaskCount(finishFlag);
        cureTaskRtnBO.setGoalTaskCount(chickBaseConfigCO.getFinishTaskLessTCount());
        return cureTaskRtnBO;
    }

    /**
     * 更新治愈任务完成情况
     *
     * @param cureTaskBOList
     * @param taskType
     */
    private void updateInitCureTask(List<CureTaskBO> cureTaskBOList, Byte taskType) {
        for (CureTaskBO cureTaskBO : cureTaskBOList) {
            if (cureTaskBO.getChickenCureTaskTypeEnum().getVal().equals(taskType)) {
                cureTaskBO.setFinish(true);
            }
        }
    }

    /**
     * 初始化治愈任务
     *
     * @return
     */
    private List<CureTaskBO> initCureTask() {
        List<CureTaskBO> cureTaskBOList = new ArrayList<>();
        CureTaskBO cureTaskBO1 = new CureTaskBO();
        cureTaskBO1.setChickenCureTaskTypeEnum(ChickenCureTaskTypeEnum.INVISIT);
        cureTaskBO1.setFinish(false);
        cureTaskBO1.setNumber(0);
        cureTaskBOList.add(cureTaskBO1);
        CureTaskBO cureTaskBO2 = new CureTaskBO();
        cureTaskBO2.setChickenCureTaskTypeEnum(ChickenCureTaskTypeEnum.SALES);
        cureTaskBO2.setFinish(false);
        cureTaskBO2.setNumber(0);
        cureTaskBOList.add(cureTaskBO2);
        CureTaskBO cureTaskBO3 = new CureTaskBO();
        cureTaskBO3.setChickenCureTaskTypeEnum(ChickenCureTaskTypeEnum.FEEDCLEAN);
        cureTaskBO3.setFinish(false);
        cureTaskBO3.setNumber(0);
        cureTaskBOList.add(cureTaskBO3);
        CureTaskBO cureTaskBO4 = new CureTaskBO();
        cureTaskBO4.setChickenCureTaskTypeEnum(ChickenCureTaskTypeEnum.TRADE);
        cureTaskBO4.setFinish(false);
        cureTaskBO4.setNumber(0);
        cureTaskBOList.add(cureTaskBO4);
        return cureTaskBOList;

    }
}
