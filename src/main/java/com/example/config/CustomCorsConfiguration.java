package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 全部配置跨域问题
 * extends WebMvcConfigurerAdapter 重写addCorsMappings也可实现
 * @Company
 * @Discription
 * @Author guoxiaojing
 * @CreateDate 2018/4/18 10:40
 * @Version 1.0
 */
@Configuration
public class CustomCorsConfiguration {
    @Bean
    public WebMvcConfigurer corsConfigurer() {

        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                // 设置了可以被跨域访问的路径和可以被哪些主机跨域访问
                registry.addMapping("/**").allowedOrigins("http://localhost:8081", "http://localhost:8082");
            }
        };
    }
}
