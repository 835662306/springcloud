package com.example.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import java.lang.reflect.Method;

/**
 * @Company
 * @Discription
 * @Author guoxiaojing
 * @CreateDate 2018/3/13 11:52
 * @Version 1.0
 */
@Configuration
@EnableCaching // 开启注解缓存
public class RedisConfig extends CachingConfigurerSupport{

    /**
     * 定义缓存bean的生成策略
     * 方法名+所以参数
     * @return
     */
    @Bean
    @Override
    public KeyGenerator keyGenerator() {
        return new KeyGenerator() {
            @Override
            public Object generate(Object target, Method method, Object... params) {
                StringBuilder sb = new StringBuilder();
                sb.append("springBoot:");
                sb.append("fun" + "[" + method.getName() + "]");
                for (Object obj : params) {
                    //拼接缓存的 key
                    sb.append("params[" + String.valueOf(obj) + "]");
                }
                return String.valueOf(sb);
            }
        };
    }

    /**
     * 自定义 RedisTemplate 提 注解使用
     * spring-data-redis 的序列化类有下面这几个
     * Jackson2JsonRedisSerializer: 跟JacksonJsonRedisSerializer实际上是一样的 (推荐 , 首选)
     * JacksonJsonRedisSerializer: 序列化object对象为json字符串  (结果清晰，容易阅读，而且存储字节少，速度快，推荐)
     * GenericToStringSerializer: 可以将任何对象泛化为字符串并序列化 (和 StringRedisSerializer 差不多, 会自动转为 String 类型)
     * StringRedisSerializer: 简单的字符串序列化  (一般如果key-value都是string的话，使用StringRedisSerializer就可以了 推荐 )
     * JdkSerializationRedisSerializer: 序列化java对象  被序列化的对象必须实现Serializable接口 (不宜阅读 不推荐)
     * @param factory
     * @return
     */
    @Bean
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory){
        StringRedisTemplate template = new StringRedisTemplate(factory);

        // 封装  Jackson2JsonRedisSerializer
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);

        //设置value 序列换
        template.setValueSerializer(jackson2JsonRedisSerializer);

        //设置key 序列换 (我们使用自定义的 key 生成 策略)
        template.setKeySerializer(jackson2JsonRedisSerializer);

        template.afterPropertiesSet();

        return template;
    }

    /**
     * 要启用spring缓存支持,需要一个 CacheManager的 bean
     * CacheManager 接口有很多实现，这里Redis 的集成，用 RedisCacheManager这个实现类
     * @param redisTemplate
     * @return
     */
    @Bean
    public CacheManager cacheManager(RedisTemplate redisTemplate) {
        RedisCacheManager manager = new RedisCacheManager(redisTemplate);
        //设置缓存过期时间
        manager.setDefaultExpiration(60);//秒
        return manager;
    }
}
