package com.lawu.chick.repository.domain;

import java.io.Serializable;
import java.util.Date;

public class SysConfigDO implements Serializable {
    /**
     *
     * 主键
     * sys_config.id
     *
     * @mbg.generated
     */
    private Long id;

    /**
     *
     * 小鸡配置生效时间
     * sys_config.chicken_effective_time
     *
     * @mbg.generated
     */
    private Date chickenEffectiveTime;

    /**
     *
     * 鸡蛋配置生效时间
     * sys_config.egg_effective_time
     *
     * @mbg.generated
     */
    private Date eggEffectiveTime;

    /**
     *
     * 牧场配置生效时间
     * sys_config.rangeland_effective_time
     *
     * @mbg.generated
     */
    private Date rangelandEffectiveTime;

    /**
     *
     * 更新时间
     * sys_config.gmt_modified
     *
     * @mbg.generated
     */
    private Date gmtModified;

    /**
     *
     * 创建时间
     * sys_config.gmt_create
     *
     * @mbg.generated
     */
    private Date gmtCreate;

    /**
     *
     * 小鸡配置信息json
     * sys_config.chicken_config
     *
     * @mbg.generated
     */
    private String chickenConfig;

    /**
     *
     * 鸡蛋配置信息json
     * sys_config.egg_config
     *
     * @mbg.generated
     */
    private String eggConfig;

    /**
     *
     * 牧场配置信息json
     * sys_config.rangeland_config
     *
     * @mbg.generated
     */
    private String rangelandConfig;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table sys_config
     *
     * @mbg.generated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_config.id
     *
     * @return the value of sys_config.id
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_config.id
     *
     * @param id the value for sys_config.id
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_config.chicken_effective_time
     *
     * @return the value of sys_config.chicken_effective_time
     *
     * @mbg.generated
     */
    public Date getChickenEffectiveTime() {
        return chickenEffectiveTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_config.chicken_effective_time
     *
     * @param chickenEffectiveTime the value for sys_config.chicken_effective_time
     *
     * @mbg.generated
     */
    public void setChickenEffectiveTime(Date chickenEffectiveTime) {
        this.chickenEffectiveTime = chickenEffectiveTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_config.egg_effective_time
     *
     * @return the value of sys_config.egg_effective_time
     *
     * @mbg.generated
     */
    public Date getEggEffectiveTime() {
        return eggEffectiveTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_config.egg_effective_time
     *
     * @param eggEffectiveTime the value for sys_config.egg_effective_time
     *
     * @mbg.generated
     */
    public void setEggEffectiveTime(Date eggEffectiveTime) {
        this.eggEffectiveTime = eggEffectiveTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_config.rangeland_effective_time
     *
     * @return the value of sys_config.rangeland_effective_time
     *
     * @mbg.generated
     */
    public Date getRangelandEffectiveTime() {
        return rangelandEffectiveTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_config.rangeland_effective_time
     *
     * @param rangelandEffectiveTime the value for sys_config.rangeland_effective_time
     *
     * @mbg.generated
     */
    public void setRangelandEffectiveTime(Date rangelandEffectiveTime) {
        this.rangelandEffectiveTime = rangelandEffectiveTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_config.gmt_modified
     *
     * @return the value of sys_config.gmt_modified
     *
     * @mbg.generated
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_config.gmt_modified
     *
     * @param gmtModified the value for sys_config.gmt_modified
     *
     * @mbg.generated
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_config.gmt_create
     *
     * @return the value of sys_config.gmt_create
     *
     * @mbg.generated
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_config.gmt_create
     *
     * @param gmtCreate the value for sys_config.gmt_create
     *
     * @mbg.generated
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_config.chicken_config
     *
     * @return the value of sys_config.chicken_config
     *
     * @mbg.generated
     */
    public String getChickenConfig() {
        return chickenConfig;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_config.chicken_config
     *
     * @param chickenConfig the value for sys_config.chicken_config
     *
     * @mbg.generated
     */
    public void setChickenConfig(String chickenConfig) {
        this.chickenConfig = chickenConfig == null ? null : chickenConfig.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_config.egg_config
     *
     * @return the value of sys_config.egg_config
     *
     * @mbg.generated
     */
    public String getEggConfig() {
        return eggConfig;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_config.egg_config
     *
     * @param eggConfig the value for sys_config.egg_config
     *
     * @mbg.generated
     */
    public void setEggConfig(String eggConfig) {
        this.eggConfig = eggConfig == null ? null : eggConfig.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_config.rangeland_config
     *
     * @return the value of sys_config.rangeland_config
     *
     * @mbg.generated
     */
    public String getRangelandConfig() {
        return rangelandConfig;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_config.rangeland_config
     *
     * @param rangelandConfig the value for sys_config.rangeland_config
     *
     * @mbg.generated
     */
    public void setRangelandConfig(String rangelandConfig) {
        this.rangelandConfig = rangelandConfig == null ? null : rangelandConfig.trim();
    }
}