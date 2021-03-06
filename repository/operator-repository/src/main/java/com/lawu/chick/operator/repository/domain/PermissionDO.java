package com.lawu.chick.operator.repository.domain;

import java.io.Serializable;
import java.util.Date;

public class PermissionDO implements Serializable {
    /**
     *
     * 自增ID
     * permission.id
     *
     * @mbg.generated
     */
    private Integer id;

    /**
     *
     * 权限名称
     * permission.permission_name
     *
     * @mbg.generated
     */
    private String permissionName;

    /**
     *
     * 权限键值
     * permission.permission_key
     *
     * @mbg.generated
     */
    private String permissionKey;

    /**
     *
     * 权限URL
     * permission.permission_url
     *
     * @mbg.generated
     */
    private String permissionUrl;

    /**
     *
     * 父节点ID
     * permission.parent_id
     *
     * @mbg.generated
     */
    private Integer parentId;

    /**
     *
     * 排序
     * permission.sort_id
     *
     * @mbg.generated
     */
    private Integer sortId;

    /**
     *
     * 修改时间
     * permission.gmt_modified
     *
     * @mbg.generated
     */
    private Date gmtModified;

    /**
     *
     * 创建时间
     * permission.gmt_create
     *
     * @mbg.generated
     */
    private Date gmtCreate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table permission
     *
     * @mbg.generated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column permission.id
     *
     * @return the value of permission.id
     *
     * @mbg.generated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column permission.id
     *
     * @param id the value for permission.id
     *
     * @mbg.generated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column permission.permission_name
     *
     * @return the value of permission.permission_name
     *
     * @mbg.generated
     */
    public String getPermissionName() {
        return permissionName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column permission.permission_name
     *
     * @param permissionName the value for permission.permission_name
     *
     * @mbg.generated
     */
    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName == null ? null : permissionName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column permission.permission_key
     *
     * @return the value of permission.permission_key
     *
     * @mbg.generated
     */
    public String getPermissionKey() {
        return permissionKey;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column permission.permission_key
     *
     * @param permissionKey the value for permission.permission_key
     *
     * @mbg.generated
     */
    public void setPermissionKey(String permissionKey) {
        this.permissionKey = permissionKey == null ? null : permissionKey.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column permission.permission_url
     *
     * @return the value of permission.permission_url
     *
     * @mbg.generated
     */
    public String getPermissionUrl() {
        return permissionUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column permission.permission_url
     *
     * @param permissionUrl the value for permission.permission_url
     *
     * @mbg.generated
     */
    public void setPermissionUrl(String permissionUrl) {
        this.permissionUrl = permissionUrl == null ? null : permissionUrl.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column permission.parent_id
     *
     * @return the value of permission.parent_id
     *
     * @mbg.generated
     */
    public Integer getParentId() {
        return parentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column permission.parent_id
     *
     * @param parentId the value for permission.parent_id
     *
     * @mbg.generated
     */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column permission.sort_id
     *
     * @return the value of permission.sort_id
     *
     * @mbg.generated
     */
    public Integer getSortId() {
        return sortId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column permission.sort_id
     *
     * @param sortId the value for permission.sort_id
     *
     * @mbg.generated
     */
    public void setSortId(Integer sortId) {
        this.sortId = sortId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column permission.gmt_modified
     *
     * @return the value of permission.gmt_modified
     *
     * @mbg.generated
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column permission.gmt_modified
     *
     * @param gmtModified the value for permission.gmt_modified
     *
     * @mbg.generated
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column permission.gmt_create
     *
     * @return the value of permission.gmt_create
     *
     * @mbg.generated
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column permission.gmt_create
     *
     * @param gmtCreate the value for permission.gmt_create
     *
     * @mbg.generated
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }
}