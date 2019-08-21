package com.zdh.frame.shiro.web.filter;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>
 * filter 实现用户登录过滤器
 * </p>
 *
 * @Author Yupanpan
 * @Since JDK 1.8
 * @Version 1.0.0
 * @Date 2019.08.21 15:19
 */
public class AdminFilter implements Filter {

    private static final Logger LOG = LoggerFactory.getLogger(AdminFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        try {
            String servletPath = request.getServletPath();
            // 过滤静态文件
            if (!servletPath.startsWith("/static/")) {
                if (!servletPath.endsWith("signIn")) {
                    if (request.getSession().getAttribute("status") == null) {
                        response.sendRedirect("/signIn");
                        return;
                    }
                }
            }
        } catch (Exception e) {
            LOG.error("过滤器异常信息：--> {}", e.getMessage());
        }
        chain.doFilter(request, response);
        return;
    }

    @Override
    public void destroy() {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>> 过滤器已销毁 <<<<<<<<<<<<<<<<<<<<<<<<");
    }
}
