package com.example.utils.remote;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Company
 * @Discription
 * @Author guoxiaojing
 * @CreateDate 2018/3/16 15:02
 * @Version 1.0
 */
public class RemoteUtil {

    static final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssS");

    public static String getRandomOutputKey() {
        return sdf.format(new Date())+"_"+ (int)(Math.random()*1000);
    }

    public static void main(String [] args){
        System.out.println(getRandomOutputKey());
    }
}
