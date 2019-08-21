package com.zdh.frame.shiro.web.controller.login;

import com.zdh.frame.shiro.common.security.context.AdminContext;
import com.zdh.frame.shiro.service.service.IUserService;
import com.zdh.frame.shiro.web.controller.base.BaseController;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * <p>
 *     用户登录Controller
 * </p>
 *
 * @Author Yupanpan
 * @Since JDK 1.8
 * @Version 1.0.0
 * @Date 2019.08.21 14:11
 */
@Controller
@Scope("prototype")
@RequestMapping
public class UserController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private AdminContext adminContext;

    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/signIn", method = RequestMethod.GET)
    ModelAndView login(ModelAndView modelAndView) {
        modelAndView.setViewName("signIn/login");
        return modelAndView;
    }

    // @RequestMapping(value = "/signIn",method = RequestMethod.POST)
    // @ResponseBody
    // String login(HttpSession session, UserModel userModel, Model model){
    //     String info = loginUser(userModel);
    //     if (!"SUCC".equals(info)) {
    //         return errorObjectStr("用户不存在或密码错误！");
    //     }else{
    //         session.setAttribute("status",1);
    //         return successObjectStr("登陆成功！");//返回的页面
    //     }
    // }
    //
    // private String loginUser(UserModel user) {
    //     if (isRelogin(user)) return "SUCC"; // 如果已经登陆，无需重新登录
    //
    //     return shiroLogin(user); // 调用shiro的登陆验证
    // }
    // private String shiroLogin(UserModel user) {
    //     // 组装token，包括客户公司名称、简称、客户编号、用户名称；密码
    //     UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(), user.getPassword().toCharArray(), null);
    //     token.setRememberMe(true);
    //     // shiro登陆验证
    //     try {
    //         SecurityUtils.getSubject().login(token);
    //     } catch (UnknownAccountException ex) {
    //         return "用户不存在或者密码错误！";
    //     } catch (IncorrectCredentialsException ex) {
    //         return "用户不存在或者密码错误！";
    //     } catch (AuthenticationException ex) {
    //         return ex.getMessage(); // 自定义报错信息
    //     } catch (Exception ex) {
    //         ex.printStackTrace();
    //         return "内部错误，请重试！";
    //     }
    //     return "SUCC";
    // }
    //
    // private boolean isRelogin(UserModel user) {
    //     Subject us = SecurityUtils.getSubject();
    //     if (us.isAuthenticated()) {
    //         return true; // 参数未改变，无需重新登录，默认为已经登录成功
    //     }
    //     return false; // 需要重新登陆
    // }

    /**
     * 进入主页面
     *
     * @return
     */
    @RequestMapping(value = "/index")
    public ModelAndView toIndex() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("signIn/index");
        return modelAndView;
    }

    /**
     * 退出登录
     *
     * @author yupanpan
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    @ResponseBody
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.logout();
            adminContext = null;
        } catch (Exception e) {
            LOGGER.error(">>>>>>退出登录异常：--> {}", e.getMessage());
            return errorObjectStr("退出失败!");
        }
        return successObjectStr("成功退出！");
    }
}
