package com.zdh.frame.shiro.web.controller.base;

import com.alibaba.fastjson.JSONObject;
import com.zdh.frame.shiro.common.model.AdminModel;
import com.zdh.frame.shiro.common.security.context.AdminContext;
import com.zdh.frame.shiro.service.domain.admin.UserDomain;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * <p>
 *      Controller基类
 * </p>
 *
 * @Author Yupanpan
 * @Since JDK 1.8
 * @Version 1.0.0
 * @Date 2019.08.21 14:12
 */
public class BaseController {

    @Autowired
    private AdminContext adminContext;

    /**
     * success数据
     *
     * @param message
     * @author yupanpan
     */
    protected String successObjectStr(String message) {
        JSONObject json = new JSONObject();
        json.put("ok", 200);
        json.put("message", message);
        return json.toJSONString();
    }

    /**
     * error数据
     *
     * @param message
     * @author yupanpan
     */
    protected String errorObjectStr(String message) {
        if (message == null) {
            new RuntimeException("登录信息异常");
        }
        Map<String, Object> map = new HashMap<>();
        map.put("error", 300);
        map.put("message", message);
        return JSONObject.toJSONString(map);
    }

    /**
     * 获取所有权限
     *
     * @author yupanpan
     */
    protected Set<String> getPermissions() {
        return adminContext.getPermissions();
    }

    /**
     * 获取当前登录用户
     *
     * @author yupanpan
     */
    protected AdminModel<UserDomain> getCurrentAdmin() {
        AdminModel<UserDomain> adminModel = adminContext.getCurrentUser().getAdminModel();
        if (adminModel == null) {
            new RuntimeException("登录信息异常！");
        }
        return adminModel;
    }
}
