package com.microtao.crowd.util;

import com.microtao.crowd.constant.CrowdConstant;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CrowdUtil {

    /**
     * 判断当前请求是否为Ajax请求
     *
     * @param request 请求对象
     * @return true：当前请求是Ajax请求
     * false：当前请求不是Ajax请求
     */
    public static boolean judgeRequestType(HttpServletRequest request) {

        // 1.获取请求消息头
        String acceptHeader = request.getHeader("Accept");
        String xRequestHeader = request.getHeader("X-Requested-With");

        // 2.判断
        return (acceptHeader != null && acceptHeader.contains("application/json"))
                ||
                (xRequestHeader != null && xRequestHeader.equals("XMLHttpRequest"));
    }

    /**
     * 使用md5进行加密操作
     */
    public static String md5(String useranme) {
        //判断账号是否为空
        if(null == useranme || useranme.length()==0){
            throw new RuntimeException(CrowdConstant.MESSAGE_STRING_INVALIDATE);
        }
        // 加密的算法
        String algorithm = "md5";
        try {
            MessageDigest instance = MessageDigest.getInstance(algorithm);
            byte[] md5String = instance.digest(useranme.getBytes());
            // BigInteger的signum=1表示为正数
            BigInteger bigInteger = new BigInteger(1,md5String);
            // 将加密的字符串转换成16进制的格式
            String encoded = bigInteger.toString(16);
            return encoded;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
