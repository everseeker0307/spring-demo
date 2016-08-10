package com.everseeker.service;

import com.everseeker.entity.User;

/**
 * Created by everseeker on 16/8/9.
 */
public interface UserAlertService {
    //将发送邮件任务存入消息队列
    void sendEmailToUserQueue(User user);

    //发送邮件
    void sendEmailToUser(String to, User user);
}
