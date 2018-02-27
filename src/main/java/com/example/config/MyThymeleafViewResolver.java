package com.example.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.View;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;

import java.util.Locale;

/**
 * http://blog.csdn.net/qq_15042899/article/details/72885889
 * @Company
 * @Discription
 * @Author guoxiaojing
 * @CreateDate 2018/2/26 17:11
 * @Version 1.0
 */
public class MyThymeleafViewResolver extends ThymeleafViewResolver{

    @Value("${spring.thymeleaf.prefix}")
    private String prefix;
    @Value("${spring.thymeleaf.suffix}")
    private String suffix;

    /**
     * 如果是他存在，则加载视图，如果不存在，寻找其他视图解析器
     * @param viewName
     * @param locale
     * @return
     * @throws Exception
     */
    @Override
    protected View loadView(String viewName, Locale locale) throws Exception {
        String resourceName = prefix + viewName + suffix;
        try {
            this.getApplicationContext().getResource(resourceName).getInputStream();
        } catch (Exception e) {
            if(logger.isDebugEnabled()){
                if(logger.isTraceEnabled()){
                    logger.trace("视图名："+resourceName+"{}不存在");
                }else{
                    logger.debug("视图名："+resourceName+"{}不存在");
                }
            }
            return null;
        }
        return super.loadView(viewName, locale);
    }
}
