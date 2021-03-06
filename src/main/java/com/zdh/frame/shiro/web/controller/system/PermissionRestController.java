package com.zdh.frame.shiro.web.controller.system;

import com.alibaba.fastjson.JSONObject;
import com.zdh.frame.shiro.common.enums.BeUsedEnum;
import com.zdh.frame.shiro.common.enums.PermissionAvailableTypeEnum;
import com.zdh.frame.shiro.common.enums.PermissionTypeEnum;
import com.zdh.frame.shiro.common.model.PermissionModel;
import com.zdh.frame.shiro.common.model.UserModel;
import com.zdh.frame.shiro.query.PermissionQuery;
import com.zdh.frame.shiro.query.UserQuery;
import com.zdh.frame.shiro.service.domain.admin.PermissionAssignDomain;
import com.zdh.frame.shiro.service.domain.admin.PermissionDomain;
import com.zdh.frame.shiro.service.domain.admin.UserDomain;
import com.zdh.frame.shiro.service.service.IPermissionAssignService;
import com.zdh.frame.shiro.service.service.IPermissionService;
import com.zdh.frame.shiro.service.service.IRoleAssignService;
import com.zdh.frame.shiro.service.service.IRoleService;
import com.zdh.frame.shiro.service.service.IUserService;
import com.zdh.frame.shiro.web.controller.base.BaseController;
import com.zdh.frame.shiro.web.utils.DateTimeUtils;
import com.zdh.frame.shiro.web.utils.ShiroPermissions;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 权限资源管理Controller
 * </p>
 *
 * @Author Yupanpan
 * @Since JDK 1.8
 * @Version 1.0.0
 * @Date 2019.08.29 17:11
 */
@Controller
@Scope("prototype")
@ShiroPermissions(name = "系统设置", type = PermissionTypeEnum.CLICK, moduleLabel = "system")
@RequiresPermissions("system:permissions:manager")
@RequestMapping(value = "/permission")
public class PermissionRestController extends BaseController {

    private static final Logger LOG = LoggerFactory.getLogger(PermissionRestController.class);

    @Autowired
    private IUserService userService;

    @Autowired
    private IPermissionService permissionService;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IRoleAssignService roleAssignService;

    @Autowired
    private IPermissionAssignService permissionAssignService;


    /**
     * 权限列表【展示页面】
     *
     * @return
     */
    @ShiroPermissions(name = "权限列表", type = PermissionTypeEnum.MENU, moduleLabel = "system", parentPermissions = "system:permissions:system")
    @RequiresPermissions("system:permissions:index")
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView permissionList() {
        ModelAndView view = new ModelAndView();
        view.setViewName("system/list");
        return view;
    }

    /**
     * 权限列表【获取数据】
     *
     * @param permissionQuery
     * @return
     */
    @ShiroPermissions(name = "权限列表", type = PermissionTypeEnum.MENU, moduleLabel = "system", parentPermissions = "system:permissions:system")
    @RequiresPermissions(value = "system:permissions:index")
    @RequestMapping(value = "/index", method = RequestMethod.POST)
    @ResponseBody
    public String permissionList(PermissionQuery permissionQuery) {
        if (StringUtils.isEmpty(permissionQuery.getName())) {
            permissionQuery.setName(null);
        }
        List<PermissionDomain> list = permissionService.getList(permissionQuery);
        List<PermissionModel> collect = list.stream().map(en -> {
            PermissionModel model = new PermissionModel();
            BeanUtils.copyProperties(en, model);
            model.setUpdateTime(DateTimeUtils.dateToYyyyMMDD(en.getUpdateTime()));
            return model;
        }).collect(Collectors.toList());
        return JSONObject.toJSONString(collect);
    }

    /**
     * 新增权限
     */
    @ShiroPermissions(name = "新增权限", type = PermissionTypeEnum.BUTTON, moduleLabel = "system", parentPermissions = "system:permissions:index")
    @RequiresPermissions("system:permissions:add")
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView addPermission() {
        ModelAndView view = new ModelAndView();
        view.setViewName("system/add");
        return view;
    }

