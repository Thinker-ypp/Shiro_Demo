package com.zdh.frame.shiro.web.controller.system;

import com.alibaba.fastjson.JSONObject;
import com.zdh.frame.shiro.common.enums.BeUsedEnum;
import com.zdh.frame.shiro.common.enums.PermissionTypeEnum;
import com.zdh.frame.shiro.query.PermissionQuery;
import com.zdh.frame.shiro.service.domain.admin.PermissionDomain;
import com.zdh.frame.shiro.service.service.IPermissionService;
import com.zdh.frame.shiro.web.controller.base.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * 模块Controller
 * </p>
 *
 * @Author Yupanpan
 * @Since JDK 1.8
 * @Version 1.0.0
 * @Date 2019.08.26 17:38
 */
@Controller
@Scope("prototype")
@RequestMapping(value = "/module")
public class ModuleController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ModuleController.class);

    @Autowired
    private IPermissionService permissionService;

    /**
     * 获取用户所关联的所有一级菜单
     *
     * @param moduleId
     * @return
     */
    @RequestMapping(value = "/click")
    @ResponseBody
    public String getClick(Long moduleId) {
        PermissionQuery query = new PermissionQuery();
        query.setModuleId(moduleId);
        query.setType(PermissionTypeEnum.CLICK.getType());
        query.setAvailable(BeUsedEnum.BE_USED.getCode());
        //根据条件查询权限
        List<PermissionDomain> permissionList = permissionService.getList(query);
        Set<String> permissionSet = this.getPermissions();
        List<PermissionDomain> permissionDomains = new ArrayList<>();
        if (permissionList != null && permissionList.size() > 0) {
            for (PermissionDomain permission : permissionList) {
                for (String permissionStr : permissionSet) {
                    if (permission.getPermission().equals(permissionStr)) {
                        permissionDomains.add(permission);
                    }
                }
            }
        }
        return JSONObject.toJSONString(permissionDomains);
    }
}
