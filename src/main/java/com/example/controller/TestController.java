package com.example.controller;

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

    /**
     * 获取进程id
     * @return
     */
    @ResponseBody
    @RequestMapping("getPid")
    public String getPid(){
        try {
            String jvmName = ManagementFactory.getRuntimeMXBean().getName();
            return jvmName.split("@")[0];
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
