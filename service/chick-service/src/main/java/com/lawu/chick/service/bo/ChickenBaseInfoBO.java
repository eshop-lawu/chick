package com.lawu.chick.service.bo;

import java.math.BigDecimal;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

/**
 * 小鸡基础信息BO
 */
public class ChickenBaseInfoBO {

    private Long id;

    private String num;

    private String memberNum;

    private String name;

    private Byte period;

    private Byte status;

    //是否在鸡舍
    private Boolean isInHouse;

    private Boolean isPregnant;

    private Boolean isOutside;

    private Integer joyfulVal;

    private Integer growthVal;

    private Integer fullVal;

    private BigDecimal layEggs;

    private Date lifeStartTime;

    private Date gmtModified;

    private Date gmtCreate;

    private BigDecimal houseEggs;
	/**
	 * 是否赠送道具
	 */
	private boolean giveFlag; 
	
	/**
	 * 赠送道具名称
	 */
	private String giveFoodsName;
	
	/**
	 * 赠送道具数量
	 */
	private int giveFoodsCount;
	
	/**
	 * 清洁度
	 */
	private int cleannessVal;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getMemberNum() {
        return memberNum;
    }

    public void setMemberNum(String memberNum) {
        this.memberNum = memberNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Byte getPeriod() {
        return period;
    }

    public void setPeriod(Byte period) {
        this.period = period;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Boolean getOutside() {
        return isOutside;
    }

    public void setOutside(Boolean outside) {
        isOutside = outside;
    }

    public Integer getJoyfulVal() {
        return joyfulVal;
    }

    public void setJoyfulVal(Integer joyfulVal) {
        this.joyfulVal = joyfulVal;
    }

    public Integer getGrowthVal() {
        return growthVal;
    }

    public void setGrowthVal(Integer growthVal) {
        this.growthVal = growthVal;
    }

    public Integer getFullVal() {
        return fullVal;
    }

    public void setFullVal(Integer fullVal) {
        this.fullVal = fullVal;
    }

    public BigDecimal getLayEggs() {
        return layEggs;
    }

    public void setLayEggs(BigDecimal layEggs) {
        this.layEggs = layEggs;
    }

    public Date getLifeStartTime() {
        return lifeStartTime;
    }

    public void setLifeStartTime(Date lifeStartTime) {
        this.lifeStartTime = lifeStartTime;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Boolean getInHouse() {
        return isInHouse;
    }

    public void setInHouse(Boolean inHouse) {
        isInHouse = inHouse;
    }

    public Boolean getPregnant() {
        return isPregnant;
    }

    public void setPregnant(Boolean pregnant) {
        isPregnant = pregnant;
    }

	public boolean isGiveFlag() {
		return giveFlag;
	}

	public void setGiveFlag(boolean giveFlag) {
		this.giveFlag = giveFlag;
	}

	public String getGiveFoodsName() {
		return giveFoodsName;
	}

	public void setGiveFoodsName(String giveFoodsName) {
		this.giveFoodsName = giveFoodsName;
	}

	public int getGiveFoodsCount() {
		return giveFoodsCount;
	}

	public void setGiveFoodsCount(int giveFoodsCount) {
		this.giveFoodsCount = giveFoodsCount;
	}

	/**
	 * @return the cleannessVal
	 */
	public int getCleannessVal() {
		return cleannessVal;
	}

	public void setCleannessVal(int cleannessVal) {
		this.cleannessVal = cleannessVal;
	}

	public BigDecimal getHouseEggs() {
		return houseEggs;
	}

	public void setHouseEggs(BigDecimal houseEggs) {
		this.houseEggs = houseEggs;
	}
    
	
}
