package com.microtao.crowd.exception;

/**
 * @Author : Microtao
 * @Date: 2020/11/15
 * @Description: 自定义权限获取失败异常类
 */
public class AccessForbiddenException extends RuntimeException{
    public AccessForbiddenException() {
    }

    public AccessForbiddenException(String message) {
        super(message);
    }

    public AccessForbiddenException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccessForbiddenException(Throwable cause) {
        super(cause);
    }

    public AccessForbiddenException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
