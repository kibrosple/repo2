package com.itheima.utils;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.resource.DefaultServletHttpRequestHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 登陆拦截器
 */
public class LoginInterceptor implements HandlerInterceptor{

    /**
     * 执行controller方法之前执行
     * @param request
     * @param response
     * @param handler
     * @return
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
       // System.out.println(handler);
        if(handler instanceof DefaultServletHttpRequestHandler){
            return true;
        }
//        String requestURI = request.getRequestURI();
//        System.out.println(requestURI);
        //获取session对象
        HttpSession session = request.getSession();
        if(session!=null&&session.getAttribute("user")!=null){
               return true;
        }else{
            try {
//                request.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(request,response);
                response.sendRedirect(request.getContextPath()+"/index.jsp");
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
