package com.lawu.chick.repository.domain;

import java.io.Serializable;
import java.util.Date;

public class SignInRecordDO implements Serializable {
    /**
     *
     * 主键
     * sign_in_record.id
     *
     * @mbg.generated
     */
    private Long id;

    /**
     *
     * 关联规则id
     * sign_in_record.rule_id
     *
     * @mbg.generated
     */
    private Long ruleId;

    /**
     *
     * 日常奖励商品编号
     * sign_in_record.product_num
     *
     * @mbg.generated
     */
    private String productNum;

    /**
     *
     * 额外奖励商品编号
     * sign_in_record.extra_product_num
     *
     * @mbg.generated
     */
    private String extraProductNum;

    /**
     *
     * 用户编号
     * sign_in_record.member_num
     *
     * @mbg.generated
     */
    private String memberNum;

    /**
     *
     * 签到时间
     * sign_in_record.gmt_create
     *
     * @mbg.generated
     */
    private Date gmtCreate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table sign_in_record
     *
     * @mbg.generated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sign_in_record.id
     *
     * @return the value of sign_in_record.id
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sign_in_record.id
     *
     * @param id the value for sign_in_record.id
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sign_in_record.rule_id
     *
     * @return the value of sign_in_record.rule_id
     *
     * @mbg.generated
     */
    public Long getRuleId() {
        return ruleId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sign_in_record.rule_id
     *
     * @param ruleId the value for sign_in_record.rule_id
     *
     * @mbg.generated
     */
    public void setRuleId(Long ruleId) {
        this.ruleId = ruleId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sign_in_record.product_num
     *
     * @return the value of sign_in_record.product_num
     *
     * @mbg.generated
     */
    public String getProductNum() {
        return productNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sign_in_record.product_num
     *
     * @param productNum the value for sign_in_record.product_num
     *
     * @mbg.generated
     */
    public void setProductNum(String productNum) {
        this.productNum = productNum == null ? null : productNum.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sign_in_record.extra_product_num
     *
     * @return the value of sign_in_record.extra_product_num
     *
     * @mbg.generated
     */
    public String getExtraProductNum() {
        return extraProductNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sign_in_record.extra_product_num
     *
     * @param extraProductNum the value for sign_in_record.extra_product_num
     *
     * @mbg.generated
     */
    public void setExtraProductNum(String extraProductNum) {
        this.extraProductNum = extraProductNum == null ? null : extraProductNum.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sign_in_record.member_num
     *
     * @return the value of sign_in_record.member_num
     *
     * @mbg.generated
     */
    public String getMemberNum() {
        return memberNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sign_in_record.member_num
     *
     * @param memberNum the value for sign_in_record.member_num
     *
     * @mbg.generated
     */
    public void setMemberNum(String memberNum) {
        this.memberNum = memberNum == null ? null : memberNum.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sign_in_record.gmt_create
     *
     * @return the value of sign_in_record.gmt_create
     *
     * @mbg.generated
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sign_in_record.gmt_create
     *
     * @param gmtCreate the value for sign_in_record.gmt_create
     *
     * @mbg.generated
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }
}