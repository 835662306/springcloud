package com.example.test.webservice;

import javax.jws.WebService;

/**
 * @Company
 * @Discription
 * @Author guoxiaojing
 * @CreateDate 2018/3/15 16:48
 * @Version 1.0
 */
@WebService
public class WebServiceImpl {
    public String query(String name){
        System.out.println(name);
        String result = "天朗气清，惠风和畅!";
        return result;
    }
}
