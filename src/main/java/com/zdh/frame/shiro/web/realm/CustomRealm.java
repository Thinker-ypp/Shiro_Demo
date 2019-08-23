package com.zdh.frame.shiro.web.realm;

import com.zdh.frame.shiro.common.security.context.AdminContext;
import com.zdh.frame.shiro.service.service.IModuleService;
import com.zdh.frame.shiro.service.service.IPermissionAssignService;
import com.zdh.frame.shiro.service.service.IPermissionService;
import com.zdh.frame.shiro.service.service.IRoleAssignService;
import com.zdh.frame.shiro.service.service.IRoleService;
import com.zdh.frame.shiro.service.service.IUserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * 自定义域realm
 * </p>
 *
 * @Author Yupanpan
 * @Since JDK 1.8
 * @Version 1.0.0
 * @Date 2019.08.23 14:33
 */
public class CustomRealm extends AuthorizingRealm {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomRealm.class);

    @Autowired
    private AdminContext adminContext;

    @Autowired
    private IUserService userService;

    @Autowired
    private IRoleAssignService roleAssignService;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IPermissionAssignService permissionAssignService;

    @Autowired
    private IPermissionService permissionService;

    @Autowired
    private IModuleService moduleService;

    /**
     * 登录认证
     *
     * @param token
     * @author yupanpan
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        return null;
    }

    /**
     * 权限认证
     *
     * @param principals
     * @author yupanpan
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }
}
