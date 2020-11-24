package com.microtao.crowd.exception;

/**
 * @Author : Microtao
 * @Date: 2020/11/19 22:46
 * @Description: 保存或者更新时导致唯一键冲突的异常
 */
public class LoginAcctForUpdateAlreadyInUseException extends RuntimeException{
    public LoginAcctForUpdateAlreadyInUseException() {
    }

    public LoginAcctForUpdateAlreadyInUseException(String message) {
        super(message);
    }

    public LoginAcctForUpdateAlreadyInUseException(String message, Throwable cause) {
        super(message, cause);
    }

    public LoginAcctForUpdateAlreadyInUseException(Throwable cause) {
        super(cause);
    }

    public LoginAcctForUpdateAlreadyInUseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
