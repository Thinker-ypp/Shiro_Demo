package com.zdh.frame.shiro.web.tag;

import com.zdh.frame.shiro.service.domain.admin.ModuleDomain;
import com.zdh.frame.shiro.common.enums.PermissionTypeEnum;
import com.zdh.frame.shiro.common.model.AdminModel;
import com.zdh.frame.shiro.common.security.context.AdminContext;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *      系统默认标签库
 * </p>
 *
 * @Author Yupanpan
 * @Since JDK 1.8
 * @Version 1.0.0
 * @Date 2019.08.21 10:32
 */
public class DefaultTags {

    private static AdminContext adminContexts;

    public DefaultTags(AdminContext context) {
        adminContexts = context;
    }

    /**
     * 获取登录用户信息
     *
     * @author yupanpan
     */
    public static AdminModel getAdmin() {
        AdminModel adminModel = adminContexts.getCurrentUser().getAdminModel();
        if (adminModel == null) {
            new Exception("登录信息异常！");
        }
        return adminModel;
    }

    /**
     * 获取菜单模块
     *
     * @author yupanpan
     */
    public static List<ModuleDomain> getModules() {
        List<ModuleDomain> moduleModels = new ArrayList<>();
        AdminContext currentUser = adminContexts.getCurrentUser();
        if (currentUser != null) {
            moduleModels = currentUser.getModuleDomains();
        }
        return moduleModels;
    }

    /**
     * 获取所有权限资源类型
     *
     * @author yupanpan
     */
    public static List<String> getPermissionType() {
        List<String> permissionTypes = new ArrayList<>();
        PermissionTypeEnum[] values = PermissionTypeEnum.values();
        for (PermissionTypeEnum value : values) {
            permissionTypes.add(value.getType());
        }
        return permissionTypes;
    }
}
