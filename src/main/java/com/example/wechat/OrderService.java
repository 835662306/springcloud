package com.example.wechat;

import org.springframework.stereotype.Service;

/**
 * @Company
 * @Discription
 * @Author guoxiaojing
 * @CreateDate 2018/4/17 15:41
 * @Version 1.0
 */
@Service
public class OrderService {
    public Order get(String id){
        return new Order();
    }
}
