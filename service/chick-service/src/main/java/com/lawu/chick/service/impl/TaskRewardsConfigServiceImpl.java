package com.lawu.chick.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.lawu.chick.cache.service.ChickBaseConfigCacheService;
import com.lawu.chick.cache.service.co.ChickBaseConfigCO;
import com.lawu.chick.cache.service.co.RewardsConfigCO;
import com.lawu.chick.service.ChickenService;
import com.lawu.chick.service.InventoryService;
import com.lawu.chick.service.ProductService;
import com.lawu.chick.service.TaskRecordService;
import com.lawu.chick.service.bo.*;
import com.lawu.chick.service.enums.*;
import com.lawu.chick.service.exception.WxAuthException;
import com.lawu.chick.service.param.InventoryParam;
import com.lawu.chick.wx.service.WxMpAuthService;
import com.lawu.chick.wx.service.bo.WxMpUserBO;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.lawu.chick.cache.service.TaskRewardsConfigCacheService;
import com.lawu.chick.cache.service.co.TaskRewardsConfigCO;
import com.lawu.chick.repository.domain.TaskRewardsConfigDO;
import com.lawu.chick.repository.domain.TaskRewardsConfigDOExample;
import com.lawu.chick.repository.mapper.TaskRewardsConfigDOMapper;
import com.lawu.chick.service.TaskRewardsConfigService;
import com.lawu.chick.service.converter.TaskRewardsConfigConverter;
import com.lawu.chick.service.param.TaskRewardsConfigParam;
import com.lawu.chick.service.query.TaskRewardsConfigQuery;
import com.lawu.framework.core.page.Page;

/**
 * @author meishuquan
 * @date 2018/6/15.
 */
@Service
public class TaskRewardsConfigServiceImpl implements TaskRewardsConfigService {

    @Autowired
    private TaskRewardsConfigDOMapper taskRewardsConfigDOMapper;

    @Autowired
    private TaskRewardsConfigCacheService taskRewardsConfigCacheService;

    @Autowired
    private ProductService productService;

    @Autowired
    private WxMpAuthService wxMpAuthService;

    @Autowired
    private InventoryService inventoryService;

    @Autowired
    private ChickenService chickenService;
    
    @Autowired
    private TaskRecordService taskRecordService;
    
    @Autowired
    private ChickBaseConfigCacheService chickBaseConfigCacheService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int saveTaskRewardsConfig(TaskRewardsConfigParam param) {
        if (param.getId() == 0) {
            TaskRewardsConfigDOExample example = new TaskRewardsConfigDOExample();
            example.createCriteria().andTypeEqualTo(param.getTypeEnum().getVal());
            long count = taskRewardsConfigDOMapper.countByExample(example);
            if (count > 0) {
                return (int) count;
            }
        }

        List<RewardsConfigBO> configBOS = new ArrayList<>();
        String[] productNumArr = param.getProductNumGroup().split(",");
        String[] productCountArr = param.getProductCountGroup().split(",");
        for (int i = 0; i < productNumArr.length; i++) {
            RewardsConfigBO configBO = new RewardsConfigBO();
            configBO.setProductNum(productNumArr[i]);
            configBO.setProductCount(Integer.valueOf(productCountArr[i]));
            configBOS.add(configBO);
        }

        TaskRewardsConfigDO configDO = new TaskRewardsConfigDO();
        configDO.setRewardsConfig(JSONArray.toJSONString(configBOS));
        configDO.setEffectiveTime(param.getEffectiveTime());
        configDO.setType(param.getTypeEnum().getVal());
        configDO.setStatus(param.getStatusEnum().getValue());
        configDO.setGmtModified(new Date());
        if (param.getId() != null && param.getId() > 0) {
            configDO.setId(param.getId());
            taskRewardsConfigDOMapper.updateByPrimaryKeySelective(configDO);
        } else {
            configDO.setGmtCreate(new Date());
            taskRewardsConfigDOMapper.insertSelective(configDO);
        }

        if (param.getImmediatelyCache()) {
            TaskRewardsConfigCO configCO = TaskRewardsConfigConverter.convertCO(configBOS);
            configCO.setStatus(param.getStatusEnum().getValue());
            taskRewardsConfigCacheService.saveCacheTaskRewardsConfig(param.getTypeEnum().toString(), configCO);
        }
        return 0;
    }

    @Override
    public Page<TaskRewardsConfigBO> listTaskRewardsConfig(TaskRewardsConfigQuery query) {
        RowBounds rowBounds = new RowBounds(query.getOffset(), query.getPageSize());
        List<TaskRewardsConfigDO> configDOS = taskRewardsConfigDOMapper.selectByExampleWithRowbounds(null, rowBounds);

        Page<TaskRewardsConfigBO> page = new Page<>();
        page.setCurrentPage(query.getCurrentPage());
        page.setTotalCount((int) taskRewardsConfigDOMapper.countByExample(null));
        page.setRecords(TaskRewardsConfigConverter.convertBOS(configDOS));
        return page;
    }

    @Override
    public TaskRewardsConfigBO getTaskRewardsConfig(Long id) {
        TaskRewardsConfigDO configDO = taskRewardsConfigDOMapper.selectByPrimaryKey(id);
        return TaskRewardsConfigConverter.convertBO(configDO);
    }

