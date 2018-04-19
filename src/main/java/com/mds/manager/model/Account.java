package com.mds.manager.model;

import java.util.Date;

public class Account {
    /**
     * 
     */
    private Long id;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 密码（MD5）
     */
    private String password;

    /**
     * 状态(1:启用、0:禁用）
     */
    private Integer status;

    /**
     * 是否删除(1:以删除，0:未删除)
     */
    private Integer del;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 
     */
    private Integer version;

    /**
     * 
     * @return id 
     */
    public Long getId() {
        return id;
    }

    /**
     * 
     * @param id 
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 手机号码
     * @return phone 手机号码
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 手机号码
     * @param phone 手机号码
     */
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    /**
     * 密码（MD5）
     * @return password 密码（MD5）
     */
    public String getPassword() {
        return password;
    }

    /**
     * 密码（MD5）
     * @param password 密码（MD5）
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * 状态(1:启用、0:禁用）
     * @return status 状态(1:启用、0:禁用）
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 状态(1:启用、0:禁用）
     * @param status 状态(1:启用、0:禁用）
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 是否删除(1:以删除，0:未删除)
     * @return del 是否删除(1:以删除，0:未删除)
     */
    public Integer getDel() {
        return del;
    }

    /**
     * 是否删除(1:以删除，0:未删除)
     * @param del 是否删除(1:以删除，0:未删除)
     */
    public void setDel(Integer del) {
        this.del = del;
    }

    /**
     * 创建时间
     * @return create_time 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 
     * @return version 
     */
    public Integer getVersion() {
        return version;
    }

    /**
     * 
     * @param version 
     */
    public void setVersion(Integer version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", status=" + status +
                ", del=" + del +
                ", createTime=" + createTime +
                ", version=" + version +
                '}';
    }
}