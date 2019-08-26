package com.zdh.frame.shiro.model;


import com.zdh.frame.shiro.common.model.Query;

import java.io.Serializable;

/**
 * <p></p>
 *
 * @Author Yupanpan
 * @Since JDK 1.8
 * @Version 1.0.0
 * @Date 2019.08.26 10:09
 */
public class UserModel extends Query implements Serializable {

    private static final long serialVersionUID = -1342310211345659917L;

    private String userName;

    private String password;

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
}
