package com.microtao.crowd.mvc.config;

import com.google.gson.Gson;
import com.microtao.crowd.exception.LoginAcctAlreadyInUseException;
import com.microtao.crowd.exception.LoginAcctForUpdateAlreadyInUseException;
import com.microtao.crowd.exception.LoginFailedException;
import com.microtao.crowd.util.CrowdUtil;
import com.microtao.crowd.util.ResultEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 该类异常注解类
 */

// @ControllerAdvice表示当前类是一个基于注解的异常处理器类
@ControllerAdvice
public class CrowdExceptionResolver {


    /**
     * 处理更新信息导致的唯一键约束异常
     */
    @ExceptionHandler(value = LoginAcctForUpdateAlreadyInUseException.class)
    public ModelAndView resolveLoginAcctForUpdateAlreadyInUseException(
            LoginAcctForUpdateAlreadyInUseException exception,
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        String viewName = "target/failed";

        return commonResolve(viewName, exception, request, response);
    }
    /**
     * 空指针异常处理
     */
    @ExceptionHandler(value = LoginFailedException.class)
    public ModelAndView LoginFailedException(
            LoginFailedException exception,
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        //登录失败依然跳转到登录界面
        String viewName = "admin-login";

        return commonResolve(viewName, exception, request, response);
    }
    /**
     * 空指针异常处理
     */
    @ExceptionHandler(value = LoginAcctAlreadyInUseException.class)
    public ModelAndView resolveLoginAcctAlreadyInUseException(
            LoginAcctAlreadyInUseException exception,
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        //添加失败依然去添加页面
        String viewName = "admin-add";

        return commonResolve(viewName, exception, request, response);
    }


    /**
     * 空指针异常处理
     */
    @ExceptionHandler(value = NullPointerException.class)
    public ModelAndView resolveNullPointerException(
            ArithmeticException exception,
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {

        String viewName = "target/failed";

        return commonResolve(viewName, exception, request, response);
    }

    /**
     * @param viewName  异常跳转页面
     * @param exception 异常类型
     * @param request   请求体
     * @param response  响应体
     */
    private ModelAndView commonResolve(String viewName, RuntimeException exception,
                                       HttpServletRequest request, HttpServletResponse response) throws IOException {
        //1、判断是哪一类请求
        boolean type = CrowdUtil.judgeRequestType(request);
        //如果是Ajax请求
        if (type) {
            ResultEntity<Object> failed = ResultEntity.failed(exception.getMessage());
            Gson gson = new Gson();
            String json = gson.toJson(failed);
            response.getWriter().write(json);
            return null;
        }
        // 8.如果不是Ajax请求则创建ModelAndView对象
        ModelAndView modelAndView = new ModelAndView();
        // 9.将Exception对象存入模型
        modelAndView.addObject("exception", exception);
        // 10.设置对应的视图名称
        modelAndView.setViewName(viewName);
        return modelAndView;
    }
}
