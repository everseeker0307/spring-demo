package com.everseeker.service.impl;

import com.everseeker.entity.ConfirmEmail;
import com.everseeker.entity.User;
import com.everseeker.service.UserAlertService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * Created by everseeker on 16/8/9.
 */
@Service("userAlertService")
public class UserAlertServiceImpl implements UserAlertService {
    private RabbitTemplate rabbit;
    private JavaMailSender mailSender;

    @Autowired
    public UserAlertServiceImpl(RabbitTemplate rabbit, JavaMailSender mailSender) {
        this.rabbit = rabbit;
        this.mailSender = mailSender;
    }

    public void sendEmailToUserQueue(ConfirmEmail confirmEmail) {
        //convertAndSend(String exchange, String routingKey, Object object), 将对象object封装成Message对象后, 发送给exchange
        rabbit.convertAndSend("user.alert.email.exchange", "user.alerts.email", confirmEmail);
    }

    public void sendEmailToUser(String to, ConfirmEmail confirmEmail) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom("wuhao_0307@163.com");
        helper.setTo(to);
        helper.setSubject("请激活帐号,完成注册.");
        String text = "<html><body><h4>亲爱的" + confirmEmail.getUser().getUsername() + ": </h4><br /><p>请点击下面的链接激活帐号: </p><br />"
                + "<a href=\"http://localhost:8989/register?confirm=" + confirmEmail.getConfirmAddress() + "\">"
                + "http://localhost:8989/register?confirm=" + confirmEmail.getConfirmAddress() + "</a><br /></body></html>";
        helper.setText(text, true);
        mailSender.send(message);
    }
}
