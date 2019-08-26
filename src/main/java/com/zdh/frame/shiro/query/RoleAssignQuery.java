package com.zdh.frame.shiro.query;

import com.zdh.frame.shiro.common.persistence.criteria.QueryCriteria;
import com.zdh.frame.shiro.common.query.Query;
import com.zdh.frame.shiro.service.domain.admin.RoleAssignDomain;
import tk.mybatis.mapper.entity.Example;

/**
 * <p></p>
 *
 * @Author Yupanpan
 * @Since JDK 1.8
 * @Version 1.0.0
 * @Date 2019.08.23 15:19
 */
public class RoleAssignQuery extends Query {

    private Integer id;

    private Integer adminId;

    public RoleAssignQuery(Integer adminId, Integer id) {
        this.adminId = adminId;
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    @Override
    public QueryCriteria toCriteria() {
        QueryCriteria queryCriteria = new QueryCriteria(RoleAssignDomain.class);
        Example.Criteria criteria = queryCriteria.createCriteria();
        if (valid(id)) {
            criteria.andEqualTo("id", id);
        }
        if (valid(adminId)) {
            criteria.andEqualTo("adminId", adminId);
        }
        return queryCriteria;
    }
}
