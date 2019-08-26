package com.zdh.frame.shiro.query;

import com.zdh.frame.shiro.common.persistence.criteria.QueryCriteria;
import com.zdh.frame.shiro.common.query.Query;
import com.zdh.frame.shiro.service.domain.admin.PermissionAssignDomain;
import tk.mybatis.mapper.entity.Example;

/**
 * <p></p>
 *
 * @Author Yupanpan
 * @Since JDK 1.8
 * @Version 1.0.0
 * @Date 2019.08.23 15:50
 */
public class PermissionAssignQuery extends Query {

    private Long roleId;
    private Long permissionId;

    public PermissionAssignQuery(Long roleId, Long permissionId) {
        this.roleId = roleId;
        this.permissionId = permissionId;
    }

    public Long getPermissionId() {
        return permissionId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }

    @Override
    public QueryCriteria toCriteria() {
        QueryCriteria queryCriteria = new QueryCriteria(PermissionAssignDomain.class);
        Example.Criteria criteria = queryCriteria.createCriteria();
        if (valid(roleId)) {
            criteria.andEqualTo("roleId", roleId);
        }
        if (valid(permissionId)) {
            criteria.andEqualTo("permissionId", permissionId);
        }
        return queryCriteria;
    }
}
