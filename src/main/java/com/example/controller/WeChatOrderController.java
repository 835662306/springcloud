package com.example.controller;

import com.example.wechat.*;
import com.google.common.collect.Maps;
import com.netflix.discovery.converters.Auto;
import org.hibernate.annotations.Source;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.SortedMap;

/**
 * @Company
 * @Discription
 * @Author guoxiaojing
 * @CreateDate 2018/4/17 15:19
 * @Version 1.0
 */
@RequestMapping(value="/weChat")
@Controller("weChatOrderController")
public class WeChatOrderController {
    private static final Logger logger = LoggerFactory.getLogger(WeChatOrderController.class);

    @Autowired
    private OrderService orderService;
    @Autowired
    private WeChatPayService wechatPayService;

    @RequestMapping(value = "unifiedOrder")
    public String unifiedOrder(HttpServletRequest request, Model model){
        //用户同意授权，获得的code
        String code = request.getParameter("code");
        //请求授权携带的参数【根据自己需要设定值，此处我传的是订单id】
        String state = request.getParameter("state");
        Order order = orderService.get(state);//订单信息
        //通过code获取网页授权access_token
        AuthToken authToken = WeChatUtils.getTokenByAuthCode(code);
        //构建微信统一下单需要的参数
        Map<String,Object> map = Maps.newHashMap();
        map.put("openId",authToken.getOpenid());//用户标识openId
        map.put("remoteIp",request.getRemoteAddr());//请求Ip地址
        //调用统一下单service
        Map<String,Object> resultMap = WeChatPayService.unifiedOrder(order,map);
        String returnCode = (String) resultMap.get("return_code");//通信标识
        String resultCode = (String) resultMap.get("result_code");//交易标识
        //只有当returnCode与resultCode均返回“success”，才代表微信支付统一下单成功
        if (WeChatConstant.RETURN_SUCCESS.equals(resultCode)&&WeChatConstant.RETURN_SUCCESS.equals(returnCode)){
            String appId = (String) resultMap.get("appid");//微信公众号AppId
            String timeStamp = WeChatUtils.getTimeStamp();//当前时间戳
            String prepayId = "prepay_id="+resultMap.get("prepay_id");//统一下单返回的预支付id
            String nonceStr = WeChatUtils.getRandomStr(20);//不长于32位的随机字符串
            SortedMap<String,Object> signMap = Maps.newTreeMap();//自然升序map
            signMap.put("appId",appId);
            signMap.put("package",prepayId);
            signMap.put("timeStamp",timeStamp);
            signMap.put("nonceStr",nonceStr);
            signMap.put("signType","MD5");
            model.addAttribute("appId",appId);
            model.addAttribute("timeStamp",timeStamp);
            model.addAttribute("nonceStr",nonceStr);
            model.addAttribute("prepayId",prepayId);
            model.addAttribute("paySign",WeChatUtils.getSign(signMap));//获取签名
        }else {
            logger.error("微信统一下单失败,订单编号:"+order.getOrderNumber()+",失败原因:"+resultMap.get("err_code_des"));
            return "redirect:/m/orderList";//支付下单失败，重定向至订单列表
        }
        //将支付需要参数返回至页面，采用h5方式调用支付接口
        return "/h5Pay";
    }

}
