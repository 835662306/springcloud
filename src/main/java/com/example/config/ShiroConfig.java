package com.example.config;

import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.session.SessionListener;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Company
 * @Discription
 * @Author guoxiaojing
 * @CreateDate 2018/3/2 10:16
 * @Version 1.0
 */
@Configuration
public class ShiroConfig {

    /**
     * ShiroFilterFactoryBean 处理拦截资源文件问题。
     * 注意：单独一个ShiroFilterFactoryBean配置是或报错的
     * 初始化ShiroFilterFactoryBean的时候需要注入：SecurityManager
     */
    @Bean
    public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        // 必须设置 SecurityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        // 拦截器.
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();

        // <!-- 过滤链定义，从上向下顺序执行，一般将 /**放在最为下边 -->:这是一个坑呢，一不小心代码就不好使了;
        // <!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
        filterChainDefinitionMap.put("/login", "anon");//anon 可以理解为不拦截
        filterChainDefinitionMap.put("/static/**", "anon");
        //filterChainDefinitionMap.put("/page/*", "authc");
        // 配置退出过滤器,其中的具体的退出代码Shiro已经替我们实现了
        //filterChainDefinitionMap.put("/security/logoff", "logout");

        shiroFilterFactoryBean.setLoginUrl("/#/login");
        // 未授权界面;
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        return shiroFilterFactoryBean;
    }

    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        MyRealm myRealm = new MyRealm();
        //告诉realm,使用credentialsMatcher加密算法类来验证密文
        //myRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        //启用缓存,默认false
        myRealm.setCachingEnabled(true);
        //  启用身份验证缓存，即缓存AuthenticationInfo信息，默认false；
        myRealm.setAuthenticationCachingEnabled(true);
        //  缓存AuthenticationInfo信息的缓存名称,即配置在ehcache.xml中的cache name
        myRealm.setAuthenticationCacheName("authenticationCache");
        //  启用授权缓存，即缓存AuthorizationInfo信息，默认false；
        myRealm.setAuthorizationCachingEnabled(true);
        //  缓存AuthorizationInfo信息的缓存名称；
        myRealm.setAuthorizationCacheName("authorizationCache");
        securityManager.setRealm(myRealm);
        securityManager.setSessionManager(sessionManager());
        securityManager.setCacheManager(ehCacheManager());
        return securityManager;
    }

    @Bean
    public SessionManager sessionManager() {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        Collection<SessionListener> listeners = new ArrayList<SessionListener>();
        listeners.add(new MySessionListener());
        sessionManager.setSessionListeners(listeners);
        return sessionManager;
    }

    /**
     * shiro缓存管理器;
     */
    @Bean
    public EhCacheManager ehCacheManager() {
        EhCacheManager ehCacheManager = new EhCacheManager();
        ehCacheManager.setCacheManagerConfigFile("classpath:config/encache.xml");
        return ehCacheManager;
    }

}
