package com.example.controller;

import com.example.common.aspect.RequestLimit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.management.ManagementFactory;

/**
 * @Company
 * @Discription
 * @Author guoxiaojing
 * @CreateDate 2018/2/26 14:39
 * @Version 1.0
 */
@Controller
public class TestController {

    @Autowired
    private RedisTemplate redisTemplate;
    /**
     * 获取进程id
     * @return
     */
    @ResponseBody
    @RequestMapping("getPid")
    @RequestLimit(count = 10, time = 20000)
    public String getPid(){
        redisTemplate.opsForSet().add("list", 12346);
        try {
            String jvmName = ManagementFactory.getRuntimeMXBean().getName();
            return jvmName.split("@")[0];
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
