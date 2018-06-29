package com.lawu.chick.operator.api.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author meishuquan
 * @date 2018/6/1.
 */
public class HouseCleannessDTO {

    @ApiModelProperty(value = "清洁度")
    private Integer houseCleanness;

    @ApiModelProperty(value = "衰减愉悦值")
    private Integer attenuationJoyfulVal;

    @ApiModelProperty(value = "衰减次数")
    private Integer attenuationTimes;

    public Integer getHouseCleanness() {
        return houseCleanness;
    }

    public void setHouseCleanness(Integer houseCleanness) {
        this.houseCleanness = houseCleanness;
    }

    public Integer getAttenuationJoyfulVal() {
        return attenuationJoyfulVal;
    }

    public void setAttenuationJoyfulVal(Integer attenuationJoyfulVal) {
        this.attenuationJoyfulVal = attenuationJoyfulVal;
    }

    public Integer getAttenuationTimes() {
        return attenuationTimes;
    }

    public void setAttenuationTimes(Integer attenuationTimes) {
        this.attenuationTimes = attenuationTimes;
    }
}
