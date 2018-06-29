package com.lawu.chick.operator.api.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.lawu.chick.service.enums.ChickStatusEnum;
import com.lawu.chick.service.enums.PeriodTypeEnum;

/**
 * @Description
 * @author zhangrc
 * @date 2018年4月28日
 */
public class ChickenManagePageDTO {

	/**
	 *
	 * 主键 chicken.id
	 *
	 * @mbg.generated
	 */
	private Long id;

	/**
	 *
	 * 小鸡编号 chicken.num
	 *
	 * @mbg.generated
	 */
	private String num;

	/**
	 *
	 * 所属用户编号 chicken.member_num
	 *
	 * @mbg.generated
	 */
	private String memberName;

	/**
	 *
	 * 名称 chicken.name
	 *
	 * @mbg.generated
	 */
	private String name;

	/**
	 *
	 * 所处时期，0成长期，1半成熟期，2成熟期，3生病，4治疗中，5死亡 chicken.period
	 *
	 * @mbg.generated
	 */
	private PeriodTypeEnum periodType;

	/**
	 *
	 * 状态，0活动，1睡眠，2休眠，3生产 chicken.status
	 *
	 * @mbg.generated
	 */
	private ChickStatusEnum statusEnum;

	/**
	 *
	 * 是否放养，0否，1是 chicken.is_outside
	 *
	 * @mbg.generated
	 */
	private Boolean isOutside;

	/**
	 *
	 * 愉悦值 chicken.joyful_val
	 *
	 * @mbg.generated
	 */
	private Integer joyfulVal;

	/**
	 *
	 * 饱腹值 chicken.full_val
	 *
	 * @mbg.generated
	 */
	private Integer fullVal;

	/**
	 *
	 * 成长值 chicken.growth_val
	 *
	 * @mbg.generated
	 */
	private Integer growthVal;

	/**
	 *
	 * 最新分配产蛋数 chicken.lay_eggs
	 *
	 * @mbg.generated
	 */
	private BigDecimal layEggs;

	/**
	 *
	 * 生命周期计时起点 chicken.life_start_time
	 *
	 * @mbg.generated
	 */
	private Date lifeStartTime;

	/**
	 *
	 * 创建时间 chicken.gmt_create
	 *
	 * @mbg.generated
	 */
	private Date gmtCreate;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the num
	 */
	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	/**
	 * @return the memberName
	 */
	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the periodType
	 */
	public PeriodTypeEnum getPeriodType() {
		return periodType;
	}

	public void setPeriodType(PeriodTypeEnum periodType) {
		this.periodType = periodType;
	}

	/**
	 * @return the statusEnum
	 */
	public ChickStatusEnum getStatusEnum() {
		return statusEnum;
	}

	public void setStatusEnum(ChickStatusEnum statusEnum) {
		this.statusEnum = statusEnum;
	}

	/**
	 * @return the isOutside
	 */
	public Boolean getIsOutside() {
		return isOutside;
	}

	public void setIsOutside(Boolean isOutside) {
		this.isOutside = isOutside;
	}

	/**
	 * @return the joyfulVal
	 */
	public Integer getJoyfulVal() {
		return joyfulVal;
	}

	public void setJoyfulVal(Integer joyfulVal) {
		this.joyfulVal = joyfulVal;
	}

	/**
	 * @return the fullVal
	 */
	public Integer getFullVal() {
		return fullVal;
	}

	public void setFullVal(Integer fullVal) {
		this.fullVal = fullVal;
	}

	/**
	 * @return the growthVal
	 */
	public Integer getGrowthVal() {
		return growthVal;
	}

	public void setGrowthVal(Integer growthVal) {
		this.growthVal = growthVal;
	}

	/**
	 * @return the layEggs
	 */
	public BigDecimal getLayEggs() {
		return layEggs;
	}

	public void setLayEggs(BigDecimal layEggs) {
		this.layEggs = layEggs;
	}

	/**
	 * @return the lifeStartTime
	 */
	public Date getLifeStartTime() {
		return lifeStartTime;
	}

	public void setLifeStartTime(Date lifeStartTime) {
		this.lifeStartTime = lifeStartTime;
	}

	/**
	 * @return the gmtCreate
	 */
	public Date getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

}
