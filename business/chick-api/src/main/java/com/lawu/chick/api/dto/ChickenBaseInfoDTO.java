package com.lawu.chick.api.dto;

import java.math.BigDecimal;

import com.lawu.chick.api.enums.ChickStatusGeneralEnum;
import com.lawu.chick.service.enums.ChickStatusEnum;
import com.lawu.chick.service.enums.PeriodTypeEnum;
import io.swagger.annotations.ApiModelProperty;

public class ChickenBaseInfoDTO {

    @ApiModelProperty(value = "小鸡id")
    private Long id;

    @ApiModelProperty(value = "小鸡编号")
    private String num;

    @ApiModelProperty(value = "所属用户编号")
    private String memberNum;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "是否在鸡舍")
    private Boolean isInHouse;

    @ApiModelProperty(value = "所处阶段")
    private PeriodTypeEnum period;

    @ApiModelProperty(value = "状态0活动，1睡眠，2休眠，3生产")
    private ChickStatusGeneralEnum status;

    @ApiModelProperty(value = "是否放养，0否，1是")
    private Boolean isOutside;

    @ApiModelProperty(value = "愉悦值")
    private Integer joyfulVal;

    @ApiModelProperty(value = "成长值")
    private Integer growthVal;

    @ApiModelProperty(value = "饱腹值")
    private Integer fullVal;
    
    @ApiModelProperty(value = "清洁度")
	private int cleannessVal;
    
    @ApiModelProperty(value = "蛋窝中的鸡蛋数量")
    private BigDecimal houseEggs;
    
    @ApiModelProperty(value = "产蛋倒计时（分钟）")
    private int eggLayCutdown;
    
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

    public PeriodTypeEnum getPeriod() {
        return period;
    }

    public void setPeriod(PeriodTypeEnum period) {
        this.period = period;
    }

    public ChickStatusGeneralEnum getStatus() {
        return status;
    }

    public void setStatus(ChickStatusGeneralEnum status) {
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

    public Boolean getInHouse() {
        return isInHouse;
    }

    public void setInHouse(Boolean inHouse) {
        isInHouse = inHouse;
    }

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

	public int getEggLayCutdown() {
		return eggLayCutdown;
	}

	public void setEggLayCutdown(int eggLayCutdown) {
		this.eggLayCutdown = eggLayCutdown;
	}
    
}
