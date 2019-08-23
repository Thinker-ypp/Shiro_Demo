package com.zdh.frame.shiro.service.mapper;

import com.zdh.frame.shiro.common.persistence.Mapper;
import com.zdh.frame.shiro.service.domain.admin.UserDomain;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * <p></p>
 *
 * @Author Yupanpan
 * @Since JDK 1.8
 * @Version 1.0.0
 * @Date 2019.08.21 15:46
 */
@Component
public interface IUserMapper extends Mapper<UserDomain> {

    UserDomain queryUserByName(String userName);

    Set<String> queryRolesByName(String userName);

}
