package com.lawu.chick.operator.api.dto;

import java.util.List;

/**
 * @author lihj
 * @date 2018年4月25日
 */
public class ChickBaseConfigDTO {
	/**
	 * 小鸡默认名称
	 */
	private String chickDefauleName;
	/**
	 * 小鸡领养个数
	 */
	private int chickAdoptionCount;
	
	/**
	 * 小鸡最大领养个数
	 */
	private int chickMaxAdoptionCount;
	
	/**
	 * 小鸡初始成长值（默认1）
	 */
	private int chickInitGrowthVal;
	/**
	 * 小鸡每天自动成长多少(5)
	 */
	private int chickDayGrowthVal;
	/**
	 * 小鸡最大成长值（默认100）
	 */
	private int chickMaxGrowthVal;

	/**
	 * 小鸡半成熟值（默认50）
	 */
	private int chickSemiMatureVal;
	/**
	 * 小鸡成长值每日上线（默认15）
	 */
	/*private int chickDayMaxLimit;*/
	/**
	 * 主人喂食加成长度（默认+2）
	 */
	private int ownerFeedingGrowthVal;
	/**
	 * 主人喂食每天最大次数(默认2次)
	 */
	private int ownerFeedingMaxDayTimes;

	/**
	 * 好友喂养加成长度（默认+1）
	 */
	/*private int friendFeedingGrowthVal;*/

	/**
	 * 好友喂养每天最大次数（默认7）
	 */
	/*private int friendFeedingMaxDayTimes;*/

	/**
	 * 喂食特殊饲料（默认4）
	 */
//	private int specialFeedGrowthVal;

	/**
	 * 喂食特殊饲料每天最多次数（默认1）
	 */
//	private int specialFeedMaxDayTimes;
	/**
	 * 白天小鸡在鸡舍中不放养时多少分钟下降愉悦值
	 */
	private int chickInhouseReduFullMinute;
	/**
	 * 白天小鸡在鸡舍中不放养时多少分钟下降多少愉悦值
	 */
	private int chickInhouseReduFullyVal; 

	/**
	 * 小鸡默认饱腹值（100）
	 */
	private int chickInitFullVal;
	/**
	 * 小鸡最大饱腹值(100)
	 */
	private int chickMaxFullVal;

	private int chickHungerVal;
	/**
	 * 小鸡休眠饱腹值（10）
	 */
	private int chickDormancyMinFullVal;
	/**
	 * 小鸡每多少分钟下降一次饱腹值
	 */
	private int chickDeclineFullValMinute;
	/**
	 * 小鸡每次下降多少饱腹值
	 */
	private int chickDeclineFullVal;

	/**
	 * 小鸡初始愉悦值(100)
	 */
	private int chickInitJoyfulVal;
	/**
	 * 小鸡愉悦度多少分钟下降一次(单位分钟)
	 */
	/*private int chickDeclineJoyfulValMinute;*/
	/**
	 * 小鸡鸡舍清洁度小于多少时减愉悦值
	 */
	/*private int chickDeclineMinusJoyfulVal;*/
	/**
	 * 小鸡每次愉悦值下降多少（默认2）
	 */
	private int chickDeclineJoyfulVal;

	/**
	 * 小鸡在牧场每多少分钟增加一次愉悦值(分钟)
	 */
	private int chickInRangelandAddJoyfulValMinute;

	/**
	 * 小鸡在牧场每次增加多少愉悦值(默认1)
	 */
	private int chickInRangelandAddJoyfulVal;
	/**
	 * 小鸡开始活动时间
	 */
	private String chickStartActivitiesTime;

	/**
	 * 小鸡结束活动时间
	 */
	private String chickEndActivitiesTime;

	/**
	 * 产蛋时间
	 */
	private List<String> chickEggProdTime;
	/**
	 * 配置开始时间
	 */
	private String settingStartTime;

	/**
	 * 提前多久进入产房（单位分钟）
	 */
	private int chickLayingEggsTime;
	/**
	 * 小鸡多久开始生病
	 */
	private int chickWaitSick;
	
	/**
	 * 位于牧场清洁度多少开始加愉悦值
	 */
	private int chickRangelandCleanVal;
	
	//完成小鸡治愈任务，需邀请几个好友
	private int finishTaskInviteCount;
	//完成小鸡治愈任务，需成功消费几次
	private int finishTaskSalesCount;
	//完成小鸡治愈任务，需帮助几个好友喂食和打扫
	private int finishTaskHelpCount;
	//完成小鸡治愈任务，需兑换几次鸡蛋
	private int finishTaskTradeCount;
	//至少完成几个任务，小鸡成功治疗
	private int finishTaskLessTCount;
	
