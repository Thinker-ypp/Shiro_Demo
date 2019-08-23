package com.zdh.frame.shiro.query;

import com.zdh.frame.shiro.common.persistence.criteria.QueryCriteria;
import com.zdh.frame.shiro.common.query.Query;
import com.zdh.frame.shiro.service.domain.admin.UserDomain;
import tk.mybatis.mapper.entity.Example;

/**
 * <p></p>
 *
 * @Author Yupanpan
 * @Since JDK 1.8
 * @Version 1.0.0
 * @Date 2019.08.23 17:00
 */
public class UserQuery extends Query {

    private String userName;
    private String password;
    private Long id;

    @Override
    public QueryCriteria toCriteria() {
        QueryCriteria queryCriteria = new QueryCriteria(UserDomain.class);
        Example.Criteria criteria = queryCriteria.createCriteria();
        if (valid(userName)){
            criteria.andEqualTo("userName",userName);
        }
        if(valid(password)){
            criteria.andEqualTo("password",password);
        }
        if (valid(id)){
            criteria.andEqualTo("id",id);
        }
        return queryCriteria;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
