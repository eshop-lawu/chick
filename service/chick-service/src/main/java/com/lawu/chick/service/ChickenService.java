package com.lawu.chick.service;

import java.util.List;

import com.lawu.chick.cache.service.co.ChickenCacheOperateCO;
import com.lawu.chick.repository.domain.extend.ChickDOView;
import com.lawu.chick.repository.param.ChickGrowthParam;
import com.lawu.chick.repository.param.ChickUpdateInfoParam;
import com.lawu.chick.repository.param.ChickenDayHenhouseQueryParam;
import com.lawu.chick.repository.param.ChickenReduceCleanessParam;
import com.lawu.chick.repository.param.CommonListPageParam;
import com.lawu.chick.service.bo.ChickenBaseInfoBO;
import com.lawu.chick.service.bo.ChickenCleanessBO;
import com.lawu.chick.service.bo.ChickenGrowthBO;
import com.lawu.chick.service.bo.ChickenManagePageBO;
import com.lawu.chick.service.enums.AdoptTypeEnum;
import com.lawu.chick.service.exception.DataNotExistException;
import com.lawu.chick.service.param.ChickenPageParam;
import com.lawu.chick.service.param.ChickenSickParam;
import com.lawu.framework.core.page.Page;

/**
 * 小鸡接口
 *
 * @author zhangrc
 * @Description
 * @date 2018年4月25日
 */
public interface ChickenService {

    /**
     * 创建小鸡
     *
     * @param memberNum
     *            用户编号
     * @param typeEnum
     *            渠道
     */
	void createChicken(String memberNum, AdoptTypeEnum typeEnum, int chickenCount);

    /**
     * 查询成熟期后待生病的小鸡列表
     *
     * @return
     */
    /*List<ChickenBaseInfoBO> getToBeSickChickList(int offset, int pageSize);*/

    /**
     * 定时任务，小鸡生病
     *
     * @param chickenSickParam
     */
    void doSick(ChickenSickParam chickenSickParam);

    /**
     * 修改小鸡名称
     *
     * @param num
     * @param name
     * @author meishuquan
     */
    void updateChickenName(String num, String name);

    /**
     * 查询当前处于活动+非死亡的小鸡列表，进鸡舍
     *
     * @param offset
     * @param pageSize
     * @return
     */
    List<ChickenBaseInfoBO> getToBeIntoHenhouseChickList(int offset, int pageSize);

    /**
     * 小鸡定时进入鸡舍
     *
     * @param chickenSickParams
     */
    void intoHenhouse(List<ChickenSickParam> chickenSickParams);

    /**
     * 所有小鸡信息列表查询接口，排除死亡
     *
     * @param memberNum
     * @return
     */
    List<ChickenBaseInfoBO> getChickenListByMemberNum(String memberNum);

    /**
     * 放养小鸡，针对处于睡眠中的小鸡
     *
     * @param memberNum
     * @return
     */
    void restocking(String memberNum);

    /**
     * 小鸡治疗，针对生病的小鸡
     *
     * @param memberNum
     * @param chickenNum
     * @param isTreat
     * @return
     */
    void treat(String memberNum, String chickenNum, Boolean isTreat);

    /**
     * 查询成长值小于100的小鸡id
     *
     * @param offset
     * @param pageSize
     * @return
     */
    List<ChickenGrowthBO> getChickGrowthValLessThanConfig(int offset, int pageSize);

    void addDayGrowth(ChickGrowthParam param);

    /**
     * 小鸡进入产房并且分配鸡蛋
     * 
     * @author jiangxinjun
     * @createDate 2018年4月26日
     * @updateDate 2018年4月26日
     */
    void intoDeliveryRoomAndLayEgg();

    /**
     * 鸡蛋分配
     *
     * @author jiangxinjun
     * @createDate 2018年4月26日
     * @updateDate 2018年4月26日
     */
    void eggDistribution();

    /**
     * 查询饱腹值需要递减的值
     * 
     * @param offset
     * @param pageSize
     * @return
     */
    List<ChickenBaseInfoBO> getAllChickNotDieChick(int offset, int pageSize);

    /**
     * 查询清洁度大于60的牧场中满足时间15分钟条件累加愉悦值的小鸡列表
     *
     * @param param
     * @return
     */
   /* List<ChickDOView> getChickRangelandClean(ChickQueryParam param);*/

    /**
     * 将清洁度大于60的牧场中满足时间15分钟条件累加愉悦值的小鸡信息放入缓存
     *
     * @param chickDOViewList
     */
    void addRangelandCleanJoyfullCache(List<ChickDOView> chickDOViewList);

    /**
     * 查询饱腹值需要递减的值
     * 
     * @param offset
     * @param pageSize
     * @return
     */
    List<ChickDOView> getChickNotDiefullPeriod(int offset, int pageSize);

    /**
     * 将饱腹值需要递减的值放入缓存
     * 
     * @param list
     */
    void addChickFullValToCache(List<ChickDOView> list);

    /**
     * 查询清洁度小于于60的鸡舍中满足时间10分钟条件减愉悦值的小鸡列表
     *
     * @param param
     * @return
     */
    /*List<ChickDOView> getHenhouseCleanMinusJoyfullChickens(ChickenMinusJoyfullQueryParam param);*/

    /**
     * 将清洁度小于60的鸡舍中满足时间10分钟条件减愉悦值的小鸡信息放入缓存
     *
     * @param chickDOViewList
     */
    void addHenhouseCleanJoyfullCache(List<ChickDOView> chickDOViewList);

