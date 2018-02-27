package com.example.config;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;
import java.util.Map;

/**
 * @Company
 * @Discription
 * @Author guoxiaojing
 * @CreateDate 2018/2/26 17:03
 * @Version 1.0
 */
//@Configuration
//@EnableWebMvc
public class MvcConfig extends WebMvcConfigurerAdapter{

    @Value("${spring.thymeleaf.template-resolver-order}")
    private int thymeleafTemplateResolverOrder;
    @Value("${spring.freemarker.template-resolver-order}")
    private int freemarkerTemplateResolverOrder;

    @Autowired
    private FreeMarkerViewResolver freeMarkerViewResolver;

    @Autowired
    private ThymeleafViewResolver thymeleafViewResolver;

    @Bean
    public ThymeleafViewResolver thymeleafViewResolver(){
        MyThymeleafViewResolver myThymeleafViewResolver = new MyThymeleafViewResolver();
        BeanUtils.copyProperties(thymeleafViewResolver, myThymeleafViewResolver);
        myThymeleafViewResolver.setOrder(thymeleafTemplateResolverOrder);
        return myThymeleafViewResolver;
    }

    public View resolverViewName(String viewName, Map<String, Object> model, Locale locale, HttpServletRequest request) throws Exception{
//        for(ViewResolver viewResolver: thymeleafViewResolver){
//            View view = viewResolver.resolveViewName(viewName, locale);
//            if(view != null){
//                return view;
//            }
//        }
        return null;
    }
}
