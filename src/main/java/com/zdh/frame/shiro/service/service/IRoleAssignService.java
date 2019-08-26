package com.zdh.frame.shiro.service.service;

import com.zdh.frame.shiro.common.service.IBaseService;
import com.zdh.frame.shiro.service.domain.admin.RoleAssignDomain;
import com.zdh.frame.shiro.service.domain.admin.UserDomain;

import java.util.Set;

/**
 * <p></p>
 *
 * @Author Yupanpan
 * @Since JDK 1.8
 * @Version 1.0.0
 * @Date 2019.08.23 15:07
 */
public interface IRoleAssignService extends IBaseService<RoleAssignDomain> {

    /**
     * 通过用户获取角色对象信息
     *
     * @param userDomain
     * @author yupanpan
     * @return
     */
    Set<Long> getRoleIds(UserDomain userDomain);
}
