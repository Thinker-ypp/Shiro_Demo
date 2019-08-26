package com.zdh.frame.shiro.service.service;

import com.zdh.frame.shiro.common.service.IBaseService;
import com.zdh.frame.shiro.service.domain.admin.RoleDomain;

import java.util.Set;

/**
 * <p></p>
 *
 * @Author Yupanpan
 * @Since JDK 1.8
 * @Version 1.0.0
 * @Date 2019.08.23 15:23
 */
public interface IRoleService extends IBaseService<RoleDomain> {

    /**
     * 获取角色表详细信息
     *
     * @param roleIds
     * @author yupanpan
     */
    Set<RoleDomain> getRoles(Set<Long> roleIds);
}
