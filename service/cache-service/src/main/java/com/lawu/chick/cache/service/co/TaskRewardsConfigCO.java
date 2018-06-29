package com.lawu.chick.cache.service.co;

import java.io.Serializable;
import java.util.List;

/**
 * @author meishuquan
 * @date 2018/6/15.
 */
public class TaskRewardsConfigCO implements Serializable {

    private static final long serialVersionUID = 6644479333950559180L;

    private Byte status;

    private List<RewardsConfigCO> rewardsConfigCOS;

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public List<RewardsConfigCO> getRewardsConfigCOS() {
        return rewardsConfigCOS;
    }

    public void setRewardsConfigCOS(List<RewardsConfigCO> rewardsConfigCOS) {
        this.rewardsConfigCOS = rewardsConfigCOS;
    }
}
