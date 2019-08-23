package com.zdh.frame.shiro.service.service.impl;

import com.zdh.frame.shiro.common.service.impl.BaseServiceImpl;
import com.zdh.frame.shiro.query.PermissionAssignQuery;
import com.zdh.frame.shiro.service.domain.admin.PermissionAssignDomain;
import com.zdh.frame.shiro.service.mapper.IPermissionAssignMapper;
import com.zdh.frame.shiro.service.service.IPermissionAssignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p></p>
 *
 * @Author Yupanpan
 * @Since JDK 1.8
 * @Version 1.0.0
 * @Date 2019.08.23 15:47
 */
@SuppressWarnings("SpringJavaAutowiringInspection")
@Service
public class PermissionAssignServiceImpl extends BaseServiceImpl<PermissionAssignDomain> implements IPermissionAssignService {

    @Autowired
    private IPermissionAssignMapper permissionAssignMapper;


    @Override
    public Set<Long> getPermissionAssign(Set<Long> roleIds) {
        Set<Long> sets = new HashSet<>();
        for (Long roleId: roleIds) {
            List<PermissionAssignDomain> domainList = super.getList(new PermissionAssignQuery(roleId,null));
            if(domainList != null) {
                for (PermissionAssignDomain permissionAssignDomain : domainList) {
                    sets.add(permissionAssignDomain.getPermissionId());
                }
            }
        }
        return sets;
    }
}
