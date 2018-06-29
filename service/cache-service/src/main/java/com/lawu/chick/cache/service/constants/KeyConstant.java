package com.lawu.chick.cache.service.constants;

/**
 * 缓存key常量
 * @author jiangxinjun
 * @createDate 2018年4月24日
 * @updateDate 2018年4月24日
 */
public class KeyConstant {

    /**
     * 账号-token
     */
    public static final String REDIS_ACCOUNT_PREFIX = "AUTH_ACCOUNT_";

    /**
     * token-账号
     */
    public static final String REDIS_TOKEN_PREFIX = "AUTH_TOKEN_";

    /**
     * token-删除原因
     */
    public static final String REDIS_TOKEN_CLEAR_PREFIX = "AUTH_TOKEN_DEL_";

    /**
     * 鸡蛋配置
     */
    public static final String REDIS_KEY_EGG_CONFIG = "EGG_CONFIG";
    
    /**
     * 小鸡配置
     */
    public static final String REDIS_KEY_CHICK_BASE_CONFIG = "CHICK_BASE_CONFIG";
    
    /**
     * 牧场配置
     */
    public static final String REDIS_KEY_RANGELAND_CONFIG = "RANGELAND_CONFIG";

    /**
     * 清洁度大于60的牧场 & 满足时间15分钟的小鸡信息，加入缓存
     */
    public static final String REDIS_KEY_RANGELAND_CLEAN_JOYFULL = "RANGELAND_CLEAN_JOYFULL";

    /**
     * 小鸡饱腹值递减缓存
     */
    public static final String REDIS_KEY_CHICKEN_FULLVAL ="REDIS_KEY_CHICKEN_FULLVAL";

    /**
     * 清洁度小于60的鸡舍 & 满足时间10分钟的小鸡信息，加入缓存
     */
    public static final String REDIS_KEY_HENHOUSE_CLEAN_JOYFULL = "HENHOUSE_CLEAN_JOYFULL";

    /**
     * 将白天未处牧区每15分钟-2愉悦值的小鸡信息，加入缓存
     */
    public static final String REDIS_KEY_DAY_HENHOUSE_JOYFULL = "DAY_HENHOUSE_JOYFULL";

    /**
     * 牧场收益
     */
    public static final String REDIS_KEY_DAY_RANGELAND_PROFIT = "DAY_RANGELAND_PROFIT";
    /**
     * 打扫奖励
     */
    public static final String REDIS_KEY_SWEEP_AWARD_CONFIG = "SWEEP_AWARD_CONFIG_";

    /**
     * 周期缓存控制
     */
    public static final String REDIS_KEY_PERIOD_CTRL = "PERIOD_CTRL_";
    
    /**
     * 微信SessionKey
     */
    public static final String REDIS_KEY_WX_SESSION_KEY = "WX_SESSION_KEY_";

    /**
     * 衰减愉悦值次数
     */
    public static final String REDIS_KEY_ATTENUATION_JOYFUL_VAL_TIMES = "ATTENUATION_JOYFUL_VAL_TIMES_";

    /**
     * 衰减愉悦值毫秒数
     */
    public static final String REDIS_KEY_ATTENUATION_JOYFUL_VAL_MILLIISECONDS = "ATTENUATION_JOYFUL_VAL_MILLIISECONDS";

    /**
     * 连续签到次数
     */
    public static final String REDIS_KEY_PRAY_SIGN = "REDIS_KEY_PRAY_SIGN_";

    /**
     * 任务奖励配置
     */
    public static final String REDIS_KEY_TASK_REWARDS_CONFIG = "TASK_REWARDS_CONFIG_";

    /**
     * 祈福签到配置
     */
    public static final String REDIS_KEY_PRAY_SIGN_RULE = "REDIS_KEY_PRAY_SIGN_RULE";

}