    @Override
    public void executeJob() {
        List<TaskRewardsConfigDO> configDOS = taskRewardsConfigDOMapper.selectByExampleWithBLOBs(null);
        if (configDOS.isEmpty()) {
            return;
        }

        int deviationVal = 2000 * 60;
        for (TaskRewardsConfigDO configDO : configDOS) {
            if (configDO.getEffectiveTime().getTime() <= System.currentTimeMillis() && System.currentTimeMillis() <= configDO.getEffectiveTime().getTime() + deviationVal) {
                TaskRewardsConfigBO configBO = TaskRewardsConfigConverter.convertBO(configDO);
                TaskRewardsConfigCO configCO = TaskRewardsConfigConverter.convertCO(configBO.getRewardsConfigBOS());
                configCO.setStatus(configBO.getStatus());
                taskRewardsConfigCacheService.saveCacheTaskRewardsConfig(TaskRewardsTypeEnum.getEnum(configBO.getType()).toString(), configCO);
            }
        }
    }

    @Override
    public boolean isExistRewardsConfig(TaskRewardsTypeEnum typeEnum) {
        TaskRewardsConfigCO taskRewardsConfigCO = taskRewardsConfigCacheService.getCacheTaskRewardsConfig(typeEnum.toString());
        if (taskRewardsConfigCO == null || StatusEnum.DISABLE.getValue().equals(taskRewardsConfigCO.getStatus())) {
            return false;
        }
        return true;
    }

    @Override
    public List<RewewardConfigForwardBO> getRewardsConfig(TaskRewardsTypeEnum typeEnum) {
        TaskRewardsConfigCO taskRewardsConfigCO = taskRewardsConfigCacheService.getCacheTaskRewardsConfig(typeEnum.toString());
        if (taskRewardsConfigCO == null || StatusEnum.DISABLE.getValue().equals(taskRewardsConfigCO.getStatus())) {
            return null;
        }
        List<RewardsConfigCO> rewardsConfigCOS = taskRewardsConfigCO.getRewardsConfigCOS();
        List<RewewardConfigForwardBO> rewewardConfigForwardBOS = new ArrayList<>();
        for (RewardsConfigCO configCO : rewardsConfigCOS) {
            RewewardConfigForwardBO rewewardConfigForwardBO = new RewewardConfigForwardBO();
            ProductBO productBO = productService.getProductByNum(configCO.getProductNum());
            if (productBO != null) {
                rewewardConfigForwardBO.setProductCount(configCO.getProductCount());
                rewewardConfigForwardBO.setProductNum(configCO.getProductNum());
                rewewardConfigForwardBO.setProductImgPath(productBO.getImgPath());
                rewewardConfigForwardBO.setProductName(productBO.getName());
                rewewardConfigForwardBOS.add(rewewardConfigForwardBO);
            }
        }
        return rewewardConfigForwardBOS;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void doHandleAfterTask(String userNum, String openid, TaskRewardsTypeEnum typeEnum,boolean insert) {
        if (TaskRewardsTypeEnum.ATTENTION.getVal().equals(typeEnum.getVal())) {
            WxMpUserBO wxMpUserBO;
            try {
                wxMpUserBO = wxMpAuthService.getWxUserAfterSubscribe(openid);
            } catch (Exception e) {
                e.printStackTrace();
                throw new WxAuthException("获取微信授权信息异常，请重试");
            }
            if (!wxMpUserBO.getSubscribe()) {
                throw new WxAuthException("当前微信没有授权，请先授权");
            }
        }

        InventoryDetailTypeEnum inventoryDetailTypeEnum = InventoryDetailTypeEnum.ATTENTION;
        if (TaskRewardsTypeEnum.INVITE.getVal().equals(typeEnum.getVal())) {
            inventoryDetailTypeEnum = InventoryDetailTypeEnum.INVITE;
        }
        if(insert){
        	int flag = taskRecordService.saveTaskRecord(userNum, typeEnum);
        	if(flag==0){
        		throw new RuntimeException("任务记录保存异常");
        	}
        }
        TaskRewardsConfigCO taskRewardsConfigCO = taskRewardsConfigCacheService.getCacheTaskRewardsConfig(typeEnum.toString());
        List<RewardsConfigCO> rewardsConfigCOS = taskRewardsConfigCO.getRewardsConfigCOS();
        for (RewardsConfigCO configCO : rewardsConfigCOS) {
            ProductBO productBO = productService.getProductByNum(configCO.getProductNum());
            if (ProductTypeEnum.CHICK.getValue().equals(productBO.getType())) {
                List<ChickenBaseInfoBO> chickenBaseInfoBOList = chickenService.getChickenListByMemberNum(userNum);
                ChickBaseConfigCO chickBaseConfigCO = chickBaseConfigCacheService.getCacheChickBaseInfo();
                if (chickBaseConfigCO.getChickMaxAdoptionCount() == chickenBaseInfoBOList.size()) {
                    continue;//判断小鸡数是否达到最大配置数量
                }
                //小鸡直接激活
                chickenService.createChicken(userNum,AdoptTypeEnum.SEND,configCO.getProductCount());
                continue;
            }
            InventoryParam inventoryParam = new InventoryParam();
            inventoryParam.setMemberNum(userNum);
            inventoryParam.setProductNum(configCO.getProductNum());
            inventoryParam.setQuantity(configCO.getProductCount());
            inventoryParam.setTypeEnum(inventoryDetailTypeEnum);
            inventoryParam.setDirectionEnum(InventoryDetailDirectionEnum.ADD);
            inventoryService.addInventory(inventoryParam);
        }
    }

}
