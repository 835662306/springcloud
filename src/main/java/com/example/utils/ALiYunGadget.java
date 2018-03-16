package com.example.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * @Company
 * @Discription
 * @Author guoxiaojing
 * @CreateDate 2018/3/16 15:22
 * @Version 1.0
 */
public class ALiYunGadget {
    private static Logger logger = Logger.getLogger(ALiYunGadget.class);
    private static String host = "http://jisuip.market.alicloudapi.com";

    public static JSONObject ipAddress(String ip){
        String path = "/ip/location";
        String method = "GET";
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE 21baa1d563de4f97bf32649db34da822");
        Map<String, String> querys = new HashMap<String, String>();
        querys.put("ip", ip);
        HttpResponse response = null;
        JSONObject jsonObject=null;
        try {
            response = HttpUtils.doGet(host, path, method, headers, querys);
            String text= EntityUtils.toString(response.getEntity());
            logger.info("阿里云api  ip地址解析:"+text);
            if (StringUtils.isNotBlank(text)){
                jsonObject=JSONObject.parseObject(text);
            }
        } catch (Exception e) {
            logger.error("阿里云api  ip地址解析异常",e);
        }

        return jsonObject;
    }

    public static void main(String [] args){
        ipAddress("39.106.127.25");
    }
}
