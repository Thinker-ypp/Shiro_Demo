package com.zdh.frame.shiro.service.service;

import com.zdh.frame.shiro.common.service.IBaseService;
import com.zdh.frame.shiro.service.domain.admin.PermissionDomain;

import java.util.Set;

/**
 * <p></p>
 *
 * @Author Yupanpan
 * @Since JDK 1.8
 * @Version 1.0.0
 * @Date 2019.08.23 15:52
 */
public interface IPermissionService extends IBaseService<PermissionDomain> {

    /**
     * 获取可用资源对象
     *
     * @param longSet
     * @author yupanpan
     */
    Set<PermissionDomain> getPermissionsById(Set<Long> longSet);

    /**
     * 获取所有资源对象
     *
     * @param permissionIds
     * @author yupanpan
     * @return
     */
    Set<PermissionDomain> getPermissions(Set<Long> permissionIds);

}
