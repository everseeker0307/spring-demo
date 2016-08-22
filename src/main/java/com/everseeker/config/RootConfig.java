package com.everseeker.config;

import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

/**
 * Created by everseeker on 16/8/7.
 */
@Configuration
@ComponentScan(basePackages = {"com.everseeker"})
@ImportResource({"classpath:spring-mybatis.xml", "classpath:spring-rabbitmq.xml"})
@PropertySource("classpath:mail.properties")
public class RootConfig {

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
}
