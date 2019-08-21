package com.zdh.frame.shiro.common.model;

import java.io.Serializable;

/**
 * <p>
 * 管理员用户对象
 * </p>
 *
 * @Author Yupanpan
 * @Since JDK 1.8
 * @Version 1.0.0
 * @Date 2019.08.21 9:53
 */
public class AdminModel<T> implements Serializable {

    private static final long serialVersionUID = 3885701764449096171L;

    /**
     * 管理员唯一标识ID
     */
    private String id;

    /**
     * 名称
     */
    private String realName;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

    /**
     * 盐
     */
    private String salt;

    /**
     * 是否可用 0：不可用 1：可用
     */
    private Integer locked;

    /**
     * 最后登陆IP
     */
    private String lastLoginIp;

    /**
     * 登录次数
     */
    private Integer loginCount;

    /**
     * 泛型对象
     */
    private T obj;

    public AdminModel() {
        super();
    }

    public AdminModel(String id, String realName, String userName, String password, String salt, Integer locked, String lastLoginIp, Integer loginCount) {

        super();
        this.id = id;
        this.realName = realName;
        this.userName = userName;
        this.password = password;
        this.salt = salt;
        this.locked = locked;
        this.lastLoginIp = lastLoginIp;
        this.loginCount = loginCount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Integer getLocked() {
        return locked;
    }

    public void setLocked(Integer locked) {
        this.locked = locked;
    }

    public String getLastLoginIp() {
        return lastLoginIp;
    }

    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
    }

    public Integer getLoginCount() {
        return loginCount;
    }

    public void setLoginCount(Integer loginCount) {
        this.loginCount = loginCount;
    }

    public T getObj() {
        return obj;
    }

    public void setObj(T obj) {
        this.obj = obj;
    }
}
