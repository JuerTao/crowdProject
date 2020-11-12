package com.microtao.crowd.util;
/**
 * 响应实体工具类
 * */
public class ResultEntity<T> {
    private static final String SUCCESS = "success";
    private static final String FAILED = "failed";
    private String result;
    private String message;
    private T data;

    public ResultEntity(String result, String message, T data) {
        this.result = result;
        this.message = message;
        this.data = data;
    }

    /**
     * 处理成功，并返回相应的数据
     */
    public static <E> ResultEntity<E> successWithData(E data) {
        return new ResultEntity<E>(SUCCESS, null, data);
    }

    /**
     * 处理成功，但不反悔数据
     */
    public static <E> ResultEntity<E> successWithoutData(E data) {
        return new ResultEntity<E>(SUCCESS, null, data);
    }

    /**
     * 处理失败，返回提示信息
     */
    public static <E> ResultEntity<E> failed(String message) {
        return new ResultEntity<E>(FAILED, message, null);
    }

    public static String getSUCCESS() {
        return SUCCESS;
    }

    public static String getFAILED() {
        return FAILED;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
