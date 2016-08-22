package com.everseeker.service;

import com.everseeker.entity.ConfirmEmail;

import javax.mail.MessagingException;

/**
 * Created by everseeker on 16/8/9.
 */
public interface UserAlertService {
    //将发送邮件任务存入消息队列
    void sendEmailToUserQueue(ConfirmEmail confirmEmail);

    //发送邮件
    void sendEmailToUser(String to, ConfirmEmail confirmEmail) throws MessagingException;
}
