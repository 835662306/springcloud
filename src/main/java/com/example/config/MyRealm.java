package com.example.config;

import com.example.dao.UserRepository;
import com.example.entry.User;
import org.apache.log4j.Logger;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Company
 * @Discription
 * @Author guoxiaojing
 * @CreateDate 2018/3/2 10:27
 * @Version 1.0
 */
public class MyRealm extends AuthorizingRealm {

    private final static Logger logger = Logger.getLogger(MyRealm.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        logger.debug("登录验证后进行权限认证....");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        logger.debug("登录操作进行登录认证......");
        UsernamePasswordToken token = (UsernamePasswordToken)authenticationToken;

        User user = userRepository.getUserByName(token.getUsername());
        if (user == null) {
            // 没找到帐号
            throw new UnknownAccountException(
                    "没有在本系统中找到对应的用户信息。");
        }
        //简单验证
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(
                user.getNickName(),user.getPswd(),getName());

        return info;
    }
}
