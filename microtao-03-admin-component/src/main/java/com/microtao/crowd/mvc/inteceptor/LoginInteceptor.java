package com.microtao.crowd.mvc.inteceptor;

import com.microtao.crowd.constant.CrowdConstant;
import com.microtao.crowd.exception.AccessForbiddenException;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 登录拦截器：判断是否有登录之后操作其他功能的权限
 *
 *  备注：
 *      实现拦截器的方式：implements HandlerInterceptor
 *      但是实现这个接口需要实现3个方法
 *       @Override
 *     public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
 *         return false;
 *     }
 *     @Override
 *     public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
 *
 *     }
 *     @Override
 *     public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
 *
 *     }
 *     而我们只需前置handler，其他的不需要，所以直接继承HandlerInterceptorAdapter 并重写preHandler方法
 * */
public class LoginInteceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 1、获取Session
        HttpSession session = request.getSession();
        // 2、尝试从session对象中获取用户的信息
        String admin = (String) session.getAttribute(CrowdConstant.LOGIN_ADMIN);
        if(null == admin){
            // 用户没有登录，则抛出自定义异常
            throw new AccessForbiddenException(CrowdConstant.MESSAGE_ACCESS_FORBIDEN);
        }
        // 3、如果session中存在该用户说明已经登录，则放行
        return true;
    }
}
