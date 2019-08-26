package com.zdh.frame.shiro.query;

import com.zdh.frame.shiro.common.persistence.criteria.QueryCriteria;
import com.zdh.frame.shiro.common.query.Query;
import com.zdh.frame.shiro.service.domain.admin.RoleDomain;
import tk.mybatis.mapper.entity.Example;

/**
 * <p></p>
 *
 * @Author Yupanpan
 * @Since JDK 1.8
 * @Version 1.0.0
 * @Date 2019.08.23 15:41
 */
public class RoleQuery extends Query {

    private Long roleId;
    private String name;
    /**
     * 是否开启 0:关闭 1:开启
     */
    private Integer available;

    public RoleQuery(Long roleId,String name,Integer available){
        this.roleId = roleId;
        this.name = name;
        this.available = available;
    }
    @Override
    public QueryCriteria toCriteria() {
        QueryCriteria queryCriteria = new QueryCriteria(RoleDomain.class);
        Example.Criteria criteria = queryCriteria.createCriteria();
        if(valid(roleId)){
            criteria.andEqualTo("id",roleId);
        }
        if(valid(name)){
            criteria.andEqualTo("name",name);
        }
        if(valid(available)) {
            criteria.andEqualTo("available", available);
        }
        return queryCriteria;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAvailable() {
        return available;
    }

    public void setAvailable(Integer available) {
        this.available = available;
    }
}
