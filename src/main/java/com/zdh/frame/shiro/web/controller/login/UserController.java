package com.zdh.frame.shiro.web.controller.login;

import com.zdh.frame.shiro.common.security.context.AdminContext;
import com.zdh.frame.shiro.model.UserModel;
import com.zdh.frame.shiro.service.service.IUserService;
import com.zdh.frame.shiro.web.controller.base.BaseController;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * <p>
 * 用户登录Controller
 * </p>
 *
 * @Author Yupanpan
 * @Since JDK 1.8
 * @Version 1.0.0
 * @Date 2019.08.21 14:11
 */
@Controller
@Scope("prototype")
public class UserController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private AdminContext adminContext;

    @Autowired
    private IUserService userService;

    private static final String SUCCESS = "SUCCESS";

    /**
     * 跳转页面
     *
     * @param modelAndView
     * @return
     */
    @RequestMapping(value = "/signIn", method = RequestMethod.GET)
    ModelAndView login(ModelAndView modelAndView) {
        modelAndView.setViewName("signIn/login");
        return modelAndView;
    }

    /**
     * 提交表单数据登录
     *
     * @param session
     * @param userModel
     * @param model
     * @return
     */
    @RequestMapping(value = "/signIn", method = RequestMethod.POST)
    @ResponseBody
    public String login(HttpSession session, UserModel userModel, Model model) {
        String loginInfo = loginUser(userModel);
        if (!SUCCESS.equals(loginInfo)){
            //登录失败
            return errorObjectStr("用户名或密码错误！");
        }else {
            //登录成功
            session.setAttribute("status",200);
            return successObjectStr("登录成功！");
        }
    }

    private String loginUser(UserModel user) {
        if (isReLogin(user)){
            //如果已经登陆，无需重复登录
            return "SUCCESS";
        }
        //调用shiro登陆认证接口
        String loginResult = shiroLogin(user);
        return loginResult;
    }


    private boolean isReLogin(UserModel user) {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()){
            // 参数未改变，无需重新登录，默认为已经登录成功
            return true;
        }
        // 需要重新登陆
        return false;
    }

    private String shiroLogin(UserModel user) {
        //组装token 包括用户所属公司、简称、用户编码、用户名称、密码
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(), user.getPassword().toCharArray(),null);
        token.setRememberMe(true);
        //shiro登陆验证
        try {
            SecurityUtils.getSubject().login(token);
        }catch (UnknownAccountException ex){
            LOGGER.error("用户名或密码错误异常：--->{}",ex.getMessage());
            return "FAILED";
        }catch (IncorrectCredentialsException ex){
            LOGGER.error("用户名或密码错误异常：--->{}",ex.getMessage());
            return "FAILED";
        }catch (AuthenticationException ex) {
            LOGGER.error("登录异常：--->{}",ex.getMessage());
            return "FAILED";
        }catch (Exception ex){
            LOGGER.error("系统异常：--->{}",ex.getMessage());
            return "FAILED";
        }
        //登录成功
        return "SUCCESS";
    }

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
