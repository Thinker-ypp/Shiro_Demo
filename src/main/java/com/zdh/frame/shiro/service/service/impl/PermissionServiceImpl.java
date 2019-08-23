package com.zdh.frame.shiro.service.service.impl;

import com.zdh.frame.shiro.common.service.impl.BaseServiceImpl;
import com.zdh.frame.shiro.query.PermissionQuery;
import com.zdh.frame.shiro.service.domain.admin.PermissionDomain;
import com.zdh.frame.shiro.service.mapper.IPermissionMapper;
import com.zdh.frame.shiro.service.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * <p></p>
 *
 * @Author Yupanpan
 * @Since JDK 1.8
 * @Version 1.0.0
 * @Date 2019.08.23 15:57
 */
@SuppressWarnings("SpringJavaAutowiringInspection")
@Service
public class PermissionServiceImpl extends BaseServiceImpl<PermissionDomain> implements IPermissionService {

    @Autowired
    private IPermissionMapper permissionMapper;

    @Override
    public Set<PermissionDomain> getPermissionsById(Set<Long> longSet) {
        Set<PermissionDomain> sets = new HashSet<>();
        for (Long item:longSet) {
            PermissionQuery permissionQuery = new PermissionQuery();
            permissionQuery.setId(item);
            permissionQuery.setAvailable(0);
            PermissionDomain domain = super.getOne(permissionQuery);
            if (domain != null) {
                sets.add(domain);
            }
        }
        return sets;
    }

    @Override
    public Set<PermissionDomain> getPermissions(Set<Long> longSet) {
        Set<PermissionDomain> sets = new HashSet<>();
        for (Long item:longSet) {
            PermissionQuery permissionQuery = new PermissionQuery();
            permissionQuery.setId(item);
            PermissionDomain domain = super.getOne(permissionQuery);
            if (domain != null){
                sets.add(domain);
            }
        }
        return sets;
    }
}
