package com.example.web.listener;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * 监听在线用户上线下线
 * @Company
 * @Discription
 * @Author guoxiaojing
 * @CreateDate 2018/3/21 16:30
 * @Version 1.0
 */
public class OnlineListener implements HttpSessionListener, ServletContextListener{

    private static ApplicationContext context = null;

    public OnlineListener(){

    }

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
    }

    /**
     * 服务器初始化
     */

    public void contextInitialized(ServletContextEvent evt) {
        context = WebApplicationContextUtils.getWebApplicationContext(evt.getServletContext());
    }

    public static ApplicationContext getCtx() {
        return context;
    }


    public void contextDestroyed(ServletContextEvent paramServletContextEvent) {

    }
}
