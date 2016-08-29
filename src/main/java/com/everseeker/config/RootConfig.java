package com.everseeker.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

/**
 * Created by everseeker on 16/8/7.
 */
@Configuration
@EnableCaching
@ComponentScan(basePackages = {"com.everseeker"})
@ImportResource({"classpath:spring-mybatis.xml", "classpath:spring-rabbitmq.xml"})
@PropertySource("classpath:mail.properties")
public class RootConfig {
    /**
     * JavaMailSender
     * @param env
     * @return
     */
    @Bean(name = "mailSender")
    public JavaMailSender mailSender(Environment env) {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        //如果为普通邮箱, 非ssl认证等, 比如163邮箱
        mailSender.setHost(env.getProperty("mailserver.host"));
        mailSender.setPort(Integer.parseInt(env.getProperty("mailserver.port")));
        mailSender.setUsername(env.getProperty("mailserver.username"));
        mailSender.setPassword(env.getProperty("mailserver.password"));
        mailSender.setDefaultEncoding("utf-8");

        //如果邮件服务器采用了ssl认证, 增加以下配置, 比如gmail邮箱, qq邮箱
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.port", "465");
        mailSender.setJavaMailProperties(props);

        return mailSender;
    }

    /**
     * RedisConnectionFactory
     * @return
     */
    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        JedisConnectionFactory cf = new JedisConnectionFactory();
        cf.setHostName("127.0.0.1");
        cf.setPort(6379);
        cf.afterPropertiesSet();
        return cf;
    }

    /**
     * RedisTemplate
     * @param cf
     * @return
     */
    @Bean(name = "redisTemplate")
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory cf) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();
        redisTemplate.setConnectionFactory(cf);
        /**
         * 1. 默认采用JdkSerializationRedisSerializer()序列化. 优点是可以直接设置对象, 比如redisTemplate.opsForValue().set(sid, user),
         *      之后redisTemplate.opsForValue().get(sid)获得的就是user对象. 缺点是在redis客户端查看不直观.
         * 2. 也可以设置为其他方式序列化. 比如以下代码设置value为通过jackson2json序列化, 在redis客户端可以直观查看, 同时, 通过get(sid)获得的
         *      对象为json对象, 对应Java中的数据结构为java.util.LinkedHashMap.
         */
//        redisTemplate.setKeySerializer(new StringRedisSerializer());
//        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<Object>(Object.class));
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    /**
     * StringRedisTemplate
     * @param cf
     * @return
     */
//    @Bean(name = "stringRedisTemplate")
//    public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory cf) {
//        return new StringRedisTemplate(cf);
//    }

    /**
     * CacheManager
     * @param redisTemplate
     * @return
     */
    @Bean
    public CacheManager cacheManager(RedisTemplate redisTemplate) {
        return new RedisCacheManager(redisTemplate);
    }
}
