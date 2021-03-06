package com.mds.manager.model;

import java.util.Date;

public class User {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user.id
     *
     * @mbggenerated Wed Aug 16 11:49:52 CST 2017
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user.username
     *
     * @mbggenerated Wed Aug 16 11:49:52 CST 2017
     */
    private String username;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user.password
     *
     * @mbggenerated Wed Aug 16 11:49:52 CST 2017
     */
    private String password;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user.nickname
     *
     * @mbggenerated Wed Aug 16 11:49:52 CST 2017
     */
    private String nickname;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user.status
     *
     * @mbggenerated Wed Aug 16 11:49:52 CST 2017
     */
    private Integer status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user.userdesc
     *
     * @mbggenerated Wed Aug 16 11:49:52 CST 2017
     */
    private String userdesc;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user.organization
     *
     * @mbggenerated Wed Aug 16 11:49:52 CST 2017
     */
    private String organization;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user.birth_date
     *
     * @mbggenerated Wed Aug 16 11:49:52 CST 2017
     */
    private String birthDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user.user_type
     *
     * @mbggenerated Wed Aug 16 11:49:52 CST 2017
     */
    private Integer userType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user.phone_number
     *
     * @mbggenerated Wed Aug 16 11:49:52 CST 2017
     */
    private String phoneNumber;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user.address
     *
     * @mbggenerated Wed Aug 16 11:49:52 CST 2017
     */
    private String address;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user.email
     *
     * @mbggenerated Wed Aug 16 11:49:52 CST 2017
     */
    private String email;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user.create_time
     *
     * @mbggenerated Wed Aug 16 11:49:52 CST 2017
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user.login_time
     *
     * @mbggenerated Wed Aug 16 11:49:52 CST 2017
     */
    private Date loginTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user.modify_time
     *
     * @mbggenerated Wed Aug 16 11:49:52 CST 2017
     */
    private Date modifyTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user.id
     *
     * @return the value of t_user.id
     *
     * @mbggenerated Wed Aug 16 11:49:52 CST 2017
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user.id
     *
     * @param id the value for t_user.id
     *
     * @mbggenerated Wed Aug 16 11:49:52 CST 2017
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user.username
     *
     * @return the value of t_user.username
     *
     * @mbggenerated Wed Aug 16 11:49:52 CST 2017
     */
    public String getUsername() {
        return username;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user.username
     *
     * @param username the value for t_user.username
     *
     * @mbggenerated Wed Aug 16 11:49:52 CST 2017
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user.password
     *
     * @return the value of t_user.password
     *
     * @mbggenerated Wed Aug 16 11:49:52 CST 2017
     */
    public String getPassword() {
        return password;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user.password
     *
     * @param password the value for t_user.password
     *
     * @mbggenerated Wed Aug 16 11:49:52 CST 2017
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user.nickname
     *
     * @return the value of t_user.nickname
     *
     * @mbggenerated Wed Aug 16 11:49:52 CST 2017
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user.nickname
     *
     * @param nickname the value for t_user.nickname
     *
     * @mbggenerated Wed Aug 16 11:49:52 CST 2017
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user.status
     *
     * @return the value of t_user.status
     *
     * @mbggenerated Wed Aug 16 11:49:52 CST 2017
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user.status
     *
     * @param status the value for t_user.status
     *
     * @mbggenerated Wed Aug 16 11:49:52 CST 2017
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user.userdesc
     *
     * @return the value of t_user.userdesc
     *
     * @mbggenerated Wed Aug 16 11:49:52 CST 2017
     */
    public String getUserdesc() {
        return userdesc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user.userdesc
     *
     * @param userdesc the value for t_user.userdesc
     *
     * @mbggenerated Wed Aug 16 11:49:52 CST 2017
     */
    public void setUserdesc(String userdesc) {
        this.userdesc = userdesc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user.organization
     *
     * @return the value of t_user.organization
     *
     * @mbggenerated Wed Aug 16 11:49:52 CST 2017
     */
    public String getOrganization() {
        return organization;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user.organization
     *
     * @param organization the value for t_user.organization
     *
     * @mbggenerated Wed Aug 16 11:49:52 CST 2017
     */
    public void setOrganization(String organization) {
        this.organization = organization;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user.birth_date
     *
     * @return the value of t_user.birth_date
     *
     * @mbggenerated Wed Aug 16 11:49:52 CST 2017
     */
    public String getBirthDate() {
        return birthDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user.birth_date
     *
     * @param birthDate the value for t_user.birth_date
     *
     * @mbggenerated Wed Aug 16 11:49:52 CST 2017
     */
    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user.user_type
     *
     * @return the value of t_user.user_type
     *
     * @mbggenerated Wed Aug 16 11:49:52 CST 2017
     */
    public Integer getUserType() {
        return userType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user.user_type
     *
     * @param userType the value for t_user.user_type
     *
     * @mbggenerated Wed Aug 16 11:49:52 CST 2017
     */
    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user.phone_number
     *
     * @return the value of t_user.phone_number
     *
     * @mbggenerated Wed Aug 16 11:49:52 CST 2017
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user.phone_number
     *
     * @param phoneNumber the value for t_user.phone_number
     *
     * @mbggenerated Wed Aug 16 11:49:52 CST 2017
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user.address
     *
     * @return the value of t_user.address
     *
     * @mbggenerated Wed Aug 16 11:49:52 CST 2017
     */
    public String getAddress() {
        return address;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user.address
     *
     * @param address the value for t_user.address
     *
     * @mbggenerated Wed Aug 16 11:49:52 CST 2017
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user.email
     *
     * @return the value of t_user.email
     *
     * @mbggenerated Wed Aug 16 11:49:52 CST 2017
     */
    public String getEmail() {
        return email;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user.email
     *
     * @param email the value for t_user.email
     *
     * @mbggenerated Wed Aug 16 11:49:52 CST 2017
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user.create_time
     *
     * @return the value of t_user.create_time
     *
     * @mbggenerated Wed Aug 16 11:49:52 CST 2017
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user.create_time
     *
     * @param createTime the value for t_user.create_time
     *
     * @mbggenerated Wed Aug 16 11:49:52 CST 2017
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user.login_time
     *
     * @return the value of t_user.login_time
     *
     * @mbggenerated Wed Aug 16 11:49:52 CST 2017
     */
    public Date getLoginTime() {
        return loginTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user.login_time
     *
     * @param loginTime the value for t_user.login_time
     *
     * @mbggenerated Wed Aug 16 11:49:52 CST 2017
     */
    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user.modify_time
     *
     * @return the value of t_user.modify_time
     *
     * @mbggenerated Wed Aug 16 11:49:52 CST 2017
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user.modify_time
     *
     * @param modifyTime the value for t_user.modify_time
     *
     * @mbggenerated Wed Aug 16 11:49:52 CST 2017
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}