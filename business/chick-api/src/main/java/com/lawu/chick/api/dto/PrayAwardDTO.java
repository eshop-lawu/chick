package com.lawu.chick.api.dto;

import java.util.List;

/**
 * @author zhangyong
 * @date 2018/6/26.
 */
public class PrayAwardDTO {
    private List<AwardDTO> awardDTOS;

    public List<AwardDTO> getAwardDTOS() {
        return awardDTOS;
    }

    public void setAwardDTOS(List<AwardDTO> awardDTOS) {
        this.awardDTOS = awardDTOS;
    }
}
