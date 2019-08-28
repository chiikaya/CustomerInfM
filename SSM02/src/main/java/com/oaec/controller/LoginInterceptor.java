package com.oaec.controller;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 登录验证拦截器
public class LoginInterceptor implements HandlerInterceptor{
    @Override
    public boolean preHandle(HttpServletRequest req,
                             HttpServletResponse resp,
                             Object o) throws Exception {
        Object username = req.getSession().getAttribute("username");

        /* 如果你复制主界面的地址，它会跳到登录界面 */
        if(username==null){
            resp.sendRedirect("/SSM02/login.jsp");
            return  false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
