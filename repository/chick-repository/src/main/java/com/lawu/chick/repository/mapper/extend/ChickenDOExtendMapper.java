package com.lawu.chick.repository.mapper.extend;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lawu.chick.repository.domain.ChickenDO;
import com.lawu.chick.repository.domain.extend.ChickDOView;
import com.lawu.chick.repository.domain.extend.ChickenInHouseDOView;
import com.lawu.chick.repository.domain.extend.CountEggProductionDO;
import com.lawu.chick.repository.param.ChickDealWithParam;
import com.lawu.chick.repository.param.ChickEggOperatorParam;
import com.lawu.chick.repository.param.ChickGrowthParam;
import com.lawu.chick.repository.param.ChickQueryFullValParam;
import com.lawu.chick.repository.param.ChickQueryParam;
import com.lawu.chick.repository.param.ChickUpdateInfoParam;
import com.lawu.chick.repository.param.ChickenDayHenhouseQueryParam;
import com.lawu.chick.repository.param.ChickenMinusJoyfullQueryParam;
import com.lawu.chick.repository.param.ChickenReduceCleanessParam;
import com.lawu.chick.repository.param.CommonListPageParam;
import com.lawu.chick.repository.param.ReduceCleanParam;

/**
 * 
 * @author jiangxinjun
 * @createDate 2018年4月26日
 * @updateDate 2018年4月26日
 */
public interface ChickenDOExtendMapper {
    
    /**
     * 获取当前生产中小鸡的总的愉悦值
     * 
     * @return
     * @author jiangxinjun
     * @createDate 2018年4月26日
     * @updateDate 2018年4月26日
     */
    BigDecimal totalJoyfulVal();
    
    /**
     * 查询满足生病条件的小鸡
     *
     * @param period
     * @param chickWaitSickMins
     * @return
     */
    /*List<ChickenDO> getToBeSickChickList(@Param("period") Byte period, @Param("chickWaitSickMins") int chickWaitSickMins, @Param("offset")int offset, @Param("pageSize") int pageSize);*/

    /**
     * 查询清洁度大于60的农场的小鸡信息
     * @param param
     * @return
     */
    /*List<ChickDOView> getChickRangelandClean(ChickQueryParam param);*/
    
    /**
     * 查询小鸡的饱腹值
     * @param param
     * @return
     */
    List<ChickDOView> getChickFullValPeriod(ChickQueryFullValParam param);

    void addDayGrowth(ChickGrowthParam param);

    /**
     * 查询清洁度小于于60的鸡舍中满足时间10分钟条件减愉悦值的小鸡列表
     *
     * @param param
     * @return
     */
    /*List<ChickDOView> getHenhouseCleanMinusJoyfullChickens(ChickenMinusJoyfullQueryParam param);*/

    void doDecreaseFullVal(ChickDealWithParam param);

    /**
     * 查询未在牧区满足15分钟减愉悦值的小鸡列表
     *
     * @param param
     * @return
     */
    List<ChickDOView> getDayHenhouseMinusJoyfullChickens(ChickenDayHenhouseQueryParam param);

	void updateFullValDate(String chickNum);

	/**
	 * 喂养小鸡更新小鸡信息
	 * @param param
	 */
	void feedChickUpdateChickInfo(ChickUpdateInfoParam param);

    /**
     * 小鸡位于清洁度>=60的农场每15分钟+1愉悦值
     *
     * @param param
     */
    void doAddJoyful(ChickDealWithParam param);

    /**
     * 小鸡位于清洁度<60的鸡舍每10分钟-1愉悦值
     *
     * @param param
     */
    void doMinusJoyful(ChickDealWithParam param);
    
    /**
     * 统计产蛋数量
     * @return
     * @author jiangxinjun
     * @createDate 2018年5月7日
     * @updateDate 2018年5月7日
     */
    CountEggProductionDO countEggProduction();
    
    /**
     * 小鸡进入生产状态
     */
    int enterProduction();
    
    /**
     * 鸡窝鸡蛋数量操作
     * 
     * @param param
     * @author jiangxinjun
     * @createDate 2018年4月27日
     * @updateDate 2018年4月27日
     */
    void eggOperator(ChickEggOperatorParam param);

    /**
     * 离开鸡窝更新小鸡在鸡窝累计时长
     * @param chickNum
     */
    void updateChickInHouse(String chickNum);

    /**
     * 查询在鸡窝并且累计时长>=120分钟的小鸡
     *
     * @param param
     * @return
     */
    List<ChickenInHouseDOView> getChickenInHouseReduceCleanessList(ChickenReduceCleanessParam param);

    void reduceCleaness(ReduceCleanParam param);

    List<ChickenInHouseDOView> getChickenInHouseList(CommonListPageParam param);

    void updateChickInHouseTime(Long id);
    
    /**
	 * 是否有小鸡在牧场
	 * 
	 * @param memberNum
	 * @return
	 */
	int chicksIsInExternal(String memberNum);

	List<ChickDOView> getChickInRangelandAndIsClean(ChickenDayHenhouseQueryParam param);

	List<ChickDOView> getChickInRangelandAndIsHunger(ChickenDayHenhouseQueryParam param);
}
