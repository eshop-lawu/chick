package com.lawu.chick.service.bo;

import java.util.Date;
import java.util.List;

/**
 * @author meishuquan
 * @date 2018/6/15.
 */
public class TaskRewardsConfigBO {

    private Long id;

    private List<RewardsConfigBO> rewardsConfigBOS;

    private Date effectiveTime;

    private Byte type;

    private Byte status;

    private Date gmtModified;

    private Date gmtCreate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<RewardsConfigBO> getRewardsConfigBOS() {
        return rewardsConfigBOS;
    }

    public void setRewardsConfigBOS(List<RewardsConfigBO> rewardsConfigBOS) {
        this.rewardsConfigBOS = rewardsConfigBOS;
    }

    public Date getEffectiveTime() {
        return effectiveTime;
    }

    public void setEffectiveTime(Date effectiveTime) {
        this.effectiveTime = effectiveTime;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
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
}
