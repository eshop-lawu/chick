package com.lawu.chick.repository.domain;

import java.io.Serializable;
import java.util.Date;

public class SignInRuleDO implements Serializable {
    /**
     *
     * 主键
     * sign_in_rule.id
     *
     * @mbg.generated
     */
    private Long id;

    /**
     *
     * 日常获得商品编号
     * sign_in_rule.product_num
     *
     * @mbg.generated
     */
    private String productNum;

    /**
     *
     * 每日奖励数量
     * sign_in_rule.product_count
     *
     * @mbg.generated
     */
    private Integer productCount;

    /**
     *
     * 状态，0禁用，1启用
     * sign_in_rule.status
     *
     * @mbg.generated
     */
    private Byte status;

    /**
     *
     * json，额外奖励规则，签到满几天奖励
     * sign_in_rule.extra
     *
     * @mbg.generated
     */
    private String extra;

    /**
     *
     * 开始时间
     * sign_in_rule.gmt_start
     *
     * @mbg.generated
     */
    private Date gmtStart;

    /**
     *
     * 结束时间
     * sign_in_rule.gmt_end
     *
     * @mbg.generated
     */
    private Date gmtEnd;

    /**
     *
     * 更新时间
     * sign_in_rule.gmt_modified
     *
     * @mbg.generated
     */
    private Date gmtModified;

    /**
     *
     * 创建时间
     * sign_in_rule.gmt_create
     *
     * @mbg.generated
     */
    private Date gmtCreate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table sign_in_rule
     *
     * @mbg.generated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sign_in_rule.id
     *
     * @return the value of sign_in_rule.id
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sign_in_rule.id
     *
     * @param id the value for sign_in_rule.id
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sign_in_rule.product_num
     *
     * @return the value of sign_in_rule.product_num
     *
     * @mbg.generated
     */
    public String getProductNum() {
        return productNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sign_in_rule.product_num
     *
     * @param productNum the value for sign_in_rule.product_num
     *
     * @mbg.generated
     */
    public void setProductNum(String productNum) {
        this.productNum = productNum == null ? null : productNum.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sign_in_rule.product_count
     *
     * @return the value of sign_in_rule.product_count
     *
     * @mbg.generated
     */
    public Integer getProductCount() {
        return productCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sign_in_rule.product_count
     *
     * @param productCount the value for sign_in_rule.product_count
     *
     * @mbg.generated
     */
    public void setProductCount(Integer productCount) {
        this.productCount = productCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sign_in_rule.status
     *
     * @return the value of sign_in_rule.status
     *
     * @mbg.generated
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sign_in_rule.status
     *
     * @param status the value for sign_in_rule.status
     *
     * @mbg.generated
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sign_in_rule.extra
     *
     * @return the value of sign_in_rule.extra
     *
     * @mbg.generated
     */
    public String getExtra() {
        return extra;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sign_in_rule.extra
     *
     * @param extra the value for sign_in_rule.extra
     *
     * @mbg.generated
     */
    public void setExtra(String extra) {
        this.extra = extra == null ? null : extra.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sign_in_rule.gmt_start
     *
     * @return the value of sign_in_rule.gmt_start
     *
     * @mbg.generated
     */
    public Date getGmtStart() {
        return gmtStart;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sign_in_rule.gmt_start
     *
     * @param gmtStart the value for sign_in_rule.gmt_start
     *
     * @mbg.generated
     */
    public void setGmtStart(Date gmtStart) {
        this.gmtStart = gmtStart;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sign_in_rule.gmt_end
     *
     * @return the value of sign_in_rule.gmt_end
     *
     * @mbg.generated
     */
    public Date getGmtEnd() {
        return gmtEnd;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sign_in_rule.gmt_end
     *
     * @param gmtEnd the value for sign_in_rule.gmt_end
     *
     * @mbg.generated
     */
    public void setGmtEnd(Date gmtEnd) {
        this.gmtEnd = gmtEnd;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sign_in_rule.gmt_modified
     *
     * @return the value of sign_in_rule.gmt_modified
     *
     * @mbg.generated
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sign_in_rule.gmt_modified
     *
     * @param gmtModified the value for sign_in_rule.gmt_modified
     *
     * @mbg.generated
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sign_in_rule.gmt_create
     *
     * @return the value of sign_in_rule.gmt_create
     *
     * @mbg.generated
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sign_in_rule.gmt_create
     *
     * @param gmtCreate the value for sign_in_rule.gmt_create
     *
     * @mbg.generated
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }
}