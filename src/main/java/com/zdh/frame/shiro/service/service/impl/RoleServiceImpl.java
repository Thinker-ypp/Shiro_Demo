package com.zdh.frame.shiro.service.service.impl;

import com.zdh.frame.shiro.common.enums.RoleAvailableEnum;
import com.zdh.frame.shiro.common.service.impl.BaseServiceImpl;
import com.zdh.frame.shiro.query.RoleQuery;
import com.zdh.frame.shiro.service.domain.admin.RoleDomain;
import com.zdh.frame.shiro.service.mapper.IRoleMapper;
import com.zdh.frame.shiro.service.service.IRoleService;
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
 * @Date 2019.08.23 15:27
 */
@Service
public class RoleServiceImpl extends BaseServiceImpl<RoleDomain> implements IRoleService {

    @Autowired
    private IRoleMapper roleMapper;

    /**
     * 获取角色表详细信息
     *
     * @param roleIds
     * @author yupanpan
     */
    @Override
    public Set<RoleDomain> getRoles(Set<Long> roleIds) {
        Set<RoleDomain> roleSet = new HashSet<>();
        for (Long roleId : roleIds) {
            //获取角色
            RoleDomain roleDomain = super.getOne(new RoleQuery(roleId, null, RoleAvailableEnum.OPEN.getCode()));
            if (roleDomain != null){
                roleSet.add(roleDomain);
            }
        }
        return roleSet;
    }
}