	//帮助几个好友喂养
	private int helpFriendFeedCount;
	//帮助好友获得几包草料
	private int getHelpFriendFeedForage;
	//最大获取多少包
	private int getMaxHelpFriendFeedForage;
	
	
	public String getChickDefauleName() {
		return chickDefauleName;
	}

	public void setChickDefauleName(String chickDefauleName) {
		this.chickDefauleName = chickDefauleName;
	}

	public int getChickAdoptionCount() {
		return chickAdoptionCount;
	}

	public void setChickAdoptionCount(int chickAdoptionCount) {
		this.chickAdoptionCount = chickAdoptionCount;
	}

	public int getChickInitGrowthVal() {
		return chickInitGrowthVal;
	}

	public void setChickInitGrowthVal(int chickInitGrowthVal) {
		this.chickInitGrowthVal = chickInitGrowthVal;
	}

	public int getChickMaxGrowthVal() {
		return chickMaxGrowthVal;
	}

	public void setChickMaxGrowthVal(int chickMaxGrowthVal) {
		this.chickMaxGrowthVal = chickMaxGrowthVal;
	}

	public int getChickSemiMatureVal() {
		return chickSemiMatureVal;
	}

	public void setChickSemiMatureVal(int chickSemiMatureVal) {
		this.chickSemiMatureVal = chickSemiMatureVal;
	}

	public int getOwnerFeedingGrowthVal() {
		return ownerFeedingGrowthVal;
	}

	public void setOwnerFeedingGrowthVal(int ownerFeedingGrowthVal) {
		this.ownerFeedingGrowthVal = ownerFeedingGrowthVal;
	}

	public int getOwnerFeedingMaxDayTimes() {
		return ownerFeedingMaxDayTimes;
	}

	public void setOwnerFeedingMaxDayTimes(int ownerFeedingMaxDayTimes) {
		this.ownerFeedingMaxDayTimes = ownerFeedingMaxDayTimes;
	}

	public int getChickInhouseReduFullMinute() {
		return chickInhouseReduFullMinute;
	}

	public void setChickInhouseReduFullMinute(int chickInhouseReduFullMinute) {
		this.chickInhouseReduFullMinute = chickInhouseReduFullMinute;
	}

	public int getChickInhouseReduFullyVal() {
		return chickInhouseReduFullyVal;
	}

	public void setChickInhouseReduFullyVal(int chickInhouseReduFullyVal) {
		this.chickInhouseReduFullyVal = chickInhouseReduFullyVal;
	}

	public int getChickInitFullVal() {
		return chickInitFullVal;
	}

	public void setChickInitFullVal(int chickInitFullVal) {
		this.chickInitFullVal = chickInitFullVal;
	}

	public int getChickMaxFullVal() {
		return chickMaxFullVal;
	}

	public void setChickMaxFullVal(int chickMaxFullVal) {
		this.chickMaxFullVal = chickMaxFullVal;
	}

	public int getChickDormancyMinFullVal() {
		return chickDormancyMinFullVal;
	}

	public void setChickDormancyMinFullVal(int chickDormancyMinFullVal) {
		this.chickDormancyMinFullVal = chickDormancyMinFullVal;
	}

	public int getChickDeclineFullValMinute() {
		return chickDeclineFullValMinute;
	}

	public void setChickDeclineFullValMinute(int chickDeclineFullValMinute) {
		this.chickDeclineFullValMinute = chickDeclineFullValMinute;
	}

	public int getChickDeclineFullVal() {
		return chickDeclineFullVal;
	}

	public void setChickDeclineFullVal(int chickDeclineFullVal) {
		this.chickDeclineFullVal = chickDeclineFullVal;
	}

	public int getChickInitJoyfulVal() {
		return chickInitJoyfulVal;
	}

	public void setChickInitJoyfulVal(int chickInitJoyfulVal) {
		this.chickInitJoyfulVal = chickInitJoyfulVal;
	}

	public int getChickDeclineJoyfulVal() {
		return chickDeclineJoyfulVal;
	}

	public void setChickDeclineJoyfulVal(int chickDeclineJoyfulVal) {
		this.chickDeclineJoyfulVal = chickDeclineJoyfulVal;
	}

	public int getChickInRangelandAddJoyfulValMinute() {
		return chickInRangelandAddJoyfulValMinute;
	}

	public void setChickInRangelandAddJoyfulValMinute(int chickInRangelandAddJoyfulValMinute) {
		this.chickInRangelandAddJoyfulValMinute = chickInRangelandAddJoyfulValMinute;
	}

