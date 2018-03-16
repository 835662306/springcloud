package com.example.utils;

import com.alibaba.fastjson.JSONObject;
import com.example.utils.http.HttpUtil;
import com.example.utils.http.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @Company
 * @Discription
 * @Author guoxiaojing
 * @CreateDate 2018/3/16 15:29
 * @Version 1.0
 */
public class BaiduGadget {
    private static final Logger log = LoggerFactory.getLogger(BaiduGadget.class);

    private static final String _APIKEY = "38dc2ae61e043b1400afcdf8f26f64ae";

    public static void main(String [] args){
        System.out.println(weather("西安"));
    }


    /**
     * 微信热门
     * @param num
     * @return<code>{"0":{"description":"经典短篇阅读","picUrl":"http://zxpic.gtimg.com/infonew/0/wechat_pics_-670234.jpg/640","title":"费雯丽：我为你褪去一身骄傲，你却转身朝她微笑","url":"http://mp.weixin.qq.com/s?__biz=MzA5NzIyNjgwMA==&amp;idx=1&amp;mid=211868374&amp;sn=f78d0173caa5692577403554ac3d5265&amp;qb_mtt_show_type=1"},"code":200,"msg":"ok"}</code>
     */
    public static JSONObject wxHot(int num){
        String api="http://apis.baidu.com/txapi/weixin/wxhot";
        Map<String, String> params= new HashMap<String, String>();
        params.put("num",String.valueOf(num));
        params.put("rand",String.valueOf(1));
        return requstBaiDuApi(api,params);
    }

    /**
     * 猜一猜
     * @return<code>{"Answer":"不打不走；上了圈套","Title":"懒牛拉磨","id":"20403"}</code>
     */
    public static JSONObject c1c(){
        String api="http://apis.baidu.com/myml/c1c/c1c";
        return requstBaiDuApi(api,null);
    }

    /**
     * 美女图片
     * @param num
     * @return<code>{"0":{"description":"<b>中俄混血朱圣t自拍合集</b>...","picUrl":"http://img9.tu11.com:8080/uploads/allimg/150310/1_0310231P2E58.jpg","title":"<b>中俄混血朱圣t自拍合集</b>","url":"http://www.yixiuba.com/shenghuomeinvzipai/2015/6293.html"},"code":200,"msg":"ok"}</code>
     */
    public static JSONObject meinv(int num){
        String api="http://apis.baidu.com/txapi/mvtp/meinv";
        Map<String, String> params= new HashMap<String, String>();
        params.put("num",String.valueOf(num));
        return requstBaiDuApi(api,params);
    }

    /**
     * 奇闻趣事
     * @param num
     * @return<code>{"0":{"description":"母猪体内惊现婴儿吓坏当地村民...","picUrl":"http://img521.lieqi.com/upload/picture/37/10815.jpg","title":"母猪体内惊现婴儿吓坏当地村民","url":"http://www.lieqi.com/read/4/10815/"},"code":200,"msg":"ok"}</code>
     */
    public static JSONObject qiwen(int num){
        String api="http://apis.baidu.com/txapi/qiwen/qiwen";
        Map<String, String> params= new HashMap<String, String>();
        params.put("num",String.valueOf(num));
        return requstBaiDuApi(api,params);
    }
    /**
     * 图灵机器人
     * @param info
     * @return<code>{"code":100000,"text":"选择你所喜欢的，喜欢你所选择的。"}</code>
     */
    public static JSONObject turing(String info){
        String api="http://apis.baidu.com/turing/turing/turing";
        Map<String, String> params= new HashMap<String, String>();
        params.put("key","879a6cb3afb84dbf4fc84a1df2ab7319");
        params.put("info",info);
        params.put("userid", "eb2edb736");
        return requstBaiDuApi(api,params);
    }
    /**
     * 百度天气
     * @param city
     * @return<code> {
    errNum: 0,
    errMsg: "success",
    retData: {
    city: "北京", //城市
    pinyin: "beijing", //城市拼音
    citycode: "101010100",  //城市编码
    date: "15-02-11", //日期
    time: "11:00", //发布时间
    postCode: "100000", //邮编
    longitude: 116.391, //经度
    latitude: 39.904, //维度
    altitude: "33", //海拔
    weather: "晴",  //天气情况
    temp: "10", //气温
    l_tmp: "-4", //最低气温
    h_tmp: "10", //最高气温
    WD: "无持续风向",	 //风向
    WS: "微风(<10m/h)", //风力
    sunrise: "07:12", //日出时间
    sunset: "17:44" //日落时间
    }
    }</code>
     * @throws java.io.IOException
     * @throws java.util.concurrent.ExecutionException
     * @throws InterruptedException
     *
     */
    public static JSONObject weather(String city){
        String api="http://apis.baidu.com/apistore/weatherservice/weather";
        Map<String, String> params= new HashMap<String, String>();
        params.put("citypinyin",city);
        return requstBaiDuApi(api,params);
    }

    /**
     * ip所属地
     * @param ip
     * @return<code>
     * {"errMsg":"success","errNum":0,"retData":{"carrier":"中国联通","city":"西安","country":"中国","district":"雁塔","ip":"113.200.115.90","province":"陕西"}}
     * </code>
     */
    public static JSONObject ipAddress(String ip){
        String api = "http://apis.baidu.com/apistore/iplookupservice/iplookup";
        Map<String, String> params= new HashMap<String, String>();
        params.put("ip",ip);
        return requstBaiDuApi(api,params);

    }

    private static JSONObject requstBaiDuApi(String url,Map<String, String> params){
        Map<String, String> headers= new HashMap<String, String>();
        headers.put("apikey",_APIKEY);
        if(null==params){
            params = new HashMap<String, String>();
        }
        params.put("apikey",_APIKEY);
        try {
            Result post = HttpUtil.get(url,headers,params);
            String text=post.getBody();
            log.info(JSONObject.parseObject(text).toJSONString());
            return JSONObject.parseObject(text);
        } catch (Exception e) {
            return null;
        }
    }
}
