package com.zdh.frame.shiro.service.service;

import com.zdh.frame.shiro.common.service.IBaseService;
import com.zdh.frame.shiro.service.domain.admin.UserDomain;

import java.util.Set;

/**
 * <p></p>
 *
 * @Author Yupanpan
 * @Since JDK 1.8
 * @Version 1.0.0
 * @Date 2019.08.21 14:52
 */
public interface IUserService extends IBaseService<UserDomain> {

    UserDomain queryUserByName(String userName);
    Set<String> queryRolesByName(String userName);

}
