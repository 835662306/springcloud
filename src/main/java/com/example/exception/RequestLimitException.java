package com.example.exception;

/**
 * @Company
 * @Discription
 * @Author guoxiaojing
 * @CreateDate 2018/3/13 15:20
 * @Version 1.0
 */
public class RequestLimitException extends RuntimeException{
    public RequestLimitException() {
        super("HTTP请求超出设定的限制");
    }

    public RequestLimitException(String localAddr){
        super(localAddr);
    }
}
