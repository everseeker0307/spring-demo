package com.everseeker.service.handler;

import com.everseeker.entity.ConfirmEmail;
import com.everseeker.service.UserAlertService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.mail.MessagingException;

/**
 * Created by everseeker on 16/8/10.
 */
public class UserAlertHandler {
    private UserAlertService userAlertService;

    @Autowired
    public UserAlertHandler(UserAlertService userAlertService) {
        this.userAlertService = userAlertService;
    }

    public void sendEmailToUserHandler(ConfirmEmail confirmEmail) {
        System.out.println(confirmEmail);
        try {
            userAlertService.sendEmailToUser(confirmEmail.getUser().getEmail(), confirmEmail);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
