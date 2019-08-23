package com.zdh.frame.shiro.service.service.impl;

import com.zdh.frame.shiro.common.service.impl.BaseServiceImpl;
import com.zdh.frame.shiro.service.domain.admin.UserDomain;
import com.zdh.frame.shiro.service.mapper.IUserMapper;
import com.zdh.frame.shiro.service.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * <p></p>
 *
 * @Author Yupanpan
 * @Since JDK 1.8
 * @Version 1.0.0
 * @Date 2019.08.21 14:52
 */
@SuppressWarnings("SpringJavaAutowiringInspection")
@Service
public class UserServiceImpl extends BaseServiceImpl<UserDomain> implements IUserService {

    @Autowired
    private IUserMapper userMapper;

    @Override
    public UserDomain queryUserByName(String userName) {
        return userMapper.queryUserByName(userName);
    }

    @Override
    public Set<String> queryRolesByName(String userName) {
        return userMapper.queryRolesByName(userName);
    }
}
