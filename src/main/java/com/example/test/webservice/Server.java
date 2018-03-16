package com.example.test.webservice;

import javax.xml.ws.Endpoint;

/**
 * @Company
 * @Discription
 * @Author guoxiaojing
 * @CreateDate 2018/3/15 16:50
 * @Version 1.0
 */
public class Server {
    public static void main(String [] args){
        Endpoint.publish("http://127.0.0.1:12345/weather", new WebServiceImpl());
    }
}