    @ShiroPermissions(name = "新增权限", type = PermissionTypeEnum.BUTTON, moduleLabel = "system", parentPermissions = "system:permissions:index")
    @RequiresPermissions("system:permissions:add")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public String addPermission(PermissionDomain domain) {
        try {
            PermissionQuery query = new PermissionQuery();
            query.setName(domain.getName());
            query.setType(domain.getType());
            query.setUrl(domain.getUrl());
            PermissionDomain permissionDomain = permissionService.getFirst(query);
            if (permissionDomain != null) {
                return errorObjectStr("已存在该资源路径！");
            }
            domain.setUpdateTime(new Date());
            domain.setCreateTime(new Date());
            permissionService.create(domain);
            // 系统设置默认给管理员添加权限
            if (domain.getModuleId() == 1) {
                PermissionAssignDomain permissionAssignDomain = new PermissionAssignDomain();
                permissionAssignDomain.setCreateTime(new Date());
                permissionAssignDomain.setPermissionId(domain.getId());
                permissionAssignDomain.setRoleId(1L);//超级管理员
                permissionAssignService.create(permissionAssignDomain);
            }
            return successObjectStr("添加成功!");
        } catch (Exception e) {
            LOG.error("添加权限异常：---> {}", e.getMessage());
            return errorObjectStr("添加失败!");
        }
    }

    /**
     * 获取当前所选权限类型的所有父权限类型 button - all menu
     *
     * @param label
     * @return
     */
    @RequestMapping(value = "/getParentType", method = RequestMethod.POST)
    @ResponseBody
    public String getParentType(String label) {
        PermissionQuery query = new PermissionQuery();
        query.setAvailable(PermissionAvailableTypeEnum.ON.getCode());
        if (PermissionTypeEnum.BUTTON.getType().equals(label)) {
            query.setType(PermissionTypeEnum.MENU.getType());
        } else if (PermissionTypeEnum.MENU.getType().equals(label)) {
            query.setType(PermissionTypeEnum.CLICK.getType());
        }
        List<PermissionDomain> list = permissionService.getList(query);
        return JSONObject.toJSONString(list);
    }

    /**
     * 编辑权限
     *
     * @param query
     * @return
     */
    @ShiroPermissions(name = "编辑权限", type = PermissionTypeEnum.BUTTON, moduleLabel = "system", parentPermissions = "system:permissions:index")
    @RequiresPermissions("system:permissions:update")
    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public ModelAndView updatePermission(PermissionQuery query) {
        ModelAndView view = new ModelAndView();
        Long id = query.getId();
        PermissionDomain permissionDomain = permissionService.get(id);
        view.setViewName("system/update");
        view.addObject("domain", permissionDomain);
        return view;
    }

    @ShiroPermissions(name = "编辑权限", type = PermissionTypeEnum.BUTTON, moduleLabel = "system", parentPermissions = "system:permissions:index")
    @RequiresPermissions("system:permissions:update")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public String updatePermission(PermissionDomain permissionDomain) {
        LOG.info("所编辑权限的Id ：--> {}", permissionDomain.getId());
        PermissionDomain domain = permissionService.get(permissionDomain.getId());
        if (domain == null) {
            return errorObjectStr("该数据不存在！");
        }
        try {
            permissionService.update(permissionDomain);
        } catch (Exception e) {
            return errorObjectStr("更新失败：" + e.getMessage());
        }
        return successObjectStr("更新成功！");
    }

    /**
     * 分配权限管理
     *
     * @return
     */
    @ShiroPermissions(name = "分配权限管理", type = PermissionTypeEnum.MENU, moduleLabel = "system", parentPermissions = "system:permissions:system")
    @RequestMapping(value = "/distribution", method = RequestMethod.GET)
    @RequiresPermissions(value = "system:permissions:distribution")
    public ModelAndView permissions() {
        ModelAndView model = new ModelAndView();
        model.setViewName("system/distribution");
        return model;
    }

    @ShiroPermissions(name = "分配权限管理", type = PermissionTypeEnum.MENU, moduleLabel = "system", parentPermissions = "system:permissions:system")
    @RequestMapping(value = "/distribution", method = RequestMethod.POST)
    @RequiresPermissions(value = "system:permissions:distribution")
    @ResponseBody
    public String permissions(UserQuery query) {
        List<UserDomain> domains = userService.getList(query);
        List<UserModel> userList = domains.stream().map(x -> {
            UserModel user = new UserModel();
            user.setId(x.getId());
            user.setRealName(x.getRealName());
            user.setUserName(x.getUserName());
            user.setPhone(x.getPhone());
            user.setLockedName(BeUsedEnum.valueOfCode(x.getLocked()));
            user.setCreateTime(DateTimeUtils.dateToYyyyMMDD(x.getCreateTime()));
            user.setLastLoginTime(DateTimeUtils.dateToYyyyMMDD(x.getLastLoginTime()));
            return user;
        }).collect(Collectors.toList());
        return JSONObject.toJSONString(userList);
    }
}
