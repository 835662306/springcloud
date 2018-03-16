package com.example.utils;

import org.apache.commons.lang.StringUtils;
import org.springframework.mobile.device.Device;
import org.springframework.mobile.device.DeviceUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * 判断请求的是移动端还是微信
 * @Company
 * @Discription
 * @Author guoxiaojing
 * @CreateDate 2018/3/16 16:24
 * @Version 1.0
 */
public class MobileRequestUtils {

    /**
     * 检测是否是移动设备访问
     *
     * @Title: checkIsMobile
     * @Date : 2014-7-7 下午01:29:07

     * @return true:移动设备接入，false:pc端接入
     */
    public static boolean isMobileRequest(HttpServletRequest request){
        Device device = DeviceUtils.getCurrentDevice(request);
        return device.isMobile();
    }

    /**
     * 判断是否微信请求
     * @param request
     * @return
     */
    public static boolean isWachatRequest(HttpServletRequest request){
        String  userAgent=request.getHeader("User-Agent");
        return StringUtils.isNotBlank(userAgent)&&userAgent.indexOf("MicroMessenger")>-1;
    }
}
