package com.example.utils.http;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 百度翻译
 * @Company
 * @Discription
 * @Author guoxiaojing
 * @CreateDate 2018/3/16 14:28
 * @Version 1.0
 */
public class BaiduTranslate {
    private static Logger log = LoggerFactory.getLogger(BaiduTranslate.class);

    public static String translate(String src){
        String url = "http://api.fanyi.baidu.com/api/trans/vip/translate";

        Map<String,String> map = new HashMap<>();
        map.put("client_id","uv5gskTGW7Z1FHMx6cXHW4TV");
        map.put("from","auto");
        map.put("to","auto");
        map.put("q",src==null?"":src);
        Result result = null;
        String dst = null;

        try {
            result = HttpUtil.get(url, null, map);
            JSONObject json = (JSONObject) JSON.parse(result.getBody());
            if(json.get("error_code") != null) return "";
            JSONObject jsonObject = (JSONObject) ((JSONArray) json.get("trans_result")).get(0);
            dst = (String) jsonObject.get("dst");
        } catch (IOException e) {
            log.info(e.getMessage());
        }finally {
            if(result != null)  HttpUtil.closeClient(result.getHttpClient());
        }

        return dst;
    }

    public static void main(String [] args){
        System.out.println(translate("http://music.163.com/#/my/m/music/playlist?id=481314259"));
    }
}
