package com.zdh.frame.shiro.service.service.impl;

import com.zdh.frame.shiro.common.service.impl.BaseServiceImpl;
import com.zdh.frame.shiro.query.RoleAssignQuery;
import com.zdh.frame.shiro.service.domain.admin.RoleAssignDomain;
import com.zdh.frame.shiro.service.domain.admin.UserDomain;
import com.zdh.frame.shiro.service.mapper.IRoleAssignMapper;
import com.zdh.frame.shiro.service.service.IRoleAssignService;
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
 * @Date 2019.08.23 15:11
 */
@Service
public class RoleAssignServiceImpl extends BaseServiceImpl<RoleAssignDomain> implements IRoleAssignService {

    @Autowired
    private IRoleAssignMapper roleAssignMapper;
    /**
     * 通过用户获取角色对象信息
     * @author yupanpan
	 * @param userDomain
     */
    @Override
    public Set<Long> getRoleIds(UserDomain userDomain) {
        Set<Long> longSet = new HashSet<>();
        if (userDomain != null){
            List<RoleAssignDomain> list = super.getList(new RoleAssignQuery(userDomain.getId()));
            if (null != list){
                for (RoleAssignDomain roleAssignDomain : list) {
                    longSet.add(roleAssignDomain.getId());
                }
            }
        }
        return longSet;
    }
}
