package com.example.config.dataSource;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

/**
 * 多数据源源配置  (Mysql)
 * @Company
 * @Discription
 * @Author guoxiaojing
 * @CreateDate 2018/3/12 13:59
 * @Version 1.0
 */
//@PropertySource(value = {"db.properties"}, ignoreResourceNotFound = false)  //加载自定义配置文件 可用 @valeu 注解 接收
//@Configuration
//@EnableJpaRepositories(basePackages = "com.example.baseproject.repostitory")  /*使用SpringData JPA*/
public class MysqlDBConfig {

    /**
     * 数据源配置对象
     */
    @Bean
    @Primary /*表示默认的对象，Autowire可注入，不是默认的得明确名称注入*/
    @ConfigurationProperties("mysql.datasource")  /*使用标准的yml 格式书写 配置文件 , DataSourceProperties 会自己找到对应关系 */
    private DataSourceProperties mysqlDataSourceProperties() {
        DataSourceProperties dataSourceProperties = new DataSourceProperties();
        return dataSourceProperties;
    }

    /**
     * 数据源对象
     */
    @Bean(name = "mysqlDruidDataSource", destroyMethod = "close", initMethod = "init")
    @Primary
    @ConfigurationProperties(prefix = "mysql.datasource")  //配置文件的信息，读取并自动封装成实体类
    public DruidDataSource firstDataSource() {
        DruidDataSource druidDataSource = (DruidDataSource) mysqlDataSourceProperties().initializeDataSourceBuilder().build();
        return druidDataSource;
    }

    /**
     * 多数据源配置
     * spring.datasource.primary.url=jdbc:mysql://localhost:3306/test1
     * spring.datasource.primary.username=root
     * .datasource.primary.password=root
     * spring.datasource.primary.driver-class-name=com.mysql.jdbc.Driver

     * spring.datasource.secondary.url=jdbc:mysql://localhost:3306/test2
     * spring.datasource.secondary.username=root
     * spring.datasource.secondary.password=root
     * spring.datasource.secondary.driver-class-name=com.mysql.jdbc.Driver
     */
//    @Bean(name = "primaryDataSource")
//    @Qualifier("primaryDataSource")
//    @ConfigurationProperties(prefix="spring.datasource.primary")
//    public DataSource primaryDataSource() {
//        return DataSourceBuilder.create().build();
//    }
//
//    @Bean(name = "secondaryDataSource")
//    @Qualifier("secondaryDataSource")
//    @Primary
//    @ConfigurationProperties(prefix="spring.datasource.secondary")
//    public DataSource secondaryDataSource() {
//        return DataSourceBuilder.create().build();
//    }

//    @Bean(name = "primaryJdbcTemplate")
//    public JdbcTemplate primaryJdbcTemplate(
//            @Qualifier("primaryDataSource") DataSource dataSource) {
//        return new JdbcTemplate(dataSource);
//    }
//
//    @Bean(name = "secondaryJdbcTemplate")
//    public JdbcTemplate secondaryJdbcTemplate(
//            @Qualifier("secondaryDataSource") DataSource dataSource) {
//        return new JdbcTemplate(dataSource);
//    }
}
