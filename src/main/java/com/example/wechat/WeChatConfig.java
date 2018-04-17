package com.example.wechat;

/**
 * wei信支付所有参数定义
 * @Company
 * @Discription
 * @Author guoxiaojing
 * @CreateDate 2018/4/17 14:35
 * @Version 1.0
 */
public class WeChatConfig {
    /**公众号AppId*/
    public static final String APP_ID = "";

    /**公众号AppSecret*/
    public static final String APP_SECRET = "";

    /**微信支付商户号*/
    public static final String MCH_ID = "";

    /**微信支付API秘钥*/
    public static final String KEY = "";

    /**微信支付api证书路径*/
    public static final String CERT_PATH = "***/apiclient_cert.p12";

    /**微信统一下单url*/
    public static final String UNIFIED_ORDER_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";

    /**微信申请退款url*/
    public static final String REFUND_URL = "https://api.mch.weixin.qq.com/secapi/pay/refund";

    /**微信支付通知url*/
    public static final String NOTIFY_URL = "此处url用于接收微信服务器发送的支付通知，并处理商家的业务";

    /**微信交易类型:公众号支付*/
    public static final String TRADE_TYPE_JSAPI = "JSAPI";

    /**微信交易类型:原生扫码支付*/
    public static final String TRADE_TYPE_NATIVE = "NATIVE";

    /**微信甲乙类型:APP支付*/
    public static final String TRADE_TYPE_APP = "APP";
}
