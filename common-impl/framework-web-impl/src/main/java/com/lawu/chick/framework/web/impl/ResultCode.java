package com.lawu.chick.framework.web.impl;

import com.lawu.framework.web.BaseResultCode;

/**
 * 返回码
 *
 * @author Leach
 * @date 2017/3/13
 */
public class ResultCode extends BaseResultCode {
    // 公共代码11xx
    public static final int MEMBER_WRONG_PWD = 1103;
    public static final int RESOURCE_NOT_FOUND = 1104;
    public static final int WRONG_OPERATION = 1105;
    public static final int REQUIRED_PARM_EMPTY = 1106;
    public static final int FAIL = 1107;
    public static final int ILLEGAL_OPERATION = 1108;
    
	// FastDFS上传图片异常
    public static final int FD_FILE_ERROR =1201;
    public static final int FD_FILE_IMG_BIG =1202;
    public static final int FD_FILE_CUT_ERROR=1203;
    public static final int FD_FILE_VIDEO_CODE_ERROR = 1204;
    public static final int UPLOAD_SIZE_BIGER = 1207;
	
    // chick-api2xxx
    public static final int WEI_XIN_UNAUTHORIZED = 2000;
    public static final int RESTOCKING_IS_EMPTY = 2001;
    public static final int TREAT_IS_EMPTY = 2002;
    public static final int TREAT_IS_EXIST_ONE = 2003;
    
    public static final int IS_FRIEND = 2004;
    public static final int ADOPT_CHICK_NUMBER_OVER = 2005;
    public static final int EGG_INVENTORY_SHORTAGE = 2006;

    public static final int WX_LOGIN_FAIL = 2007;
    public static final int WX_NOT_AUTH = 2008;

    public static final int HAS_SIGN_RECORD = 2009;
    public static final int SIGN_RECORD_ERROR = 2010;
    public static final int GET_SIGN_ERROR = 2011;
    
    public static final int CHICK_FULLVAL_MAX=2012;
    public static final int CHICK_OWNER_FEED_MAX_TIMES=2013;
	public static final int CHICK_GROWTH_VAL_MAX_TIMES=2014;
	
	public static final int FEED_INVENTORY_NOT_ENOUGH=2015;

    public static final int EGG_EXCHANGE_RULE_NOT_SET = 2016;
    public static final int REDPACKET_AMOUNT_GET_FAIL = 2017;
    public static final int CHICK_FEED_MAX_TIMES = 2018;
    public static final int CHICK_IS_FULL=2019;
    public static final int CHICK_IS_SICK=2020;

    public static final int GIFT_INVENTORY_SHORTAGE = 2021;
    public static final int CHICK_FEED_PROD_TIMES = 2022;
    
    // operator-api3xxx
    public static final int USER_ACCOUNT_DISABLE = 3000;
    public static final int USER_UNAUTHORIZED = 3001;
    public static final int USER_NOT_LOGIN = 3002;
    public static final int USER_ACCOUNT_EXIST = 3003;


    // 初始化状态码与文字说明
    static {

        // 公共代码
        messageMap.put(MEMBER_WRONG_PWD, "用户名或密码错误");
        messageMap.put(RESOURCE_NOT_FOUND, "数据不存在");
    	messageMap.put(WRONG_OPERATION, "异常操作");
        messageMap.put(REQUIRED_PARM_EMPTY, "非法参数");
        messageMap.put(FAIL, "操作失败");
        messageMap.put(ILLEGAL_OPERATION, "非法操作");
        
        // FastDFS上传图片异常
        messageMap.put(FD_FILE_ERROR, "获取上传文件信息异常");
        messageMap.put(FD_FILE_IMG_BIG, "上传图片应小于5M");
        messageMap.put(FD_FILE_CUT_ERROR, "获取视频帧转图片异常");
        messageMap.put(FD_FILE_VIDEO_CODE_ERROR, "视频编码不支持，您可转换为H264编码重新上传");
        messageMap.put(UPLOAD_SIZE_BIGER, "上传文件应小于50M");
        
        // chick-api2xxx
    	messageMap.put(WEI_XIN_UNAUTHORIZED, "当前微信没有授权，请先授权");

        messageMap.put(RESTOCKING_IS_EMPTY, "无可放养的小鸡");
        messageMap.put(TREAT_IS_EMPTY, "无可治疗的小鸡");
        messageMap.put(TREAT_IS_EXIST_ONE, "只允许存在一只治疗中的小鸡");

        messageMap.put(IS_FRIEND, "两人已经是好友");
        messageMap.put(EGG_INVENTORY_SHORTAGE, "鸡蛋库存不足");

        messageMap.put(WX_LOGIN_FAIL, "微信登录失败");
        messageMap.put(WX_NOT_AUTH, "用户未经微信授权");
        messageMap.put(HAS_SIGN_RECORD, "已经签到了");
        messageMap.put(SIGN_RECORD_ERROR, "签到异常");
        messageMap.put(GET_SIGN_ERROR, "还没满足领取条件哦~");
        
        messageMap.put(CHICK_FULLVAL_MAX, "小鸡饱腹值达到上限无法喂养");
        messageMap.put(CHICK_OWNER_FEED_MAX_TIMES, "您喂养次数今日已达上限");

        messageMap.put(CHICK_FEED_MAX_TIMES, "喂养次数今日已达上限");
        messageMap.put(CHICK_IS_FULL, "小鸡已经吃饱了~");
        messageMap.put(CHICK_IS_SICK, "小鸡生病了，不能喂食哦~");
        messageMap.put(CHICK_GROWTH_VAL_MAX_TIMES, "小鸡成长度今日已达上限");
        messageMap.put(FEED_INVENTORY_NOT_ENOUGH, "您的饲料不够");
        messageMap.put(EGG_EXCHANGE_RULE_NOT_SET, "鸡蛋兑换规则未设置");
        messageMap.put(REDPACKET_AMOUNT_GET_FAIL, "红包金额获取失败");

        messageMap.put(GIFT_INVENTORY_SHORTAGE, "礼品已兑完");
        messageMap.put(CHICK_FEED_PROD_TIMES, "该道具已经达到今日上线");


        // operator-api3xxx
        messageMap.put(USER_ACCOUNT_DISABLE, "账号已停用，请联系管理员");
        messageMap.put(USER_UNAUTHORIZED, "未授权");
        messageMap.put(USER_NOT_LOGIN, "用户未登录");
        messageMap.put(USER_ACCOUNT_EXIST, "账号已存在");


    }
}
