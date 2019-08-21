package com.zdh.frame.shiro.common.security.context;

import com.zdh.frame.shiro.service.domain.admin.ModuleDomain;
import com.zdh.frame.shiro.common.enums.BizConstants;
import com.zdh.frame.shiro.common.model.AdminModel;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * <p>
 *      管理员上下文对象
 * </p>
 *
 * @Author Yupanpan
 * @Since JDK 1.8
 * @Version 1.0.0
 * @Date 2019.08.21 10:05
 */
@Component
public class AdminContext implements Serializable {

    private static final long serialVersionUID = -4213457799461554685L;

    /**
     * 用户对象
     */
    private AdminModel adminModel;

    /**
     * 角色列表
     */
    private Set<String> roleSet;

    /**
     * 权限列表
     */
    private Set<String> permissions;

    /**
     * 菜单模块
     */
    private List<ModuleDomain> moduleDomains;


    /**
     * 获取上下文对象信息
     *
     * @return
     */
    public AdminContext getCurrentUser() {
        AdminContext adminContext = new AdminContext();
        Subject subject = SecurityUtils.getSubject();
        if (subject != null) {
            Session session = subject.getSession();
            if (session != null) {
                Object object = session.getAttribute(BizConstants.CURRENT_USER);
                if (object != null) {
                    adminContext = (AdminContext) object;
                }
            }
        }
        return adminContext;
    }

    public AdminModel getAdminModel() {
        return adminModel;
    }

    public void setAdminModel(AdminModel adminModel) {
        this.adminModel = adminModel;
    }

    public Set<String> getRoleSet() {
        return roleSet;
    }

    public void setRoleSet(Set<String> roleSet) {
        this.roleSet = roleSet;
    }

    public Set<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<String> permissions) {
        this.permissions = permissions;
    }

    public List<ModuleDomain> getModuleDomains() {
        return moduleDomains;
    }

    public void setModuleDomains(List<ModuleDomain> moduleDomains) {
        this.moduleDomains = moduleDomains;
    }
}
