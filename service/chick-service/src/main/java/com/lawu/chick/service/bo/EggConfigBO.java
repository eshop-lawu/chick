package com.lawu.chick.service.bo;

import java.util.Date;

/**
 * @author meishuquan
 * @date 2018/4/25.
 */
public class EggConfigBO extends EggConfigBaseBO {

    private Date eggEffectiveTime;

    public Date getEggEffectiveTime() {
        return eggEffectiveTime;
    }

    public void setEggEffectiveTime(Date eggEffectiveTime) {
        this.eggEffectiveTime = eggEffectiveTime;
    }

}