    /**
     * 处理缓存中小鸡饱腹值递减任务
     */
    void doDecreFullVal(ChickenCacheOperateCO co);

    /**
     * 小鸡产蛋,把分配的鸡蛋放入到蛋窝
     *
     * @author jiangxinjun
     * @createDate 2018年4月27日
     * @updateDate 2018年4月27日
     */
    void eggProduction();

    /**
     * 白天未处牧区每15分钟-2愉悦值
     *
     * @param param
     * @return
     */
    List<ChickDOView> getDayHenhouseMinusJoyfullChickens(ChickenDayHenhouseQueryParam param);

    /**
     * 将白天未处牧区每15分钟-2愉悦值的小鸡信息放入缓存
     * @param chickDOViewList
     */
    void addDayHenhouseJoyfullCache(List<ChickDOView> chickDOViewList);

    /**
     * 根据用户编号和小鸡编号查询小鸡信息
     * @param memberNum
     * @param num
     * @return
     */
	ChickenBaseInfoBO getChickenInfoByMembernumNum(String memberNum, String num);

	/**
	 * 喂养小鸡更新小鸡信息
	 * @param feedUpdate
	 */
	void feedChickUpdateChickInfo(ChickUpdateInfoParam feedUpdate);

    /**
     * 小鸡位于清洁度>=60的农场每15分钟+1愉悦值 将缓存同步数据库
     */
    /*void doAddJoyful(ChickenCacheOperateCO cache);*/

    /**
     * 小鸡位于清洁度<60的鸡舍每10分钟-1愉悦值
     */
   /* void doMinusJoyfulForHouseClean(ChickenCacheOperateCO cache);*/

    /**
     * 白天未处牧区每15分钟-2愉悦值 同步数据库
     */
    void doMinusJoyfulForDayHouse(ChickenCacheOperateCO cache);
    
    	/**
	 * 小鸡管理
	 * 
	 * @param param
	 * @return
	 */
	Page<ChickenManagePageBO> managPage(ChickenPageParam param);

    /**
     * 查询自己和好友的小鸡
     * @param chickenNum
     * @param memberNum
     * @return
     */
    ChickenBaseInfoBO getChickenByChickenNum(String memberNum,String chickenNum);

    /**
     * 查询凌晨6点后要改为活动的小鸡，非死亡的，睡眠中的小鸡
     * @param offset
     * @param pageSize
     * @return
     */
	List<ChickenBaseInfoBO> getChickenToActiveList(int offset, int pageSize);

	/**
	 * 小鸡变为活动状态
	 * @param chickParam
	 */
	void tobeActive(List<ChickenSickParam> chickParam);
	
	/**
	 * 获取小鸡数量
	 * @param memberNum
	 * @return
	 */
	int getChickenCount(String memberNum);
	
		
   /**
     * 收取鸡蛋, 把蛋窝的鸡蛋放入个人账户
     * 
     * @author jiangxinjun
     * @createDate 2018年4月27日
     * @updateDate 2018年4月27日
     */
    void receiveEggs(String memberNum, String chickNum) throws DataNotExistException;


    /**
     * 离开鸡窝更新小鸡在鸡窝累计时长
     * @param chickNum
     */
    void updateChickInHouse(String chickNum);

    /**
     * 查询衰减愉悦值的小鸡列表
     * @param offset
     * @param pageSize
     * @return
     */
    List<ChickenBaseInfoBO> listAttenuationJoyfulVal(int offset, int pageSize);

    /**
     * 衰减愉悦值
     * @param chickenBaseInfoBO
     */
    void attenuationJoyfulVal(ChickenBaseInfoBO chickenBaseInfoBO);

    /**
     * 判断小鸡是否在鸡窝(null：小鸡不存在 true：小鸡在鸡窝 false 小鸡不在鸡窝)
     * 未放养、睡眠、饿晕、生产的小鸡在鸡窝
     * @param chickNum
     */
    Boolean isInHouse(String chickNum);

    /**
     * 查询在鸡窝中的小鸡并且在鸡窝累计时长>120分钟
     * @param param
     * @return
     */
    List<ChickenCleanessBO> getChickenInHouseReduceCleanessList(ChickenReduceCleanessParam param);

    /**
     * 在鸡窝中的小鸡并且在鸡窝累计时长>120分钟 减少10清洁度并重置
     * @param cleanBO
     */
    void reduceCleaness(ChickenCleanessBO cleanBO);

    /**
     * 查询在鸡窝中的小鸡列表
     * @param param
     * @return
     */
    List<ChickenCleanessBO> getChickenInHouseList(CommonListPageParam param);

    /**
     * 更新累计时长并重置时间
     * @param id
     */
    void updateChickInHouseTime(Long id);
    
    /**
     * 判断是否存在有小鸡在牧场
     * 
     * @param memberNum
     * @return
     */
    Boolean chicksIsInExternal(String memberNum);
	/**
	 * 牧场收益
	 * @param cache
	 */
	void doRangelandProfit(ChickenCacheOperateCO cache);
	
	List<ChickDOView> getChickInRangelandAndIsClean(int offset, int pageSize);

	List<ChickDOView> getChickInRangelandAndIsHunger(int offset, int pageSize);

	void addCacheRangelandProfit(List<ChickDOView> lt);

    /**
     * 查询用户的小鸡数量
     * @param memberNum
     * @return
     */
	Integer getChickCount(String memberNum);

}
