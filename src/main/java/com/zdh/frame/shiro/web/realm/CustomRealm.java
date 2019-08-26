package com.zdh.frame.shiro.web.realm;

import com.zdh.frame.shiro.common.enums.BizConstants;
import com.zdh.frame.shiro.common.enums.PermissionTypeEnum;
import com.zdh.frame.shiro.common.model.AdminModel;
import com.zdh.frame.shiro.common.security.context.AdminContext;
import com.zdh.frame.shiro.query.ModuleQuery;
import com.zdh.frame.shiro.query.UserQuery;
import com.zdh.frame.shiro.service.domain.admin.ModuleDomain;
import com.zdh.frame.shiro.service.domain.admin.PermissionDomain;
import com.zdh.frame.shiro.service.domain.admin.RoleDomain;
import com.zdh.frame.shiro.service.domain.admin.UserDomain;
import com.zdh.frame.shiro.service.service.IModuleService;
import com.zdh.frame.shiro.service.service.IPermissionAssignService;
import com.zdh.frame.shiro.service.service.IPermissionService;
import com.zdh.frame.shiro.service.service.IRoleAssignService;
import com.zdh.frame.shiro.service.service.IRoleService;
import com.zdh.frame.shiro.service.service.IUserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>
 *      自定义域realm
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
     * @param authToken
     * @author yupanpan
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authToken) throws AuthenticationException {
        //将token转换成User对象
        UserQuery userLogin = tokenToUser((UsernamePasswordToken) authToken);
        UserDomain userDomain = userService.getOne(userLogin);
        if (userDomain == null) {
            //异常处理找不到数据
            return null;
        }
        //当前Realm的name
        String realmName = this.getName();
        //登陆的主要信息: 可以是一个实体类的对象, 但该实体类的对象一定是根据 token 的 username 查询得到的.
        Object principal = authToken.getPrincipal();
        //设置用户信息
        AdminModel adminModel = new AdminModel();
        adminModel.setObj(userDomain);
        adminContext.setAdminModel(adminModel);

        //绑定角色对象【获取对象所有角色】
        Set<Long> roleIds = roleAssignService.getRoleIds(userDomain);
        Set<RoleDomain> roleDomains = roleService.getRoles(roleIds);
        //角色处理
        Set<String> roleSet = new HashSet<String>();
        for (RoleDomain roleDomain : roleDomains) {
            //存放角色名称
            roleSet.add(roleDomain.getName());
        }
        // 将角色名称存入 adminContext中
        adminContext.setRoleSet(roleSet);//角色

        //权限对象【根据角色ID获取所有权限】
        Set<Long> permissionIds = permissionAssignService.getPermissionAssign(roleIds);
        Set<PermissionDomain> permissionDomains = permissionService.getPermissions(permissionIds);
        Set<String> permissions = new HashSet<>();
        for (PermissionDomain permissionDomain : permissionDomains) {
            //保存权限字符串
            permissions.add(permissionDomain.getPermission());
        }
        // 将权限字符串存入 adminContext中
        adminContext.setPermissions(permissions);


        // 获取click对象模块
        Set<Long> set = new HashSet<Long>();
        for (PermissionDomain item : permissionDomains) {
            //筛选出所有模块是click 的对象
            if (item != null && item.getType().equals(PermissionTypeEnum.CLICK.getType())) {
                set.add(item.getModuleId());
            }
        }
        List<Long> idList = new ArrayList<Long>(set);
        List<ModuleDomain> moduleDomains = null;
        if (idList.size() > 0) {
            try {
                moduleDomains = moduleService.getList(new ModuleQuery(idList, false, "sorting"));
            } catch (Exception e) {
                LOGGER.error("获取模块信息出现异常：---> {}", e.getMessage());
            }
        }
        modulePermission(moduleDomains);
        adminContext.setModuleDomains(moduleDomains);

        setSession(BizConstants.CURRENT_USER, adminContext);
        return new SimpleAuthenticationInfo(principal, userLogin.getPassword(), realmName);
    }


    /**
     * 权限认证
     *
     * @param principals
     * @author yupanpan
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String username = (String) principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        if (StringUtils.isEmpty(username)){
            return info;
        }
        info.setRoles(adminContext.getCurrentUser().getRoleSet());
        info.setStringPermissions(adminContext.getCurrentUser().getPermissions());
        return info;
    }

    /**
     * 取出token中的用户信息，放在本地对象中
     *
     * @param authToken
     * @author yupanpan
     */
    private UserQuery tokenToUser(UsernamePasswordToken authToken) {
        UserQuery user = new UserQuery();
        user.setUserName(authToken.getUsername());
        user.setPassword(String.valueOf(authToken.getPassword()));
        return user;
    }

    /**
     * 对模块结合排序，获取所有模块
     *
     * @param moduleDomains
     */
    private void modulePermission(List<ModuleDomain> moduleDomains) {
        if (moduleDomains == null) {
            return;
        }
        List<ModuleDomain> lists = new ArrayList<>();
        ModuleDomain domain = moduleDomains.get(0);
        lists.add(domain);
        for (ModuleDomain item : moduleDomains) {
            if (!item.getId().equals(domain.getId())) {
                lists.add(item);
                domain = item;
            }
        }
        // 0、-1 决定正序或倒叙
        lists.sort((ModuleDomain x, ModuleDomain y) -> x.getId() > y.getId() ? 0 : -1);
    }

    /**
     * 将用户信息保存至session
     *
     * @param key
     * @param value
     */
    public void setSession(Object key, Object value) {
        Subject currentUser = SecurityUtils.getSubject();
        if (null != currentUser) {
            Session session = currentUser.getSession();
            if (null != session) {
                session.setAttribute(key, value);
            }
        }
    }
}
