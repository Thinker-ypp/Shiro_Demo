package com.zdh.frame.shiro.common.model;

import java.io.Serializable;

/**
 * <p></p>
 *
 * @Author Yupanpan
 * @Since JDK 1.8
 * @Version 1.0.0
 * @Date 2019.09.11 10:03
 */
public class UserModel implements Serializable {

    private static final long serialVersionUID = 7379015702484877738L;

    /*管理员id*/
    private Integer id;

    /*姓名*/
    private String realName;

    /*用户名*/
    private String userName;


    /*手机*/
    private String phone;


    /*是否可用 0:不可用 1:可用*/
    private Integer locked;
    private String lockedName;

    /*最后登录时间*/
    private String lastLoginTime;

    /*创建时间*/
    private String createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public Integer getLocked() {
        return locked;
    }

    public void setLocked(Integer locked) {
        this.locked = locked;
    }

    public String getLockedName() {
        return lockedName;
    }

    public void setLockedName(String lockedName) {
        this.lockedName = lockedName;
    }

    public String getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(String lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}