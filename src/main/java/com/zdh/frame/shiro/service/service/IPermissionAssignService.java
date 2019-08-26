package com.zdh.frame.shiro.service.service;

import com.zdh.frame.shiro.common.service.IBaseService;
import com.zdh.frame.shiro.service.domain.admin.PermissionAssignDomain;

import java.util.Set;

/**
 * <p></p>
 *
 * @Author Yupanpan
 * @Since JDK 1.8
 * @Version 1.0.0
 * @Date 2019.08.23 15:45
 */
public interface IPermissionAssignService extends IBaseService<PermissionAssignDomain> {

    /**
     * 根据权限获取资源编号
     *
     * @param roleIds
     * @author yupanpan
     * @return
     */
    Set<Long> getPermissionAssign(Set<Long> roleIds);
}
