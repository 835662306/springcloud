package com.example.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Security配置登录
 * @Company
 * @Discription
 * @Author guoxiaojing
 * @CreateDate 2018/3/7 14:17
 * @Version 1.0
 */
@Configuration  // 配置文件
@EnableWebSecurity // 开启Security
@EnableGlobalMethodSecurity(prePostEnabled = true) //AOP
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //路由策略和访问权限的简单配置
        http
                .formLogin()                        //启用默认登陆页面
                .failureUrl("/login?error")         //登陆失败返回URL:/login?error
                .defaultSuccessUrl("/getPid")         //登陆成功跳转URL
                .permitAll();                       //登陆页面全部权限可访问

        super.configure(http);
    }

    /**
     * 配置内存用户
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("test").password("test").roles("USER");
    }
}