	public int getChickInRangelandAddJoyfulVal() {
		return chickInRangelandAddJoyfulVal;
	}

	public void setChickInRangelandAddJoyfulVal(int chickInRangelandAddJoyfulVal) {
		this.chickInRangelandAddJoyfulVal = chickInRangelandAddJoyfulVal;
	}

	public String getChickStartActivitiesTime() {
		return chickStartActivitiesTime;
	}

	public void setChickStartActivitiesTime(String chickStartActivitiesTime) {
		this.chickStartActivitiesTime = chickStartActivitiesTime;
	}

	public String getChickEndActivitiesTime() {
		return chickEndActivitiesTime;
	}

	public void setChickEndActivitiesTime(String chickEndActivitiesTime) {
		this.chickEndActivitiesTime = chickEndActivitiesTime;
	}

	public List<String> getChickEggProdTime() {
		return chickEggProdTime;
	}

	public void setChickEggProdTime(List<String> chickEggProdTime) {
		this.chickEggProdTime = chickEggProdTime;
	}

	public String getSettingStartTime() {
		return settingStartTime;
	}

	public void setSettingStartTime(String settingStartTime) {
		this.settingStartTime = settingStartTime;
	}

	public int getChickLayingEggsTime() {
		return chickLayingEggsTime;
	}

	public void setChickLayingEggsTime(int chickLayingEggsTime) {
		this.chickLayingEggsTime = chickLayingEggsTime;
	}

	public int getChickWaitSick() {
		return chickWaitSick;
	}

	public void setChickWaitSick(int chickWaitSick) {
		this.chickWaitSick = chickWaitSick;
	}

	public int getChickDayGrowthVal() {
		return chickDayGrowthVal;
	}

	public void setChickDayGrowthVal(int chickDayGrowthVal) {
		this.chickDayGrowthVal = chickDayGrowthVal;
	}

	public int getFinishTaskInviteCount() {
		return finishTaskInviteCount;
	}

	public void setFinishTaskInviteCount(int finishTaskInviteCount) {
		this.finishTaskInviteCount = finishTaskInviteCount;
	}

	public int getFinishTaskSalesCount() {
		return finishTaskSalesCount;
	}

	public void setFinishTaskSalesCount(int finishTaskSalesCount) {
		this.finishTaskSalesCount = finishTaskSalesCount;
	}

	public int getFinishTaskHelpCount() {
		return finishTaskHelpCount;
	}

	public void setFinishTaskHelpCount(int finishTaskHelpCount) {
		this.finishTaskHelpCount = finishTaskHelpCount;
	}

	public int getFinishTaskTradeCount() {
		return finishTaskTradeCount;
	}

	public void setFinishTaskTradeCount(int finishTaskTradeCount) {
		this.finishTaskTradeCount = finishTaskTradeCount;
	}

	public int getFinishTaskLessTCount() {
		return finishTaskLessTCount;
	}

	public void setFinishTaskLessTCount(int finishTaskLessTCount) {
		this.finishTaskLessTCount = finishTaskLessTCount;
	}

	public int getHelpFriendFeedCount() {
		return helpFriendFeedCount;
	}

	public void setHelpFriendFeedCount(int helpFriendFeedCount) {
		this.helpFriendFeedCount = helpFriendFeedCount;
	}

	public int getGetHelpFriendFeedForage() {
		return getHelpFriendFeedForage;
	}

	public void setGetHelpFriendFeedForage(int getHelpFriendFeedForage) {
		this.getHelpFriendFeedForage = getHelpFriendFeedForage;
	}

	public int getGetMaxHelpFriendFeedForage() {
		return getMaxHelpFriendFeedForage;
	}

	public void setGetMaxHelpFriendFeedForage(int getMaxHelpFriendFeedForage) {
		this.getMaxHelpFriendFeedForage = getMaxHelpFriendFeedForage;
	}

	public int getChickRangelandCleanVal() {
		return chickRangelandCleanVal;
	}

	public void setChickRangelandCleanVal(int chickRangelandCleanVal) {
		this.chickRangelandCleanVal = chickRangelandCleanVal;
	}

	/**
	 * @return the chickMaxAdoptionCount
	 */
	public int getChickMaxAdoptionCount() {
		return chickMaxAdoptionCount;
	}

	public void setChickMaxAdoptionCount(int chickMaxAdoptionCount) {
		this.chickMaxAdoptionCount = chickMaxAdoptionCount;
	}

	public int getChickHungerVal() {
		return chickHungerVal;
	}

	public void setChickHungerVal(int chickHungerVal) {
		this.chickHungerVal = chickHungerVal;
	}
	
	
}
