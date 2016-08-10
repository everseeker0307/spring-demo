package com.everseeker.service.impl;

import com.everseeker.entity.User;
import com.everseeker.service.UserAlertService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

/**
 * Created by everseeker on 16/8/9.
 */
@Service("userAlertService")
public class UserAlertServiceImpl implements UserAlertService {
    private RabbitTemplate rabbit;
    private MailSender mailSender;

    @Autowired
    public UserAlertServiceImpl(RabbitTemplate rabbit, MailSender mailSender) {
        this.rabbit = rabbit;
        this.mailSender = mailSender;
    }

    public void sendEmailToUserQueue(User user) {
        //convertAndSend(String exchange, String routingKey, Object object), 将对象object封装成Message对象后, 发送给exchange
        rabbit.convertAndSend("user.alert.email.exchange", "user.alerts.email", user);
    }

    public void sendEmailToUser(String to, User user) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("wuhao_0307@189.cn");
        message.setTo(to);
        message.setSubject(user.getUsername() + "激活邮件");
        message.setText(user.toString());
        mailSender.send(message);
    }
}
