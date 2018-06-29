package com.lawu.chick.cache.service.co;

import java.io.Serializable;

/**
 * @author zhangyong
 * @date 2018/6/26.
 */
public class PraySignRuleCO implements Serializable {
    private static final long serialVersionUID = -280233520096944246L;


    private Long id;

    private String productNum;

    /**
     *
     * 每日奖励数量
     * pray_sign_rule.product_count
     *
     */
    private Integer productCount;

    /**
     *
     * 是否根据小鸡数量
     * pray_sign_rule.is_basis_chick
     *
     */
    private Boolean isBasisChick;

    /**
     *
     * 连续签到天数
     * pray_sign_rule.day
     *
     */
    private Integer day;

    /**
     *
     * json，额外奖励规则
     * pray_sign_rule.extra
     *
     */
    private String extra;

    /**
     *
     * 状态，0禁用，1启用
     * pray_sign_rule.status
     *
     */
    private Byte status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductNum() {
        return productNum;
    }

    public void setProductNum(String productNum) {
        this.productNum = productNum;
    }

    public Integer getProductCount() {
        return productCount;
    }

    public void setProductCount(Integer productCount) {
        this.productCount = productCount;
    }

    public Boolean getBasisChick() {
        return isBasisChick;
    }

    public void setBasisChick(Boolean basisChick) {
        isBasisChick = basisChick;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }
}
