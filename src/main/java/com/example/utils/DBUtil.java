package com.example.utils;

import com.example.web.listener.OnlineListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;

/**
 * 获取数据库类型
 * @Company
 * @Discription
 * @Author guoxiaojing
 * @CreateDate 2018/3/21 16:26
 * @Version 1.0
 */
public class DBUtil {

    private static Logger logger = LoggerFactory.getLogger(DBUtil.class);

    private static String getDBType() {
        String str = "";
        ApplicationContext context = OnlineListener.getCtx();
        if(context == null){
            return str;
        }else {
            LocalSessionFactoryBean factoryBean = (LocalSessionFactoryBean) context.getBean("&sessionFactory");
            String property = factoryBean.getHibernateProperties().getProperty("hibernate.dialect");
            if (property.equals("org.hibernate.dialect.MySQLDialect")) {
                str="mysql";
            }else if (property.contains("Oracle")) {//oracle有多个版本的方言
                str = "oracle";
            }else if (property.equals("org.hibernate.dialect.SQLServerDialect")) {
                str = "sqlserver";
            }else if (property.equals("org.hibernate.dialect.PostgreSQLDialect")) {
                str = "postgres";
            }
        }
        return str;
    }
}
